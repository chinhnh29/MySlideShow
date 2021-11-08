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
import com.photoeditor.slideshow.my_slide_show.obj.FrameInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FrameAdapter extends RecyclerView.Adapter<FrameAdapter.FrameHolder> {
    private List<FrameInfo> frameInfos;
    private Context context;
    private MyTranController tranController;

    public FrameAdapter(Context context, List<FrameInfo> frameInfos, MyTranController tranController) {
        this.frameInfos = frameInfos;
        this.context = context;
        this.tranController = tranController;
    }


    @NonNull
    @Override
    public FrameHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_frame, parent, false);
        return new FrameHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FrameHolder holder, int position) {
        holder.onBind((position));
    }

    @Override
    public int getItemCount() {
        return frameInfos.size();
    }

    public class FrameHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_frame)
        AppCompatImageView imgTran;
        @BindView(R.id.txt_frame)
        TextView txtTran;

        public FrameHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.img_frame)
        void onClick() {
//            tranController.changeTransition(frameInfos.get(getAdapterPosition()));
        }

        public void onBind(int position) {
            txtTran.setText(frameInfos.get(position).getFileName());
            Glide.with(context)
                    .applyDefaultRequestOptions(new RequestOptions().skipMemoryCache(true).
                            diskCacheStrategy(DiskCacheStrategy.NONE))
                    .load(frameInfos.get(position).getResId())
                    .into(imgTran);
        }
    }
}
