package com.dao;

import com.utils.DevLog;
import com.utils.StrOM;

import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.InvalidParameterException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * <p>Summary: Data flow converter among Entity, ResultSet, PreparedStatement.</p>
 * <p>Authors: Heller Song (HellerSong@Outlook.com)</p>
 **/
class DaoConverter {
    private class EntityConvertModel {
        private String fieldName;
        private Class<?> fieldType;
        private Method getterMethod;
        private Method setterMethod;

        EntityConvertModel() {
        }

        String getFieldName() {
            return fieldName;
        }

        void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        Class<?> getFieldType() {
            return fieldType;
        }

        void setFieldType(Class<?> fieldType) {
            this.fieldType = fieldType;
        }

        Method getGetterMethod() {
            return getterMethod;
        }

        void setGetterMethod(Method getterMethod) {
            this.getterMethod = getterMethod;
        }

        Method getSetterMethod() {
            return setterMethod;
        }

        void setSetterMethod(Method setterMethod) {
            this.setterMethod = setterMethod;
        }

    }

    private String mainKeyName;
    private List<EntityConvertModel> modelList = new ArrayList<EntityConvertModel>();

    DaoConverter(Class<?> entityClass, String mainKeyName) {
        //// Some scenario should exclude main key as auto increment
        this.mainKeyName = mainKeyName;

        //// At first, I hope get in one collection set such as: { "name", "getName", "setName" }
        for (Field f : entityClass.getDeclaredFields()) {
            // Find the field name
            String fieldName = f.getName();
            EntityConvertModel model = new EntityConvertModel();
            model.setFieldName(fieldName);
            model.setFieldType(f.getType());

            // Find the get methods by field name
            String getterMethodName = "get" + StrOM.upperWordFirstLetter(fieldName);
            for (Method m : entityClass.getMethods()) {
                if (m.getName().equals(getterMethodName)) {
                    model.setGetterMethod(m);
                    break;
                }
            }

            // Find the set methods by field name
            String setterMethodName = "set" + StrOM.upperWordFirstLetter(fieldName);
            for (Method m : entityClass.getMethods()) {
                if (m.getName().equals(setterMethodName)) {
                    model.setSetterMethod(m);
                    break;
                }
            }

            modelList.add(model);
        }
    }

    private <T> PreparedStatement setPstmtData(T t, PreparedStatement pstmt, int pstmtIndex, EntityConvertModel model)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException {
        if (model == null || pstmt == null || pstmtIndex <= 0)
            throw new InvalidParameterException();

        Class<?> fType = model.getFieldType();
        Method getterMethod = model.getGetterMethod();
        if (fType.equals(int.class)) {
            int tempInt = (Integer) getterMethod.invoke(t);
            pstmt.setInt(pstmtIndex, tempInt);
        } else if (fType.equals(String.class)) {
            String tempStr = (String) getterMethod.invoke(t);
            pstmt.setString(pstmtIndex, tempStr);
        } else if (fType.equals(Timestamp.class)) {
            Timestamp tempTime = (Timestamp) getterMethod.invoke(t);
            pstmt.setTimestamp(pstmtIndex, tempTime);
        } else if (fType.equals(boolean.class)) {
            boolean tempBool = (Boolean) getterMethod.invoke(t);
            pstmt.setBoolean(pstmtIndex, tempBool);
        }

        return pstmt;
    }

    /**
     * Convert ResultSet data into Entity instance.
     *
     * @param t,  the entity instance you want to set data;
     * @param rs, the database result set;
     * @return T, the instance with ResultSet data in it;
     */
    <T> T convertDatabaseDataToEntityForReading(T t, ResultSet rs) {
        if (t == null || rs == null)
            throw new InvalidParameterException();

        try {
            for (EntityConvertModel model : modelList) {
                String fName = model.getFieldName();
                Class<?> fType = model.getFieldType();
                Method setterMethod = model.getSetterMethod();

                if (fType.equals(int.class)) {
                    setterMethod.invoke(t, rs.getInt(fName));
                } else if (fType.equals(String.class)) {
                    setterMethod.invoke(t, rs.getString(fName));
                } else if (fType.equals(Timestamp.class)) {
                    setterMethod.invoke(t, rs.getTimestamp(fName));
                } else if (fType.equals(Boolean.class)) {
                    setterMethod.invoke(t, rs.getBoolean(fName));
                }
            }
        } catch (Exception e) {
            DevLog.write(e);
        }

        return t;
    }

