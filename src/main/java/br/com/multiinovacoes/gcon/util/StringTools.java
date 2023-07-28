package br.com.multiinovacoes.gcon.util;

import java.util.TreeSet;

public class StringTools
{

    public StringTools()
    {
    }

    public static String[] arrayConcat(String array1[], String array2[])
    {
        String result[] = new String[array1.length + array2.length];
        for(int i = 0; i < array1.length; i++)
        {
            result[i] = array1[i];
        }

        for(int i = 0; i < array2.length; i++)
        {
            result[array1.length + i] = array2[i];
        }

        return result;
    }

    public static boolean arrayContains(String array[], String subArray[])
    {
        boolean result = array.length == 0;
        for(int i = 0; i < subArray.length; i++)
        {
            result = arrayContains(array, subArray[i]);
            if(!result)
            {
                return result;
            }
        }

        return result;
    }

    public static boolean arrayContains(String array[], String value)
    {
        return arrayIndexOf(array, value) >= 0;
    }

    public static int arrayContainsCount(String array[], String subArray[])
    {
        int result = 0;
        for(int i = 0; i < subArray.length; i++)
        {
            String value[] = {
                subArray[i]
            };
            if(arrayContains(array, value))
            {
                result++;
            }
        }

        return result;
    }

    public static String[] arrayGetElements(String array[], int fromIndex, int toIndex)
    {
        int count = (toIndex - fromIndex) + 1;
        String result[] = new String[count];
        for(int i = 0; i < count; i++)
        {
            result[i] = array[fromIndex + i];
        }

        return result;
    }

    public static int arrayIndexOf(String array[], String element)
    {
        for(int i = 0; i < array.length; i++)
        {
            if(array[i].equals(element))
            {
                return i;
            }
        }

        return -1;
    }

    public static String[] arraySort(String array[])
    {
        TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        for(int i = 0; i < array.length; i++)
        {
            treeSet.add(array[i]);
        }

        Object sorted[] = treeSet.toArray();
        String result[] = new String[array.length];
        for(int i = 0; i < result.length; i++)
        {
            result[i] = (String)sorted[i];
        }

        return result;
    }

    public static String arrayStringToString(String array[], String separator)
    {
        String result = "";
        for(int i = 0; i < array.length; i++)
        {
            result = result + array[i];
            if(i < array.length - 1)
            {
                result = result + separator;
            }
        }

        return result;
    }

    public static boolean compareArrays(String array1[], String array2[])
    {
        boolean result = false;
        if(array1.length != array2.length)
        {
            return result;
        }
        for(int i = 0; i < array1.length; i++)
        {
            result = array1[i].equalsIgnoreCase(array2[i]);
            if(!result)
            {
                return result;
            }
        }

        return result;
    }

    public static String format(String value, boolean toCamelCase, boolean toCaptalCase, boolean removeAccents, boolean removeSpaces, boolean spacesToUnderline)
    {
        String result = value;
        if(toCamelCase)
        {
            result = toCamelCase(result);
        }
        if(toCaptalCase)
        {
            result = toCaptalCase(result);
        }
        if(removeAccents)
        {
            result = removeAccents(result);
        }
        if(removeSpaces)
        {
            result = removeSpaces(result);
        }
        if(spacesToUnderline)
        {
            result = spacesToUnderline(result);
        }
        return result;
    }

    public static String formatCPFCNPJ(String value)
    {
        if(value.length() == 11)
        {
            return formatCustomMask(value, "000.000.000-00", '0');
        }
        if(value.length() == 14)
        {
            return formatCustomMask(value, "00.000.000/0000-00", '0');
        } else
        {
            return value;
        }
    }

    public static String formatCustomMask(String value, String mask, char maskChar)
    {
        if(value.equals(""))
        {
            return "";
        }
        String stResult = "";
        while(!mask.equals("")) 
        {
            if(mask.charAt(0) == maskChar)
            {
                stResult = stResult + value.charAt(0);
                value = value.substring(1);
            } else
            {
                stResult = stResult + mask.charAt(0);
            }
            mask = mask.substring(1);
            if(value.equals(""))
            {
                break;
            }
        }
        return stResult;
    }

    public static String htmlTextToText(String htmlText)
    {
        String result = htmlText;
        result = result.replaceAll("<br>", "\r\n");
        result = result.replaceAll("<br />", "\r\n");
        result = result.replaceAll("%20", " ");
        result = result.replace('"', '\'');
        return result;
    }

    public static String removeAccents(String value)
    {
        StringBuffer result = new StringBuffer(value);
        for(int i = 0; i < result.length(); i++)
        {
            char chChar = result.charAt(i);
            if("\341\340\343\342".indexOf(chChar) >= 0)
            {
                chChar = 'a';
            } else
            if("\351\350\352".indexOf(chChar) >= 0)
            {
                chChar = 'e';
            } else
            if("\355\354\356".indexOf(chChar) >= 0)
            {
                chChar = 'i';
            } else
            if("\363\362\364\365".indexOf(chChar) >= 0)
            {
                chChar = 'o';
            } else
            if("\372\371\373\374".indexOf(chChar) >= 0)
            {
                chChar = 'u';
            } else
            if("\347".indexOf(chChar) >= 0)
            {
                chChar = 'c';
            } else
            if("\301\300\302\303\302".indexOf(chChar) >= 0)
            {
                chChar = 'A';
            } else
            if("\311\312\310".indexOf(chChar) >= 0)
            {
                chChar = 'E';
            } else
            if("\315\314\316".indexOf(chChar) >= 0)
            {
                chChar = 'I';
            } else
            if("\323\322\324\325".indexOf(chChar) >= 0)
            {
                chChar = 'O';
            } else
            if("\332\331\333\334".indexOf(chChar) >= 0)
            {
                chChar = 'U';
            } else
            if("\307".indexOf(chChar) >= 0)
            {
                chChar = 'C';
            }
            result.setCharAt(i, chChar);
        }

        return result.toString();
    }

    public static String removeSpaces(String value)
    {
        return value.replaceAll(" ", "");
    }

    public static String spacesToUnderline(String value)
    {
        return value.replaceAll(" ", "_");
    }

    public static String textToHTMLText(String text)
    {
        if(text == null || text.equals(""))
        {
            return text;
        } else
        {
            String result = text;
            result = result.replaceAll("\r\n", "<br />");
            result = result.replaceAll("\n\r", "<br />");
            result = result.replaceAll("\r", "<br />");
            result = result.replaceAll("\n", "<br />");
            result = result.replace('"', '\'');
            return result;
        }
    }

    public static String toCamelCase(String value)
    {
        String result = "";
        String names[] = value.split(" ");
        for(int i = 0; i < names.length; i++)
        {
            String name = names[i];
            String firstChar = name.charAt(0) + "";
            result = result + (i != 0 ? firstChar.toUpperCase() : firstChar.toLowerCase()) + name.substring(1, name.length()).toLowerCase();
        }

        return result;
    }

    public static String toCaptalCase(String value)
    {
        String result = "";
        String names[] = value.split(" ");
        for(int i = 0; i < names.length; i++)
        {
            String name = names[i];
            String firstChar = name.charAt(0) + "";
            result = result + firstChar.toUpperCase() + name.substring(1, name.length()).toLowerCase();
        }

        return result;
    }
}
