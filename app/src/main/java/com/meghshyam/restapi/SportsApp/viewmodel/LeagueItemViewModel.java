package com.meghshyam.restapi.SportsApp.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;

import com.meghshyam.restapi.SportsApp.model.DataModel;

public class LeagueItemViewModel  extends BaseObservable {
    private DataModel leagueModel;

    public LeagueItemViewModel(DataModel leagueModel) {
        this.leagueModel = leagueModel;
    }

    public void setUp() {
        // perform set up tasks, such as adding listeners
    }

    public void tearDown() {
        // perform tear down tasks, such as removing listeners
    }

    @Bindable
    public String getTitle() {
        return !TextUtils.isEmpty(leagueModel.getStrFormat()) ? leagueModel.getStrFormat() : "";
    }

}

