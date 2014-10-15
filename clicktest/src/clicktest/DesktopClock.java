package clicktest;

import com.example.clicktest.R;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class DesktopClock extends AppWidgetProvider {
	private final String TAG = "UpdateUIService";
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
		Log.v(TAG, "onDeleted");

        Intent intent = new Intent(context, UpdateUIService.class);  
        context.stopService(intent);  
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		Log.v(TAG, "onUpdate");
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.main);
		//UpdateUIService.remoteViews = remoteViews;
		UpdateUIService.appWidgetManager = appWidgetManager;
		UpdateUIService.context = context;
		
//		Intent intent = new Intent(context, UpdateUIService.class);
//		
//		context.startService(intent);
		Intent intent = new Intent(context, UpdateUIService.class);
		context.startService(intent);
		Log.v(TAG, "====onUpdate");
	}

}
