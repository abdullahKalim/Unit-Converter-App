package com.example.help_me_out;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.text.style.UpdateLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DictionaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DictionaryFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    boolean first=false;
    Spinner spinner;
    List<DataModule> list;
    EditText editText;
    TextView textView;
    DownloadTask task;
    String audio="";
    TextView aud;
    MediaPlayer mediaPlayer=new MediaPlayer();
    ImageButton imageButton;




    public DictionaryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DictionaryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DictionaryFragment newInstance(String param1, String param2) {
        DictionaryFragment fragment = new DictionaryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dictionary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         editText=view.findViewById(R.id.edit_word);
        mediaPlayer=new MediaPlayer();
        Button button=view.findViewById(R.id.search);
        LinearLayout linearLayout=(LinearLayout)view.findViewById(R.id.linear_layout);
        ScrollView scrollView=(ScrollView)view.findViewById(R.id.scroll);
        Button history=view.findViewById(R.id.historyDict);
        spinner=view.findViewById(R.id.SpinHistoryDict);
        spinner.setOnItemSelectedListener(this);
        textView=view.findViewById(R.id.define);
        textView.setMovementMethod(new ScrollingMovementMethod());
        imageButton=view.findViewById(R.id.play);
        aud=view.findViewById(R.id.aud);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word=editText.getText().toString();
                String url="https://api.dictionaryapi.dev/api/v2/entries/en_US/"+word;
                 task = new DownloadTask(linearLayout,word,getActivity());
                task.execute(url);
                imageButton.setImageResource(R.drawable.ic_not_ready);
                LottieAnimationView animationView=view.findViewById(R.id.animatio);
                animationView.setVisibility(View.VISIBLE);
                animationView.playAnimation();

            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHandler data=new DataBaseHandler(getActivity());
                list=data.getAll("DICTIONARY_TABLE");
                ArrayList<String> ar=new ArrayList<String>();
                for(DataModule t:list)
                {
                    ar.add(t.getExpression());
                }
                ArrayAdapter<String> ad=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,ar);
                ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(ad);
                spinner.performClick();
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String audio=aud.getText().toString();
                MediaPlayer mediaPlayer=new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(audio);
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {

                            imageButton.setImageResource(R.drawable.ic_ready);
                            mp.start();
                        }
                    });
                    mediaPlayer.prepareAsync();
                }
                catch (Exception e)
                {
                    Toast.makeText(getContext(), "Cannot find audio", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void LoadAudio()
    {

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        editText.setText(list.get(position).getExpression());
        textView.setText(list.get(position).getResult());
        textView.setVisibility(View.VISIBLE);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}