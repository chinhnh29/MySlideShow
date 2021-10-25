package com.photoeditor.slideshow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.orhanobut.hawk.Hawk;
import com.photoeditor.slideshow.components.MyVideoPlayerGif;
import com.photoeditor.slideshow.imagetovideo.CustomPreviewView;
import com.photoeditor.slideshow.imagetovideo.VideoMaker;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {
    private VideoMaker videoMaker;
    private MyVideoPlayerGif myVideoPlayerGif;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ImageView imgImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


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

    private void initVideo() {
        ConstraintLayout constraintLayout = findViewById(R.id.layout_edit_video);
        videoMaker = VideoMaker.getInstance();
        CustomPreviewView customPreviewView = findViewById(R.id.mCustomPreviewViewGif);
//        RangeSeekBar rangeSeekBar = (RangeSeekBar) _$_findCachedViewById(R.id.sb_range_1);
//        ImageView imageView = (ImageView) findViewById(R.id.mImgControl);
//        ImageView imageView2 = (ImageView) findViewById(R.id.img_play);
//        TextView textView = (TextView) findViewById(R.id.mTvTimeControl);
//        TextView textView2 = (TextView) findViewById(R.id.mTvDuration);
        myVideoPlayerGif = new MyVideoPlayerGif(
                this, constraintLayout, videoMaker, arrayList, customPreviewView /*, rangeSeekBar, imageView,
                imageView2, textView, textView2, this, (MainFragment) null, (DrafVideoModel) null,
                4096, (DefaultConstructorMarker) null)*/);
//        if (mVideoMaker4 != null) {
//            float totalDuration = mVideoMaker4.getTotalDuration();
//            ((GifTimeLineView) _$_findCachedViewById(R.id.view_time_line)).setTime((int) totalDuration);
//        }
        myVideoPlayerGif.playSlideShow();
    }
}