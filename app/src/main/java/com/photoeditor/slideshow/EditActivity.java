package com.photoeditor.slideshow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaygoo.widget.RangeSeekBar;
import com.orhanobut.hawk.Hawk;
import com.photoeditor.slideshow.common.AppConst;
import com.photoeditor.slideshow.components.MyTranController;
import com.photoeditor.slideshow.components.MyVideoPlayer;
import com.photoeditor.slideshow.imagetovideo.CustomPreviewView;
import com.photoeditor.slideshow.imagetovideo.Transition;
import com.photoeditor.slideshow.imagetovideo.VideoMaker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.photoeditor.slideshow.interfaces.VideoPlayInterface;
import com.photoeditor.slideshow.java.PhotorTool;
import com.photoeditor.slideshow.models.GifTheme;
import com.photoeditor.slideshow.models.GifTransition;
import com.photoeditor.slideshow.utils.Constants;
import com.photoeditor.slideshow.viewmodel.RenderActivity;
//import com.photoeditor.slideshow.models.GifTransitionViewModel;
//import com.photoeditor.slideshow.models.GifTransitionViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditActivity extends AppCompatActivity implements TransitionListener, VideoPlayInterface {

    @BindView(R.id.sb_range_1)
    RangeSeekBar rangeSeekBar;
    @BindView(R.id.mImgControl)
    ImageView imageView;
    @BindView(R.id.img_play)
    ImageView imgPlay;
    @BindView(R.id.mTvTimeControl)
    TextView mTvTimeControl;
    @BindView(R.id.mTvDuration)
    TextView mTvDuration;

    @BindView(R.id.ll_transit)
    LinearLayout llTransit;
    @BindView(R.id.ll_frame)
    LinearLayout llFrame;
    @BindView(R.id.ll_ratio)
    LinearLayout llRatio;
    @BindView(R.id.ll_filter)
    LinearLayout llFilter;
    @BindView(R.id.mCustomPreviewView)
    CustomPreviewView customPreviewView;

    private MyTranController myTranController;
    private VideoMaker videoMaker;
    private MyVideoPlayer myVideoPlayer;
    private ArrayList<String> arrayListImage = new ArrayList<>();
    private ImageView imgImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);

