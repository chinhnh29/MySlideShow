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
import com.photoeditor.slideshow.my_slide_show.obj.DataCategoryTrans;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryTransitAdapter extends RecyclerView.Adapter<CategoryTransitAdapter.CategoryViewHolder> {
    private List<DataCategoryTrans> dataCategoryList;
    private Context context;
    private MyTranController myTranController;

    public CategoryTransitAdapter(Context context, List<DataCategoryTrans> dataCategoryList, MyTranController myTranController) {
        this.dataCategoryList = dataCategoryList;
        this.context = context;
        this.myTranController = myTranController;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tran_tab, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.onBind((position));
    }

    @Override
    public int getItemCount() {
        return dataCategoryList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_name_tab)
        TextView txtTabName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.txt_name_tab)
        void onClick() {
            myTranController.updateListTransit(dataCategoryList.get(getAdapterPosition()).getName());
        }

        public void onBind(int position) {
            txtTabName.setText(dataCategoryList.get(position).getName());
        }
    }
}
