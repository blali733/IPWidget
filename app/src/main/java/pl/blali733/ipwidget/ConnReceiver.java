package pl.blali733.ipwidget;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.Map;

public class ConnReceiver extends BroadcastReceiver {
    //TODO javadoc
    //TODO Investigate reason why this code stops working after screen restart.
    @Override
    public void onReceive(final Context context, final Intent intent) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ip_widget);
        ComponentName thisWidget = new ComponentName(context, IPReceiver.class);
        Map<Integer,String> conn = Utils.getIPAddress(true);

        if(!conn.get(1).equals("err")) {
            views.setTextViewText(R.id.name, conn.get(1));
            views.setTextViewText(R.id.ip, conn.get(2));
        }else{
            views.setTextViewText(R.id.name, context.getString(R.string.conn_name));
            views.setTextViewText(R.id.ip, context.getString(R.string.ip_v4));
        }
        appWidgetManager.updateAppWidget(thisWidget, views);
    }
}
