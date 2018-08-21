package com.example.lukaszwachowski.capstoneproject.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import com.example.lukaszwachowski.capstoneproject.R;
import com.example.lukaszwachowski.capstoneproject.network.model.Feature;
import com.example.lukaszwachowski.capstoneproject.ui.MainActivity;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class WidgetProvider extends AppWidgetProvider {

  static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
      Feature feature, int appWidgetId) {

    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
        new Intent(context, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

    RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.earthquake_app_widget);
    views.setTextViewText(R.id.widget_title, feature.properties.title);

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

    views.setTextViewText(R.id.widget_date, sdf.format(feature.properties.date));
    views.setTextViewText(R.id.widget_magnitude, String.valueOf(feature.properties.mag));
    views.setTextViewText(R.id.widget_magnitude_type, feature.properties.magType);
    views.setTextViewText(R.id.widget_significance, String.valueOf(feature.properties.sig));
    views.setTextViewText(R.id.widget_alert, String.valueOf(feature.properties.alert));
    views.setOnClickPendingIntent(R.id.feature_widget_holder, pendingIntent);

    appWidgetManager.updateAppWidget(appWidgetId, views);
  }

  public static void updateRecipeWidgets(Context context, AppWidgetManager appWidgetManager,
      Feature feature, int[] appWidgetIds) {
    for (int appWidgetId : appWidgetIds) {
      updateAppWidget(context, appWidgetManager, feature, appWidgetId);
    }
  }

  @Override
  public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
  }

  @Override
  public void onEnabled(Context context) {
  }

  @Override
  public void onDisabled(Context context) {
  }
}
