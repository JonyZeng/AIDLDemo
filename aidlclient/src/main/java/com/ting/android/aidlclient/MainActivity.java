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
import android.widget.TextView;

import com.ting.android.aidldemo.IMyAidlInterface;
import com.ting.android.aidldemo.UserInfo;

public class MainActivity extends AppCompatActivity {

    UserInfo userInfo = null;
    private Button btn;
    private TextView textView;
    private TextView textView2;
    private IMyAidlInterface iMyAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent mIntent = new Intent("com.ting.android.aidldemo.UserInfo"); //服务器配置的 action name 要保持一致。
        mIntent.setPackage("com.ting.android.aidldemo");    //这里设置服务器的包名，这样避免要求显示调用
        bindService(mIntent, new MyServletConn(), BIND_AUTO_CREATE);

        Intent mIntent2 = new Intent("com.ting.android.aidldemo.MyService");
        mIntent2.setPackage("com.ting.android.aidldemo");    //这里设置服务器的包名，这样避免要求显示调用
        bindService(mIntent2, new MyServiceConnection(), BIND_AUTO_CREATE);
        Button btn2 = findViewById(R.id.btn2);
        textView2 = findViewById(R.id.text2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String a = iMyAidlInterface.getProduct().toString();
                    textView2.setText(a);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        btn = findViewById(R.id.btn);
        textView = findViewById(R.id.text);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String info = userInfo.getInfo();
                    textView.setText(info);
                    Log.i("AIDL: ", "绑定成功" + info);
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
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
            Log.i("AIDL: ", "绑定成功");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            userInfo = null;
        }
    }

    private class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
            Log.i("AIDL: ", "绑定成功");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            iMyAidlInterface = null;
        }
    }
}
