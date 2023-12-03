package com.workflow.utils;

import java.util.ArrayList;
import java.util.List;

public class StringParser {
    public static List<String> parseStringAttr(String line){
        String[] split = line.split("\\$");
        List<String> list=new ArrayList<>();
        for (String s : split) {
            int l = s.indexOf('{');
            int r = s.indexOf('}');
            if (!(l == -1 || r == -1)){
                list.add(s.substring(l + 1, r));
            }
        }
        return list;
    }
}
