package com.jfeat.module.crudcode.api;

import com.alibaba.fastjson.JSONObject;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.module.crudcode.util.CompressionFile;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.zip.ZipOutputStream;

/**
 * @author: HTAO
 * @CreateDate: 2022/5/26 17:24
 */
@RestController
@Api("LowMainPage")
@RequestMapping("/api/pageconfig/lowMainPage/pageConfig")
public class PageConfig {

    @PostMapping("/{name}")
    public Tip buildPageConfig(@PathVariable String name,
                               @RequestBody JSONObject setting,
                               HttpServletResponse response) throws IOException {

        // 创建configGroups文件夹
        String projectPath = new File("").getAbsolutePath();
        File fileDir = new File("pageConfig");
        if (!fileDir.exists()) fileDir.mkdir();
        File pageConfigDir = new File("pageConfig/" + name);
        if (!pageConfigDir.exists()) pageConfigDir.mkdir();
        File configDir = new File("pageConfig/" + name + "/config");
        if (!configDir.exists()) configDir.mkdir();

        // 创建StringBuild存入字符串
        StringBuilder stringBuilder = new StringBuilder();

        // 创建-add.js文件
        String add = name + "-add";
        File pageConfigA = new File(pageConfigDir.getPath() + "/" + add +".js");
        try(FileWriter fileWriter = new FileWriter(pageConfigA)){
            stringBuilder.delete(0,stringBuilder.length());
            stringBuilder.append("import React from 'react';\n");
            stringBuilder.append("import ZEle from 'zero-element';\n");
            stringBuilder.append("import config from './config/"+ add +"';\n");
            stringBuilder.append("\n");
            stringBuilder.append("export default function () {\n");
            stringBuilder.append("\n");
            stringBuilder.append("\t\treturn <ZEle namespace=\"" + add + "\" config={config} />\n");
            stringBuilder.append("  }");
            fileWriter.write(stringBuilder.toString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 创建-edit.js文件
        String edit = name + "-edit";
        File pageConfigE = new File(pageConfigDir.getPath() + "/" + edit + ".js");
        try(FileWriter fileWriter = new FileWriter(pageConfigE)){
            stringBuilder.delete(0,stringBuilder.length());
            stringBuilder.append("import React from 'react';\n");
            stringBuilder.append("import ZEle from 'zero-element';\n");
            stringBuilder.append("import config from './config/" + edit + "';\n");
            stringBuilder.append("\n");
            stringBuilder.append("export default function () {\n");
            stringBuilder.append("\n");
            stringBuilder.append("\t\treturn <ZEle namespace=\"" + edit + "\" config={config} />\n");
            stringBuilder.append("  }");
            fileWriter.write(stringBuilder.toString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 创建-view.js文件
        String view = name + "-view";
        File pageConfigV = new File(pageConfigDir.getPath() + "/" + view + ".js");
        try(FileWriter fileWriter = new FileWriter(pageConfigV)){
            stringBuilder.delete(0,stringBuilder.length());
            stringBuilder.append("import React from 'react';\n");
            stringBuilder.append("import ZEle from 'zero-element';\n");
            stringBuilder.append("import config from './config/" + view + "';\n");
            stringBuilder.append("\n");
            stringBuilder.append("export default function () {\n");
            stringBuilder.append("\n");
            stringBuilder.append("\t\treturn <ZEle namespace=\"" + view + "\" config={config} />\n");
            stringBuilder.append("  }");
            fileWriter.write(stringBuilder.toString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 创建index.js文件
        File pageConfigIndex = new File(pageConfigDir.getPath() + "/index.js");
        try(FileWriter fileWriter = new FileWriter(pageConfigIndex)){
            stringBuilder.delete(0,stringBuilder.length());
            stringBuilder.append("import React from 'react';\n");
            stringBuilder.append("import ZEle from 'zero-element';\n");
            stringBuilder.append("import config from './config/index';\n");
            stringBuilder.append("\n");
            stringBuilder.append("export default function () {\n");
            stringBuilder.append("\n");
            stringBuilder.append("\treturn <ZEle namespace=\"" + name + "\" config={config} />\n");
            stringBuilder.append("  }");
            fileWriter.write(stringBuilder.toString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 在内部文件夹config中创建-add.js
        File configA = new File(configDir.getPath() + "/" + name + "-add.js");
        try(FileWriter fileWriter = new FileWriter(configA)){
            stringBuilder.delete(0,stringBuilder.length());
            stringBuilder.append("const setting = require('./" + name + "-setting.json');\n");
            stringBuilder.append("\n");
            stringBuilder.append("module.exports = {\n");
            stringBuilder.append("  layout: setting.layout.form,\n");
            stringBuilder.append("  title: setting.pageName.new,\n");
            stringBuilder.append("  items: [\n");
            stringBuilder.append("    {\n");
            stringBuilder.append("      component: 'Form',\n");
            stringBuilder.append("      config: {\n");
            stringBuilder.append("        API: {\n");
            stringBuilder.append("          createAPI: setting.createAPI,\n");
            stringBuilder.append("        },\n");
            stringBuilder.append("        layout: 'Grid',\n");
            stringBuilder.append("        layoutConfig: {\n");
            stringBuilder.append("          value: Array(setting.columns).fill(~~(24 / setting.columns)),\n");
            stringBuilder.append("        },\n");
            stringBuilder.append("        fields: setting.createFields || setting.formFields,\n");
            stringBuilder.append("      },\n");
            stringBuilder.append("    },\n");
            stringBuilder.append("  ],\n");
            stringBuilder.append("};\n");
            fileWriter.write(stringBuilder.toString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 在内部文件夹config中创建-edit.js
        File configE = new File(configDir.getPath() + "/" + name + "-edit.js");
        try(FileWriter fileWriter = new FileWriter(configE)){
            stringBuilder.delete(0,stringBuilder.length());
            stringBuilder.append("const setting = require('./" + name + "-setting.json');\n");
            stringBuilder.append("\n");
            stringBuilder.append("module.exports = {\n");
            stringBuilder.append("  layout: setting.layout.form,\n");
            stringBuilder.append("  title: setting.pageName.edit,\n");
            stringBuilder.append("  items: [\n");
            stringBuilder.append("    {\n");
            stringBuilder.append("      component: 'Form',\n");
            stringBuilder.append("      config: {\n");
            stringBuilder.append("        API: {\n");
            stringBuilder.append("            getAPI: setting.getAPI,\n");
            stringBuilder.append("            updateAPI: setting.updateAPI,\n");
            stringBuilder.append("        },\n");
            stringBuilder.append("        layout: 'Grid',\n");
            stringBuilder.append("        layoutConfig: {\n");
            stringBuilder.append("          value: Array(setting.columns).fill(~~(24 / setting.columns)),\n");
            stringBuilder.append("        },\n");
            stringBuilder.append("        fields: setting.updateFields || setting.formFields,\n");
            stringBuilder.append("      },\n");
            stringBuilder.append("    },\n");
            stringBuilder.append("  ],\n");
            stringBuilder.append("};\n");
            fileWriter.write(stringBuilder.toString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 在内部文件夹config中创建-setting.json
        File configS = new File(configDir.getPath() + File.separator + name + "-setting.json");
        try(FileWriter fileWriter = new FileWriter(configS)){
            stringBuilder.delete(0,stringBuilder.length());
            stringBuilder.append(setting.toString());
            fileWriter.write(stringBuilder.toString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 在内部文件夹config中创建-view.js
        File configV = new File(configDir.getPath() + "/" + name + "-view.js");
        try(FileWriter fileWriter = new FileWriter(configV)){
            stringBuilder.delete(0,stringBuilder.length());
            stringBuilder.append("const setting = require('./" + name + "-setting.json');\n");
            stringBuilder.append("\n");
            stringBuilder.append("module.exports = {\n");
            stringBuilder.append("  layout: setting.layout.form,\n");
            stringBuilder.append("  title: setting.pageName.view,\n");
            stringBuilder.append("  items: [\n");
            stringBuilder.append("    {\n");
            stringBuilder.append("      component: 'Form',\n");
            stringBuilder.append("      config: {\n");
            stringBuilder.append("        API: {\n");
            stringBuilder.append("          getAPI: setting.getAPI,\n");
            stringBuilder.append("        },\n");
            stringBuilder.append("        layout: 'Grid',\n");
            stringBuilder.append("        layoutConfig: {\n");
            stringBuilder.append("          value: Array(setting.columns).fill(~~(24 / setting.columns)),\n");
            stringBuilder.append("        },\n");
            stringBuilder.append("        fields: setting.viewConfig || setting.formFields,\n");
            stringBuilder.append("        otherProps: {\n");
            stringBuilder.append("          footerButton: false\n");
            stringBuilder.append("        }\n");
            stringBuilder.append("      },\n");
            stringBuilder.append("    },\n");
            stringBuilder.append("  ],\n");
            stringBuilder.append("};\n");
            fileWriter.write(stringBuilder.toString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 在内部文件夹config中创建index.js
        File configIndex = new File(configDir.getPath() + "/index.js");
        try(FileWriter fileWriter = new FileWriter(configIndex)){
            stringBuilder.delete(0,stringBuilder.length());
            stringBuilder.append("\n");
            stringBuilder.append("const setting = require('./" + name + "-setting.json');\n");
            stringBuilder.append("\n");
            stringBuilder.append("module.exports = {\n");
            stringBuilder.append("  layout: setting.layout.table,\n");
            stringBuilder.append("  title: setting.pageName.table,\n");
            stringBuilder.append("  items: [\n");
            stringBuilder.append("    {\n");
            stringBuilder.append("      component: 'Search',\n");
            stringBuilder.append("      config: {\n");
            stringBuilder.append("        fields: setting.searchFields,\n");
            stringBuilder.append("      },\n");
            stringBuilder.append("    },\n");
            stringBuilder.append("    {\n");
            stringBuilder.append("      component: 'Table',\n");
            stringBuilder.append("      config: {\n");
            stringBuilder.append("        API: {\n");
            stringBuilder.append("          listAPI: setting.listAPI,\n");
            stringBuilder.append("          appendAPI: '',\n");
            stringBuilder.append("          deleteAPI: setting.deleteAPI,\n");
            stringBuilder.append("        },\n");
            stringBuilder.append("        actions: setting.tableActions,\n");
            stringBuilder.append("        fields: setting.tableFields,\n");
            stringBuilder.append("        operation: setting.tableOperation,\n");
            stringBuilder.append("      },\n");
            stringBuilder.append("    },\n");
            stringBuilder.append("  ],\n");
            stringBuilder.append("};\n");
            fileWriter.write(stringBuilder.toString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 压缩文件夹
        // 创建压缩流，定义好生成的压缩文件存放的位置
        try(ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(fileDir.getPath() + "/" + name +".zip"))){
            // 调用压缩工具类
            CompressionFile.compression(pageConfigDir,zipOutputStream,name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 给前端返回下载链接
        File zipFile = new File(fileDir.getPath() + "/" + name + ".zip"); //获取文件相对路径
        return SuccessTip.create(zipFile.getPath());

        // 利用流传给前端下载
        /*response.setHeader("Content-Disposition","attachment;filename="+ name + ".zip");
        OutputStream outputStream = response.getOutputStream();
        File zipFile = new File(fileDir.getPath() + "/" + name + ".zip");
        int length;
        byte[] bytes = new byte[1024];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(zipFile));
        while ((length = bufferedInputStream.read(bytes)) != -1){
            outputStream.write(bytes,0,length);
        }
        bufferedInputStream.close();*/
    }

}
