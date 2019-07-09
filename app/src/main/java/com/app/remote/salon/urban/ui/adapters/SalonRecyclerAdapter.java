package com.app.remote.salon.urban.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.app.remote.domain.models.customerOrders.CustomerOrder;
import com.app.remote.salon.urban.R;
import com.app.remote.salon.urban.ui.activities.SalonDetailsActivity;
import java.util.List;

/**
 * Created by Korir on 5/26/19.
 * amoskrr@gmail.com
 */
public class SalonRecyclerAdapter extends RecyclerView.Adapter<SalonRecyclerAdapter.MyViewHolder> {
  private Context context;
  private List<CustomerOrder> customerOrders;

  public SalonRecyclerAdapter(Context context,
      List<CustomerOrder> customerOrders) {
    this.context = context;
    this.customerOrders = customerOrders;
  }

  @NonNull @Override public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(context).inflate(R.layout.item_salon_layout, viewGroup, false);
    return new MyViewHolder(view);
  }

  @Override public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
    myViewHolder.itemView.setOnClickListener(v -> {
      context.startActivity(new Intent(context, SalonDetailsActivity.class));
    });
  }

  @Override public int getItemCount() {
    return customerOrders.size();
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {

    public MyViewHolder(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
