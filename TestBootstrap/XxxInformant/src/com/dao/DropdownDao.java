package com.dao;

import com.pojo.Code_tPojo;
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
    private Hashtable<String, String> code_tMappingHt = new Hashtable<String, String>();
    private Hashtable<String, String> codelocalMappingHt = new Hashtable<String, String>();
    private Hashtable<String, String> orgnizeMappingHt = new Hashtable<String, String>();


    public DropdownDao() {
        //// Code_t库表中的下拉选项的处理
        code_tMappingHt.put("JBKJXSLY_LYFS", "举报方式代码");
        code_tMappingHt.put("JBKJXSLY_LYZL", "举报函件种类");
        code_tMappingHt.put("JBKJXSLY_BJBRZTLB", "申诉主体代码");
        Code_tDao code_tDao = new Code_tDao();

        for (String key : code_tMappingHt.keySet()) {
            String optionType = code_tMappingHt.get(key);
            List<DropdownItemPojo> itemList = new ArrayList<DropdownItemPojo>();
            List<Code_tPojo> pojoList = code_tDao.getAll("where OptionName='" + optionType + "'");
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

        CodelocalDao codelocalDao = new CodelocalDao();
        codelocalDao.closeAll();


        //// 转往单位下拉选项单独处理
        orgnizeMappingHt.put("JBKJXSLY_ZJDW", "转往单位");
        String key = "JBKJXSLY_ZJDW";
        OrgnizeDao orgnizeDao = new OrgnizeDao();
        List<DropdownItemPojo> itemList = new ArrayList<DropdownItemPojo>();
        List<OrgnizePojo> pojoList = orgnizeDao.getAll("where IsZWDW='1'");
        for (OrgnizePojo p : pojoList) {
            DropdownItemPojo dropdownItemPojo = new DropdownItemPojo();
            dropdownItemPojo.setOptionType(orgnizeMappingHt.get(key));
            dropdownItemPojo.setOptionValue(p.getID());
            dropdownItemPojo.setOptionHtmlContent(p.getDisplayName());
            itemList.add(dropdownItemPojo);
        }
        regionDropdownHt.put(key, itemList);
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
