
package com.meghshyam.restapi.SportsApp;

import android.app.Application;
import android.databinding.DataBindingUtil;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.meghshyam.restapi.SportsApp.databinding.AppDataBindingComponent;

public class App extends Application {
    private static GoogleAnalytics sAnalytics;
    private static Tracker sTracker;
    public static String path="";
    public static String selectedSport="";
    public static String selectedTeamId="";
    public static String selectedMatchId="";
    public static String searchIsOn="";

    @Override
    public void onCreate() {
        super.onCreate();
        DataBindingUtil.setDefaultComponent(new AppDataBindingComponent());
        sAnalytics = GoogleAnalytics.getInstance(this);
    }
    synchronized public Tracker getDefaultTracker() {
        // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
        if (sTracker == null) {
          //  sTracker = sAnalytics.newTracker;
            sTracker = sAnalytics.newTracker(R.xml.globaldir);
        }
        return sTracker;
    }
}
