package kr.ds.dialog;

import kr.ds.mylib.R;
import kr.ds.utils.DsDebugUtils;
import kr.ds.utils.DsObjectUtils;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 질문관련 다이얼로그
 * @author Chodongsuk
 * @since 2015.02.02
 * 
 * DsFaqDialogFragment mDsAlertDialogFragment = new DsFaqDialogFragment(new ResultListener() {
				
				@Override
				public void onSuccess(String message) {
					// TODO Auto-generated method stub
					DsDebugUtils.Message(message);
				}
				
				@Override
				public void onCancle() {
					// TODO Auto-generated method stub
					
				}
			});
			mDsAlertDialogFragment.setView(mContext);
			mDsAlertDialogFragment.setTargetFragment(this, 0);
			mDsAlertDialogFragment.show(getActivity().getSupportFragmentManager(),"dialog");
 */
public class DsDialogFaqFragment extends DialogFragment {
	private static final String TAG = DsDialogFaqFragment.class.getSimpleName();
	private ResultListener mResultListener;
	private View mView;
	
	public DsDialogFaqFragment(){
	}
	public DsDialogFaqFragment setView(Context context){
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		mView = inflater.inflate(R.layout.ds_dialog_faq, null);
		return this;
	}
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	        try {
	        	mResultListener = (ResultListener) getTargetFragment();
			} catch (Exception e) {
				// TODO: handle exception
			}
    }
	public DsDialogFaqFragment(ResultListener listener) {
		mResultListener = listener;
	}
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
		final EditText mEditTextFaq = (EditText)mView.findViewById(R.id.ds_editText_faq);
		Button mButtonFaqSuccess = (Button)mView.findViewById(R.id.ds_button_faq_success);
		Button mButtonFaqCancel = (Button)mView.findViewById(R.id.ds_button_faq_cancel);
		
		mButtonFaqSuccess.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mResultListener != null ){
					if(!DsObjectUtils.isEmpty(mEditTextFaq.getText().toString())){
						mResultListener.onSuccess(mEditTextFaq.getText().toString());
						dismiss();
					}
				}
			}
		});
		mButtonFaqCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mResultListener != null){
					mResultListener.onCancle();
					dismiss();
				}
			}
		});
		
		mBuilder.setView(mView);
		return mBuilder.create();
	}
	public interface ResultListener {
		public void onSuccess(String message);
		public void onCancle();
	}
}
