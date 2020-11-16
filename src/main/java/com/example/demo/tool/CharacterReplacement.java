package com.example.demo.tool;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CharacterReplacement {
    public static void main(String[] args) {
//        String str="12313xxxC";
//        System.out.println(str.toUpperCase());

        //System.out.println(str==null?"1":str);
        //str.substring(str.length() - 6)
//        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy");
//        String date=dateFormat.format(new Date());
//        String code;
//        if(date.equals(str.substring(str.length() - 10,str.length() - 6))){
//            int newEquipment = Integer.parseInt(str.substring(str.length() - 6)) + 1;
//            code= String.format(str.substring(0,str.length() - 6) + "%06d", newEquipment);
//            System.out.println(code);
//        }else {
//            code=String.format("XH"+date+ "%06d", 1);
//            System.out.println(code);
//        }

//        String str="79a00dd5a0a1373aa8d0aafebb31ac54保险单.jpg";
//        System.out.println(str.substring(32,str.length()));

        Map<String,String> map= new HashMap<>();
        map.put("1","一");
        map.put("2","二");
        Set<String> set=map.keySet();
        Iterator<String> it=set.iterator();
        String key;
        while(it.hasNext()){
            key=it.next();
            System.out.println(key+"val:"+map.get(key));
        }
    }
}
