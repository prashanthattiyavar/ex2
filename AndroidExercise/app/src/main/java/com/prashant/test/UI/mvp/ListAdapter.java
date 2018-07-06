package com.prashant.test.UI.mvp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prashant.test.R;
import com.prashant.test.business.DataObject;
import com.prashant.test.UI.mvp.ItemsList;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ItemsList>{

    private List<DataObject> mDataList;
    private ListContractor.Presenter mPresenter;
    private Context mContext;


    public ListAdapter(ListContractor.Presenter actionPresenter, Context cnt )
    {
        this.mPresenter = actionPresenter;
        this.mContext = cnt;
    }

    @Override
    public ItemsList onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_layout, parent, false);
        return new ItemsList(view, mContext);
    }

    @Override
    public void onBindViewHolder(ItemsList holder, int position) {
        DataObject mData = mDataList.get(holder.getAdapterPosition());
        holder.bind(mData);
        holder.itemView.setOnClickListener(v -> mPresenter.onDataClicked(mData));
    }

    @Override
    public int getItemCount() {
        if (mDataList != null && !mDataList.isEmpty()) {
            return mDataList.size();
        } else {
            return 0;
        }
    }

    public void displayData(List<DataObject> mList) {
        this.mDataList = mList;
    }
}
