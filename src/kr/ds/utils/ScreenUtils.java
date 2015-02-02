package kr.ds.utils;

import kr.ds.mylib.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;

public class ScreenUtils {

	public static int screenWidthPx = 0;

	public static int screenHeightPx = 0;

	public static float densityPx = 0;

	private static ScreenUtils screenUtils = null;

	public ScreenUtils() {
	}

	public static ScreenUtils getInstance() {
		if (screenUtils == null) {
			synchronized (ScreenUtils.class) {
				if (screenUtils == null) {
					screenUtils = new ScreenUtils();
				}
			}
		}

		return screenUtils;
	}

	@SuppressLint("NewApi")
	public static void initScreenSize(Context mContext) {
		final DisplayMetrics dm = new DisplayMetrics();
		if (android.os.Build.VERSION.SDK_INT >= 17) {
			((Activity) mContext).getWindowManager().getDefaultDisplay()
					.getRealMetrics(dm);
			screenWidthPx = dm.widthPixels;
			screenHeightPx = dm.heightPixels;
			densityPx = dm.density;
		} else {
			((Activity) mContext).getWindowManager().getDefaultDisplay()
					.getMetrics(dm);
			screenWidthPx = dm.widthPixels;
			screenHeightPx = dm.heightPixels;
			densityPx = dm.density;
		}
	}

	public int getScreenWidth() {
		return screenWidthPx;
	}

	public static int dipConvertPx(int size) {
		return (int) (size * densityPx);
	}

	public static int VersionCode(Context context) {
		int VersionCode = 1;
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo packageInfo = pm.getPackageInfo(
					context.getPackageName(), 0);
			VersionCode = packageInfo.versionCode; // 버전
			return VersionCode;

		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static String VersionName(Context context) {
		String VersionName = null;
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo packageInfo = pm.getPackageInfo(
					context.getPackageName(), 0);
			VersionName = packageInfo.versionName; // 버전 네임
			return VersionName;

		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
