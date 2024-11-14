package com.example.hraj.utils;

import android.content.Context;

import com.example.hraj.R;

public class CommonUtils {
    private static Context appContext;

    public static void initializeAppContext(Context context) {
        appContext = context.getApplicationContext();  // Prevent activity context leak
    }

    public static int getColorResource(String colorName) {
        int colorId = appContext.getResources().getIdentifier(colorName, "color", appContext.getPackageName());
        if (colorId == 0) {
            return appContext.getResources().getColor(R.color.white, null);
        }
        return appContext.getResources().getColor(colorId, null);
    }

    public static int getResourceId(String resourceName) {
        return appContext.getResources().getIdentifier(resourceName, "drawable", appContext.getPackageName());
    }
}

