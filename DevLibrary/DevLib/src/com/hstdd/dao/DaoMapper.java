package com.hstdd.dao;

import com.hstdd.utils.StrOM;

import java.security.InvalidParameterException;

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
		if (entityClass == null)
			throw new InvalidParameterException();

		String classFullName = entityClass.getName();
		// Example: "com.hstdd.entity.HeroInfoPojo" => "HeroInfoPojo"
		String targetString = classFullName.substring(classFullName.lastIndexOf('.') + 1);

		// Example: "HeroInfoPojo" => "HeroInfo"
		targetString = targetString.replaceAll("Pojo", "");

		return targetString;
	}

	/**
	 * Mapping rule for entity class to Database table name, "HeroInfoPojo" => "t_heroInfo"
	 *
	 * @param entityClass HeroInfoPojo
	 * @return TableName of t_heroInfo
	 */
	static String mapDbTableFromEntity(Class<?> entityClass) {
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
	static String mapDbTableMainKeyFromEntity(Class<?> entityClass) {
		if (entityClass == null)
			throw new InvalidParameterException();

		String tempStr = extractBasicEntityName(entityClass);
		tempStr = StrOM.convertToLowerCamelCase(tempStr);

		return tempStr + "Id";
	}
}
