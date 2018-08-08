// IMyAidlInterface.aidl
package com.ting.android.aidldemo;

// Declare any non-default types here with import statements
import com.ting.android.aidldemo.Product;

interface IMyAidlInterface {
    Map getMap(in String country, in Product product);
    Product getProduct();
}
