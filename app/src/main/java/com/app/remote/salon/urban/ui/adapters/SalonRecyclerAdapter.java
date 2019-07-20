package com.app.remote.salon.urban.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.app.remote.domain.constants.Constants;
import com.app.remote.domain.models.SalonModel;
import com.app.remote.salon.urban.R;
import com.app.remote.salon.urban.ui.activities.SalonDetailsActivity;
import java.util.List;

/**
 * Created by Korir on 5/26/19.
 * amoskrr@gmail.com
 */
public class SalonRecyclerAdapter extends RecyclerView.Adapter<SalonRecyclerAdapter.MyViewHolder> {
  private Context context;
  private List<SalonModel> salonModels;

  public SalonRecyclerAdapter(Context context,
      List<SalonModel> salonModels) {
    this.context = context;
    this.salonModels = salonModels;
  }

  @NonNull @Override public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(context).inflate(R.layout.item_salon_layout, viewGroup, false);
    return new MyViewHolder(view);
  }

  @Override public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
    myViewHolder.itemView.setOnClickListener(v -> {

      context.startActivity(new Intent(context, SalonDetailsActivity.class)
          .putExtra(Constants.SALON_MODE,salonModels.get(i))
      );
    });
  }

  @Override public int getItemCount() {
    return salonModels.size();
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {

    public MyViewHolder(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
