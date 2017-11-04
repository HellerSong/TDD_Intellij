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

    static {
        initRegionDropdownHt();
        initSearchDropdownHt();
        initTreeDropdownHt();
    }

    private DropdownDao() {

    }

    private static void initRegionDropdownHt() {
        //// Code_t库表中的下拉选项的处理
        regionDropdownHt.clear();
        Hashtable<String, String> mappingHt = new Hashtable<String, String>();
        mappingHt.put("JBKJXSLY_LYFS", "举报来源方式");
        mappingHt.put("JBKJXSLY_LYZL", "举报函件种类");
        mappingHt.put("JBKJXSLY_BJBRZTLB", "主体类别");
        mappingHt.put("CLFS", "举报线索处理方式");
        mappingHt.put("JBKJXSLY_JYLX", "机要类型");
        mappingHt.put("JBKJXSLY_MZ", "民族代码");
        mappingHt.put("JBKJXSLY_ZZMM", "政治面貌代码");
        mappingHt.put("JBKJXSLY_ZW", "干部职务名称代码");
        mappingHt.put("JBKJXSLY_SF", "被举报人身份");
        mappingHt.put("JBKJXSLY_ZJ", "(举报)干部职级代码");
        mappingHt.put("JBKJXSLY_ZJ_FULL", "(举报)干部职级代码");
        mappingHt.put("JBKJXSLY_ZYSXXZ", "案由代码");
        mappingHt.put("JBKJXSLY_CYSXXZ", "案由代码");
        mappingHt.put("JBKJXSLY_SALY", "商业贿赂领域");

        Code_tDao code_tDao = new Code_tDao();
        for (String key : mappingHt.keySet()) {
            List<DropdownItem> itemList = new ArrayList<DropdownItem>();
            String optionType = mappingHt.get(key);

            // Add first item if necessary
            if (key.equals("JBKJXSLY_LYZL") || key.equals("JBKJXSLY_BJBRZTLB") || key.equals("JBKJXSLY_ZW") ||
                    key.equals("JBKJXSLY_SF") ||
                    key.equals("JBKJXSLY_ZJ") ||
                    key.equals("JBKJXSLY_ZYSXXZ") || key.equals("JBKJXSLY_CYSXXZ") ||
                    key.equals("JBKJXSLY_SALY")) {
                DropdownItem firstDropdownItem = new DropdownItem();
                firstDropdownItem.setId("0");
                firstDropdownItem.setText("请选择");
                itemList.add(firstDropdownItem);
            }

            for (Code_tPojo p : code_tDao.getAll("where OptionName='" + optionType + "' order by CodeId")) {
                DropdownItem dropdownItem = new DropdownItem();
                dropdownItem.setId(p.getCodeId());
                if (key.equals("JBKJXSLY_ZJ_FULL")) {
                    dropdownItem.setText("[" + p.getContent() + "]  -  " + p.getScript());
                } else {
                    dropdownItem.setText(p.getContent());
                }
                itemList.add(dropdownItem);
            }

            regionDropdownHt.put(key, itemList);
        }
        code_tDao.closeAll();

        //// Add 案发地区 一级下拉选项
        CodelocalDao codelocalDao = new CodelocalDao();
        List<DropdownItem> itemList = new ArrayList<DropdownItem>();
        for (CodelocalPojo p : codelocalDao.getAll("where OPTIONNAME='地区代码' and OWNERID='-1' order by CodeID")) {
            DropdownItem dropdownItem = new DropdownItem();
            dropdownItem.setId(p.getCodeID());
            dropdownItem.setText(p.getCONTENT());
            itemList.add(dropdownItem);
        }
        regionDropdownHt.put("JBKJXSLY_AFDQ", itemList);
    }

    private static void initSearchDropdownHt() {
        searchDropdownHt.clear();

        String[] searchOptionArray = new String[]{"JBKJXSLY_LYFS", "JBKJXSLY_AFDQ"};

        for (String s : searchOptionArray) {
            // Add "All" item to dropdown item list
            List<DropdownItem> itemList = regionDropdownHt.get(s);
            List<DropdownItem> resultList = new ArrayList<DropdownItem>();

            DropdownItem firstDropdownItem = new DropdownItem();
            firstDropdownItem.setId("0");
            firstDropdownItem.setText("全部");
            resultList.add(firstDropdownItem);
            for (DropdownItem p : itemList) {
                resultList.add(p);
            }

            searchDropdownHt.put(s, resultList);
        }
    }

    private static void initTreeDropdownHt() {
        treeDropdownHt.clear();

        //// Add 单位 tree dropdown list
        List<DropdownTreeNode> companyTreeNodeList = new ArrayList<DropdownTreeNode>();
        OrgnizeDao orgnizeDao = new OrgnizeDao();
        DropdownTreeNode companyFirstTreeNode = new DropdownTreeNode();
        companyFirstTreeNode.setId("0");
        companyFirstTreeNode.setText("请选择单位");
        companyFirstTreeNode.setState("open");
        companyTreeNodeList.add(companyFirstTreeNode);

        for (OrgnizePojo firstP : orgnizeDao.getAll("where IsZWDW='1' and MemberType='otTemp'")) {
            DropdownTreeNode treeNode = new DropdownTreeNode();
            treeNode.setId(firstP.getID());
            treeNode.setText(firstP.getDisplayName());
            treeNode.setState("closed");

            // Get second dropdown items
            List<DropdownItem> childItemList = new ArrayList<DropdownItem>();
            for (OrgnizePojo p : orgnizeDao.getAll("where IsZWDW='1' and OwnerId='" + firstP.getID() + "'")) {

                DropdownItem dropdownItem = new DropdownItem();
                dropdownItem.setId(p.getID());
                dropdownItem.setText(p.getDisplayName());
                childItemList.add(dropdownItem);
            }
            treeNode.setChildren(childItemList);

            companyTreeNodeList.add(treeNode);
        }

        treeDropdownHt.put("COMPANY", companyTreeNodeList);
        orgnizeDao.closeAll();


        CodelocalDao codelocalDao = new CodelocalDao();

        //// Add 地区 tree dropdown list
        List<DropdownTreeNode> zoneTreeNodeList = new ArrayList<DropdownTreeNode>();
        DropdownTreeNode zoneFirstTreeNode = new DropdownTreeNode();
        zoneFirstTreeNode.setId("0");
        zoneFirstTreeNode.setText("请选择地区");
        zoneFirstTreeNode.setState("open");
        zoneTreeNodeList.add(zoneFirstTreeNode);

        for (CodelocalPojo firstP : codelocalDao.getAll("where OPTIONNAME='地区代码' and OWNERID='-1' order by CodeID")) {
            DropdownTreeNode treeNode = new DropdownTreeNode();
            treeNode.setId(firstP.getCodeID());
            treeNode.setText(firstP.getCONTENT());
            if (firstP.getCONTENT().contains("特别行政区")) {
                treeNode.setState("open");
            } else {
                treeNode.setState("closed");
            }

            // Get second dropdown items
            List<DropdownItem> childItemList = new ArrayList<DropdownItem>();
            for (CodelocalPojo p : codelocalDao.getAll("where OPTIONNAME='地区代码' and OWNERID='" + firstP.getID() + "'")) {
                DropdownItem dropdownItem = new DropdownItem();
                dropdownItem.setId(p.getCodeID());
                dropdownItem.setText(p.getCONTENT());
                childItemList.add(dropdownItem);
            }
            treeNode.setChildren(childItemList);

            zoneTreeNodeList.add(treeNode);
        }

        treeDropdownHt.put("ZONE", zoneTreeNodeList);

        //// Add 身份 tree dropdown list
        List<DropdownTreeNode> idTreeNodeList = new ArrayList<DropdownTreeNode>();
        DropdownTreeNode idFirstTreeNode = new DropdownTreeNode();
        idFirstTreeNode.setId("0");
        idFirstTreeNode.setText("请选择身份");
        idFirstTreeNode.setState("open");
        idTreeNodeList.add(idFirstTreeNode);

        for (CodelocalPojo firstP : codelocalDao.getAll("where OPTIONNAME='身份' and OWNERID='-1' order by CodeID")) {
            DropdownTreeNode treeNode = new DropdownTreeNode();
            treeNode.setId(firstP.getCodeID());
            treeNode.setText(firstP.getCONTENT());

            // Get second dropdown items
            List<DropdownItem> childItemList = new ArrayList<DropdownItem>();
            for (CodelocalPojo p : codelocalDao.getAll("where OPTIONNAME='身份' and OWNERID='" + firstP.getID() + "'")) {
                DropdownItem dropdownItem = new DropdownItem();
                dropdownItem.setId(p.getCodeID());
                dropdownItem.setText(p.getCONTENT());
                childItemList.add(dropdownItem);
            }
            treeNode.setChildren(childItemList);

            idTreeNodeList.add(treeNode);
        }

        treeDropdownHt.put("ID", idTreeNodeList);


        //// Add 特殊身份 tree dropdown list
        List<DropdownTreeNode> specialIdTreeNodeList = new ArrayList<DropdownTreeNode>();
        DropdownTreeNode specialIdFirstTreeNode = new DropdownTreeNode();
        specialIdFirstTreeNode.setId("0");
        specialIdFirstTreeNode.setText("请选择身份");
        specialIdFirstTreeNode.setState("open");
        specialIdTreeNodeList.add(specialIdFirstTreeNode);

        for (CodelocalPojo firstP : codelocalDao.getAll("where OPTIONNAME='特殊身份' and OWNERID='-1' order by CodeID")) {
            DropdownTreeNode treeNode = new DropdownTreeNode();
            treeNode.setId(firstP.getCodeID());
            treeNode.setText(firstP.getCONTENT());

            // Get second dropdown items
            List<DropdownItem> childItemList = new ArrayList<DropdownItem>();
            for (CodelocalPojo p : codelocalDao.getAll("where OPTIONNAME='特殊身份' and OWNERID='" + firstP.getID() + "'")) {
                DropdownItem dropdownItem = new DropdownItem();
                dropdownItem.setId(p.getCodeID());
                dropdownItem.setText(p.getCONTENT());
                childItemList.add(dropdownItem);
            }
            treeNode.setChildren(childItemList);

            specialIdTreeNodeList.add(treeNode);
        }

        treeDropdownHt.put("SPECIAL_ID", specialIdTreeNodeList);


        //// Add 涉嫌性质 tree dropdown list
        List<DropdownTreeNode> natureTreeNodeList = new ArrayList<DropdownTreeNode>();
        DropdownTreeNode natureFirstTreeNode = new DropdownTreeNode();
        natureFirstTreeNode.setId("0");
        natureFirstTreeNode.setText("请选择性质");
        natureFirstTreeNode.setState("open");
        natureTreeNodeList.add(natureFirstTreeNode);

        for (CodelocalPojo firstP : codelocalDao.getAll("where OPTIONNAME='涉嫌性质' and OWNERID='-1' order by CodeID")) {
            DropdownTreeNode treeNode = new DropdownTreeNode();
            treeNode.setId(firstP.getCodeID());
            treeNode.setText(firstP.getCONTENT());
            if (firstP.getCONTENT().contains("一般违法") || firstP.getCONTENT().contains("其他")) {
                treeNode.setState("open");
            } else {
                treeNode.setState("closed");
            }

            // Get second dropdown items
            List<DropdownItem> childItemList = new ArrayList<DropdownItem>();
            for (CodelocalPojo p : codelocalDao.getAll("where OPTIONNAME='涉嫌性质' and OWNERID='" + firstP.getID() + "'")) {
                DropdownItem dropdownItem = new DropdownItem();
                dropdownItem.setId(p.getCodeID());
                dropdownItem.setText(p.getCONTENT());
                childItemList.add(dropdownItem);
            }
            treeNode.setChildren(childItemList);

            natureTreeNodeList.add(treeNode);
        }

        treeDropdownHt.put("NATURE", natureTreeNodeList);


        codelocalDao.closeAll();
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
