package com.example.help_me_out;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.airbnb.lottie.LottieAnimationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DownloadTask extends AsyncTask<String,Integer,String> {
    LinearLayout layout;
    String word;
    Context context;
   String audio;


    public DownloadTask(LinearLayout linearLayout, String w, Context c) {
        layout = linearLayout;
        word = w;
        context = c;
    }
    String result;

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
                result = result + ch;
                d = reader.read();
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return "Something Went Wrong";

        }
    }

    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        String def = "";
        String phon;

        try {

            JSONArray jarr = new JSONArray(result);
            try {
                JSONObject phob = new JSONObject(jarr.getString(0));
                String phonetics = phob.getString("phonetics");
                JSONArray jp = new JSONArray(phonetics);
                JSONObject jpob = new JSONObject(jp.getString(0));
                phon = jpob.getString("text");
                 audio = jpob.getString("audio");

            }
            catch(Exception e) {
                EditText ed =layout.findViewById(R.id.edit_word);
                phon=ed.getText().toString();
                audio="Not found";
            }
            def=def+"Phonetics : "+phon+"\n\n";
            for (int z = 0; z < jarr.length(); z++) {
                JSONObject ob = new JSONObject(jarr.getString(z));


                String res = ob.getString("meanings");
                JSONArray arr = new JSONArray(res);
                for (int x = 0; x < arr.length(); x++) {
                    JSONObject jo = new JSONObject(arr.getString(x));
                    def = def + "Part of Speech = " + jo.getString("partOfSpeech") + "\n\n";
                    JSONArray jrr = new JSONArray(jo.getString("definitions"));
                    for (int y = 0; y < jrr.length(); y++) {
                        JSONObject job = new JSONObject(jrr.getString(y));
                        try {
                            def = def + "Definitions = " + job.getString("definition") + "\n\n";
                            def = def + "Example = " + job.getString("example") + "\n\n";
                            JSONArray synarr = new JSONArray(job.getString("synonyms"));
                            def = def + "Synonyms = \n";
                            for (int t = 0; t < synarr.length(); t++) {
                                try {
                                    def = def + (t + 1) + ". " + synarr.getString(t) + "\n";
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            def = def + "\n";
                        } catch (Exception e) {
                            continue;
                        }
                    }
                    def = def + "\n-------------------------------------------\n\n";
                }

            }
        } catch (Exception e) {
            def = "Cannot Find Word";
        }

        LottieAnimationView lottieAnimationView=layout.findViewById(R.id.animatio);
            lottieAnimationView.cancelAnimation();
        lottieAnimationView.setVisibility(View.GONE);
        TextView textView = layout.findViewById(R.id.define);
        textView.setText(def);
        textView.setVisibility(View.VISIBLE);
        TextView aud=layout.findViewById(R.id.aud);
        aud.setText(audio);
        DataModule dataModule = new DataModule(word, def, -1);
        DataBaseHandler data = new DataBaseHandler(context);
        data.add(dataModule, "DICTIONARY_TABLE");



    }


}
