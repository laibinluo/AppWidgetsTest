package clicktest;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.w3c.dom.Comment;

import com.example.clicktest.R;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import android.content.BroadcastReceiver;

public class UpdateUIService extends Service {
	public final String TAG = "UpdateUIService";
	public static Context context;
	public static AppWidgetManager appWidgetManager;
	private SimpleDateFormat df = new SimpleDateFormat("HHmmss");
	public static RemoteViews remoteViews; 
//	private int [] viewId = new int[]{
//			R.id.hour01,
//			R.id.hour02,
//			R.id.minute01,
//			R.id.minute02
//	};
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.v(TAG, "onCreate");
		
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(Intent.ACTION_TIME_TICK);
		intentFilter.addAction(Intent.ACTION_TIME_CHANGED);
		registerReceiver(broadreceiver, intentFilter);
	}

	private BroadcastReceiver broadreceiver = new BroadcastReceiver(){
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			Log.v(TAG, "broadreceiver::onReceive");
			UpdateUI();
		}
	};
	
	private void UpdateUI() {
		// TODO Auto-generated method stub
		Log.v(TAG, "updateUI");
		String timeString = df.format(new Date());
		//int num;

		remoteViews.setTextViewText(R.id.viewId, timeString);
		ComponentName componentName = new ComponentName(context, DesktopClock.class);
		appWidgetManager.updateAppWidget(componentName, remoteViews);
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(broadreceiver);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
