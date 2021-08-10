package com.example.help_me_out;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.spec.ECField;
import java.util.Iterator;


public class DownloadCurrency extends AsyncTask<String,String,String> {
    String result;
    Context context;
    SharedPreferences sharedPreferences;
    String localTime;
    public DownloadCurrency(Context c,String time)
    {
        context=c;
        localTime=time;
        sharedPreferences=context.getSharedPreferences("com.example.help_me_out",Context.MODE_PRIVATE);
    }

    @Override
    protected String doInBackground(String... urlIn) {
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(urlIn[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            int d = reader.read();
            result = "";
            while (d != -1) {
                char ch = (char) d;
                result =result+ ch;
                d = reader.read();
            }
            return result;

        } catch (Exception e) {
            return "Something Went Wrong";

        }


    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s.equals("Something Went Wrong")) {
            Toast.makeText(context, "Cannot Update: Check Internet Connection", Toast.LENGTH_SHORT).show();
            if (sharedPreferences.contains("Time")) {
                ConvertCurrency convertCurrency = new ConvertCurrency();
                convertCurrency.load(context, "CURRENCY_TABLE");
            }
        } else {
            try {
                JSONObject object = new JSONObject(s);
                String res = object.getString("quotes");
                JSONObject ob = new JSONObject(res);
                Iterator<String> iterator = ob.keys();
                DataBaseHandler database = new DataBaseHandler(context);
                database.deleteAll("CURRENCY_TABLE");
                while (iterator.hasNext()) {

                    String key = iterator.next();
                    try {
                        String quo = ob.getString(key);
                        DataModule db = new DataModule(key, quo, -1);
                        database.add(db, "CURRENCY_TABLE");
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }

                }
                ConvertCurrency convertCurrency = new ConvertCurrency();
                convertCurrency.load(context, "CURRENCY_TABLE");
                SharedPreferences.Editor myedit = sharedPreferences.edit();
                myedit.putString("Time", localTime);
                myedit.commit();
                Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }
}
