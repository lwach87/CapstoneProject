package com.example.lukaszwachowski.capstoneproject.fragments.list;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.lukaszwachowski.capstoneproject.EarthquakeApp;
import com.example.lukaszwachowski.capstoneproject.R;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

public class ListFragment extends Fragment {

  @Inject
  ListAdapter listAdapter;

  @Inject
  ListFragmentViewModelFactory factory;

  @BindView(R.id.recycler_view)
  RecyclerView recyclerView;

  private ListFragmentViewModel viewModel;
  private CompositeDisposable disposable = new CompositeDisposable();

  public ListFragment() {
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    ((EarthquakeApp) getActivity().getApplication()).getComponent().inject(this);

    viewModel = ViewModelProviders.of(this, factory).get(ListFragmentViewModel.class);
    getDataFromDatabase();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_list, container, false);
    ButterKnife.bind(this, view);

    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.setAdapter(listAdapter);

    return view;
  }

  private void getDataFromDatabase() {
    disposable.add(viewModel.getFeatures()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(features -> listAdapter.swapData(features)));
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    disposable.clear();
  }
}
