package com.example.lukaszwachowski.capstoneproject.ui.list;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.lukaszwachowski.capstoneproject.R;
import dagger.android.support.AndroidSupportInjection;
import javax.inject.Inject;

public class ListFragment extends Fragment {

  @Inject
  ListAdapter listAdapter;

  @Inject
  ViewModelProvider.Factory factory;

  @BindView(R.id.recycler_view)
  RecyclerView recyclerView;

  private ListFragmentViewModel viewModel;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    AndroidSupportInjection.inject(this);

    viewModel = ViewModelProviders.of(this, factory).get(ListFragmentViewModel.class);
    viewModel.getFeatures();
    subscribeToLiveData();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_list, container, false);
    ButterKnife.bind(this, view);

    recyclerView.setAdapter(listAdapter);

    return view;
  }

  private void subscribeToLiveData() {
    viewModel.getFeaturesLiveData().observe(this, features -> listAdapter.swapData(features));
  }
}
