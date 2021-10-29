package com.photoeditor.slideshow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaygoo.widget.RangeSeekBar;
import com.orhanobut.hawk.Hawk;
import com.photoeditor.slideshow.components.MyTranController;
import com.photoeditor.slideshow.components.MyVideoPlayer;
import com.photoeditor.slideshow.components.MyVideoPlayerGif;
import com.photoeditor.slideshow.customView.TabTranIndicator;
import com.photoeditor.slideshow.imagetovideo.CustomPreviewView;
import com.photoeditor.slideshow.imagetovideo.Transition;
import com.photoeditor.slideshow.imagetovideo.VideoMaker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.photoeditor.slideshow.interfaces.VideoPlayInterface;
import com.photoeditor.slideshow.models.GifTransition;
//import com.photoeditor.slideshow.models.GifTransitionViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditActivity extends AppCompatActivity implements TransitionListener, VideoPlayInterface {
    private VideoMaker videoMaker;
    private MyVideoPlayer myVideoPlayer;
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
            arrayList.add("/storage/emulated/0/DCIM/Camera/20201111_210719.jpg");
            arrayList.add("/storage/emulated/0/DCIM/Camera/20200715_153006.jpg");
            arrayList.add("/storage/emulated/0/DCIM/Camera/20200716_150145.jpg");
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


    private MyTranController myTranController;
//    public GifTransitionViewModel transitionViewModel;

    private void initVideo() {
//        ConstraintLayout constraintLayout = findViewById(R.id.layout_edit_video);
//        videoMaker = VideoMaker.getInstance();
//        CustomPreviewView customPreviewView = findViewById(R.id.mCustomPreviewViewGif);
//        myVideoPlayerGif = new MyVideoPlayerGif(
//                this, constraintLayout, videoMaker, arrayList, customPreviewView , rangeSeekBar, imageView,
//                imgPlay, mTvTimeControl, mTvDuration, this/*, (MainFragment) null, (DrafVideoModel) null,
//                4096, (DefaultConstructorMarker) null)*/);
////        if (mVideoMaker4 != null) {
////            float totalDuration = mVideoMaker4.getTotalDuration();
////            ((GifTimeLineView) _$_findCachedViewById(R.id.view_time_line)).setTime((int) totalDuration);
////        }
//        myVideoPlayerGif.playSlideShow();

        CustomPreviewView customPreviewView = findViewById(R.id.mCustomPreviewView);
        videoMaker = VideoMaker.getInstance();
        myVideoPlayer = new MyVideoPlayer(
                this, videoMaker, arrayList, customPreviewView, rangeSeekBar, imageView,
                imgPlay, mTvTimeControl, mTvDuration, this);
//        if (mVideoMaker4 != null) {
//            float totalDuration = mVideoMaker4.getTotalDuration();
//            ((GifTimeLineView) _$_findCachedViewById(R.id.view_time_line)).setTime((int) totalDuration);
//        }
//        MyVideoPlayerGif myVideoPlayer = getMyVideoPlayer();
//        if (myVideoPlayer != null) {
        myVideoPlayer.playSlideShow();
//        }

//        ViewModel viewModel2 = new ViewModelProvider(this).get(GifTransitionViewModel.class);
//        transitionViewModel = ((GifTransitionViewModel) viewModel2);
        GifTransition transition = new GifTransition("Slide L", "Slide L", "classic", R.drawable.slide_l, true,  null, Transition.SLIDE_LEFT);
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_tran);
//        TabTranIndicator tabTranIndicator = (TabTranIndicator) findViewById(R.id.tab_layout_tran);
        myTranController = (new MyTranController(transition, this,
                this, null, null, videoMaker, this));



//        MyTranController myTranController = getMyTranController();
//        if (myTranController != null) {
//            myTranController.setUnlockMate(isUnlockMate());
//            myTranController.setPremium(this.isPremium);
//            myTranController.setEditStickerListener(this);
//        }
//
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_tran);
//        TabTranIndicator tabTranIndicator = (TabTranIndicator) _$_findCachedViewById(R.id.tab_layout_tran);
//        VideoMaker mVideoMaker = getMVideoMaker();
//        RelativeLayout relativeLayout = (RelativeLayout) _$_findCachedViewById(R.id.view_empty_transition);
//        setMyTranController(new MyTranController(dialogViewModel, transition, transitionViewModel, viewLifecycleOwner,
//                context, recyclerView, tabTranIndicator, mVideoMaker, relativeLayout, getActivity()));
//        MyTranController myTranController = getMyTranController();
//        if (myTranController != null) {
//            myTranController.setUnlockMate(isUnlockMate());
//            myTranController.setPremium(this.isPremium);
//            myTranController.setEditStickerListener(this);
//        }
    }

    private final void doneTransitionNew() {
//        MyTranController myTranController = getMyTranController();
//        if (myTranController != null) {
//            myTranController.doneTranNew();
//        }
//        setCurrentViewShow(VIEW_SHOW.VIEW_MAIN);
//        View _$_findCachedViewById = _$_findCachedViewById(R.id.layout_transition_new);
//        if (_$_findCachedViewById != null) {
//            ExtentionsKt.gone(_$_findCachedViewById);
//        }
//        animationShowFromBottom(_$_findCachedViewById(R.id.recycle_main_edit));
    }

    @Override
    public void onEffectSelected(int position) {

    }

    @Override
    public void currentVideoPercent(float f) {

    }
}