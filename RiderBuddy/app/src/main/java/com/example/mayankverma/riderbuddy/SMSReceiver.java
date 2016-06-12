package com.example.mayankverma.riderbuddy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.text.TextUtils;

/**
 * Created by mayankverma on 12/06/16.
 */
public class SMSReceiver extends BroadcastReceiver {

    private SharedPreferences preferences;
    private final String SMS_STRING_PATTERN = "Get Rider Info";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                try {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[0]);
                    if (smsMessage != null) {
                        String smsBody = smsMessage.getMessageBody();
                        if (!TextUtils.isEmpty(smsBody) && smsBody.contains(SMS_STRING_PATTERN)) {
                            //Todo send the lat long location of the user by starting the activity
                        }
                    }

                } catch (Exception e) {
//                            Log.d("Exception caught",e.getMessage());
                }
            }
        }
    }
}
