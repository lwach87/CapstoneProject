package com.example.lukaszwachowski.capstoneproject.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.lukaszwachowski.capstoneproject.R;
import com.example.lukaszwachowski.capstoneproject.fragments.details.DetailsFragment;
import com.example.lukaszwachowski.capstoneproject.fragments.list.ListFragment;
import com.example.lukaszwachowski.capstoneproject.fragments.map.MapFragment;

public class PagerAdapter extends FragmentPagerAdapter {

  private Context context;

  public PagerAdapter(Context context, FragmentManager fm) {
    super(fm);
    this.context = context;
  }

  @Override
  public Fragment getItem(int position) {
    switch (position) {
      case 0:
        return new MapFragment();
      case 1:
        return new ListFragment();
      case 2:
        return new DetailsFragment();
    }
    return null;
  }

  @Override
  public int getCount() {
    return 3;
  }

  @Override
  public CharSequence getPageTitle(int position) {
    switch (position) {
      case 0:
        return context.getString(R.string.action_map);
      case 1:
        return context.getString(R.string.action_list);
      case 2:
        return context.getString(R.string.action_details);
    }
    return null;
  }
}
