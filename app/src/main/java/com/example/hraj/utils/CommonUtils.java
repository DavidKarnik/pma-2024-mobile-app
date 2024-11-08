package com.example.hraj.utils;

import android.content.Context;

import com.example.hraj.R;

public class CommonUtils {

    // TODO do better - how to load context without need to be parameter of fun ? On mainActivity create ?

    /**
     * Method to get color resource by its name
     *
     * @param colorName - string name of color (defined in res/values/colors.xml)
     * @param context   - context of the application
     * @return - number of color (f.e. @color/black) ready to apply
     */
    public static int getColorResource(String colorName, Context context) {
        int colorId = context.getResources().getIdentifier(colorName, "color", context.getPackageName());
        if (colorId == 0) {
            // Pokud barva není nalezena, použijte výchozí bílou nebo jinou barvu
            return context.getResources().getColor(R.color.white, null);
        }
        return context.getResources().getColor(colorId, null);
    }

    /**
     * Method to get drawable resource by its name
     *
     * @param resourceName - string name of drawable (defined in res/drawable)
     * @param context      - context of the application
     * @return - number of drawable (f.e. @drawable/icon) ready to apply
     */
    public static int getResourceId(String resourceName, Context context) {
        return context.getResources().getIdentifier(
                resourceName, "drawable", context.getPackageName());
    }
}
