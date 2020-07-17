package com.meghshyam.restapi.SportsApp.viewmodel;

import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.AsyncTask;
import android.util.Log;

import com.meghshyam.restapi.SportsApp.App;
import com.meghshyam.restapi.SportsApp.BR;
import com.meghshyam.restapi.SportsApp.adapter.DataAdapter;
import com.meghshyam.restapi.SportsApp.model.DataModel;

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

public class TeamListViewModel extends BaseObservable {


    private static final String TAG = "LeagueViewModel";
    private DataAdapter adapter;
    private List<DataModel> leagueData;
    ProgressDialog progressDialog;
    public TeamListViewModel(Context context){
        leagueData = new ArrayList<>();
        adapter = new DataAdapter();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.setCancelable(false);
    }


    class MyTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... params) {

            try {
                String url="";
                if(App.searchIsOn.length()>0)
                {
                    url = "https://www.thesportsdb.com/api/v1/json/1/searchteams.php?t="+App.searchIsOn;
                }
                else
                {
                    url  = "https://www.thesportsdb.com/api/v1/json/1/lookup_all_teams.php?id="+ App.selectedTeamId;
                }
                JSONObject response = getJSONObjectFromURL(url); // calls method to get JSON object
                JSONArray jsonArray = response.getJSONArray("teams");
                if(jsonArray.length()>0)
                {
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String nameSprot ="";
                        nameSprot= jsonObject.getString("strSport");
                        Log.w("onClick",""+App.selectedSport +"  ----  "+ jsonObject.getString("strSport"));

                        DataModel dataModel = new DataModel();
                        dataModel.setStrSprot(jsonObject.getString("strTeam"));
                        dataModel.setIdSport(jsonObject.getString("idTeam"));
                        dataModel.setStrFormat(jsonObject.getString("strSport"));
                        leagueData.add(dataModel);
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
                populateData();
            }
            progressDialog.dismiss();
        }
        @Override
        protected void onPreExecute() {
            progressDialog.show();
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


    public void setUp() {
        // perform set up tasks, such as adding listeners, data population, etc.
        //  populateData();
        new MyTask().execute();
    }

    public void tearDown() {
        leagueData.clear();
        notifyPropertyChanged(BR.data);
        notifyPropertyChanged(BR.adapter);
        // perform tear down tasks, such as removing listeners
    }
    @Bindable
    public List<DataModel > getData() {
        return this.leagueData;
    }

    @Bindable
    public DataAdapter getAdapter() {
        return this.adapter;
    }

    private void populateData() {
        // populate the data from the source, such as the database.
       /* for (int i = 0; i < 50; i++) {
            DataModel dataModel = new DataModel();
            dataModel.setTitle("Bhargav "+String.valueOf(i));
            data.add(dataModel);
        }*/
        notifyPropertyChanged(BR.data);
    }
}
