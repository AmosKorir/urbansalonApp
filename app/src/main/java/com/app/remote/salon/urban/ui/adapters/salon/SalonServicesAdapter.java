package com.app.remote.salon.urban.ui.adapters.salon;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.app.remote.domain.constants.Constants;
import com.app.remote.domain.models.Service;
import com.app.remote.salon.urban.R;
import com.app.remote.salon.urban.ui.fragments.sallon.ServicesFragment;
import com.bumptech.glide.Glide;
import java.util.List;

/**
 * Created by Korir on 6/18/19.
 * amoskrr@gmail.com
 */
public class SalonServicesAdapter extends RecyclerView.Adapter<SalonServicesAdapter.MyViewHolder> {
  Context context;
  List<Service> services;
  SalonServiceAdapterInterface salonServiceAdapterInterface;

  public SalonServicesAdapter(Context context, List<Service> services,
      ServicesFragment servicesFragment) {
    this.context = context;
    this.services = services;
    salonServiceAdapterInterface = servicesFragment;
  }

  @NonNull @Override public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    return new MyViewHolder(
        LayoutInflater.from(context).inflate(R.layout.item_salon_service_admin, viewGroup, false));
  }

  @Override public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
    Service service = services.get(i);
    myViewHolder.serviceName.setText(service.getName());
    myViewHolder.amountTv.setText(service.getPrice()+ context.getString(R.string.tail_cash));
    Glide
        .with(context)
        .load(Constants.IMAGE_URL + service.getAvatar())
        .centerCrop()
        .placeholder(R.drawable.image_holder)
        .into(myViewHolder.imageView);
    myViewHolder.more.setOnClickListener(v -> {
        showPopup(service,v);
    });
    switch (service.getStatus()) {
      case "0":
        myViewHolder.availableSwich.setChecked(true);
        break;
      case "1":
        myViewHolder.availableSwich.setChecked(false);
        break;
    }
    myViewHolder.availableSwich.setOnCheckedChangeListener(
        (buttonView, isChecked) -> {
          if (myViewHolder.availableSwich.isChecked()) {
            salonServiceAdapterInterface.setAvailability(service.getServiceid(), "0");
          } else {
            salonServiceAdapterInterface.setAvailability(service.getServiceid(),"1");
          }
        });
  }

  @Override public int getItemCount() {
    return services.size();
  }

  public void showPopup(Service service, View v) {
    PopupMenu popup = new PopupMenu(context, v);
    MenuInflater inflater = popup.getMenuInflater();
    inflater.inflate(R.menu.menu_item_salon_service, popup.getMenu());
    popup.show();

    popup.setOnMenuItemClickListener(item -> {
      int id = item.getItemId();
      if (id == R.id.delete) {
        salonServiceAdapterInterface.setAvailability(service.getServiceid(), "2");
      }
      return false;
    });
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.serviceName) TextView serviceName;
    @BindView(R.id.amount) TextView amountTv;
    @BindView(R.id.availableSwich) Switch availableSwich;
    @BindView(R.id.image) ImageView imageView;
    @BindView(R.id.more) ImageView more;

    public MyViewHolder(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  public interface SalonServiceAdapterInterface {
    void setAvailability(String serviceId, String status);
  }
}
