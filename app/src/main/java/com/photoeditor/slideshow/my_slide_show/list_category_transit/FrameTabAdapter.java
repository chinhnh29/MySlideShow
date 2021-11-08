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
import com.photoeditor.slideshow.my_slide_show.obj.FrameTab;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FrameTabAdapter extends RecyclerView.Adapter<FrameTabAdapter.FrameTabViewHolder> {
    private List<FrameTab> frameTabs;
    private Context context;
    private MyTranController myTranController;

    public FrameTabAdapter(Context context, List<FrameTab> frameTabs, MyTranController myTranController) {
        this.frameTabs = frameTabs;
        this.context = context;
        this.myTranController = myTranController;
    }

    @NonNull
    @Override
    public FrameTabViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tran_tab, parent, false);
        return new FrameTabViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FrameTabViewHolder holder, int position) {
        holder.onBind((position));
    }

    @Override
    public int getItemCount() {
        return frameTabs.size();
    }

    public class FrameTabViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_name_tab)
        TextView txtTabName;

        public FrameTabViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.txt_name_tab)
        void onClick() {
            myTranController.updateListFrame(frameTabs.get(getAdapterPosition()).getName());
        }

        public void onBind(int position) {
            txtTabName.setText(frameTabs.get(position).getName());
        }
    }
}
