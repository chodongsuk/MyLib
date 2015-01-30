package kr.ds.receiver;

import kr.ds.receiver.DsNetworkStateReceiver.NetworkStateListener;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
/**
 * 재부팅 완료 후 실행 
 * @author Chodongsuk
 * @since 20150130
 * <receiver
android:name=".DsBootReceiver"
android:permission="android.permission.RECEIVE_BOOT_COMPLETED" >
<intent-filter>
<action android:name="android.intent.action.BOOT_COMPLETED" />
</intent-filter>
</receiver>
*/
public class DsBootStateReceiver extends BroadcastReceiver{
	private static final String TAG = DsBootStateReceiver.class.getSimpleName();
	private static final String BOOT_COMPLETED = "android.intent.action.BOOT_COMPLETED";
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		final Bundle extra = intent.getExtras();
        if (extra == null) {
            return;
        }
		if (intent.getAction().equals(BOOT_COMPLETED)) {
			/*
			 * 실행 
			 */
		}
	}
}
