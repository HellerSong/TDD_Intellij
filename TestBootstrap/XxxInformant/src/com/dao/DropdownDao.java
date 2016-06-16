package com.dao;

import com.pojo.Code_tPojo;
import com.pojo.DropdownItemPojo;

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
        mappingHt.put("JBKJXSLY_LYFS", "举报方式代码");

        Code_tDao code_tDao = new Code_tDao();

        for (String key : mappingHt.keySet()) {
            String optionType = mappingHt.get(key);
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


        //// Codelocal库表中的下拉选项的处理

        CodelocalDao codelocalDao = new CodelocalDao();


        // 转往单位下拉选项单独处理
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
