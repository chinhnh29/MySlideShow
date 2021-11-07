//package com.photoeditor.slideshow.components;
//
//import android.widget.Toast;
//import com.gomin.slideshowmaker.videoeditor.videomaker.musicvideo.R;
//import com.photoeditor.slideshow.imagetovideo.VideoMaker;
//import com.photoeditor.slideshow.models.GifTransition;
//import com.photoeditor.slideshow.models.main.TransitionDrawModel;
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Iterator;
//import kotlin.Metadata;
//import kotlin.ResultKt;
//import kotlin.Unit;
//import kotlin.coroutines.Continuation;
//import kotlin.coroutines.intrinsics.IntrinsicsKt;
//import kotlin.coroutines.jvm.internal.DebugMetadata;
//import kotlin.coroutines.jvm.internal.SuspendLambda;
//import kotlin.jvm.functions.Function2;
//import kotlin.jvm.internal.Intrinsics;
//import kotlinx.coroutines.BuildersKt;
//import kotlinx.coroutines.CoroutineScope;
//import kotlinx.coroutines.Dispatchers;
//
//@Metadata(mo49139bv = {1, 0, 3}, mo49140d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@¢\u0006\u0004\b\u0003\u0010\u0004"}, mo49141d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo49142k = 3, mo49143mv = {1, 4, 0})
//@DebugMetadata(mo49846c = "com.photoeditor.slideshow.components.MyTranController$randomTranDraw$1", mo49847f = "MyTranController.kt", mo49848i = {0}, mo49849l = {396}, mo49850m = "invokeSuspend", mo49851n = {"$this$launch"}, mo49852s = {"L$0"})
///* compiled from: MyTranController.kt */
//final class MyTranController$randomTranDraw$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
//    final /* synthetic */ ArrayList $list;
//    final /* synthetic */ GifTransition $tranModel;
//    Object L$0;
//    int label;
//
//    /* renamed from: p$ */
//    private CoroutineScope f465p$;
//    final /* synthetic */ MyTranController this$0;
//
//    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
//    MyTranController$randomTranDraw$1(MyTranController myTranController, ArrayList arrayList, GifTransition gifTransition, Continuation continuation) {
//        super(2, continuation);
//        this.this$0 = myTranController;
//        this.$list = arrayList;
//        this.$tranModel = gifTransition;
//    }
//
//    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
//        Intrinsics.checkNotNullParameter(continuation, "completion");
//        MyTranController$randomTranDraw$1 myTranController$randomTranDraw$1 = new MyTranController$randomTranDraw$1(this.this$0, this.$list, this.$tranModel, continuation);
//        myTranController$randomTranDraw$1.f465p$ = (CoroutineScope) obj;
//        return myTranController$randomTranDraw$1;
//    }
//
//    public final Object invoke(Object obj, Object obj2) {
//        return ((MyTranController$randomTranDraw$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
//    }
//
//    public final Object invokeSuspend(Object obj) {
//        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
//        int i = this.label;
//        if (i == 0) {
//            if (this.this$0.listTransition != null) {
//                ArrayList access$getListTransition$p = this.this$0.listTransition;
//                Iterator it = access$getListTransition$p.iterator();
//                while (it.hasNext()) {
//                    GifTransition gifTransition = (GifTransition) it.next();
//                    Boolean localFile = gifTransition.getLocalFile();
//                    if (localFile.booleanValue() && gifTransition.getPath() != null) {
//                        File file = new File(gifTransition.getPath());
//                        if (file.exists()) {
//                            this.$list.add(new TransitionDrawModel(gifTransition, this.this$0.getListBitmap(file)));
//                        }
//                    }
//                }
//                if (!this.$list.isEmpty()) {
//                    VideoMaker access$getMVideoMaker$p = this.this$0.mVideoMaker;
//                    if (access$getMVideoMaker$p != null) {
//                        access$getMVideoMaker$p.applyTransitionRandomDraw(this.$tranModel, this.$list);
//                    }
//                } else {
//                    this.L$0 = coroutineScope;
//                    this.label = 1;
//                    if (BuildersKt.withContext(Dispatchers.getMain(), new C25361(this, (Continuation) null), this) == coroutine_suspended) {
//                        return coroutine_suspended;
//                    }
//                }
//            }
//        } else if (i == 1) {
//            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
//            ResultKt.throwOnFailure(obj);
//        } else {
//            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
//        }
//        return Unit.INSTANCE;
//    }
//
//    @Metadata(mo49139bv = {1, 0, 3}, mo49140d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@¢\u0006\u0004\b\u0003\u0010\u0004"}, mo49141d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo49142k = 3, mo49143mv = {1, 4, 0})
//    @DebugMetadata(mo49846c = "com.photoeditor.slideshow.components.MyTranController$randomTranDraw$1$1", mo49847f = "MyTranController.kt", mo49848i = {}, mo49849l = {}, mo49850m = "invokeSuspend", mo49851n = {}, mo49852s = {})
//    /* renamed from: com.photoeditor.slideshow.components.MyTranController$randomTranDraw$1$1 */
//    /* compiled from: MyTranController.kt */
//    static final class C25361 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
//        int label;
//
//        /* renamed from: p$ */
//        private CoroutineScope f466p$;
//        final /* synthetic */ MyTranController$randomTranDraw$1 this$0;
//
//        {
//            this.this$0 = r1;
//        }
//
//        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
//            Intrinsics.checkNotNullParameter(continuation, "completion");
//            C25361 r0 = new C25361(this.this$0, continuation);
//            r0.f466p$ = (CoroutineScope) obj;
//            return r0;
//        }
//
//        public final Object invoke(Object obj, Object obj2) {
//            return ((C25361) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
//        }
//
//        public final Object invokeSuspend(Object obj) {
//            IntrinsicsKt.getCOROUTINE_SUSPENDED();
//            if (this.label == 0) {
//                ResultKt.throwOnFailure(obj);
//                Toast.makeText(this.this$0.this$0.context, R.string.not_download, 0).show();
//                return Unit.INSTANCE;
//            }
//            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
//        }
//    }
//}
