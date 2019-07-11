package com.app.remote.salon.urban.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.app.remote.data.BuildConfig;
import com.app.remote.domain.models.Service;
import com.app.remote.salon.urban.R;
import com.bumptech.glide.Glide;
import java.util.List;

/**
 * Created by Korir on 5/26/19.
 * amoskrr@gmail.com
 */
public class ServicesRecyclerViewAdapter
    extends RecyclerView.Adapter<ServicesRecyclerViewAdapter.MyViewHolder> {
  private Context context;
  private OrderInterface orderInterface;
  private List<Service> services;
  private int layout;

  public ServicesRecyclerViewAdapter(Context context, List<Service> services,
      OrderInterface orderInterface, int item_recommended_layout) {
    this.context = context;
    this.services = services;
    this.orderInterface = orderInterface;
    this.layout=item_recommended_layout;
  }

  @NonNull @Override public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(context).inflate(layout, viewGroup, false);
    return new MyViewHolder(view);
  }

  @Override public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
    Service model = services.get(i);
    Glide
        .with(context)
        .load(BuildConfig.BASE_URL+"view/images/"+model.getAvatar())
        .centerCrop()
        .placeholder(R.drawable.image_holder)
        .into(myViewHolder.imageView);
    myViewHolder.amount.setText(model.getPrice());
    myViewHolder.serviceName.setText(model.getName());
    myViewHolder.itemView.setOnClickListener(v -> {
      orderInterface.serviceId(model.getServiceid());
    });
  }

  @Override public int getItemCount() {
    return services.size();
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {
    //@BindView(R.id.bookbtn) TextView bookBtn;
    @BindView(R.id.serviceName) TextView serviceName;
    @BindView(R.id.amount) TextView amount;
    @BindView(R.id.image) ImageView imageView;

    public MyViewHolder(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  public interface OrderInterface {
    void serviceId(String serviceid);
  }
}
