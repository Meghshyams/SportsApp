
package com.meghshyam.restapi.SportsApp.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.meghshyam.restapi.SportsApp.App;
import com.meghshyam.restapi.SportsApp.model.DataModel;

public class DataItemViewModel extends BaseObservable {
    private DataModel dataModel;

    public DataItemViewModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public void setUp() {
        // perform set up tasks, such as adding listeners
    }

    public void tearDown() {
        // perform tear down tasks, such as removing listeners
    }

    @Bindable
        public String getTitle() {
         String title="";
         if(App.path=="")
         {
             title = dataModel.getStrSprot();
         }
         if(App.path=="First")
         {
             title = dataModel.getStrFormat();
         }
         if (App.path=="Second")
         {
             title = dataModel.getStrSprot();
         }
         return title;
    }
}
