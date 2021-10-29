//package com.photoeditor.slideshow.customView.recyclical.handle;
//
//import com.photoeditor.slideshow.customView.recyclical.datasource.DataSource;
//import kotlin.Metadata;
//import kotlin.jvm.internal.Intrinsics;
//
//@Metadata(mo49139bv = {1, 0, 3}, mo49140d1 = {"\u0000\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\"\u0010\u0000\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001\u0018\u0001*\u0006\u0012\u0002\b\u00030\u0002*\u00020\u0003H\b¢\u0006\u0002\u0010\u0004*4\u0010\u0005\"\u0017\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\t2\u0017\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\t¨\u0006\n"}, mo49141d2 = {"getDataSource", "T", "Lcom/photoeditor/slideshow/customView/recyclical/datasource/DataSource;", "Lcom/photoeditor/slideshow/customView/recyclical/handle/RecyclicalHandle;", "(Lcom/photoeditor/slideshow/customView/recyclical/handle/RecyclicalHandle;)Lcom/photoeditor/slideshow/customView/recyclical/datasource/DataSource;", "AdapterBlock", "Lkotlin/Function1;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "", "Lkotlin/ExtensionFunctionType;", "slideshow_release"}, mo49142k = 2, mo49143mv = {1, 4, 0})
///* compiled from: RecyclicalHandle.kt */
//public final class RecyclicalHandleKt {
//    public static final /* synthetic */ <T extends DataSource<?>> T getDataSource(RecyclicalHandle recyclicalHandle) {
//        Intrinsics.checkNotNullParameter(recyclicalHandle, "$this$getDataSource");
//        if (recyclicalHandle instanceof RealRecyclicalHandle) {
//            RealRecyclicalHandle realRecyclicalHandle = (RealRecyclicalHandle) recyclicalHandle;
//            T dataSource = realRecyclicalHandle.getDataSource();
//            Intrinsics.reifiedOperationMarker(2, "T");
//            T t = (DataSource) dataSource;
//            if (t != null) {
//                return t;
//            }
//            StringBuilder sb = new StringBuilder();
//            sb.append(realRecyclicalHandle.getDataSource());
//            sb.append(" is not a ");
//            Intrinsics.reifiedOperationMarker(4, "T");
//            sb.append(DataSource.class.getName());
//            throw new IllegalStateException(sb.toString().toString());
//        }
//        throw new IllegalStateException("Handle is not a real implementation.".toString());
//    }
//}
