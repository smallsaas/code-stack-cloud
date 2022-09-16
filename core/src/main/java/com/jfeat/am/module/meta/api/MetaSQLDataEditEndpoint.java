package com.jfeat.am.module.meta.api;

import com.jfeat.am.module.meta.services.domain.model.MateFieldBody;
import com.jfeat.am.module.meta.services.domain.model.SQLDataEditSetting;
import com.jfeat.am.module.meta.services.domain.service.MetaSQLDataEditService;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api("MetaSQLDataEditEndpoint")
@RequestMapping("/change")
public class MetaSQLDataEditEndpoint {

    @Resource
    SQLDataEditSetting sqlDataEditSetting;

    @Resource
    MetaSQLDataEditService metaSQLDataEditService;

    @GetMapping("/setting")
    public Tip getSetting(){
        SQLDataEditSetting sqlDataEditSetting = this.sqlDataEditSetting;
        return SuccessTip.create(sqlDataEditSetting.getTableInfo());
    }

    @PutMapping("/{table}/{field}")
    public Tip putMapping(@PathVariable("table")String table,@PathVariable("field")String field,@RequestBody MateFieldBody fieldBody){
        Integer integer = metaSQLDataEditService.putField(table, field, fieldBody.getValue());
        return SuccessTip.create(integer);
    }

    @PostMapping("/{table}/{field}")
    public Tip postMapping(@PathVariable("table")String table,@PathVariable("field")String field,@RequestBody MateFieldBody fieldBody){
        Integer integer = metaSQLDataEditService.postField(table, field, fieldBody.getValue());
        return SuccessTip.create(integer);
    }

}
