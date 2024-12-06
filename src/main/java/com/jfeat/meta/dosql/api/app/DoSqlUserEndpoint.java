package com.jfeat.meta.dosql.api.app;

import com.alibaba.fastjson.JSONObject;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.crud.base.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.meta.dosql.services.domain.dao.QueryDoSqlVersionDao;
import com.jfeat.meta.dosql.services.domain.service.DoSqlServices;
import com.jfeat.meta.dosql.services.gen.persistence.model.DoSqlVersion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Api("Devops")
@RequestMapping("/api/u/lc/dosql")
public class DoSqlUserEndpoint {

    @Resource
    DoSqlServices devopsServices;

    @Resource
    QueryDoSqlVersionDao queryDevVersionDao;

    @Resource
    StringRedisTemplate stringRedisTemplate;

//    存储当前用户选择的 用户条件key  dev:userId:id
    private static final String redisKeyPrefix = "dev:userId:";


    @ApiOperation(value = "查询sql语句", response = HttpServletRequest.class)
    @GetMapping("/{sqlFile}")
    public Tip getResultList(@PathVariable("sqlFile") String sqlFile, HttpServletRequest request) {
        return SuccessTip.create(devopsServices.querySql(request, sqlFile));
    }

    @ApiOperation(value = "执行sql 语句", response = HttpServletRequest.class)
    @PostMapping("/{sqlFile}")
    public Tip executeSql(@PathVariable("sqlFile") String sqlFile, HttpServletRequest request) {
        return SuccessTip.create(devopsServices.executeSql(request, sqlFile));
    }

    //    获取当前app数据 运维操作
    @ApiOperation(value = "获取app 操作列表")
    @GetMapping("/appidOperationList")
    public Tip getAppidOperationList(@RequestParam("appid") String appid) {

        DoSqlVersion record = new DoSqlVersion();
        record.setAppid(appid);
        List<DoSqlVersion> devVersionRecordList = queryDevVersionDao.queryVersionDetail(null,record,null);
//        System.out.println(devVersionRecordList);
        if (devVersionRecordList!=null && devVersionRecordList.size()==1){
            return SuccessTip.create(devVersionRecordList.get(0));
        }
        return SuccessTip.create();
    }


    @ApiOperation(value = "获取当前用户")
    @GetMapping("/users/current")
    public Tip getCurrentUser(){
        Long userId = JWTKit.getUserId();
        String redisKey = redisKeyPrefix+String.valueOf(userId);
        if (userId==null){
            throw new BusinessException(BusinessCode.NoPermission,"没有登录");
        }
        if (stringRedisTemplate.hasKey(redisKey)){
            Long targetUser = Long.parseLong(stringRedisTemplate.opsForValue().get(redisKey)==null?"0":stringRedisTemplate.opsForValue().get(redisKey));
            if (targetUser!=null && targetUser>0){
                //return SuccessTip.create(userAccountMapper.selectById(targetUser));
                return SuccessTip.create(targetUser);
            }
        }

        return SuccessTip.create();
    }


    @BusinessLog(name = "DoSqlField", value = "create DoSqlField")
    @ApiOperation(value = "设置当前用户", response = JSONObject.class)
    @PutMapping("/users/current")
    public Tip setCurrentUser(@RequestBody JSONObject jsonObject){
        Long userId = JWTKit.getUserId();
        String redisKey = redisKeyPrefix+String.valueOf(userId);
        if (userId==null){
            throw new BusinessException(BusinessCode.NoPermission,"没有登录");
        }

        if (jsonObject==null || jsonObject.get("id")==null){
           throw new BusinessException(BusinessCode.BadRequest,"id必填项");
        }
        stringRedisTemplate.opsForValue().set(redisKey, jsonObject.get("id").toString());
        return SuccessTip.create(1);
    }

    @ApiOperation(value = "删除当前用户")
    @DeleteMapping("/users/current")
    public Tip deleteCurrentUser(){
        Long userId = JWTKit.getUserId();
        String redisKey = redisKeyPrefix+String.valueOf(userId);
        if (userId==null){
            throw new BusinessException(BusinessCode.NoPermission,"没有登录");
        }
        stringRedisTemplate.delete(redisKey);
        return SuccessTip.create(1);
    }

}
