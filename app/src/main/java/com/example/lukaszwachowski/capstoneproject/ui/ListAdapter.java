package com.example.lukaszwachowski.capstoneproject.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.lukaszwachowski.capstoneproject.R;
import com.example.lukaszwachowski.capstoneproject.helper.FeatureDiffUtil;
import com.example.lukaszwachowski.capstoneproject.network.model.Feature;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    View view = LayoutInflater.from(context).inflate(R.layout.single_data, parent, false);
    return new DataViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {

    holder.place.setText(features.get(position).properties.place);
    holder.magnitude.setText(String.valueOf(features.get(position).properties.mag));
    holder.mag_type.setText(features.get(position).properties.magType);
    holder.significance.setText(String.valueOf(features.get(position).properties.sig));

//    String mAlert = features.get(position).properties.alert;
    holder.alert.setText("none");

    Long mTime = features.get(position).properties.time;
    setDate(mTime, holder);
  }

  private void setDate(Long mTime, DataViewHolder holder) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    holder.date.setText(sdf.format(new Date(mTime)));
  }

  @Override
  public int getItemCount() {
    return features.size();
  }

  public class DataViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_view_place)
    TextView place;

    @BindView(R.id.text_view_magnitude)
    TextView magnitude;

    @BindView(R.id.text_view_mag_type)
    TextView mag_type;

    @BindView(R.id.text_view_alert)
    TextView alert;

    @BindView(R.id.text_view_significance)
    TextView significance;

    @BindView(R.id.text_view_date)
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
