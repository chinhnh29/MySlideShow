package com.photoeditor.slideshow.my_slide_show.list_category_transit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.photoeditor.slideshow.R;
import com.photoeditor.slideshow.components.MyTranController;
import com.photoeditor.slideshow.my_slide_show.obj.FilterModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.FrameHolder> {
    private List<FilterModel> filterModelList;
    private Context context;
    private MyTranController tranController;

    public FilterAdapter(Context context, List<FilterModel> filterModelList, MyTranController tranController) {
        this.filterModelList = filterModelList;
        this.context = context;
        this.tranController = tranController;
    }


    @NonNull
    @Override
    public FrameHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_filter, parent, false);
        return new FrameHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FrameHolder holder, int position) {
        holder.onBind((position));
    }

    @Override
    public int getItemCount() {
        return filterModelList.size();
    }

    public class FrameHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_filter)
        AppCompatImageView imgFilter;
        @BindView(R.id.txt_filter)
        TextView txtFilterName;

        public FrameHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.img_filter)
        void onClick() {
            tranController.chooseFilter(filterModelList.get(getAdapterPosition()));
        }

        public void onBind(int position) {
            txtFilterName.setText(filterModelList.get(position).getFileName());
            Glide.with(context)
                    .applyDefaultRequestOptions(new RequestOptions().skipMemoryCache(true).
                            diskCacheStrategy(DiskCacheStrategy.NONE))
                    .load(filterModelList.get(position).getResId())
                    .into(imgFilter);
        }
    }
}
