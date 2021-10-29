//package com.photoeditor.slideshow.customView.recyclical.handle;
//
//import android.os.Looper;
//import android.view.View;
//import androidx.recyclerview.widget.RecyclerView;
//import com.google.android.gms.measurement.api.AppMeasurementSdk;
//import com.photoeditor.slideshow.customView.recyclical.ItemDefinition;
//import com.photoeditor.slideshow.customView.recyclical.datasource.DataSource;
//import com.photoeditor.slideshow.customView.recyclical.internal.DefinitionAdapter;
//import java.util.Map;
//import kotlin.Metadata;
//import kotlin.Unit;
//import kotlin.jvm.functions.Function1;
//import kotlin.jvm.internal.Intrinsics;
//
//@Metadata(mo49139bv = {1, 0, 3}, mo49140d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001BU\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u0012\u001a\u0010\n\u001a\u0016\u0012\u0004\u0012\u00020\t\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000b0\u0007\u0012\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\r¢\u0006\u0002\u0010\u000eJ\r\u0010\u0013\u001a\u00020\u0014H\u0000¢\u0006\u0002\b\u0015J\r\u0010\u0016\u001a\u00020\u0014H\u0000¢\u0006\u0002\b\u0017J\f\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0019H\u0016J\u0018\u0010\u001a\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000b2\u0006\u0010\u001b\u001a\u00020\bH\u0016J\u0018\u0010\u001c\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000b2\u0006\u0010\u001d\u001a\u00020\tH\u0016J\u0010\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\bH\u0016J%\u0010\u001f\u001a\u00020\u00142\u001b\u0010 \u001a\u0017\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0019\u0012\u0004\u0012\u00020\u00140!¢\u0006\u0002\b\"H\u0016J\u0010\u0010#\u001a\u00020\u00142\u0006\u0010$\u001a\u00020%H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\"\u0010\n\u001a\u0016\u0012\u0004\u0012\u00020\t\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000b0\u0007X\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\f\u001a\u0006\u0012\u0002\b\u00030\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, mo49141d2 = {"Lcom/photoeditor/slideshow/customView/recyclical/handle/RealRecyclicalHandle;", "Lcom/photoeditor/slideshow/customView/recyclical/handle/RecyclicalHandle;", "emptyView", "Landroid/view/View;", "adapter", "Lcom/photoeditor/slideshow/customView/recyclical/internal/DefinitionAdapter;", "itemClassToType", "", "", "", "bindingsToTypes", "Lcom/photoeditor/slideshow/customView/recyclical/ItemDefinition;", "dataSource", "Lcom/photoeditor/slideshow/customView/recyclical/datasource/DataSource;", "(Landroid/view/View;Lcom/photoeditor/slideshow/customView/recyclical/internal/DefinitionAdapter;Ljava/util/Map;Ljava/util/Map;Lcom/photoeditor/slideshow/customView/recyclical/datasource/DataSource;)V", "getDataSource", "()Lcom/photoeditor/slideshow/customView/recyclical/datasource/DataSource;", "getEmptyView$slideshow_release", "()Landroid/view/View;", "attachDataSource", "", "attachDataSource$slideshow_release", "detachDataSource", "detachDataSource$slideshow_release", "getAdapter", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "getDefinitionForClass", "name", "getDefinitionForType", "type", "getViewTypeForClass", "invalidateList", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "showOrHideEmptyView", "show", "", "slideshow_release"}, mo49142k = 1, mo49143mv = {1, 4, 0})
///* compiled from: RealRecyclicalHandle.kt */
//public final class RealRecyclicalHandle implements RecyclicalHandle {
//    private final DefinitionAdapter adapter;
//    private final Map<Integer, ItemDefinition<?, ?>> bindingsToTypes;
//    private final DataSource<?> dataSource;
//    private final View emptyView;
//    private final Map<String, Integer> itemClassToType;
//
//    public RealRecyclicalHandle(View view, DefinitionAdapter definitionAdapter, Map<String, Integer> map, Map<Integer, ItemDefinition<?, ?>> map2, DataSource<?> dataSource2) {
//        Intrinsics.checkNotNullParameter(definitionAdapter, "adapter");
//        Intrinsics.checkNotNullParameter(map, "itemClassToType");
//        Intrinsics.checkNotNullParameter(map2, "bindingsToTypes");
//        Intrinsics.checkNotNullParameter(dataSource2, "dataSource");
//        this.emptyView = view;
//        this.adapter = definitionAdapter;
//        this.itemClassToType = map;
//        this.bindingsToTypes = map2;
//        this.dataSource = dataSource2;
//    }
//
//    public final View getEmptyView$slideshow_release() {
//        return this.emptyView;
//    }
//
//    public final DataSource<?> getDataSource() {
//        return this.dataSource;
//    }
//
//    public void showOrHideEmptyView(boolean z) {
//        if (Intrinsics.areEqual((Object) Looper.myLooper(), (Object) Looper.getMainLooper())) {
//            View view = this.emptyView;
//            if (view != null) {
//                view.setVisibility(z ? 0 : 8);
//                return;
//            }
//            return;
//        }
//        throw new IllegalStateException("DataSource interaction must be done on the main (UI) thread.".toString());
//    }
//
//    public RecyclerView.Adapter<?> getAdapter() {
//        return this.adapter;
//    }
//
//    public void invalidateList(Function1<? super RecyclerView.Adapter<?>, Unit> function1) {
//        Intrinsics.checkNotNullParameter(function1, "block");
//        if (Intrinsics.areEqual((Object) Looper.myLooper(), (Object) Looper.getMainLooper())) {
//            function1.invoke(getAdapter());
//            showOrHideEmptyView(this.dataSource.isEmpty());
//            return;
//        }
//        throw new IllegalStateException("DataSource interaction must be done on the main (UI) thread.".toString());
//    }
//
//    public int getViewTypeForClass(String str) {
//        Intrinsics.checkNotNullParameter(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
//        Integer num = this.itemClassToType.get(str);
//        if (num != null) {
//            return num.intValue();
//        }
//        throw new IllegalStateException(("Didn't find type for class " + str).toString());
//    }
//
//    public ItemDefinition<?, ?> getDefinitionForClass(String str) {
//        Intrinsics.checkNotNullParameter(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
//        return getDefinitionForType(getViewTypeForClass(str));
//    }
//
//    public ItemDefinition<?, ?> getDefinitionForType(int i) {
//        ItemDefinition<?, ?> itemDefinition = this.bindingsToTypes.get(Integer.valueOf(i));
//        if (itemDefinition != null) {
//            return itemDefinition;
//        }
//        throw new IllegalStateException(("Unable to view item definition for viewType " + i).toString());
//    }
//
//    public final void attachDataSource$slideshow_release() {
//        RecyclicalHandle recyclicalHandle = this;
//        this.dataSource.attach(recyclicalHandle);
//        this.adapter.attach(recyclicalHandle);
//    }
//
//    public final void detachDataSource$slideshow_release() {
//        this.dataSource.detach();
//        this.adapter.detach();
//    }
//}
