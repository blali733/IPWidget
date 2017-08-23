package pl.blali733.ipwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import java.util.Map;

public class IPReceiver extends AppWidgetProvider {
    //TODO javadoc
    public void onUpdate(Context ctx, AppWidgetManager appWidgetManager, int[] appWidgetIds){
        final int N = appWidgetIds.length;

        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(ctx.getPackageName(), R.layout.ip_widget);

            Map<Integer, String> conn = Utils.getIPAddress(true);

            if (!conn.get(1).equals("err")) {
                views.setTextViewText(R.id.name, conn.get(1));
                views.setTextViewText(R.id.ip, conn.get(2));
            } else {
                views.setTextViewText(R.id.name, ctx.getString(R.string.conn_name));
                views.setTextViewText(R.id.ip, ctx.getString(R.string.ip_v4));
            }

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}

