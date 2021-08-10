package com.example.help_me_out;

class NumberConversion
{
    public String DecToBin(double num)
    {
        String bin="";
        long n=(long)(num*Math.pow(2,8));
        while(n!=0)
        {
            bin=(n%2)+bin;
            n=n/2;
        }
        return bin.substring(0,bin.length()-8)+"."+bin.substring(bin.length()-8);
    }
    public String DecToOct(double num)
    {
        String oct="";
        long n=(long)(num*(Math.pow(8,8)));
        while(n!=0)
        {
            oct=n%8+oct;
            n=n/8;
        }
        return oct.substring(0,oct.length()-8)+"."+oct.substring(oct.length()-8);
    }
    public double BinToDec(String bin)
    {
        double dec=0;
        int i=bin.indexOf('.');
        boolean flag=true;
        for(int x=(bin.length()-1);x>=0;x--)
        {
            if(x==i&&flag)
            {
                i=i-1;
                flag=false;
                continue;
            }
            int t=Character.getNumericValue(bin.charAt(x));
            if(t!=0&&t!=1)
            {
                return -1;
            }
            dec=dec+t*Math.pow(2,(i==-1?(bin.length()-1)-x:(i-x)));
        }
        return dec;
    }
    public String DecToHex(double dec1)
    {
        String hex="";
        long dec=(long)(dec1*Math.pow(16,8));
        while((int)dec!=0)
        {
            double  rem=dec%16;
            char t=rem<=9.0?(char)(rem+48):(char)(55+rem);
            hex=String.valueOf(t)+hex;
            dec=dec/16;
        }
        int l=hex.length();
        return (hex.substring(0,l-8)+"."+hex.substring(l-8));
    }
    public String HexToDec(String hex)
    {
        double dec=0;
        int i=hex.indexOf('.');
        boolean flag=true;
        for(int x=(hex.length()-1);x>=0;x--)
        {
            if(x==i&&flag)
            {
                i=i-1;
                flag=false;
                continue;
            }
            char ch=hex.charAt(x);
            int t;
            if(ch=='A'||ch=='B'||ch=='C'||ch=='D'||ch=='E'||ch=='F')
            {
                t=(int)(ch-55);}
            else if((int)ch>=48&&(int)ch<=57)
            {
                t=Character.getNumericValue(ch);
            }
            else
            {
                return "Invalid Hex Number";
            }
            dec=dec+t*Math.pow(16,(i==-1?(hex.length()-1)-x:(i-x)));
        }
        return String.valueOf(dec);
    }
    public String HexToBin(String hex)
    {
        String dec=HexToDec(hex);
        return DecToBin(Double.parseDouble(dec));
    }
    public String HexToOct(String hex)
    {
        String dec=HexToDec(hex);
        return DecToOct(Double.parseDouble(dec));
    }
    public String BinToOct(String bin)
    {
        double dec=BinToDec(bin);
        if(dec==-1)
        {
            return "Invalid";
        }
        return DecToOct(dec);
    }
    public String BinToHex(String bin)
    {
        double dec=BinToDec(bin);
        if(dec==-1)
        {
            return "Invalid";
        }
        return DecToHex(dec);
    }
    public double OctToDec(String oct)
    {
        double dec=0;
        int i=oct.indexOf('.');
        boolean flag=true;
        for(int x=(oct.length()-1);x>=0;x--)
        {
            if(x==i&&flag)
            {
                i=i-1;
                flag=false;
                continue;
            }
            int t=Character.getNumericValue(oct.charAt(x));
            dec=dec+t*Math.pow(8,(i==-1?(oct.length()-1)-x:(i-x)));
        }
        return dec;
    }
    public String OctToBin(String oct)
    {
        double dec=OctToDec(oct);
        return DecToBin(dec);
    }
    public String OctToHex(String oct)
    {
        double dec=OctToDec(oct);
        return DecToHex(dec);
    }
}
