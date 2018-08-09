// IMyAidlInterface.aidl
package com.ting.android.aidldemo;

//注意：这里有一个巨坑，这包名不能加aidl。不然会报错
import com.ting.android.aidldemo.Product;

interface IMyAidlInterface {
    Map getMap(in String country, in Product product);
    Product getProduct();
}
