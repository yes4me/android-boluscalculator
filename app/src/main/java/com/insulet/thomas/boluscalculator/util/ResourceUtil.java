package com.insulet.thomas.boluscalculator.util;

import android.content.Context;

/**
 * Created by thomas on 6/20/2017.
 */

// Get the resource by name from String.xml
public class ResourceUtil {
    static Context context;

    public ResourceUtil(Context context) {
        this.context = context;
    }

    // Ex: String author = new ResourceUtil(this).getString("author");
    public String getString(String aString) {
        String packageName = context.getPackageName();
        int resId = context.getResources().getIdentifier(aString, "string", packageName);
        return context.getString(resId);
    }
    public Double getDouble(String aString) {
        return ConversionUtil.convertToDouble( getString(aString) );
    }
}