//        try {
//            Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
//            intent.addCategory("android.intent.category.DEFAULT");
//            intent.setData(Uri.parse(String.format("package:%s", new Object[]{getApplicationContext().getPackageName()})));
//            startActivityForResult(intent, 2000);
//        } catch (Exception e) {
//            Log.e("ChinhNH", "onCreate: " + "llll");
//            Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
//            startActivityForResult(intent, 2000);
//        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(EditActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        } else {
            Hawk.init(this).build();
            arrayListImage.add("/storage/emulated/0/DCIM/Camera/20200901_161012.jpg");
            arrayListImage.add("/storage/emulated/0/DCIM/Camera/20200716_150145.jpg");
            arrayListImage.add("/storage/emulated/0/DCIM/Camera/20201111_210719.jpg");
            arrayListImage.add("/storage/emulated/0/DCIM/Camera/20200715_153006.jpg");
            arrayListImage.add("/storage/emulated/0/DCIM/Camera/20200716_150145.jpg");
            arrayListImage.add("/storage/emulated/0/DCIM/Camera/20200715_101738.jpg");
            initVideo();

            imgImage = findViewById(R.id.img_image);
            imgImage.setImageURI(Uri.parse("/storage/emulated/0/DCIM/Camera/20200909_165621.jpg"));
            imgImage.setVisibility(View.GONE);
        }

    }

    private void createListTransit() {

    }


    private void initVideo() {
        videoMaker = VideoMaker.getInstance();
        myVideoPlayer = new MyVideoPlayer(
                this, videoMaker, arrayListImage, customPreviewView, rangeSeekBar, imageView,
                imgPlay, mTvTimeControl, mTvDuration, this);

        myVideoPlayer.playSlideShow();
        GifTransition transition = new GifTransition("Slide L", "Slide L", "classic", R.drawable.slide_l, true, null, Transition.SLIDE_LEFT);

        myTranController = new MyTranController(transition, this/*, transitionViewModel*/, this,
                this, videoMaker, myVideoPlayer);
    }

    @OnClick({R.id.rl_choose_effect, R.id.rl_frame, R.id.rl_ratio, R.id.txt_next_edit, R.id.rl_filter})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_choose_effect:
                goneAllLayoutMenu();
                llTransit.setVisibility(View.VISIBLE);
                break;
            case R.id.rl_frame:
                goneAllLayoutMenu();
                llFrame.setVisibility(View.VISIBLE);
                break;
            case R.id.rl_ratio:
                goneAllLayoutMenu();
                llRatio.setVisibility(View.VISIBLE);
                break;
            case R.id.rl_filter:
                goneAllLayoutMenu();
                llFilter.setVisibility(View.VISIBLE);
                break;
            case R.id.txt_next_edit:
                chooseQualityRenderVideo();
                break;

        }
    }

    private void chooseQualityRenderVideo() {
        boolean z;
        boolean z2;
        List<GifTransition> list = videoMaker.getListTransitionModel();;
        GifTheme currentThemeModel = videoMaker.getCurrentThemeModel();
        ArrayList arrayList = (ArrayList) Hawk.get(AppConst.KEY_LIST_UNLOCK, new ArrayList());
        boolean z3 = true;
//        if (currentThemeModel != null) {
//            boolean pro = currentThemeModel.getPro();
//            if (pro) {
//                if (getItemRewardModel() != null) {
//                    ItemRewardModel itemRewardModel = getItemRewardModel();
//                    if (itemRewardModel.getGifTheme() != null) {
//                        ItemRewardModel itemRewardModel2 = getItemRewardModel();
//                        GifTheme gifTheme = itemRewardModel2.getGifTheme();
//                    }
//                }
//                z = currentThemeModel.getId() == null || !arrayList.contains(currentThemeModel.getId());
//                if (list == null) {
//                    z2 = false;
//                    for (GifTransition gifTransition : list) {
//                        if (gifTransition != null) {
//                            Boolean pro2 = gifTransition.getPro();
//                            if (pro2.booleanValue()) {
//                                if (getItemRewardModel() != null) {
//                                    ItemRewardModel itemRewardModel3 = getItemRewardModel();
//                                    if (itemRewardModel3.getGifTransition() != null) {
//                                        ItemRewardModel itemRewardModel4 = getItemRewardModel();
//                                        GifTransition gifTransition2 = itemRewardModel4.getGifTransition();
//                                        if (Intrinsics.areEqual(gifTransition2.getId(), gifTransition.getId())) {
//                                            gifTransition.setPro(false);
//                                        }
//                                    }
//                                }
//                                z2 = true;
//                            }
//                        }
//                    }
//                } else {
//                    z2 = false;
//                }
//                if (!z && !z2) {
//                    z3 = false;
//                }
//                if (!AppConst.INSTANCE.isPREMIUM() || isRewarded() || !z3) {
//                    showBottomExport();
//                }
//                Context requireContext = requireContext();
//                DialogProContent dialogProContent = new DialogProContent(requireContext);
//                dialogProContent.showDialog(list, currentThemeModel, z2, z, new MainFragment$clickSaveNew$1(this));
//                return;
//            }
//        }
//        z = false;
//        if (list == null) {
//        }
//        z3 = false;
//        if (!AppConst.INSTANCE.isPREMIUM()) {
//        }
//        showBottomExport();
        myVideoPlayer.stopPreview();
        if (PhotorTool.checkHasPermission(this)) {
            boolean mHasTrimAudio = myVideoPlayer.getMHasTrimAudio();
            boolean isLoopMusic = myVideoPlayer.isLoopMusic();
            boolean isAudioVideo = false;
            Intent intent = new Intent(this, RenderActivity.class);
            intent.putExtra(Constants.EXTRA_IS_MUSIC, false);
            intent.putExtra(Constants.EXTRA_IS_HAS_TRIM_AUDIO, mHasTrimAudio);
            intent.putExtra(Constants.EXTRA_IS_LOOP_AUDIO, isLoopMusic);
            intent.putExtra(Constants.EXTRA_IS_AUDIO_VIDEO, isAudioVideo);
            intent.putExtra(Constants.EXTRA_IS_MUSIC_LONGER, myVideoPlayer.isMusicLongerVideo());
            startActivity(intent);
        } else {
//            ((TedPermission.Builder) ((TedPermission.Builder) ((TedPermission.Builder) TedPermission.with(getContext()).setPermissionListener(new MainFragment$clickSaveVideo$1(this))).setDeniedMessage((CharSequence) "If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")).setPermissions("android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE")).check();
        }

    }

    private void goneAllLayoutMenu() {
        llTransit.setVisibility(View.GONE);
        llFrame.setVisibility(View.GONE);
        llRatio.setVisibility(View.GONE);
        llFilter.setVisibility(View.GONE);
    }

    @Override
    public void onEffectSelected(int position) {

    }

    @Override
    public void currentVideoPercent(float f) {

    }
}