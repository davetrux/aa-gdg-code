package com.devfest.starter;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.IOException;


public class RestService extends IntentService {


    public RestService() {
        super("RestService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

}
