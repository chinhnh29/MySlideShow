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
import com.photoeditor.slideshow.models.GifTransition;
import com.photoeditor.slideshow.my_slide_show.obj.Transit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransitionAdapter extends RecyclerView.Adapter<TransitionAdapter.TransitionHolder> {
    private List<GifTransition> transitList;
    private Context context;
    private MyTranController tranController;

    public TransitionAdapter(Context context, List<GifTransition> transitList, MyTranController tranController) {
        this.transitList = transitList;
        this.context = context;
        this.tranController = tranController;
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
        return transitList.size();
    }

    public class TransitionHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_tran)
        AppCompatImageView imgTran;
        @BindView(R.id.txt_tran)
        TextView txtTran;

        public TransitionHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.img_tran)
        void onClick() {
            tranController.changeTransition(transitList.get(getAdapterPosition()));
        }

        public void onBind(int position) {
            txtTran.setText(transitList.get(position).getFileName());
            Glide.with(context)
                    .applyDefaultRequestOptions(new RequestOptions().skipMemoryCache(true).
                            diskCacheStrategy(DiskCacheStrategy.NONE))
                    .load(transitList.get(position).getResId())
                    .into(imgTran);
        }
    }
}
