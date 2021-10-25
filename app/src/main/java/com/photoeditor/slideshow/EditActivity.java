package com.photoeditor.slideshow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaygoo.widget.RangeSeekBar;
import com.orhanobut.hawk.Hawk;
import com.photoeditor.slideshow.components.MyVideoPlayerGif;
import com.photoeditor.slideshow.imagetovideo.CustomPreviewView;
import com.photoeditor.slideshow.imagetovideo.VideoMaker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.jaygoo.widget.RangeSeekBar;
import com.photoeditor.slideshow.interfaces.VideoPlayInterface;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditActivity extends AppCompatActivity implements TransitionListener, VideoPlayInterface {
    private VideoMaker videoMaker;
    private MyVideoPlayerGif myVideoPlayerGif;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ImageView imgImage;


    @BindView(R.id.sb_range_1)
    RangeSeekBar rangeSeekBar;
    @BindView(R.id.list_effect)
    RecyclerView rvListEffect;
    @BindView(R.id.mImgControl)
    ImageView imageView;
    @BindView(R.id.img_play)
    ImageView imgPlay;
    @BindView(R.id.mTvTimeControl)
    TextView mTvTimeControl;
    @BindView(R.id.mTvDuration)
    TextView mTvDuration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(EditActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        } else {
            Hawk.init(this).build();
            arrayList.add("/storage/emulated/0/DCIM/Camera/20200901_161012.jpg");
            arrayList.add("/storage/emulated/0/DCIM/Camera/20200716_150145.jpg");
            arrayList.add("/storage/emulated/0/DCIM/Camera/20200909_165621.jpg");
            arrayList.add("/storage/emulated/0/DCIM/Camera/20200716_145557.jpg");
            arrayList.add("/storage/emulated/0/DCIM/Camera/20201111_210719.jpg");
            arrayList.add("/storage/emulated/0/DCIM/Camera/20200715_153006.jpg");
            arrayList.add("/storage/emulated/0/DCIM/Camera/20200715_101738.jpg");
            initVideo();

            imgImage = findViewById(R.id.img_image);
            imgImage.setImageURI(Uri.parse("/storage/emulated/0/DCIM/Camera/20200909_165621.jpg"));
            imgImage.setVisibility(View.GONE);
        }

    }

    private void createListEffect() {
        List<String> listEffect = Arrays.asList(getResources().getStringArray(R.array.list_effect));
        TransitionAdapter transitionAdapter = new TransitionAdapter(this, listEffect, this, 0);
        rvListEffect.setAdapter(transitionAdapter);
    }

    private void initVideo() {
        ConstraintLayout constraintLayout = findViewById(R.id.layout_edit_video);
        videoMaker = VideoMaker.getInstance();
        CustomPreviewView customPreviewView = findViewById(R.id.mCustomPreviewViewGif);
        myVideoPlayerGif = new MyVideoPlayerGif(
                this, constraintLayout, videoMaker, arrayList, customPreviewView , rangeSeekBar, imageView,
                imgPlay, mTvTimeControl, mTvDuration, this/*, (MainFragment) null, (DrafVideoModel) null,
                4096, (DefaultConstructorMarker) null)*/);
//        if (mVideoMaker4 != null) {
//            float totalDuration = mVideoMaker4.getTotalDuration();
//            ((GifTimeLineView) _$_findCachedViewById(R.id.view_time_line)).setTime((int) totalDuration);
//        }
        myVideoPlayerGif.playSlideShow();
    }

    @Override
    public void onEffectSelected(int position) {

    }

    @Override
    public void currentVideoPercent(float f) {

    }
}