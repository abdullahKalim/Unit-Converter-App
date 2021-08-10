package com.example.help_me_out;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PostPreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostPreFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    List<DataModule> list;
    EditText editText;
    TextView textView;
    Spinner spinner;

    public PostPreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PostPreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PostPreFragment newInstance(String param1, String param2) {
        PostPreFragment fragment = new PostPreFragment();
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
        return inflater.inflate(R.layout.fragment_post_pre, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RadioGroup rg = (RadioGroup) view.findViewById(R.id.radio);
        Calculator cal = new Calculator();
         editText = view.findViewById(R.id.expression);
         textView = view.findViewById(R.id.expCon);
        Button button = view.findViewById(R.id.convert);
        Button history=view.findViewById(R.id.history);
         spinner=view.findViewById(R.id.SpinHistory);
        spinner.setOnItemSelectedListener(this);
        spinner.setSelected(false);
        DataBaseHandler db=new DataBaseHandler(getActivity());





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = rg.getCheckedRadioButtonId();
                RadioButton rb = view.findViewById(id);
                String exp = editText.getText().toString();
                String res="";
                if (rb.getText().equals("PostFix")) {
                    ArrayList<String> postexp = cal.infixToPostfix(cal.toArray(exp));

                    for (String t : postexp) {
                        res = res + " " + t;
                    }
                    textView.setText("Exp="+res);
                }
                else {
                    Stack<String> preexp = cal.Topre(cal.infixToPostfix(cal.toArray(exp)));

                    for (String i : preexp) {
                        res = res + " " + i;
                    }
                    textView.setText("Exp="+res);
                }
                if(!exp.equals("")) {
                    DataModule dataModule = new DataModule(exp, res, -1);
                    DataBaseHandler db = new DataBaseHandler(getActivity());
                    db.add(dataModule, "PosTPre_TABLE");
                }
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list=db.getAll("PosTPre_TABLE");
                ArrayList<String> ar=new ArrayList<>();
                for(DataModule t:list)
                {
                    ar.add(t.getExpression());
                }
                ArrayAdapter<String> ad=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,ar);
                ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(ad);
                spinner.setVisibility(View.VISIBLE);
                spinner.performClick();

            }
        });
        Button delete =view.findViewById(R.id.deleteAll);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHandler data=new DataBaseHandler(getContext());
                data.deleteAll("PosTPre_TABLE");

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        editText.setText(list.get(position).getExpression());
        textView.setText(list.get(position).getResult());
        spinner.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}