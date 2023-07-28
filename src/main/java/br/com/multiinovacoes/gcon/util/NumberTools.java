package br.com.multiinovacoes.gcon.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

// Referenced classes of package multiwork.util:
//            StringTools

public class NumberTools
{

    public NumberTools()
    {
    }

    public static String completeZero(int value, int length)
    {
        String result;
        for(result = Integer.toString(value); result.length() < length; result = '0' + result) { }
        return result;
    }

    public static int countFractionDigits(double value)
    {
        String numbers[] = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
        };
        String strValue = Double.toString(value);
        for(int i = 0; i < strValue.length(); i++)
        {
            String chr = strValue.charAt(strValue.length() - i - 1) + "";
            if(!StringTools.arrayContains(numbers, chr))
            {
                return i;
            }
        }

        return 0;
    }

    public static String format(double value)
    {
        return format(value, 2, 2);
    }

    public static String format(double value, int maxFractionDigits, int minimunFractionDigits)
    {
        NumberFormat format = DecimalFormat.getInstance();
        format.setMaximumFractionDigits(maxFractionDigits);
        format.setMinimumFractionDigits(minimunFractionDigits);
        return format.format(value);
    }

    public static double parseDouble(String value)
        throws Exception
    {
        return parseDouble(value, 2);
    }

    public static double parseDouble(String value, int maxFractionDigits)
        throws Exception
    {
        NumberFormat format = DecimalFormat.getInstance();
        Number number = format.parse(value);
        if(countFractionDigits(number.doubleValue()) > maxFractionDigits)
        {
            throw new Exception("A quantidade maxima de casas decimais (" + maxFractionDigits + ") " + "foi excedida em: " + value + ".");
        } else
        {
            return number.doubleValue();
        }
    }

    public static int parseInt(String value)
        throws Exception
    {
        String numbers[] = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
        };
        String newValue = "";
        for(int i = 0; i < value.length(); i++)
        {
            String number = value.substring(i, i + 1);
            if(StringTools.arrayIndexOf(numbers, number) >= 0)
            {
                newValue = newValue + number;
            }
        }

        if(newValue.equals(""))
        {
            return 0;
        } else
        {
            return Integer.parseInt(newValue);
        }
    }

    public static double round(double value)
    {
        return round(value, 2);
    }

    public static double round(double value, int precision)
    {
        double precisionMultiplier = Math.pow(10D, precision);
        value *= precisionMultiplier;
        value = Math.round(value);
        value /= precisionMultiplier;
        return value;
    }
}
