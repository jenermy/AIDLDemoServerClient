// IMyAidlInterface.aidl
package com.example.ndkjni;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);


    //接口和方法声明都不用public，方法加入public会提示错误
      String teleplay();
}
