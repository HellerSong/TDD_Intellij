package com.dao;

import com.pojo.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * <p>Summary : </p>
 * <p>Authors : Heller Song (HellerSong@Outlook.com)</p>
 */
public class DropdownDao {
    private static Hashtable<String, List<DropdownItem>> regionDropdownHt = new Hashtable<String, List<DropdownItem>>();
    private static Hashtable<String, List<DropdownItem>> searchDropdownHt = new Hashtable<String, List<DropdownItem>>();
    private static Hashtable<String, List<DropdownTreeNode>> treeDropdownHt = new Hashtable<String, List<DropdownTreeNode>>();
    private static Hashtable<String, String> mappingHt = new Hashtable<String, String>();

    static {
        initRegionDropdownHt();
        initSearchDropdownHt();
    }

    private DropdownDao() {

    }

    private static void initRegionDropdownHt() {
        regionDropdownHt.clear();

        //// Code_t库表中的下拉选项的处理
        Hashtable<String, String> code_tMappingHt = new Hashtable<String, String>();
        code_tMappingHt.put("JBKJXSLY_LYFS", "举报来源方式");
        code_tMappingHt.put("JBKJXSLY_LYZL", "举报函件种类");
        code_tMappingHt.put("JBKJXSLY_BJBRZTLB", "申诉主体代码");
        code_tMappingHt.put("CLFS", "举报线索处理方式");
        code_tMappingHt.put("JBKJXSLY_JYLX", "机要类型");
        code_tMappingHt.put("JBKJXSLY_MZ", "民族代码");
        code_tMappingHt.put("JBKJXSLY_ZZMM", "政治面貌代码");
        code_tMappingHt.put("JBKJXSLY_ZW", "干部职务名称代码");
        code_tMappingHt.put("JBKJXSLY_SF", "被举报人身份");
        code_tMappingHt.put("JBKJXSLY_TSSF", "被举报人身份");
        code_tMappingHt.put("JBKJXSLY_ZJ", "(举报)干部职级代码");
        code_tMappingHt.put("JBKJXSLY_QTZJ", "(举报)干部职级代码");
        code_tMappingHt.put("JBKJXSLY_ZYSXXZ", "案由代码");
        code_tMappingHt.put("JBKJXSLY_CYSXXZ", "案由代码");
        code_tMappingHt.put("JBKJXSLY_SALY", "商业贿赂领域");

        Code_tDao code_tDao = new Code_tDao();
        for (String key : code_tMappingHt.keySet()) {
            String optionType = code_tMappingHt.get(key);
            List<DropdownItem> itemList = new ArrayList<DropdownItem>();
            List<Code_tPojo> pojoList = code_tDao.getAll("where OptionName='" + optionType + "' order by CodeId");

            if (key.equals("JBKJXSLY_LYZL") || key.equals("JBKJXSLY_BJBRZTLB") || key.equals("JBKJXSLY_ZW") ||
                    key.equals("JBKJXSLY_SF") || key.equals("JBKJXSLY_TSSF") ||
                    key.equals("JBKJXSLY_ZJ") || key.equals("JBKJXSLY_QTZJ") ||
                    key.equals("JBKJXSLY_ZYSXXZ") || key.equals("JBKJXSLY_CYSXXZ") ||
                    key.equals("JBKJXSLY_SALY")) {
                DropdownItem firstDropdownItem = new DropdownItem();
                firstDropdownItem.setOptionType(optionType);
                firstDropdownItem.setOptionValue("0");
                firstDropdownItem.setOptionHtmlContent("请选择");
                itemList.add(firstDropdownItem);
            }

            for (Code_tPojo p : pojoList) {
                DropdownItem dropdownItem = new DropdownItem();
                dropdownItem.setOptionType(p.getOptionName());
                dropdownItem.setOptionValue(p.getCodeId());
                dropdownItem.setOptionHtmlContent(p.getContent());
                itemList.add(dropdownItem);
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
            List<DropdownItem> itemList = new ArrayList<DropdownItem>();
            List<CodelocalPojo> pojoList = codelocalDao.getAll("where OPTIONNAME='" + optionType + "' order by CONTENT");
            for (CodelocalPojo p : pojoList) {
                DropdownItem dropdownItem = new DropdownItem();
                dropdownItem.setOptionType(p.getOPTIONNAME());
                dropdownItem.setOptionValue(p.getCodeID());
                dropdownItem.setOptionHtmlContent(p.getCONTENT());
                itemList.add(dropdownItem);
            }
            regionDropdownHt.put(key, itemList);
        }
        mappingHt.putAll(codelocalMappingHt);
        codelocalDao.closeAll();


        //// 转往单位下拉选项单独处理
        OrgnizeDao orgnizeDao = new OrgnizeDao();
        List<DropdownTreeNode> treeNodeList = new ArrayList<DropdownTreeNode>();

        String companyOptionType = "转往单位";


//        DropdownItem firstDropdownItem = new DropdownItem();
//        firstDropdownItem.setOptionType("转往单位");
//        firstDropdownItem.setOptionValue("0");
//        firstDropdownItem.setOptionHtmlContent("请选择单位");
//        itemList.add(firstDropdownItem);

        List<OrgnizePojo> firstLevelPojoList = orgnizeDao.getAll("where IsZWDW='1' and MemberType='otTemp'");
        for (OrgnizePojo firstP : firstLevelPojoList) {
            DropdownTreeNode treeNode = new DropdownTreeNode();
            treeNode.setOptionType(companyOptionType);
            treeNode.setOptionValue(firstP.getID());
            treeNode.setOptionHtmlContent(firstP.getDisplayName());

            // Get second dropdown items
            List<DropdownItem> itemList = new ArrayList<DropdownItem>();
            List<OrgnizePojo> secondLevelPojoList = orgnizeDao.getAll("where IsZWDW='1' and OwnerId='" + firstP.getOwnerID() + "'");
            for (OrgnizePojo p : secondLevelPojoList) {

                DropdownItem dropdownItem = new DropdownItem();
                dropdownItem.setOptionType(companyOptionType);
                dropdownItem.setOptionValue(p.getID());
                dropdownItem.setOptionHtmlContent(p.getDisplayName());
                itemList.add(dropdownItem);
            }
            treeNode.setChildren(itemList);

            treeNodeList.add(treeNode);
        }


        Hashtable<String, String> orgnizeMappingHt = new Hashtable<String, String>();
        orgnizeMappingHt.put("JBKJXSLY_ZJDW", "转往单位");
        treeDropdownHt.put("JBKJXSLY_ZJDW", treeNodeList);
        orgnizeMappingHt.put("ZWDW", "转往单位");
        treeDropdownHt.put("ZWDW", treeNodeList);
        orgnizeMappingHt.put("CSDW", "转往单位");
        treeDropdownHt.put("CSDW", treeNodeList);


        mappingHt.putAll(orgnizeMappingHt);
        orgnizeDao.closeAll();
    }

    private static void initSearchDropdownHt() {
        searchDropdownHt.clear();

        String[] searchOptionArray = new String[]{"JBKJXSLY_LYFS"};

        for (String s : searchOptionArray) {
            // Add "All" item to dropdown item list
            List<DropdownItem> itemList = regionDropdownHt.get(s);
            List<DropdownItem> resultList = new ArrayList<DropdownItem>();

            DropdownItem firstDropdownItem = new DropdownItem();
            firstDropdownItem.setOptionType(mappingHt.get(s));
            firstDropdownItem.setOptionValue("0");
            firstDropdownItem.setOptionHtmlContent("全部");
            resultList.add(firstDropdownItem);
            for (DropdownItem p : itemList) {
                resultList.add(p);
            }

            searchDropdownHt.put(s, resultList);
        }
    }

    public static Hashtable<String, List<DropdownItem>> getRegionDropdownHt() {
        return regionDropdownHt;
    }

    public static Hashtable<String, List<DropdownItem>> getSearchDropdownHt() {
        return searchDropdownHt;
    }

    public static Hashtable<String, List<DropdownTreeNode>> getTreeDropdownHt() {
        return treeDropdownHt;
    }
}
