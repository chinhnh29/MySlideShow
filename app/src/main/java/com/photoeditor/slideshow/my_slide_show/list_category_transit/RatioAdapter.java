package com.photoeditor.slideshow.my_slide_show.list_category_transit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.photoeditor.slideshow.R;
import com.photoeditor.slideshow.components.MyTranController;
import com.photoeditor.slideshow.my_slide_show.obj.Ratio;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RatioAdapter extends RecyclerView.Adapter<RatioAdapter.RatioHolder> {
    private List<Ratio> ratioList;
    private Context context;
    private MyTranController tranController;

    public RatioAdapter(Context context, List<Ratio> ratioList, MyTranController tranController) {
        this.ratioList = ratioList;
        this.context = context;
        this.tranController = tranController;
    }


    @NonNull
    @Override
    public RatioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ratio, parent, false);
        return new RatioHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RatioHolder holder, int position) {
        holder.onBind((position));
    }

    @Override
    public int getItemCount() {
        return ratioList.size();
    }

    public class RatioHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_ratio_scale)
        TextView txtRatio;

        public RatioHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.txt_ratio_scale)
        void onClick() {
            tranController.changeRatio(ratioList.get(getAdapterPosition()).getVideo_ratio());
        }

        public void onBind(int position) {
            txtRatio.setText(ratioList.get(position).getScale());
//            Glide.with(context)
//                    .applyDefaultRequestOptions(new RequestOptions().skipMemoryCache(true).
//                            diskCacheStrategy(DiskCacheStrategy.NONE))
//                    .load(ratioList.get(position).getResId())
//                    .into(imgTran);
        }
    }
}
