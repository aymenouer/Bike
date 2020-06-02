package com.codename1.ext.codescan;

import android.app.Activity;
import android.content.Intent;
import com.codename1.impl.android.AndroidNativeUtil;
import com.codename1.impl.android.CodenameOneActivity;
import com.codename1.impl.android.IntentIntegrator;
import com.codename1.impl.android.IntentResultListener;
import com.codename1.ui.Display;
import java.util.Arrays;
import java.util.Collection;

public class NativeCodeScannerImpl implements IntentResultListener {
    
        public void scanQRCode() {
            Activity activity = AndroidNativeUtil.getActivity();
            if (activity instanceof CodenameOneActivity) {
                ((CodenameOneActivity) activity).setIntentResultListener(this);
            }
            //this.callback = callback;
            IntentIntegrator in = new IntentIntegrator(activity);
            if(!in.initiateScan(IntentIntegrator.QR_CODE_TYPES, "QR_CODE_MODE")){
                CodeScanner.scanErrorCallback(-1, "no scan app");
            
                if (activity instanceof CodenameOneActivity) {
                    ((CodenameOneActivity) activity).restoreIntentResultListener();
                }
            }
        }

        public void scanBarCode() {
            Activity activity = AndroidNativeUtil.getActivity();
            if (activity instanceof CodenameOneActivity) {
                ((CodenameOneActivity) activity).setIntentResultListener(this);
            }
            IntentIntegrator in = new IntentIntegrator(activity);
            Collection<String> types = IntentIntegrator.PRODUCT_CODE_TYPES;
            if(Display.getInstance().getProperty("scanAllCodeTypes", "false").equals("true")) {
                types = IntentIntegrator.ALL_CODE_TYPES;
            } 
            if(Display.getInstance().getProperty("android.scanTypes", null) != null) {
                String[] arr = Display.getInstance().getProperty("android.scanTypes", null).split(";");
                types = Arrays.asList(arr);
            } 
            
            if(!in.initiateScan(types, "ONE_D_MODE")){
                // restore old activity handling
                CodeScanner.scanErrorCallback(-1, "no scan app");
                if (activity instanceof CodenameOneActivity) {
                    ((CodenameOneActivity) activity).restoreIntentResultListener();
                }
            }
        }

        public void onActivityResult(int requestCode, final int resultCode, Intent data) {
            Activity activity = AndroidNativeUtil.getActivity();
            if (requestCode == IntentIntegrator.REQUEST_CODE) {
                
                if (resultCode == Activity.RESULT_OK) {
                    final String contents = data.getStringExtra("SCAN_RESULT");
                    final String formatName = data.getStringExtra("SCAN_RESULT_FORMAT");
                    final byte[] rawBytes = data.getByteArrayExtra("SCAN_RESULT_BYTES");
                    CodeScanner.scanCompletedCallback(contents, formatName, rawBytes);
                    
                } else if(resultCode == Activity.RESULT_CANCELED) {
                    CodeScanner.scanCanceledCallback();
                
                } else {
                    CodeScanner.scanErrorCallback(resultCode, null);
                    
                }
            }
            
            // restore old activity handling
            if (activity instanceof CodenameOneActivity) {
                ((CodenameOneActivity) activity).restoreIntentResultListener();
            }
        }

    public boolean isSupported() {
        return true;
    }

}
