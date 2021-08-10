package com.example.help_me_out;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConverstionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConverstionFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String  myId;
    EditText editText;
    TextView textView;
    String number[]={"Decimal","Binary","Octal","Hexadecimal"};
    String weigth[]={"Killogram","Gram","Milligram","Microgram","Quintal","Tonne","Imperial ton","US ton","Stone","Pound","Ounce"};
    String temp[]={"Kelvin","Celsius","Fahrenheit"};
    String length[]={"Femtometer","Picometer","Angstrom","Nanometer","Micrometer","Millimeter","Centimeter","Meter","kilometer","Mile","Yard","Foot","Inch","Nautical Mile"};
    String angle[]={"degree [°]","grad [^g]","Milliradian","radian [rad]","minute [']","second []","gon","sign","revolution [r]","circle","turn","quadrant","right angle","sextant"};
    String pressure[]={"pascal [Pa]","kilopascal [kPa]","bar","psi [psi]","ksi [ksi]","Standard atmosphere [atm]","exapascal [EPa]","petapascal [PPa]","terapascal [TPa]","gigapascal [GPa]","megapascal [MPa]","hectopascal [hPa]","dekapascal [daPa]","decipascal [dPa]","centipascal [cPa]","millipascal [mPa]","micropascal [µPa]","nanopascal [nPa]","picopascal [pPa]","femtopascal [fPa]","attopascal [aPa]","newton/square meter","newton/square centimeter","newton/square millimeter","kilonewton/square meter","millibar [mbar]","microbar [µbar]","dyne/square centimeter","kilogram-force/square meter","kilogram-force/sq. cm","kilogram-force/sq. millimeter","gram-force/sq. centimeter","ton-force (short)/sq. foot","ton-force (short)/sq. inch","ton-force (long)/square foot","ton-force (long)/square inch","kip-force/square inch","pound-force/square foot","pound-force/square inch","poundal/square foot","torr [Torr]","centimeter mercury (0°C)","millimeter mercury (0°C)","inch mercury (32°F) [inHg]","inch mercury (60°F) [inHg]","centimeter water (4°C)","millimeter water (4°C)","inch water (4°C) [inAq]","foot water (4°C) [ftAq]","inch water (60°F) [inAq]","foot water (60°F) [ftAq]","atmosphere technical [at]"};
    String volume[]={"cubic meter [m^3]","cubic kilometer [km^3]","cubic centimeter [cm^3]","cubic millimeter [mm^3]","liter [L, l]","milliliter [mL]","gallon (US) [gal (US)]","quart (US) [qt (US)]","pint (US) [pt (US)]","cup (US)","tablespoon (US)","teaspoon (US)","cubic mile [mi^3]","cubic yard [yd^3]","cubic foot [ft^3]","cubic inch [in^3]","cubic decimeter [dm^3]","exaliter [EL]","petaliter [PL]","teraliter [TL]","gigaliter [GL]","megaliter [ML]","kiloliter [kL]","hectoliter [hL]","dekaliter [daL]","deciliter [dL]","centiliter [cL]","microliter [µL]","nanoliter [nL]","picoliter [pL]","femtoliter [fL]","attoliter [aL]","cc [cc, cm^3]","dropbarrel (oil) [bbl (oil)]","barrel (US) [bbl (US)]","barrel (UK) [bbl (UK)]","gallon (UK) [gal (UK)]","quart (UK) [qt (UK)]","pint (UK) [pt (UK)]","cup (metric)cup (UK)","fluid ounce (US) [fl oz (US)]","fluid ounce (UK) [fl oz (UK)]","tablespoon (metric)","tablespoon (UK)","dessertspoon (US)","dessertspoon (UK)","teaspoon (metric)","teaspoon (UK)","gill (US) [gi]","gill (UK) [gi (UK)]","minim (US)","minim (UK)","ton register [ton reg]","ccf","hundred-cubic foot","acre-foot [ac*ft]","acre-foot (US survey)","acre-inch [ac*in]","dekastere","stere [st]","decistere","cord [cd]","tun","hogshead","board foot","dram [dr]","cor (Biblical)","homer (Biblical)","bath (Biblical)","hin (Biblical)","cab (Biblical)","log (Biblical)","Taza (Spanish)","Earth's volume"};
    String energy[]={"joule [J]","kilojoule [kJ]","kilowatt-hour [kW*h]","watt-hour [W*h]","calorie (nutritional)","horsepower (metric) hour","Btu (IT) [Btu (IT), Btu]","Btu (th) [Btu (th)]","gigajoule [GJ]","megajoule [MJ]","millijoule [mJ]","microjoule [µJ]","nanojoule [nJ]","attojoule [aJ]","megaelectron-volt [MeV]","kiloelectron-volt [keV]","electron-volt [eV]","erggigawatt-hour [GW*h]","megawatt-hour [MW*h]","kilowatt-second [kW*s]","watt-second [W*s]","newton meter [N*m]","horsepower hour [hp*h]","kilocalorie (IT) [kcal (IT)]","kilocalorie (th) [kcal (th)]","calorie (IT) [cal (IT), cal]","calorie (th) [cal (th)]","mega Btu (IT) [MBtu (IT)]","ton-hour (refrigeration)","fuel oil equivalent @kiloliter","fuel oil equivalent @barrel (US)","gigaton [Gton]","megaton [Mton]","kiloton [kton]","ton (explosives)","dyne centimeter [dyn*cm]","gram-force meter [gf*m]","gram-force centimeter","kilogram-force centimeter","kilogram-force meter","kilopond meter [kp*m]","pound-force foot [lbf*ft]","pound-force inch [lbf*in]","ounce-force inch [ozf*in]","foot-pound [ft*lbf]","inch-pound [in*lbf]","inch-ounce [in*ozf]","poundal foot [pdl*ft]","therm","therm (EC)","therm (US)","Hartree energy","Rydberg constant"};
    String power[]={"watt [W]","exawatt [EW]","petawatt [PW]","terawatt [TW]","gigawatt [GW]","megawatt [MW]","kilowatt [kW]","hectowatt [hW]","dekawatt [daW]","deciwatt [dW]","centiwatt [cW]","milliwatt [mW]","microwatt [µW]","nanowatt [nW]","picowatt [pW]","femtowatt [fW]","attowatt [aW]","horsepower [hp, hp (UK)]","horsepower (550 ft*lbf/s)","horsepower (metric)","horsepower (boiler)","horsepower (electric)","horsepower (water)","pferdestarke (ps)","Btu (IT)/hour [Btu/h]","Btu (IT)/minute [Btu/min]","Btu (IT)/second [Btu/s]","Btu (th)/hour [Btu (th)/h]","Btu (th)/minute","Btu (th)/second [Btu (th)/s]","MBtu (IT)/hour [MBtu/h]","MBH","ton (refrigeration)","kilocalorie (IT)/hour [kcal/h]","kilocalorie (IT)/minute","kilocalorie (IT)/second","kilocalorie (th)/hour","kilocalorie (th)/minute","kilocalorie (th)/second","calorie (IT)/hour [cal/h]","calorie (IT)/minute [cal/min]","calorie (IT)/second [cal/s]","calorie (th)/hour [cal (th)/h]","calorie (th)/minute","calorie (th)/second","foot pound-force/hour","foot pound-force/minute","foot pound-force/second","pound-foot/hour [lbf*ft/h]","pound-foot/minute","pound-foot/second","erg/second [erg/s]","kilovolt ampere [kV*A]","volt ampere [V*A]","newton meter/second","joule/second [J/s]","exajoule/second [EJ/s]","petajoule/second [PJ/s]","terajoule/second [TJ/s]","gigajoule/second [GJ/s]","megajoule/second [MJ/s]","kilojoule/second [kJ/s]","hectojoule/second [hJ/s]","dekajoule/second [daJ/s]","decijoule/second [dJ/s]","centijoule/second [cJ/s]","millijoule/second [mJ/s]","microjoule/second [µJ/s]","nanojoule/second [nJ/s]","picojoule/second [pJ/s]","femtojoule/second [fJ/s]","attojoule/second [aJ/s]","joule/hour [J/h]","joule/minute [J/min]","kilojoule/hour [kJ/h]","kilojoule/minute [kJ/min]"};
    String items[];
    int fromPos=0;
    int toPos=0;



    public ConverstionFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConverstionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConverstionFragment newInstance(String param1, String param2) {
        ConverstionFragment fragment = new ConverstionFragment();
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
       Bundle bundle=this.getArguments();
          myId=bundle.getString("Id");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_converstion, container, false);
    }
    public String[] conversionId(String a)
    {
        if(a.equals("Number System"))
        {
            items=number;
            return items;
        }
        else if(a.equals("Temperature"))
        {
            items=temp;
            return items;
        }
        else if(a.equals("Weigth"))
        {
            items=weigth;
            return items;
        }
        else if(a.equals("Length"))
        {
            items=length;
            return items;
        }
        else if(a.equals("Angle"))
        {
            items=angle;
            return items;

        }
        else if(a.equals("Pressure"))
        {
            items=pressure;
            return items;
        }
        else if(a.equals("Volume"))
        {
            items=volume;
            return items;
        }
        else if(a.equals("Energy"))
        {
            items=energy;
            return items;
        }
        else
        {
            items=power;
            return items;
        }
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //LinearLayout linearLayout=(LinearLayout)view.findViewById(R.id.linear_layout);
        Spinner spinFrom=view.findViewById(R.id.spinFrom);
        spinFrom.setOnItemSelectedListener(this);
        ArrayAdapter<String> ad=new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item,conversionId(myId));
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinFrom.setAdapter(ad);
        Spinner spinTo=view.findViewById(R.id.spinTo);
        spinTo.setOnItemSelectedListener(this);
        ArrayAdapter<String> ad2=new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item,conversionId(myId));
        ad2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinTo.setAdapter(ad2);
        editText=view.findViewById(R.id.from);
        textView=view.findViewById(R.id.textTo);
        Button convert=view.findViewById(R.id.convert);
        Conversions conversions=new Conversions();
        NumberConversion num=new NumberConversion();
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(items==number) {
                    try {
                        String n=editText.getText().toString();

                        if (fromPos == 0 && toPos == 1) {
                            textView.setText(num.DecToBin(Double.parseDouble(n)));

                        } else if (fromPos == 0 && toPos == 2) {
                            textView.setText(num.DecToOct(Double.parseDouble(n)));

                        } else if (fromPos == 0 && toPos == 3) {
                            textView.setText(num.DecToHex(Double.parseDouble(n)));

                        } else if (fromPos == 1 && toPos == 0) {
                            double dec=num.BinToDec(n);
                            textView.setText(String.valueOf(dec==-1?"Invalid":dec));

                        } else if (fromPos == 1 && toPos == 2) {
                            textView.setText(num.BinToOct(n));

                        } else if (fromPos == 1 && toPos == 3) {
                            textView.setText(num.BinToHex(n));

                        } else if (fromPos == 2 && toPos == 0) {
                            textView.setText(String.valueOf(num.OctToDec(n)));

                        } else if (fromPos == 2 && toPos == 1) {
                            textView.setText(num.OctToBin(n));

                        } else if (fromPos == 2 && toPos == 3) {
                            textView.setText(num.OctToHex(n));

                        } else if (fromPos == 3 && toPos == 0) {
                            textView.setText(num.HexToDec(n));


                        } else if (fromPos == 3 && toPos == 1) {
                            textView.setText(num.HexToBin(n));

                        } else if (fromPos == 3 && toPos == 2) {
                            textView.setText(num.HexToOct(n));

                        } else {
                            textView.setText(n);
                        }


                    }
                    catch (Exception e)
                    {
                        textView.setText("Invalid");
                    }

                }
                else if(items==temp)
                {
                    textView.setText(String.valueOf(conversions.Temperature(fromPos,toPos,Double.parseDouble(editText.getText().toString()))));

                }
                else if(items==weigth)
                {
                   textView.setText(String.valueOf(conversions.Weight(fromPos,toPos,Double.parseDouble(editText.getText().toString()))));
                }
                else if(items==length)
                {
                    textView.setText(String.valueOf(conversions.Length(fromPos,toPos,Double.parseDouble(editText.getText().toString()))));

                }
                else if(items==angle)
                {
                    textView.setText(String.valueOf(conversions.Angle(fromPos,toPos,Double.parseDouble(editText.getText().toString()))));

                }
                else if(items==pressure)
                {
                    textView.setText(String.valueOf(conversions.Pressure(fromPos,toPos,Double.parseDouble(editText.getText().toString()))));

                }
                else if(items==volume)
                {
                    textView.setText(String.valueOf(conversions.Volume(fromPos,toPos,Double.parseDouble(editText.getText().toString()))));

                }
                else if(items==energy)
                {
                    textView.setText(String.valueOf(conversions.Energy(fromPos,toPos,Double.parseDouble(editText.getText().toString()))));

                }
                else if(items==power)
                {
                    textView.setText(String.valueOf(conversions.Power(fromPos,toPos,Double.parseDouble(editText.getText().toString()))));

                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         int spinId=parent.getId();
        if(spinId==R.id.spinFrom)
        {
          fromPos=position;
            if(fromPos==3 && items==number)
            {
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
            }
            else
            {
                editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            }
        }
        else
        {
            toPos=position;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}