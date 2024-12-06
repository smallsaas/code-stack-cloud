package com.jfeat.meta.dosql.services.domain.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfeat.meta.dosql.services.domain.service.ParseSql;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ParseSqlImp implements ParseSql {

    protected final Log logger = LogFactory.getLog(getClass());

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public String readSqlFile(String fileName) {

        String file = "devops/".concat(fileName).concat(".sql");
        try {
            logger.info(System.getProperty("java.class.path"));
            String sql = new String(Files.readAllBytes(Paths.get(file)));
            return sql;
        }catch (IOException e){
            logger.error(e);
            logger.error(file);
        }

        return null;

    }

    @Override
    public String sqlParameters(String sql, Map<String, String> parameter) {

//        遍历键值对 替换sql文件中的参数
        for(Map.Entry<String, String> entry : parameter.entrySet()){
            String mapKey = entry.getKey();
            String mapValue = entry.getValue();
//            匹配sql文件中的参数 进行全部替换
            String regex = "".concat("#\\{").concat(mapKey).concat("}");
            sql =  sql.replaceAll(regex,"".concat("'").concat(mapValue).concat("'"));
        }
        return sql;
    }

    @Override
    public int executeSql(String sql) {
        List<String> sqlList = loadSql(sql);
        int affect = 0;
        for (String s:sqlList){
            try {
                logger.info(s);
                affect+=jdbcTemplate.update(s);
            }catch (Exception e){
                logger.error(e);
                logger.error("sql:"+s);
            }
        }
        return affect;
    }



    @Override
    public JSONArray querySql(String sql) {
        List<String> sqlList = loadSql(sql);
        JSONArray result = new JSONArray();

        for (String s:sqlList){
            logger.info(s);

//            只有当select 开头的语句进行查询
            if (s.toUpperCase().startsWith("SELECT")){
                try {
                    List<Map<String, Object>> list = jdbcTemplate.queryForList(s);

//                    将查询结果转换为jsonArray
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("rows",list);
                    JSONArray jsonArray = jsonObject.getJSONArray("rows");
                    result.add(jsonArray);
                }catch (Exception e){
                    logger.error("sql语句："+s);
                }

            }

        }

        return result;
    }


    /**
     * 将sql语句 分成一条一条语句列表
     * @param sql
     * @return
     */
    public  List<String> loadSql(String sql) {
        List<String> sqlList = new ArrayList<String>();
        try {
            // Windows 下换行是 \r\n, Linux 下是 \n
            String[] sqlArr = sql.split("(;\\s*\\r\\n)|(;\\s*\\n)");
            for (int i = 0; i < sqlArr.length; i++) {
//                sql中的注释
                String sqlRow = sqlArr[i].replaceAll("--.*", "").trim();
                if (!sqlRow.equals("")) {
                    sqlList.add(sqlRow);
                }
            }
            return sqlList;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
