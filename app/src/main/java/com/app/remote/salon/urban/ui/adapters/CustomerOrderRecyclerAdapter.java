package com.app.remote.salon.urban.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.app.remote.domain.constants.Constants;
import com.app.remote.domain.models.customerOrders.CustomerOrder;
import com.app.remote.domain.utils.DateTimeUtils;
import com.app.remote.salon.urban.R;
import com.bumptech.glide.Glide;
import java.util.List;

/**
 * Created by Korir on 7/6/19.
 * amoskrr@gmail.com
 */
public class CustomerOrderRecyclerAdapter
    extends RecyclerView.Adapter<CustomerOrderRecyclerAdapter.MyViewHolder> {
  private List<CustomerOrder> customerOrders;
  private Context context;

  private customerOrderAdapterInterface customerOrderAdapterInterface;

  public CustomerOrderRecyclerAdapter(
      List<CustomerOrder> customerOrders, Context context) {
    this.customerOrders = customerOrders;
    this.context = context;
  }

  public CustomerOrderRecyclerAdapter.customerOrderAdapterInterface getCustomerOrderAdapterInterface() {
    return customerOrderAdapterInterface;
  }

  public void setCustomerOrderAdapterInterface(
      CustomerOrderRecyclerAdapter.customerOrderAdapterInterface customerOrderAdapterInterface) {
    this.customerOrderAdapterInterface = customerOrderAdapterInterface;
  }

  @NonNull @Override public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view =
        LayoutInflater.from(context).inflate(R.layout.item_customer_order, viewGroup, false);
    return new MyViewHolder(view);
  }

  @Override public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
    CustomerOrder model = customerOrders.get(i);
    myViewHolder.serviceName.setText(model.getService().getName());
    myViewHolder.orderNumber.setText(model.getOrderno());
    myViewHolder.salonNameTv.setText(model.getService().getSalon().getName());
    myViewHolder.priceTv.setText(model.getService().getPrice().toString());
    myViewHolder.timeTv.setText(model.getTimebooked());
    myViewHolder.dateTv.setText(DateTimeUtils.formatDate(model.getDatebooked()));
    myViewHolder.statusTv.setText(model.getStatus().toString());
    Glide.with(context)
        .load(Constants.IMAGE_URL + model.getService().getAvatar())
        .centerCrop()
        .placeholder(R.drawable.image_holder)
        .into(myViewHolder.avatar);
    myViewHolder.more.setOnClickListener(v -> {
      showPopup(model, v);
    });
    switch (model.getStatus()) {
      case 0:
        myViewHolder.statusTv.setText("Pending");
        myViewHolder.statusTv.setTextColor(context.getResources().getColor(R.color.colorAccent));
        break;
      case 1:
        myViewHolder.statusTv.setText("Active");
        myViewHolder.statusTv.setTextColor(context.getResources().getColor(R.color.colorAccent));
        break;
      case 2:
        myViewHolder.statusTv.setText("Rejected");
        myViewHolder.statusTv.setTextColor(context.getResources().getColor(R.color.colorAccent));
        break;
      case 3:
        myViewHolder.statusTv.setText("closed");
        myViewHolder.statusTv.setTextColor(context.getResources().getColor(R.color.colorAccent));
        break;


    }
  }

  public void showPopup(CustomerOrder customerOrder, View v) {
    PopupMenu popup = new PopupMenu(context, v);
    MenuInflater inflater = popup.getMenuInflater();
    inflater.inflate(R.menu.menu_item_option, popup.getMenu());
    popup.show();

    popup.setOnMenuItemClickListener(item -> {
      int id = item.getItemId();
      if (id == R.id.cancel_action) {
        customerOrderAdapterInterface.cancelOrder(customerOrder, "2");
      }
      if (id == R.id.rate){

          customerOrderAdapterInterface.rateService(customerOrder.getServiceid());
      }
      return false;
    });
  }

  @Override public int getItemCount() {
    return customerOrders.size();
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.salonName) TextView salonNameTv;
    @BindView(R.id.status) TextView statusTv;
    @BindView(R.id.service) TextView serviceName;
    @BindView(R.id.date) TextView dateTv;
    @BindView(R.id.time) TextView timeTv;
    @BindView(R.id.orderNumber) TextView orderNumber;
    @BindView(R.id.avatar) ImageView avatar;
    @BindView(R.id.more) ImageView more;
    @BindView(R.id.price) TextView priceTv;

    public MyViewHolder(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  public interface customerOrderAdapterInterface {
    void cancelOrder(CustomerOrder customerOrder, String status);

    void rateService(String serviceId);
  }
}
