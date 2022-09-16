package com.jfeat.am.module.meta.services.domain.service.impl;

import com.jfeat.am.module.meta.services.domain.dao.MetaSQLDataEditDao;
import com.jfeat.am.module.meta.services.domain.model.MetaField;
import com.jfeat.am.module.meta.services.domain.model.MetaTableInfo;
import com.jfeat.am.module.meta.services.domain.model.MetaWhereColumn;
import com.jfeat.am.module.meta.services.domain.model.SQLDataEditSetting;
import com.jfeat.am.module.meta.services.domain.service.MetaSQLDataEditService;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class MetaSQLDataEditServiceImpl implements MetaSQLDataEditService {

    @Resource
    SQLDataEditSetting sqlDataEditSetting;

    @Resource
    MetaSQLDataEditDao metaSQLDataEditDao;

    public static final String FIELD_TYPE_STRING = "String";
    public static final String FIELD_TYPE_INT = "int";

    @Override
    public Integer putField(String tableName, String fieldName, String value){
        MetaTableInfo tableInfo = check(tableName, fieldName);
        Integer integer = metaSQLDataEditDao.putField(tableName, fieldName, value, tableInfo.getWhere());
        return integer;
    }


    @Override
    public Integer postField(String tableName, String fieldName, String value){
        MetaTableInfo tableInfo = check(tableName, fieldName);
        MetaField field = tableInfo.getFieldByName(fieldName);

        if(field == null){
            throw new BusinessException(BusinessCode.BadRequest,"获取字段异常");
        }

        Integer postIndex = field.getPostIndex();
        String filedType = field.getFiledType();
        String separator = field.getSeparator();
        separator = separator==null?"":separator;

        if(filedType == null || filedType.equals(FIELD_TYPE_STRING)){
            String oldValueString = metaSQLDataEditDao.getOldValueString(tableName, fieldName, value, tableInfo.getWhere());
            oldValueString = oldValueString ==null?"":oldValueString;

            StringBuffer endBuffer = new StringBuffer();
            if(postIndex != null){
                if(oldValueString.length() <= postIndex){
                    endBuffer.append(oldValueString);
                    endBuffer.append(value);
                }else{
                    endBuffer =  new StringBuffer(oldValueString.substring(0,oldValueString.length()-postIndex));
                    endBuffer.append(separator);
                    endBuffer.append(value);
                    endBuffer.append(oldValueString.substring(oldValueString.length()-postIndex,oldValueString.length()));
                }

            }else{
                endBuffer = new StringBuffer(oldValueString);

                endBuffer.append(value);
            }
            return  metaSQLDataEditDao.putField(tableName, fieldName, endBuffer.toString(), tableInfo.getWhere());
        }else{
            Integer oldValue = metaSQLDataEditDao.getOldValueInteger(tableName, fieldName, value, tableInfo.getWhere());
            oldValue = oldValue ==null?0:oldValue;
            Integer intVale = Integer.parseInt(value);
            oldValue = intVale + oldValue;
            return  metaSQLDataEditDao.putField(tableName, fieldName, oldValue.toString(), tableInfo.getWhere());
        }



    }


    MetaTableInfo check(String tableName,String fieldName) {
        List<MetaTableInfo> tableInfo = sqlDataEditSetting.getTableInfo();
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();

        for (MetaTableInfo info : tableInfo) {
            if (info.getTable() != null && info.getTable().equals(tableName)) {
                if (info.getField() != null) {
                    for (MetaField field : info.getField()) {
                        if (field != null && field.getName().equals(fieldName)) {
                            List<MetaWhereColumn> wheres = info.getWhere();
                            List<MetaWhereColumn> comWHereList = new ArrayList<>();
                            for (MetaWhereColumn where : wheres) {
                                String mapping = where.getMapping();
                                String whereField = where.getField();
                                String value = null;
                                if (mapping != null) {
                                    value = request.getParameter(mapping);
                                } else {
                                    value = request.getParameter(whereField);
                                }
                                if (value == null) {

                                } else {
                                    where.setValue(value);
                                    comWHereList.add(where);
                                }
                            }

                            info.setWhere(comWHereList);

                            return info;
                        }
                    }


                }
            }
        }
            throw new BusinessException(BusinessCode.BadRequest, "未注册：table: " + tableName + " field " + fieldName);
    }

}
