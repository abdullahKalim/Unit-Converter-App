package com.example.help_me_out;

import android.content.Context;
import android.util.Log;

import java.util.List;

public class ConvertCurrency {
    double rates[]=new double[168];
    public void load(Context context,String table)
    {
        DataBaseHandler data=new DataBaseHandler(context);
        List<DataModule> list=data.getAll(table);
        int i=0;
        for(DataModule t:list)
        {
            rates[i]=Double.parseDouble(t.getResult());
            Log.i("Rate =  ",String.valueOf(rates[i]));
            i++;
        }
    }

    public String convertCurrency(int fromPos,int toPos,double amt)
    {

            String res = String.valueOf(amt * (rates[toPos] / rates[fromPos]));
            return res;

    }

}
