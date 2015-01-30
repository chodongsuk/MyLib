package kr.ds.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * 문자 받기
 * @author Chodongsuk
 * @since 20150130
 * <uses-permission android:name="android.permission.RECEIVE_SMS" />
 * 
 * private BroadcastReceiver mSmsReveiver;
 * mSmsReveiver = new DsSmsReceiver(new SmsMessageListener() {
			
			@Override
			public void getOriginatingAddress(String string) {
				// TODO Auto-generated method stub
				Log.i(TAG, "전화번호:"+string+"");
			}
			
			@Override
			public void getMessageBody(String string) {
				// TODO Auto-generated method stub
				Log.i(TAG, "내용:"+string+"");
			}
		});
  registerReceiver(mSmsReveiver, DsSmsReceiver.SMS_INTENT_FILTER);
  unregisterReceiver(mSmsReveiver);
 */
public class DsSmsReceiver extends BroadcastReceiver{
	private static final String TAG = DsSmsReceiver.class.getSimpleName();
	public static final IntentFilter SMS_INTENT_FILTER = new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
	private SmsMessageListener mSmsMessageListener;
	
	public DsSmsReceiver() {
    }
	public DsSmsReceiver(SmsMessageListener listener) {
        mSmsMessageListener = listener;
    }
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String body = "";//메세지
		String address = "";//전화번호
		final Bundle extra = intent.getExtras();
		if (extra == null) {
            return;
        }
		if(mSmsMessageListener == null) {
            return;
        }
		// PDU : sms 메세지의 산업 포맷
		Object[] pdusObj = (Object[]) extra.get("pdus");
		if(pdusObj == null){
			return;
		}
		SmsMessage[] smsManagers = new SmsMessage[pdusObj.length];
		for(int i=0; i < smsManagers.length; i++){
			//Object 배열 (pdu)에 담겨있는 메세지를 byte[]로 캐스팅하여 smsMessage에 담음
			smsManagers[i] = SmsMessage.createFromPdu((byte[]) (pdusObj[i]));
			address += smsManagers[i].getOriginatingAddress();
			body += smsManagers[i].getMessageBody();
			body += "\n";
		}
		mSmsMessageListener.getOriginatingAddress(address);
		mSmsMessageListener.getMessageBody(body);
	}
	public interface SmsMessageListener {
		public void getOriginatingAddress(String string);
		public void getMessageBody(String string);
	}
}
