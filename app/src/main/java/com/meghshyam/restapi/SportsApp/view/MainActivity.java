
package com.meghshyam.restapi.SportsApp.view;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.meghshyam.restapi.SportsApp.databinding.ActivityMainNewBinding;
import com.meghshyam.restapi.SportsApp.viewmodel.MainActivityViewModel;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.meghshyam.restapi.SportsApp.App;
import com.meghshyam.restapi.SportsApp.R;


public class MainActivity extends AppCompatActivity {
    MainActivityViewModel mainActivityViewModel;
    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = bind();
        googleAnalytics();
        setFragment();
    }

    @Override
    public void onBackPressed() {
        if(App.path=="First")
        {
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragmentContainer,new SportsListFragment(),"Sports");
            transaction.addToBackStack(SportsListFragment.class.getCanonicalName());
            transaction.commit();
            App.path="";
        }
        else if(App.path=="Second")
        {
            App.searchIsOn = "";
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragmentContainer,new LeagueListFragment(),"Sports");
            transaction.addToBackStack(SportsListFragment.class.getCanonicalName());
            transaction.commit();
            App.path="First";
        }
        else if(App.path=="Third")
        {
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragmentContainer,new TeamListFragment(),"Sports");
            transaction.addToBackStack(SportsListFragment.class.getCanonicalName());
            transaction.commit();
            App.path="Second";
        }
        else
        {
            finish();
        }
    }
    public View bind()
    {
        ActivityMainNewBinding activityMainNewBinding = DataBindingUtil.setContentView(this,R.layout.activity_main_new);
        mainActivityViewModel = new MainActivityViewModel();
        activityMainNewBinding.setViewModel(mainActivityViewModel);
        return activityMainNewBinding.getRoot();
    }
    public void googleAnalytics()
    {
        // Obtain the shared Tracker instance. (Google Analytics)
        App application = (App) getApplication();
        mTracker = application.getDefaultTracker();
        String name = "Main Activity";
        Log.i("AnalyticsData", "Setting screen name: " + name);
        mTracker.setScreenName("Image~" + name);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }
    public void setFragment()
    {

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragmentContainer,new SportsListFragment(),"Sports List");
        transaction.addToBackStack(SportsListFragment.class.getCanonicalName());
        transaction.commit();
    }
}