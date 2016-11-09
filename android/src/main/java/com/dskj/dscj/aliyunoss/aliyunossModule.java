package com.dskj.lesonli.aliyunoss;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import com.facebook.react.bridge.ReadableMap;

import java.lang.String;
import android.util.Log;
/**
 * Created by lesonli on 2016/10/31.
 */

public class aliyunossModule extends ReactContextBaseJavaModule {

    public aliyunossModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }
    @Override
    public String getName() {
        return "AliyunOSS";
    }

    @ReactMethod
	public void testPrint(String name, ReadableMap info) {
		Log.i("DEBUG", name);
		Log.i("DEBUG", info.toString());
	}
}
