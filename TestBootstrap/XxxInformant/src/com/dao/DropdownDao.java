package com.dao;

import com.pojo.Code_tPojo;
import com.pojo.CodelocalPojo;
import com.pojo.DropdownItemPojo;
import com.pojo.OrgnizePojo;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * <p>Summary : </p>
 * <p>Authors : Heller Song (HellerSong@Outlook.com)</p>
 */
public class DropdownDao {
    private Hashtable<String, List<DropdownItemPojo>> regionDropdownHt = new Hashtable<String, List<DropdownItemPojo>>();
    private Hashtable<String, String> mappingHt = new Hashtable<String, String>();


    public DropdownDao() {
        //// Code_t库表中的下拉选项的处理
        Hashtable<String, String> code_tMappingHt = new Hashtable<String, String>();
        code_tMappingHt.put("JBKJXSLY_LYFS", "举报方式代码");
        code_tMappingHt.put("JBKJXSLY_LYZL", "举报函件种类");
        code_tMappingHt.put("JBKJXSLY_BJBRZTLB", "申诉主体代码");
        code_tMappingHt.put("CLYJ", "举报线索处理方式");
        code_tMappingHt.put("JBKJXSLY_JYLX", "机要类型");
        code_tMappingHt.put("JBKJXSLY_MZ", "民族代码");
        code_tMappingHt.put("JBKJXSLY_ZZMM", "政治面貌代码");
        code_tMappingHt.put("JBKJXSLY_ZJ", "(举报)干部职级代码");
        code_tMappingHt.put("JBKJXSLY_SF", "被举报人身份");
        code_tMappingHt.put("JBKJXSLY_TSSF", "被举报人身份");
        code_tMappingHt.put("JBKJXSLY_ZW", "干部职务名称代码");
        code_tMappingHt.put("JBKJXSLY_QTZW", "干部职务名称代码");
        code_tMappingHt.put("JBKJXSLY_ZYSXXZ", "案由代码");
        code_tMappingHt.put("JBKJXSLY_CYSXXZ", "案由代码");
        code_tMappingHt.put("JBKJXSLY_SALY", "商业贿赂领域");

        Code_tDao code_tDao = new Code_tDao();
        for (String key : code_tMappingHt.keySet()) {
            String optionType = code_tMappingHt.get(key);
            List<DropdownItemPojo> itemList = new ArrayList<DropdownItemPojo>();
            List<Code_tPojo> pojoList = code_tDao.getAll("where OptionName='" + optionType + "' order by Content");
            for (Code_tPojo p : pojoList) {
                DropdownItemPojo dropdownItemPojo = new DropdownItemPojo();
                dropdownItemPojo.setOptionType(p.getOptionName());
                dropdownItemPojo.setOptionValue(p.getCodeId());
                dropdownItemPojo.setOptionHtmlContent(p.getContent());
                itemList.add(dropdownItemPojo);
            }
            regionDropdownHt.put(key, itemList);
        }
        mappingHt.putAll(code_tMappingHt);
        code_tDao.closeAll();


        //// Codelocal库表中的下拉选项的处理
        Hashtable<String, String> codelocalMappingHt = new Hashtable<String, String>();
        codelocalMappingHt.put("JBKJXSLY_LXDQ", "地区代码");
        codelocalMappingHt.put("JBKJXSLY_AFDQ", "地区代码");

        CodelocalDao codelocalDao = new CodelocalDao();
        for (String key : codelocalMappingHt.keySet()) {
            String optionType = codelocalMappingHt.get(key);
            List<DropdownItemPojo> itemList = new ArrayList<DropdownItemPojo>();
            List<CodelocalPojo> pojoList = codelocalDao.getAll("where OPTIONNAME='" + optionType + "' order by CONTENT");
            for (CodelocalPojo p : pojoList) {
                DropdownItemPojo dropdownItemPojo = new DropdownItemPojo();
                dropdownItemPojo.setOptionType(p.getOPTIONNAME());
                dropdownItemPojo.setOptionValue(p.getCodeID());
                dropdownItemPojo.setOptionHtmlContent(p.getCONTENT());
                itemList.add(dropdownItemPojo);
            }
            regionDropdownHt.put(key, itemList);
        }
        mappingHt.putAll(codelocalMappingHt);
        codelocalDao.closeAll();


        //// 转往单位下拉选项单独处理
        OrgnizeDao orgnizeDao = new OrgnizeDao();
        List<DropdownItemPojo> itemList = new ArrayList<DropdownItemPojo>();
        List<OrgnizePojo> pojoList = orgnizeDao.getAll("where IsZWDW='1'");
        for (OrgnizePojo p : pojoList) {
            DropdownItemPojo dropdownItemPojo = new DropdownItemPojo();
            dropdownItemPojo.setOptionType("转往单位");
            dropdownItemPojo.setOptionValue(p.getID());
            dropdownItemPojo.setOptionHtmlContent(p.getDisplayName());
            itemList.add(dropdownItemPojo);
        }

        Hashtable<String, String> orgnizeMappingHt = new Hashtable<String, String>();
        orgnizeMappingHt.put("JBKJXSLY_ZJDW", "转往单位");
        regionDropdownHt.put("JBKJXSLY_ZJDW", itemList);
        orgnizeMappingHt.put("ZWDW", "转往单位");
        regionDropdownHt.put("ZWDW", itemList);
        orgnizeMappingHt.put("CSDW", "转往单位");
        regionDropdownHt.put("CSDW", itemList);


        mappingHt.putAll(orgnizeMappingHt);
        orgnizeDao.closeAll();
    }

    public Hashtable<String, List<DropdownItemPojo>> getDropdownHt() {
        return regionDropdownHt;
    }

    public Hashtable<String, List<DropdownItemPojo>> getSearchDropdownHt() {
        String[] searchDropdownArray = new String[]{"JBKJXSLY_LYFS"};
        Hashtable<String, List<DropdownItemPojo>> resultTable = new Hashtable<String, List<DropdownItemPojo>>();

        for (String s : searchDropdownArray) {
            // Add "All" item to dropdown item list
            List<DropdownItemPojo> itemList = regionDropdownHt.get(s);
            DropdownItemPojo dropdownItemPojo = new DropdownItemPojo();
            dropdownItemPojo.setOptionType(mappingHt.get(s));
            dropdownItemPojo.setOptionValue("0");
            dropdownItemPojo.setOptionHtmlContent("全部");
            itemList.add(0, dropdownItemPojo);

            resultTable.put(s, itemList);
        }

        return resultTable;
    }
}