    /**
     * Convert Entity data into database table by add SQL command.
     *
     * @param t,     an instance for target entity;
     * @param pstmt, the database prepared statement;
     * @return Execution result is success or failed;
     */
    <T> boolean convertEntityToDatabaseDataForAdding(T t, PreparedStatement pstmt) {
        if (t == null || pstmt == null)
            throw new InvalidParameterException();

        boolean flag = false;

        try {
            //// Set data to prepared statement by pstmtIndex
            int pstmtIndex = 1;

            for (EntityConvertModel model : modelList) {
                setPstmtData(t, pstmt, pstmtIndex, model);
                pstmtIndex++;
            }


            //// Execute to insert data
            pstmt.execute();
            flag = true;
        } catch (Exception e) {
            DevLog.write(e);
        }

        return flag;
    }

    /**
     * Convert Entity data into database table by update SQL command.
     *
     * @param t,     an instance for target entity;
     * @param pstmt, the database prepared statement;
     * @return Execution result is success or failed;
     */
    <T> boolean convertEntityToDatabaseDataForUpdating(T t, PreparedStatement pstmt) {
        if (t == null || pstmt == null)
            throw new InvalidParameterException();

        boolean flag = false;

        try {
            //// Set data to prepared statement by pstmtIndex
            int pstmtIndex = 1;

            // Set field values except main key as  it is the last to be set
            EntityConvertModel mainKeyModel = null;
            for (EntityConvertModel model : modelList) {
                if (model.getFieldName().equals(mainKeyName)) { // Except main key
                    mainKeyModel = model;
                } else {
                    pstmt = setPstmtData(t, pstmt, pstmtIndex, model);
                    pstmtIndex++;
                }
            }

            // Set main key value
            pstmt = setPstmtData(t, pstmt, pstmtIndex, mainKeyModel);


            //// Execute to update data
            pstmt.execute();
            flag = true;
        } catch (Exception e) {
            DevLog.write(e);
        }

        return flag;
    }

    /**
     * Convert Properties data into Entity instance.
     *
     * @param t,              the entity instance you want to set data;
     * @param propertiesFile, the properties file;
     * @return T, the instance with Properties data in it;
     */
    public <T> T convertPropertiesDataToEntity(T t, String propertiesFile) {
        if (t == null || propertiesFile == null || propertiesFile.length() <= 0)
            throw new InvalidParameterException();

        try {
            Properties properties = new Properties();
            properties.load(new FileReader(propertiesFile));

            for (EntityConvertModel model : modelList) {
                String fName = model.getFieldName();
                Class<?> fType = model.getFieldType();
                Method setMethod = model.getSetterMethod();
                String propertyValue = properties.getProperty(fName);

                if (fType.equals(int.class)) {
                    setMethod.invoke(t, Integer.parseInt(propertyValue));
                } else if (fType.equals(String.class)) {
                    setMethod.invoke(t, propertyValue);
                } else if (fType.equals(Timestamp.class)) {
                    setMethod.invoke(t, DateFormat.getInstance().parse(propertyValue));
                } else if (fType.equals(Boolean.class)) {
                    setMethod.invoke(t, Boolean.valueOf(propertyValue));
                }
            }
        } catch (Exception e) {
            DevLog.write(e);
        }

        return t;
    }

    /**
     * With the purpose of getting insert SQL command (Include main key).
     *
     * @return String like that "name, password, phone, email"
     */
    String getFieldNameJoinByComma() {
        String temp = "";

        for (EntityConvertModel model : modelList) {
            temp += model.getFieldName() + ",";
        }

        return temp.substring(0, temp.length() - 1);
    }

    /**
     * With the purpose of getting insert SQL command (Include main key).
     *
     * @return String like that "?, ?, ?, ?"
     */
    String getFieldsHolderString() {
        String temp = "";

        for (int i = 0; i < modelList.size(); i++) {
            temp += "?,";
        }

        return temp.substring(0, temp.length() - 1);
    }

    /**
     * With the purpose of getting insert SQL command (Except main key).
     *
     * @return String like that "name=?, password=?, phone=?, email=?"
     */
    String getFieldNameJoinByEqualAndQuestion() {
        String temp = "";

        for (EntityConvertModel model : modelList) {
            if (!model.getFieldName().equals(mainKeyName)) { // Exclude table main key
                temp += model.getFieldName() + "=?,";
            }
        }

        return temp.substring(0, temp.length() - 1);
    }
}
