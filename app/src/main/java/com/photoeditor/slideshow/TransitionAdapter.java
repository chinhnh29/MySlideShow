package com.photoeditor.slideshow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransitionAdapter extends RecyclerView.Adapter<TransitionAdapter.ViewHolder> {
    private List<String> listStringEffect;
    private Context context;
    private TransitionListener listener;
    private int selectedPos;

    public TransitionAdapter(Context context, List<String> listStringEffect, TransitionListener listener, int selectedPos) {
        this.context = context;
        this.listStringEffect = listStringEffect;
        this.listener = listener;
        this.selectedPos = selectedPos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_transition, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setSelected(selectedPos == position);
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return listStringEffect.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_effect)
        ImageView imgEffect;
        private int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind(int position) {
            this.position = position;
            String imageSource = listStringEffect.get(position);
            if (position == selectedPos) {
                imageSource += "_selected";
            }
            loadImageResourceForItemTransition(imageSource);
        }

        private void loadImageResourceForItemTransition(String imageSource) {
//            switch (imageSource) {
//                case "img_random":
//                    loadImage(R.drawable.img_random);
//                    break;
//                case "img_none":
//                    loadImage(R.drawable.img_none);
//                    break;
//                case "img_zoom_i":
//                    loadImage(R.drawable.img_zoom_i);
//                    break;
//                case "img_zoom_o":
//                    loadImage(R.drawable.img_zoom_o);
//                    break;
//                case "img_wipe_l":
//                    loadImage(R.drawable.img_wipe_l);
//                    break;
//                case "img_wipe_r":
//                    loadImage(R.drawable.img_wipe_r);
//                    break;
//                case "img_wipe_d":
//                    loadImage(R.drawable.img_wipe_d);
//                    break;
//                case "img_wipe_u":
//                    loadImage(R.drawable.img_wipe_u);
//                    break;
//                case "img_random_selected":
//                    loadImage(R.drawable.img_random_selected);
//                    break;
//                case "img_none_selected":
//                    loadImage(R.drawable.img_none_selected);
//                    break;
//                case "img_zoom_i_selected":
//                    loadImage(R.drawable.img_zoom_i_selected);
//                    break;
//                case "img_zoom_o_selected":
//                    loadImage(R.drawable.img_zoom_o_selected);
//                    break;
//                case "img_wipe_l_selected":
//                    loadImage(R.drawable.img_wipe_l_selected);
//                    break;
//                case "img_wipe_r_selected":
//                    loadImage(R.drawable.img_wipe_r_selected);
//                    break;
//                case "img_wipe_d_selected":
//                    loadImage(R.drawable.img_wipe_d_selected);
//                    break;
//                case "img_wipe_u_selected":
//                    loadImage(R.drawable.img_wipe_u_selected);
//                    break;
//            }
        }

        @OnClick(R.id.img_effect)
        void onClick() {
            notifyItemChanged(selectedPos);
            selectedPos = getAdapterPosition();
            notifyItemChanged(position);
            listener.onEffectSelected(position);
        }

        private void loadImage(int resId) {
            Glide.with(context)
                    .load(resId)
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)).into(imgEffect);
        }
    }
}
