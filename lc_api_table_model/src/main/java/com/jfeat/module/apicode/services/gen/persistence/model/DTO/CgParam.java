package com.jfeat.module.apicode.services.gen.persistence.model.DTO;

import lombok.Data;

import java.util.List;

/**
 * @description: 生成代码api的参数类，用于定义这个api所需的参数
 * @project: code-stack-cloud
 * @date: 2024/5/10 14:26
 * @author: hhhhhtao
 */
@Data
public class CgParam {
    /**
     * 项目名
     */
    private String project;
    /**
     * 子模块名
     */
    private String module;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 特征列表
     */
    private List<String> features;
    /**
     * 建表sql，生成代码工具根据这个sql进行生成代码中的实体类
     */
    private String sql;
    /**
     * 子表列表
     */
    private List<Slave> slaves;

    /**
     * 子表内部类，用于定义子表的参数
     */
    @Data
    public static class Slave {
        /**
         * 子表名
         */
        private String tableName;
        /**
         * 和主表的关联id
         */
        private String masterId;
        /**
         * 主表实体类的存放子表实体类列表的字段名
         */
        private String itemsKey = "items";
        /**
         * 中间表
         */
        private Peer peer;
    }

    /**
     * 中间表内部类，用于定义中间表的参数
     */
    @Data
    public static class Peer {
        /**
         * 中间表名
         */
        private String tableName;
        /**
         * 关联主表字段
         */
        private String masterId;
        /**
         * 关联子表字段
         */
        private String masterPeerId;
    }
}
