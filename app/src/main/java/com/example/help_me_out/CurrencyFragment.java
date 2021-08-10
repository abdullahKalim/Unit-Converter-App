package com.example.help_me_out;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CurrencyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrencyFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String curr[]={"United Arab Emirates Dirham (AED)","Afghan Afghani (AFN)","Albanian Lek (ALL)","Armenian Dram (AMD)","Netherlands Antillean Guilder (ANG)","Angolan Kwanza (AOA)","Argentine Peso (ARS)","Australian Dollar (AUD)","Aruban Florin (AWG)","Azerbaijani Manat (AZN)","Bosnia-Herzegovina Convertible Mark (BAM)","Barbadian Dollar (BBD)","Bangladeshi Taka (BDT)","Bulgarian Lev (BGN)","Bahraini Dinar (BHD)","Burundian Franc (BIF)","Bermudan Dollar (BMD)","Brunei Dollar (BND)","Bolivian Boliviano (BOB)","Brazilian Real (BRL)","Bahamian Dollar (BSD)","Bitcoin (BTC)","Bhutanese Ngultrum (BTN)","Botswanan Pula (BWP)","Belarusian Ruble (BYN)-New","Belarusian Ruble (BYR)","Belize Dollar (BZD)","Canadian Dollar (CAD)","Congolese Franc (CDF)","Swiss Franc (CHF)","Chilean Unit of Account (UF) (CLF)","Chilean Peso (CLP)","Chinese Yuan (CNY)","Colombian Peso (COP)","Costa Rican Colón (CRC)","Cuban Convertible Peso (CUC)","Cuban Peso (CUP)","Cape Verdean Escudo (CVE)","Czech Republic Koruna (CZK)","Djiboutian Franc (DJF)","Danish Krone (DKK)","Dominican Peso (DOP)","Algerian Dinar (DZD)","Egyptian Pound (EGP)","Eritrean Nakfa (ERN)","Ethiopian Birr (ETB)","Euro (EUR)","Fijian Dollar (FJD)","Falkland Islands Pound (FKP)","British Pound Sterling (GBP)","Georgian Lari (GEL)","Guernsey Pound (GGP)","Ghanaian Cedi (GHS)","Gibraltar Pound (GIP)","Gambian Dalasi (GMD)","Guinean Franc (GNF)","Guatemalan Quetzal (GTQ)","Guyanaese Dollar (GYD)","Hong Kong Dollar (HKD)","Honduran Lempira (HNL)","Croatian Kuna (HRK)","Haitian Gourde (HTG)","Hungarian Forint (HUF)","Indonesian Rupiah (IDR)","Israeli New Sheqel (ILS)","Manx pound (IMP)","Indian Rupee (INR)","Iraqi Dinar (IQD)","Iranian Rial (IRR)","Icelandic Króna (ISK)","Jersey Pound (JEP)","Jamaican Dollar (JMD)","Jordanian Dinar (JOD)","Japanese Yen (JPY)","Kenyan Shilling (KES)","Kyrgystani Som (KGS)","Cambodian Riel (KHR)","Comorian Franc (KMF)","North Korean Won (KPW)","South Korean Won (KRW)","Kuwaiti Dinar (KWD)","Cayman Islands Dollar (KYD)","Kazakhstani Tenge (KZT)","Laotian Kip (LAK)","Lebanese Pound (LBP)","Sri Lankan Rupee (LKR)","Liberian Dollar (LRD)","Lesotho Loti (LSL)","Lithuanian Litas (LTL)","Latvian Lats (LVL)","Libyan Dinar (LYD)","Moroccan Dirham (MAD)","Moldovan Leu (MDL)","Malagasy Ariary (MGA)","Macedonian Denar (MKD)","Myanma Kyat (MMK)","Mongolian Tugrik (MNT)","Macanese Pataca (MOP)","Mauritanian Ouguiya (MRO)","Mauritian Rupee (MUR)","Maldivian Rufiyaa (MVR)","Malawian Kwacha (MWK)","Mexican Peso (MXN)","Malaysian Ringgit (MYR)","Mozambican Metical (MZN)","Namibian Dollar (NAD)","Nigerian Naira (NGN)","Nicaraguan Córdoba (NIO)","Norwegian Krone (NOK)","Nepalese Rupee (NPR)","New Zealand Dollar (NZD)","Omani Rial (OMR)","Panamanian Balboa (PAB)","Peruvian Nuevo Sol (PEN)","Papua New Guinean Kina (PGK)","Philippine Peso (PHP)","Pakistani Rupee (PKR)","Polish Zloty (PLN)","Paraguayan Guarani (PYG)","Qatari Rial (QAR)","Romanian Leu (RON)","Serbian Dinar (RSD)","Russian Ruble (RUB)","Rwandan Franc (RWF)","Saudi Riyal (SAR)","Solomon Islands Dollar (SBD)","Seychellois Rupee (SCR)","Sudanese Pound (SDG)","Swedish Krona (SEK)","Singapore Dollar (SGD)","Saint Helena Pound (SHP)","Sierra Leonean Leone (SLL)","Somali Shilling (SOS)","Surinamese Dollar (SRD)","São Tomé and Príncipe Dobra (STD)","Salvadoran Colón (SVC)","Syrian Pound (SYP)","Swazi Lilangeni (SZL)","Thai Baht (THB)","Tajikistani Somoni (TJS)","Turkmenistani Manat (TMT)","Tunisian Dinar (TND)","Tongan Paʻanga (TOP)","Turkish Lira (TRY)","Trinidad and Tobago Dollar (TTD)","New Taiwan Dollar (TWD)","Tanzanian Shilling (TZS)","Ukrainian Hryvnia (UAH)","Ugandan Shilling (UGX)","United States Dollar (USD)","Uruguayan Peso (UYU)","Uzbekistan Som (UZS)","Venezuelan Bolívar Fuerte (VEF)","Vietnamese Dong (VND)","Vanuatu Vatu (VUV)","Samoan Tala (WST)","CFA Franc BEAC (XAF)","Silver (troy ounce) (XAG)","Gold (troy ounce) (XAU)","East Caribbean Dollar (XCD)","Special Drawing Rights (XDR)","CFA Franc BCEAO (XOF)","CFP Franc (XPF)","Yemeni Rial (YER)","South African Rand (ZAR)","Zambian Kwacha (pre-2013) (ZMK)","Zambian Kwacha (ZMW)","Zimbabwean Dollar (ZWL)"};
     EditText editText;
     TextView textView;
     int fromPos=0;
     int toPos=0;
    ConvertCurrency currency=new ConvertCurrency();
    public CurrencyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CurrencyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CurrencyFragment newInstance(String param1, String param2) {
        CurrencyFragment fragment = new CurrencyFragment();
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
        Calendar calendar = Calendar.getInstance();
        Date currentTime = calendar.getTime();
        DateFormat time = new SimpleDateFormat("HH:mm a");
        String localTime = time.format(currentTime);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("com.example.help_me_out", Context.MODE_PRIVATE);
        boolean firstTime=sharedPreferences.contains("Time");
        long diff_min=0;
        if(firstTime)
        {
        String t = sharedPreferences.getString("Time", "DEfault");


        try {
            Date start = (Date) time.parse(t);
            long timeDiff = currentTime.getTime() - start.getTime();
            diff_min = TimeUnit.MILLISECONDS.toMinutes(timeDiff) % 60;
        } catch (ParseException e) {
            diff_min = 0;
        }}
        if (diff_min > 360 || !firstTime) {
            DownloadCurrency dc = new DownloadCurrency(getActivity(),localTime);
            dc.execute("http://api.currencylayer.com/live?access_key=d15eba9e10098506f3783dae7f876b98");
            Toast.makeText(getContext(), "Updating", Toast.LENGTH_SHORT).show();

        } else {
            currency.load(getActivity(), "CURRENCY_TABLE");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_currency, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Spinner from=view.findViewById(R.id.spinFromCurr);
        Spinner to=view.findViewById(R.id.spinToCurr);
        //textView=view.findViewById(R.id.textToCurr);
         editText=view.findViewById(R.id.fromCurr);
        from.setOnItemSelectedListener(this);
        Button convert=view.findViewById(R.id.convert);
        ArrayAdapter<String> ad= new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item,curr);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        from.setAdapter(ad);
        to.setOnItemSelectedListener(this);
        TickerView tickerView=view.findViewById(R.id.tickerView);
        ArrayAdapter<String> adto= new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item,curr);
        adto.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        to.setAdapter(adto);
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double amt;
                try {
                    amt = Double.parseDouble(editText.getText().toString());
                }
                catch (Exception e)
                {
                    amt=0.0;
                }
                String convertAmt=currency.convertCurrency(fromPos,toPos,amt);
                if(!convertAmt.equals("Invalid")) {
                    tickerView.setCharacterLists(TickerUtils.provideNumberList());
                    tickerView.setText(convertAmt);
                }
                else
                {
                    Toast.makeText(getContext(), "Check your Internet Connection ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int id2=parent.getId();
        if(id2==R.id.spinFromCurr)
        {
         fromPos=position;
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