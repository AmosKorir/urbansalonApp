package com.app.remote.salon.urban.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.app.remote.salon.urban.R;

/**
 * Created by Korir on 5/26/19.
 * amoskrr@gmail.com
 */
public class ServicesRecyclerViewAdapter extends RecyclerView.Adapter<ServicesRecyclerViewAdapter.MyViewHolder> {
  Context context;

  public ServicesRecyclerViewAdapter(Context context) {
    this.context = context;
  }

  @NonNull @Override public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(context).inflate(R.layout.item_salon_service, viewGroup, false);
    return new MyViewHolder(view);
  }

  @Override public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

  }

  @Override public int getItemCount() {
    return 5;
  }

  public  class MyViewHolder extends RecyclerView.ViewHolder{

      public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
      }
    }
}
