package com.example.aidltest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.ndkjni.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {
    private static TextView contentTv;
    private ServerConnection serverConnection;
    public static class ServerConnection implements ServiceConnection{
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i("wanlijun","onServiceConnected");
            IMyAidlInterface iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
            try {
                contentTv.setText(iMyAidlInterface.teleplay());
            }catch (Exception e){
                e.printStackTrace();
                Log.i("wanlijun",e.toString());
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i("wanlijun","onServiceDisconnected");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contentTv = (TextView)findViewById(R.id.contentTv);
        serverConnection = new ServerConnection();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent aidlIntent = new Intent();
        aidlIntent.setComponent(new ComponentName("com.example.ndkjni","com.example.ndkjni.MyService"));
        bindService(aidlIntent,serverConnection,BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(serverConnection);
    }
}
