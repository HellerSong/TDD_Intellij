package com.dao;

import com.pojo.Code_tPojo;
import com.pojo.CodelocalPojo;

import java.util.Hashtable;
import java.util.List;

/**
 * <p>Summary : </p>
 * <p>Authors : Heller Song (HellerSong@Outlook.com)</p>
 */
public class DropdownDao {
    public Hashtable<String, List<Hashtable<String, String>>> dropdownTable = new Hashtable<String, List<Hashtable<String, String>>>();

    public DropdownDao() {
        Hashtable<String, String> mappingTable = new Hashtable<String, String>();
        mappingTable.put("举报方式代码", "JBKJXSLY_LYFS");


        List<Code_tPojo> code_tList = new Code_tDao().getAll();
        List<CodelocalPojo> codelocalList = new CodelocalDao().getAll();

        for (Code_tPojo pojo : code_tList) {

            if (mappingTable.containsKey(pojo.getOptionName())) {
                Hashtable<String, String> tempHt = new Hashtable<String, String>();
                tempHt.put(pojo.getCodeId(), pojo.getContent());
                //dropdownTable.put(mappingTable.get(pojo.getOptionName()), tempHt);
            }
        }
//        for (CodelocalPojo pojo : codelocalList) {
//            Hashtable<String, String> tempHt = new Hashtable<String, String>();
//            tempHt.put(pojo.getCodeID(), pojo.getCONTENT());
//            if (mappingTable.containsKey(pojo.getOPTIONNAME())) {
//                dropdownTable.put(mappingTable.get(pojo.getOPTIONNAME()), tempHt);
//            }
//        }

        // 转往单位单独处理
    }
}
