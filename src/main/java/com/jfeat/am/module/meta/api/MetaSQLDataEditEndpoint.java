package com.jfeat.am.module.meta.api;

import com.jfeat.am.module.meta.services.domain.model.MateFieldBody;
import com.jfeat.am.module.meta.services.domain.model.SQLDataEditSetting;
import com.jfeat.am.module.meta.services.domain.service.MetaSQLDataEditService;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Deprecated(since = "不支持配置文件配置")
@RestController
@Api("MetaSQLDataEditEndpoint")
@RequestMapping("/api/meta/change")
public class MetaSQLDataEditEndpoint {

    // 从配置文件 application.yml 中获取
    @Resource
    SQLDataEditSetting sqlDataEditSetting;

    @Resource
    MetaSQLDataEditService metaSQLDataEditService;

    @GetMapping("/setting")
    public Tip getSetting(){
        return SuccessTip.create(sqlDataEditSetting.getTableInfo());
    }

    @PutMapping("/{entity}/{field}")
    public Tip putMapping(@PathVariable("entity")String entity, @PathVariable("field")String field, @RequestBody MateFieldBody fieldBody){
        Integer integer = metaSQLDataEditService.putField(entity, field, fieldBody.getValue());
        return SuccessTip.create(integer);
    }

    @PostMapping("/{entity}/{field}")
    public Tip postMapping(@PathVariable("entity")String entity, @PathVariable("field")String field, @RequestBody MateFieldBody fieldBody){
        Integer integer = metaSQLDataEditService.postField(entity, field, fieldBody.getValue());
        return SuccessTip.create(integer);
    }

}
