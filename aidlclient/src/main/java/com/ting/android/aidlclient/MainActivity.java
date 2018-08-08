package com.ting.android.aidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ting.android.aidldemo.UserInfo;

public class MainActivity extends AppCompatActivity {

    UserInfo userInfo = null;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent mIntent = new Intent("com.ting.android.aidldemo.UserInfo"); //服务器配置的 action name 要保持一致。
        mIntent.setPackage("com.ting.android.aidldemo");    //这里设置服务器的包名，这样避免要求显示调用
        bindService(mIntent, new MyServletConn(), BIND_AUTO_CREATE);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Log.i("AIDL: ", "绑定成功"+userInfo.getInfo());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 需要绑定远程的Service
     */
    private class MyServletConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            userInfo = UserInfo.Stub.asInterface(iBinder);
            Log.i("AIDL: ","绑定成功");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            userInfo = null;
        }
    }
}
