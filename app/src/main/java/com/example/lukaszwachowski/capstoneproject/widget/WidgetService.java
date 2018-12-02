package com.example.lukaszwachowski.capstoneproject.widget;

import static com.example.lukaszwachowski.capstoneproject.utils.Constants.FEATURE_WIDGET_ACTION_UPDATE;
import static com.example.lukaszwachowski.capstoneproject.utils.Constants.FEATURE_WIDGET_DATA;
import static com.example.lukaszwachowski.capstoneproject.utils.Constants.WIDGET_SERVICE;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.example.lukaszwachowski.capstoneproject.data.model.Feature;

public class WidgetService extends IntentService {

  public WidgetService() {
    super(WIDGET_SERVICE);
  }

  public static void startActionUpdateRecipeWidgets(Context context, Feature feature) {
    Intent intent = new Intent(context, WidgetService.class);
    intent.setAction(FEATURE_WIDGET_ACTION_UPDATE);
    intent.putExtra(FEATURE_WIDGET_DATA, feature);
    context.startService(intent);
  }

  @Override
  protected void onHandleIntent(Intent intent) {
    if (intent != null) {
      final String action = intent.getAction();
      if (FEATURE_WIDGET_ACTION_UPDATE.equals(action) &&
          intent.getParcelableExtra(FEATURE_WIDGET_DATA) != null) {
        handleActionUpdateWidgets(intent.getParcelableExtra(FEATURE_WIDGET_DATA));
      }
    }
  }

  private void handleActionUpdateWidgets(Feature feature) {
    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
    int[] appWidgetIds = appWidgetManager
        .getAppWidgetIds(new ComponentName(this, WidgetProvider.class));
    WidgetProvider.updateRecipeWidgets(this, appWidgetManager, feature, appWidgetIds);
  }
}
