package com.example.lukaszwachowski.capstoneproject.ui.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.lukaszwachowski.capstoneproject.R;
import com.example.lukaszwachowski.capstoneproject.data.model.Feature;
import com.example.lukaszwachowski.capstoneproject.utils.FeatureDiffUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.DataViewHolder> {

  private List<Feature> features = new ArrayList<>();
  private Context context;

  public ListAdapter(Context context) {
    this.context = context;
  }

  @NonNull
  @Override
  public ListAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context)
        .inflate(R.layout.single_data, parent, false);
    return new DataViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {

    holder.place.setText(features.get(position).getProperties().getPlace());
    holder.magnitude.setText(String.valueOf(features.get(position).getProperties().getMag()));
    holder.mag_type.setText(features.get(position).getProperties().getMagType());
    holder.significance.setText(String.valueOf(features.get(position).getProperties().getSig()));

    String mAlert = features.get(position).getProperties().getAlert();
    setAlert(mAlert, holder);

    long mTime = features.get(position).getProperties().getDate();
    setDate(mTime, holder);
  }

  private void setDate(long mTime, DataViewHolder holder) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    holder.date.setText(sdf.format(mTime));
  }

  private void setAlert(String mAlert, DataViewHolder holder) {

    if (mAlert == null) {
      holder.alert.setText(R.string.nullAlert);
      holder.alert.setTextColor(ContextCompat.getColor(context, R.color.grayAlert));
    } else {
      switch (mAlert) {
        case "green":
          holder.alert.setText(mAlert);
          holder.alert.setTextColor(ContextCompat.getColor(context, R.color.greenAlert));
          break;
        case "yellow":
          holder.alert.setText(mAlert);
          holder.alert.setTextColor(ContextCompat.getColor(context, R.color.yellowAlert));
          break;
        case "orange":
          holder.alert.setText(mAlert);
          holder.alert.setTextColor(ContextCompat.getColor(context, R.color.orangeAlert));
          break;
        case "red":
          holder.alert.setText(mAlert);
          holder.alert.setTextColor(ContextCompat.getColor(context, R.color.redAlert));
          break;
      }
    }
  }

  @Override
  public int getItemCount() {
    return features.size();
  }

  public class DataViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_place)
    TextView place;

    @BindView(R.id.tv_magnitude)
    TextView magnitude;

    @BindView(R.id.tv_mag_type)
    TextView mag_type;

    @BindView(R.id.tv_alert)
    TextView alert;

    @BindView(R.id.tv_significance)
    TextView significance;

    @BindView(R.id.tv_date)
    TextView date;

    public DataViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }

  public void swapData(List<Feature> newList) {
    DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new FeatureDiffUtil(features, newList));
    features.clear();
    features.addAll(newList);
    diffResult.dispatchUpdatesTo(this);
  }
}
