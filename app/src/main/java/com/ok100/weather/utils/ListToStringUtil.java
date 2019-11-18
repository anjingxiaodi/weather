package com.ok100.weather.utils;


import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class ListToStringUtil {

    public static String listToStringDefule(List<String> arrayList){
        String string = "";
        for (int i = 0 ;i<arrayList.size();i++){
                    string = string + arrayList.get(i)+",";
            }
        if(string.endsWith(",")){
            string = string.substring(0,string.length()-1);
        }
        return string ;
    }


    public static List<String> stringToList(String string){
        ArrayList<String> arraylist = new ArrayList<>();
        if(TextUtils.isEmpty(string)){
            return arraylist;
        }
        String[] parts = string.split(",");
        if(parts!=null){
            for (int i= 0;i<parts.length;i++){
                arraylist.add(parts[i]);
            }
        }
        return arraylist;

    }
}
