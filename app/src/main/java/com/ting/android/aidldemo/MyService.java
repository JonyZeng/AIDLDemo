package com.ting.android.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.SparseArray;

import java.util.HashMap;
import java.util.Map;

public class MyService extends Service {

    public class MyServiceImpl extends IMyAidlInterface.Stub {

        @Override
        public Map getMap(String country, Product product) throws RemoteException {
            Map map = (Map) new HashMap<String, String>();
            map.put("country", country);
            map.put("id", product.getId());
            map.put("name", product.getName());
            map.put("price", product.getPrice());
            return map;
        }

        @Override
        public Product getProduct() throws RemoteException {
            Product product = new Product();
            product.setId(2018);
            product.setName("天地壹号");
            product.setPrice(50);
            return product;
        }
    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyServiceImpl();
    }
}
