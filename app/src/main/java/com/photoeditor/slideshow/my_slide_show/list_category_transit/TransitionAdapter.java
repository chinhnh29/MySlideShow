package com.photoeditor.slideshow.my_slide_show.list_category_transit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.photoeditor.slideshow.R;
import com.photoeditor.slideshow.my_slide_show.obj.GifTransition;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransitionAdapter extends RecyclerView.Adapter<TransitionAdapter.TransitionHolder> {
    private List<GifTransition> gifTransitionList;
    private Context context;

    public TransitionAdapter(List<GifTransition> gifTransitionList, Context context) {
        this.gifTransitionList = gifTransitionList;
        this.context = context;
    }

    @NonNull
    @Override
    public TransitionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tran, parent, false);
        return new TransitionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransitionHolder holder, int position) {
        holder.onBind((position));
    }

    @Override
    public int getItemCount() {
        return gifTransitionList.size();
    }

    public class TransitionHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_tran)
        AppCompatImageView imgTran;

        public TransitionHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(int position) {
            Glide.with(context)
                    .applyDefaultRequestOptions(new RequestOptions().skipMemoryCache(true).
                            diskCacheStrategy(DiskCacheStrategy.NONE))
                    .load(gifTransitionList.get(position).getResId())
                    .into(imgTran);
        }
    }
}
