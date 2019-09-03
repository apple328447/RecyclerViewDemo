package com.example.recyclerview_demo.dataAdapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.recyclerview_demo.R;
import com.example.recyclerview_demo.json.DataOutput;

import java.util.ArrayList;

/**
 * 步驟二 繼承
 */
public class dataAdapter_java extends RecyclerView.Adapter<dataAdapter_java.ViewHolder> {
    /**
     * 步驟三 建立Json格式的class
     */
    ArrayList<DataOutput.DepositDetail> mdata;

    public dataAdapter_java(ArrayList<DataOutput.DepositDetail> mdata) {
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        /**
         * 這個是RecyclerView的內容物
         */
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.content_detail, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.time.setText(mdata.get(position).getCreateTime());
        viewHolder.money.setText(mdata.get(position).getAmount());
        switch (mdata.get(position).getDetail()){
            case 50:
                viewHolder.detail.setText("利息");
                break;
            case 51:
                viewHolder.detail.setText("转入");
                break;
            case 52:
                viewHolder.detail.setText("转出");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    /**
     * 步驟一 建立ViewHolder
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout Lyt;
        TextView time;
        TextView money;
        TextView detail;
        View line;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Lyt=itemView.findViewById(R.id.deposit_detail_CsnLyt);
            time=itemView.findViewById(R.id.deposit_detail_time);
            money=itemView.findViewById(R.id.deposit_detail_money);
            detail=itemView.findViewById(R.id.deposit_detail_detail);
            line=itemView.findViewById(R.id.deposit_detail_detail_line);
        }
    }
}
