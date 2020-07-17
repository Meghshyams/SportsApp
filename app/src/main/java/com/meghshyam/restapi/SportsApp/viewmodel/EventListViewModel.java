package com.meghshyam.restapi.SportsApp.viewmodel;

import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.meghshyam.restapi.SportsApp.App;
import com.meghshyam.restapi.SportsApp.BR;
import com.meghshyam.restapi.SportsApp.model.EventListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EventListViewModel extends BaseObservable {
    private List<EventListModel> eventListModels =new ArrayList<>();
    ProgressDialog progressDialog;
    public EventListViewModel(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.setCancelable(false);
     }

    public void setUp() {
        // perform set up tasks, such as adding listeners

        new MyTask().execute();
    }

    public void tearDown() {
        // perform tear down tasks, such as removing listeners

    }

    @Bindable
    public String getTeamname() {
        if(eventListModels.size()>0)
         return !TextUtils.isEmpty(eventListModels.get(0).getStrEvent()) ? eventListModels.get(0).getStrEvent() : "";
        else
            return "";
    }


    @Bindable
    public String getDate()
    {
        if(eventListModels.size()>0)
         return eventListModels.get(0).getStrDate()+" "+eventListModels.get(0).getStrTime();
        else
            return "";
    }

    @Bindable
    public String getTeamname2() {
        if(eventListModels.size()>0)
            return !TextUtils.isEmpty(eventListModels.get(1).getStrEvent()) ? eventListModels.get(1).getStrEvent() : "";
        else
            return "";
    }
    @Bindable
    public String getDate2()
    {
        if(eventListModels.size()>0)
            return eventListModels.get(1).getStrDate()+" "+eventListModels.get(1).getStrTime();
        else
            return "";
    }


    @Bindable
    public String getTeamname3() {
        if(eventListModels.size()>0)
            return !TextUtils.isEmpty(eventListModels.get(2).getStrEvent()) ? eventListModels.get(2).getStrEvent() : "";
        else
            return "";
    }
    @Bindable
    public String getDate3()
    {
        if(eventListModels.size()>0)
            return eventListModels.get(2).getStrDate()+" "+eventListModels.get(2).getStrTime();
        else
            return "";
    }

    @Bindable
    public String getTeamname4() {
        if(eventListModels.size()>0)
            return !TextUtils.isEmpty(eventListModels.get(3).getStrEvent()) ? eventListModels.get(3).getStrEvent() : "";
        else
            return "";
    }
    @Bindable
    public String getDate4()
    {
        if(eventListModels.size()>0)
            return eventListModels.get(3).getStrDate()+" "+eventListModels.get(3).getStrTime();
        else
            return "";
    }

    @Bindable
    public String getTeamname5() {
        if(eventListModels.size()>0)
            return !TextUtils.isEmpty(eventListModels.get(4).getStrEvent()) ? eventListModels.get(4).getStrEvent() : "";
        else
            return "";
    }
    @Bindable
    public String getDate5()
    {
        if(eventListModels.size()>0)
            return eventListModels.get(4).getStrDate()+" "+eventListModels.get(4).getStrTime();
        else
            return "";
    }


    @Bindable
    public String getTeamnamep() {
        if(eventListModels.size()>5)
            return !TextUtils.isEmpty(eventListModels.get(5).getStrEvent()) ? eventListModels.get(5).getStrEvent() : "";
        else
            return "";
    }


    @Bindable
    public String getDatep()
    {
        if(eventListModels.size()>5)
            return eventListModels.get(5).getStrDate()+" "+eventListModels.get(5).getStrTime();
        else
            return "";
    }

    @Bindable
    public String getTeamnamep2() {
        if(eventListModels.size()>6)
            return !TextUtils.isEmpty(eventListModels.get(6).getStrEvent()) ? eventListModels.get(6).getStrEvent() : "";
        else
            return "";
    }
    @Bindable
    public String getDatep2()
    {
        if(eventListModels.size()>6)
            return eventListModels.get(6).getStrDate()+" "+eventListModels.get(6).getStrTime();
        else
            return "";
    }


    @Bindable
    public String getTeamnamep3() {
        if(eventListModels.size()>7)
            return !TextUtils.isEmpty(eventListModels.get(7).getStrEvent()) ? eventListModels.get(7).getStrEvent() : "";
        else
            return "";
    }
    @Bindable
    public String getDatep3()
    {
        if(eventListModels.size()>7)
            return eventListModels.get(7).getStrDate()+" "+eventListModels.get(7).getStrTime();
        else
            return "";
    }

    @Bindable
    public String getTeamnamep4() {
        if(eventListModels.size()>8)
            return !TextUtils.isEmpty(eventListModels.get(8).getStrEvent()) ? eventListModels.get(8).getStrEvent() : "";
        else
            return "";
    }
    @Bindable
    public String getDatep4()
    {
        if(eventListModels.size()>8)

            return eventListModels.get(8).getStrDate()+" "+eventListModels.get(8).getStrTime();
        else
            return "";
    }

    @Bindable
    public String getTeamnamep5() {
        if(eventListModels.size()>9)
            return !TextUtils.isEmpty(eventListModels.get(9).getStrEvent()) ? eventListModels.get(9).getStrEvent() : "";
        else
            return "";
    }
    @Bindable
    public String getDatep5()
    {
        if(eventListModels.size()>9)
            return eventListModels.get(9).getStrDate()+" "+eventListModels.get(9).getStrTime();
        else
            return "";
    }

    class MyTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... params) {

            try {
                JSONObject response = getJSONObjectFromURL("https://www.thesportsdb.com/api/v1/json/1/eventsnext.php?id="+ App.selectedMatchId); // calls method to get JSON object
                JSONArray jsonArray = response.getJSONArray("events");
                if(jsonArray.length()>0)
                {
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        EventListModel dataModel = new EventListModel();
                        dataModel.setIdEvent(jsonObject.getString("idEvent"));
                        dataModel.setStrEvent(jsonObject.getString("strEvent"));
                        dataModel.setStrDate((jsonObject.getString("dateEvent")));
                        dataModel.setStrTime(jsonObject.getString("strTime"));
                        eventListModels.add(dataModel);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return "Task Completed.";
        }
        @Override
        protected void onPostExecute(String result) {

            if(result.equals("Task Completed."))
            {
                /*notifyPropertyChanged(BR.date);
                notifyPropertyChanged(BR.teamname);

                notifyPropertyChanged(BR.date2);
                notifyPropertyChanged(BR.teamname2);

                notifyPropertyChanged(BR.date3);
                notifyPropertyChanged(BR.teamname3);

                notifyPropertyChanged(BR.date4);
                notifyPropertyChanged(BR.teamname4);

                notifyPropertyChanged(BR.date5);
                notifyPropertyChanged(BR.teamname5);*/
                new MyTaskPre().execute();
            }
        }
        @Override
        protected void onPreExecute() {
            progressDialog.show();
        }
        @Override
        protected void onProgressUpdate(Integer... values) {

        }
    }

    class MyTaskPre extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... params) {

            try {
                JSONObject response = getJSONObjectFromURL("https://www.thesportsdb.com/api/v1/json/1/eventslast.php?id="+App.selectedMatchId); // calls method to get JSON object
                JSONArray jsonArray = response.getJSONArray("results");
                if(jsonArray.length()>0)
                {
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        EventListModel dataModel = new EventListModel();
                        dataModel.setIdEvent(jsonObject.getString("idEvent"));
                        dataModel.setStrEvent(jsonObject.getString("strEvent"));
                        dataModel.setStrDate((jsonObject.getString("dateEvent")));
                        dataModel.setStrTime(jsonObject.getString("strTime"));
                        eventListModels.add(dataModel);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return "Task Completed.";
        }
        @Override
        protected void onPostExecute(String result) {

            if(result.equals("Task Completed."))
            {
                notifyPropertyChanged(BR.date);
                notifyPropertyChanged(BR.teamname);

                notifyPropertyChanged(BR.date2);
                notifyPropertyChanged(BR.teamname2);

                notifyPropertyChanged(BR.date3);
                notifyPropertyChanged(BR.teamname3);

                notifyPropertyChanged(BR.date4);
                notifyPropertyChanged(BR.teamname4);

                notifyPropertyChanged(BR.date5);
                notifyPropertyChanged(BR.teamname5);

                notifyPropertyChanged(BR.datep);
                notifyPropertyChanged(BR.teamnamep);

                notifyPropertyChanged(BR.datep2);
                notifyPropertyChanged(BR.teamnamep2);

                notifyPropertyChanged(BR.datep3);
                notifyPropertyChanged(BR.teamnamep3);

                notifyPropertyChanged(BR.datep4);
                notifyPropertyChanged(BR.teamnamep4);

                notifyPropertyChanged(BR.datep5);
                notifyPropertyChanged(BR.teamnamep5);
            }

            progressDialog.dismiss();
        }
        @Override
        protected void onPreExecute() {

        }
        @Override
        protected void onProgressUpdate(Integer... values) {

        }
    }

    public static JSONObject getJSONObjectFromURL(String urlString) throws IOException, JSONException {

        HttpURLConnection urlConnection = null;

        URL url = new URL(urlString);

        urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000 /* milliseconds */);
        urlConnection.setConnectTimeout(15000 /* milliseconds */);

        urlConnection.setDoOutput(true);

        urlConnection.connect();

        BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));

        char[] buffer = new char[1024];

        String jsonString = new String();

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line+"\n");
        }
        br.close();

        jsonString = sb.toString();

        System.out.println("JSON: " + jsonString);
        urlConnection.disconnect();

        return new JSONObject(jsonString);
    }

}

