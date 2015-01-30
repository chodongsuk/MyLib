package kr.ds.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
/**
 * 샘플 브로드캐스트
 * 
 * @author Administrator
 * @since 2015.01.30
 * private BroadcastReceiver mSampleReveiver;
 * mSampleReveiver = new DsSampleReceiver(new SampleMessageListener() {
			
			@Override
			public void OnMessage(String string) {
				// TODO Auto-generated method stub
				Log.i("TEST","메세지:"+string+"");
			}
		});
	registerReceiver(mSampleReveiver, DsSampleReceiver.ACTION_INTENT_FILTER);
        
		
	DsSampleReceiver.sendBroadcast(getApplicationContext(), "메세지");
 */
public class DsSampleReceiver extends BroadcastReceiver{
	private final static String MESSAGE = "message";
	private final static String ACTION = "kr.ds.samplereceiver";
	public static final IntentFilter ACTION_INTENT_FILTER = new IntentFilter("kr.ds.samplereceiver");
	
	private SampleMessageListener mSampleMessageListener;
	
	public DsSampleReceiver() {
    }
	public DsSampleReceiver(SampleMessageListener listener) {
		mSampleMessageListener = listener;
    }
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		final Bundle extra = intent.getExtras();
		if (extra == null) {
            return;
        }
		if(mSampleMessageListener == null) {
            return;
        }
		
		String message = extra.getString(MESSAGE);
		mSampleMessageListener.OnMessage(message);
	}
	
	public static void sendBroadcast(Context context, String message){
		Intent intent = new Intent(ACTION);   
		intent.putExtra(MESSAGE, message); 
		context.sendBroadcast(intent);    
	}
	public interface SampleMessageListener {
		public void OnMessage(String string);
	}

}
