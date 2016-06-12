package com.dao;

import com.pojo.*;
import com.utils.StrOM;

import java.security.InvalidParameterException;
import java.util.Locale;

/**
 * <p>Summary: Mapping rules between Database and Entity Class.</p>
 * <p>Authors: Heller Song (HellerSong@Outlook.com)</p>
 **/
class DaoMapper {
    /**
     * Extract Rule, "com.hstdd.entity.HeroInfoPojo" => "HeroInfo"
     *
     * @param entityClass HeroInfoPojo
     * @return HeroInfo
     */
    private static String extractBasicEntityName(Class<?> entityClass) {
        String classFullName = entityClass.getName();
        // "com.hstdd.entity.HeroInfoPojo" => "HeroInfoPojo"
        String targetString = classFullName.substring(classFullName.lastIndexOf('.') + 1);
        // "HeroInfoPojo" => "HeroInfo"
        targetString = targetString.replaceAll("Pojo", "");

        return targetString;
    }

    /**
     * Mapping rule for entity class to Database table name, "HeroInfoPojo" => "t_heroInfo"
     *
     * @param entityClass HeroInfoPojo
     * @return TableName of t_heroInfo
     */
    static String mapDbTableFromEntityForDefault(Class<?> entityClass) {
        if (entityClass == null)
            throw new InvalidParameterException();

        String tempStr = extractBasicEntityName(entityClass);
        tempStr = StrOM.convertToLowerCamelCase(tempStr);

        return "t_" + tempStr;
    }

    /**
     * Mapping rule for entity class to database table main key name, "HeroInfoPojo" => "heroInfoId"
     *
     * @param entityClass HeroInfo
     * @return MainKey of heroInfoId
     */
    static String mapDbTableMainKeyFromEntityForDeafult(Class<?> entityClass) {
        if (entityClass == null)
            throw new InvalidParameterException();

        String tempStr = extractBasicEntityName(entityClass);
        tempStr = StrOM.convertToLowerCamelCase(tempStr);

        return tempStr + "Id";
    }


    /**
     * Mapping rule for entity class to Database table name, "HeroInfoPojo" => "heroinfo"
     *
     * @param entityClass HeroInfoPojo
     * @return TableName of heroinfo
     */
    static String mapDbTableFromEntity(Class<?> entityClass) {
        if (entityClass == null)
            throw new InvalidParameterException();

        String tempStr = extractBasicEntityName(entityClass);

        return tempStr.toLowerCase(Locale.ENGLISH);
    }

    /**
     * Mapping rule for entity class to database table main key name, "HeroInfoPojo" => "heroInfoId"
     *
     * @param entityClass HeroInfo
     * @return MainKey of heroInfoId
     */
    static String mapDbTableMainKeyFromEntity(Class<?> entityClass) {
        if (entityClass == null)
            throw new InvalidParameterException();

        String mainKey = null;

        if (entityClass.equals(JbkjxslyPojo.class)) {
            mainKey = "JBKJXSLY_ID";
        } else if (entityClass.equals(OrgmemberinfoPojo.class)) {
            mainKey = "ID";
        } else if (entityClass.equals(OrgnizePojo.class)) {
            mainKey = "ID";
        } else if (entityClass.equals(RoleinfoPojo.class)) {
            mainKey = "Id";
        } else if (entityClass.equals(XsclPojo.class)) {
            mainKey = "JBKJXSLY_XH";
        }

        return mainKey;
    }
}
