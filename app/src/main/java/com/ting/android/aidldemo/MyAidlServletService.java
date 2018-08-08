package com.ting.android.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyAidlServletService extends Service {

    public class MyBinder extends UserInfo.Stub{

        @Override
        public String getInfo() throws RemoteException {
            return "这里是服务端返回的信息";
        }
    }
    public MyAidlServletService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
       return new MyBinder();
    }
}
