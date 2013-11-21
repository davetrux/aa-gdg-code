package com.devfest.starter;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class Main extends Activity {

    private Button mServiceButton;
    private ListView mPersonList;
    private ArrayList<Person> mData;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mServiceButton = (Button) findViewById(R.id.callService);

        mPersonList = (ListView) findViewById(R.id.personList);

        mServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPersonList.getCount() > 0) {
                    mPersonList.setAdapter(null);
                }
                //Send an intent to the service to get data
                //Check to see if we can connect
                if(WebHelper.isOnline(getApplicationContext())) {
                    //Send an intent to the service to get data
                    Intent intent = new Intent(Main.this, RestService.class);
                    intent.setData(Uri.parse("http://devfestdetroit.appspot.com/api/names/10"));
                    startService(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Not currently online", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

}
