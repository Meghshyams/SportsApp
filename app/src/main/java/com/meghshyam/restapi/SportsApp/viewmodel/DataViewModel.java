package com.meghshyam.restapi.SportsApp.viewmodel;

import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.AsyncTask;

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

public class DataViewModel extends BaseObservable {
    private static final String TAG = "DataViewModel";
    private DataAdapter adapter;
    private List<DataModel> data;
    ProgressDialog progressDialog;
    public DataViewModel(Context context) {
        data = new ArrayList<>();
        adapter = new DataAdapter();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.setCancelable(false);
    }

    public void setUp() {
        // perform set up tasks, such as adding listeners, data population, etc.
      //  populateData();
        new MyTask().execute();
    }

    public void tearDown() {
        // perform tear down tasks, such as removing listeners
    }

    @Bindable
    public List<DataModel> getData() {
        return this.data;
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

    class MyTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... params) {

            try {
                JSONObject response = getJSONObjectFromURL("https://www.thesportsdb.com/api/v1/json/1/all_sports.php"); // calls method to get JSON object
                JSONArray jsonArray = response.getJSONArray("sports");
                if(jsonArray.length()>0)
                {
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        DataModel dataModel = new DataModel();
                        dataModel.setStrSprot(jsonObject.getString("strSport"));
                        dataModel.setIdSport(jsonObject.getString("idSport"));
                        dataModel.setTitle(jsonObject.getString("strSport"));
                        data.add(dataModel);
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
            progressDialog.dismiss();
            if(result.equals("Task Completed."))
            {
                populateData();
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
