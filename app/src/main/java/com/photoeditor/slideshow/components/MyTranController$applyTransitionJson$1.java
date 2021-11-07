//package com.photoeditor.slideshow.components;
//
//import com.airbnb.lottie.LottieComposition;
//import com.airbnb.lottie.LottieCompositionFactory;
//import com.airbnb.lottie.LottieResult;
//import com.photoeditor.slideshow.imagetovideo.VideoMaker;
//import com.photoeditor.slideshow.models.GifTransition;
//import com.photoeditor.slideshow.models.main.TransitionJsonModel;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.ArrayList;
//
//import kotlin.Metadata;
//import kotlin.ResultKt;
//import kotlin.Unit;
//import kotlin.coroutines.Continuation;
//import kotlin.coroutines.intrinsics.IntrinsicsKt;
//import kotlin.coroutines.jvm.internal.DebugMetadata;
//import kotlin.coroutines.jvm.internal.SuspendLambda;
//import kotlin.jvm.functions.Function2;
//import kotlin.jvm.internal.DefaultConstructorMarker;
//import kotlin.jvm.internal.Intrinsics;
//import kotlin.text.StringsKt;
//import kotlinx.coroutines.CoroutineScope;
//
//@Metadata(mo49139bv = {1, 0, 3}, mo49140d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@¢\u0006\u0004\b\u0003\u0010\u0004"}, mo49141d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo49142k = 3, mo49143mv = {1, 4, 0})
//@DebugMetadata(mo49846c = "com.photoeditor.slideshow.components.MyTranController$applyTransitionJson$1", mo49847f = "MyTranController.kt", mo49848i = {}, mo49849l = {}, mo49850m = "invokeSuspend", mo49851n = {}, mo49852s = {})
///* compiled from: MyTranController.kt */
//final class MyTranController$applyTransitionJson$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
//    final /* synthetic */ ArrayList $listModel;
//    final /* synthetic */ GifTransition gifTransition;
//    int label;
//
//    /* renamed from: p$ */
//    private CoroutineScope f463p$;
//    final /* synthetic */ MyTranController myTranController;
//
//    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
//    MyTranController$applyTransitionJson$1(MyTranController myTranController, GifTransition gifTransition, ArrayList arrayList) {
//        super(2, continuation);
//        this.myTranController = myTranController;
//        this.gifTransition = gifTransition;
//        this.$listModel = arrayList;
//    }
//
//
//    public final Object invokeSuspend(Object obj) {
//        LottieResult<LottieComposition> fromJsonInputStreamSync;
//        if (this.label == 0) {
//            if ((fromJsonInputStreamSync = LottieCompositionFactory.fromJsonInputStreamSync(new FileInputStream(new File(this.gifTransition.getPath())), this.gifTransition.getPath())) != null) {
//                ArrayList arrayList = this.$listModel;
//                GifTransition gifTransition = this.gifTransition;
//                LottieComposition value = fromJsonInputStreamSync.getValue();
//                arrayList.add(new TransitionJsonModel(gifTransition, value, 0, 0));
//            }
//            if ((!this.$listModel.isEmpty()) && this.myTranController.mVideoMaker != null) {
//                .applyRandomJsonTransition(this.gifTransition, this.$listModel);
//            }
//        }
//    }
//}
