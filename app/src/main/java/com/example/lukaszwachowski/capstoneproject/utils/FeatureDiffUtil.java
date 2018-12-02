package com.example.lukaszwachowski.capstoneproject.utils;

import android.support.v7.util.DiffUtil;
import com.example.lukaszwachowski.capstoneproject.data.model.Feature;
import java.util.List;

public class FeatureDiffUtil extends DiffUtil.Callback {

  private List<Feature> oldList;
  private List<Feature> newList;

  public FeatureDiffUtil(List<Feature> oldList, List<Feature> newList) {
    this.oldList = oldList;
    this.newList = newList;
  }

  @Override
  public int getOldListSize() {
    return oldList.size();
  }

  @Override
  public int getNewListSize() {
    return newList.size();
  }

  @Override
  public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
    return oldList.get(oldItemPosition).getId().equals(newList.get(newItemPosition).getId());
  }

  @Override
  public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
    return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
  }
}
