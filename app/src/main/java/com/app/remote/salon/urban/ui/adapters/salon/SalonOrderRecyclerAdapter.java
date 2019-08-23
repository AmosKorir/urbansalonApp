package com.app.remote.salon.urban.ui.adapters.salon;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.app.remote.data.BuildConfig;
import com.app.remote.domain.constants.Constants;
import com.app.remote.domain.models.customerOrders.CustomerOrder;
import com.app.remote.domain.utils.TimeAgo;
import com.app.remote.salon.urban.R;
import com.app.remote.salon.urban.ui.fragments.sallon.OrderSFragment;
import com.bumptech.glide.Glide;
import java.util.List;

/**
 * Created by Korir on 6/24/19.
 * amoskrr@gmail.com
 */
public class SalonOrderRecyclerAdapter
    extends RecyclerView.Adapter<SalonOrderRecyclerAdapter.MyViewHolder> {
  SalonOrderInterface salonOrderInterface;
  private Context context;
  List<CustomerOrder> orderModels;

  public SalonOrderRecyclerAdapter(OrderSFragment orderSFragment, Context context,
      List<CustomerOrder> orderModels) {
    this.salonOrderInterface = (SalonOrderInterface) orderSFragment;
    this.context = context;
    this.orderModels = orderModels;
  }

  @NonNull @Override public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    return new MyViewHolder(
        LayoutInflater.from(context).inflate(R.layout.item_salon_order_layout, viewGroup, false));
  }

  @Override public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
    CustomerOrder model = orderModels.get(i);
    Glide.with(context)
        .load(Constants.IMAGE_URL + model.getService().getAvatar())
        .centerCrop()
        .placeholder(R.drawable.image_holder)
        .into(myViewHolder.avatar);
    myViewHolder.serviceNameTV.setText(model.getService().getName());
    myViewHolder.priceTv.setText(model.getService().getPrice().toString() + context.getString(R.string.tail_cash));
    myViewHolder.dateTv.setText(TimeAgo.covertTimeToText(model.getDatebooked()));
    myViewHolder.timeTv.setText(model.getTimebooked());
    myViewHolder.nameTv.setText(model.getCustomer().getName());
    myViewHolder.more.setOnClickListener(v-> showPopup(model,v));
  }

  @Override public int getItemCount() {
    return orderModels.size();
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.more) ImageView more;
    @BindView(R.id.name)TextView nameTv;
    @BindView(R.id.avatar) ImageView avatar;
    @BindView(R.id.serviceName) TextView serviceNameTV;
    @BindView(R.id.price) TextView priceTv;
    @BindView(R.id.date) TextView dateTv;
    @BindView(R.id.time) TextView timeTv;

    public MyViewHolder(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
  public void showPopup(CustomerOrder customerOrder, View v) {
    PopupMenu popup = new PopupMenu(context, v);
    MenuInflater inflater = popup.getMenuInflater();
    inflater.inflate(R.menu.menu_item_salon_option, popup.getMenu());
    popup.show();

    popup.setOnMenuItemClickListener(item -> {
      int id = item.getItemId();
      if (id == R.id.cancel_action) {
        salonOrderInterface.setOrderStatus(customerOrder,3);
      }else if (id==R.id.accept_action){
        salonOrderInterface.setOrderStatus(customerOrder,1);
      }else if(id==R.id.close_action){
        salonOrderInterface.setOrderStatus(customerOrder,2);
      }
      return false;
    });
  }

  public interface SalonOrderInterface {
    void repondOrder(String status);

    void setOrderStatus(CustomerOrder customerOrder, int i);
  }
}
