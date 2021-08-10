package com.example.help_me_out;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalculatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculatorFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText editText;
    TextView textView;
    Boolean flag = true;

    public CalculatorFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalculatorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalculatorFragment newInstance(String param1, String param2) {
        CalculatorFragment fragment = new CalculatorFragment();
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
        return inflater.inflate(R.layout.fragment_calculator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.edit_exp);
        editText.setShowSoftInputOnFocus(false);
        textView = view.findViewById(R.id.textView);
        Button button_history = view.findViewById(R.id.clear_button);
        Button button_paranOpen = view.findViewById(R.id.button_paranOpen);
        Button button_paranClose = view.findViewById(R.id.button_paran_Close);
        Button button_1 = view.findViewById(R.id.button_1);
        Button button_2 = view.findViewById(R.id.button_2);
        Button button_3 = view.findViewById(R.id.button_3);
        Button button_4 = view.findViewById(R.id.button_4);
        Button button_5 = view.findViewById(R.id.button_5);
        Button button_6 = view.findViewById(R.id.button_6);
        Button button_7 = view.findViewById(R.id.button_7);
        Button button_8 = view.findViewById(R.id.button_8);
        Button button_9 = view.findViewById(R.id.button_9);
        Button button_0 = view.findViewById(R.id.button_0);
        Button button_decimal = view.findViewById(R.id.button_decimal);
        Button button_multi = view.findViewById(R.id.button_multi);
        Button button_power = view.findViewById(R.id.button_power);
        Button button_div = view.findViewById(R.id.button_div);
        Button button_calc = view.findViewById(R.id.calc_button);
        ImageButton button_dlt = view.findViewById(R.id.button_dlt);
        Button button_plus = view.findViewById(R.id.button_plus);
        Button button_minus = view.findViewById(R.id.button_minus);
        button_history.setOnClickListener(this);
        button_paranClose.setOnClickListener(this);
        button_paranOpen.setOnClickListener(this);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);
        button_0.setOnClickListener(this);
        button_plus.setOnClickListener(this);
        button_power.setOnClickListener(this);
        button_minus.setOnClickListener(this);
        button_div.setOnClickListener(this);
        button_dlt.setOnClickListener(this);
        button_calc.setOnClickListener(this);
        button_decimal.setOnClickListener(this);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    editText.setText(textView.getText().toString().substring(1));
                } catch (Exception e) {
                }
            }

        });
        button_dlt.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                textView.setText("");
                editText.setText("");
                return true;
            }
        });

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Calculator calculator = new Calculator();
        switch (id) {
            case R.id.calc_button: {
                String exp;
                String res;

                try {
                    exp = editText.getText().toString();


                } catch (Exception e) {
                    exp = "0";
                }
                res = calculator.Calculate(exp);
                textView.setText("= " + res);
                DataModule dataModule = new DataModule(exp, res, -1);
                DataBaseHandler data = new DataBaseHandler(getActivity());
                data.add(dataModule, "CALC_TABLE");
                flag = true;
                break;

            }
            case R.id.button_decimal: {
                if (flag) {
                    editText.append(".");
                    flag = false;
                }
                break;
            }
            case R.id.button_1: {
                editText.append("1");
                break;
            }
            case R.id.button_2: {
                editText.append("2");
                break;
            }
            case R.id.button_3: {
                editText.append("3");
                break;
            }
            case R.id.button_4: {
                editText.append("4");
                break;
            }
            case R.id.button_5: {
                editText.append("5");
                break;
            }
            case R.id.button_6: {
                editText.append("6");
                break;
            }
            case R.id.button_7: {
                editText.append("7");
                break;
            }
            case R.id.button_8: {
                editText.append("8");
                break;
            }
            case R.id.button_9: {
                editText.append("9");
                break;
            }
            case R.id.button_0: {
                editText.append("0");
                break;
            }
            case R.id.button_plus: {
                editText.append("+");
                flag = true;
                break;
            }
            case R.id.button_minus: {
                editText.append("-");
                flag = true;
                break;
            }
            case R.id.button_power: {
                editText.append("^");
                flag = true;
                break;
            }
            case R.id.button_div: {
                editText.append("/");
                flag = true;
                break;
            }
            case R.id.button_multi: {
                editText.append("*");
                flag = true;
                break;
            }
            case R.id.button_paranOpen: {
                editText.append("(");
                break;
            }
            case R.id.button_paran_Close: {
                editText.append(")");
                break;
            }
            case R.id.button_dlt: {
                try {
                    String t = editText.getText().toString();
                    editText.setText(t.substring(0, t.length() - 1));
                } catch (Exception e) {
                    editText.setText("");
                }
                break;
            }
            case R.id.clear_button: {

            }
        }
    }
}