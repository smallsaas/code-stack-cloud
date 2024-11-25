package com.jfeat.module.crudcode.util;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 压缩工具
 * @author: HTAO
 * @CreateDate: 2022/5/27 13:48
 */

public class CompressionFile {

    /**
     * 可压缩单文件和文件夹
     * @param files 要压缩的文件/文件夹
     * @param zipOutputStream zip输出流
     * @param zipName 压缩后的文件名
     * @throws IOException
     */
    public static void compression(File files,ZipOutputStream zipOutputStream,String zipName) throws IOException {

        // 判断文件是否是文件夹，是的话进行递归
        if (files.isDirectory()){
            // 遍历文件夹
            File[] fileList = files.listFiles();
            for (File file : fileList){
                // zipName + "/" + file.getName() ：带上父路径，使文件夹压缩后的文件路径保持不变
                // 如果不需要保持文件夹的原格式可以去掉前面的父路径
                compression(file,zipOutputStream,zipName + "/" + file.getName());
            }
        }else {
                // zipName 指定压缩条目的名称，可以是路径
                zipOutputStream.putNextEntry(new ZipEntry(zipName));
                // 字节流读取文件，将读取的文件存入字节数组中
                FileInputStream fileInputStream = new FileInputStream(files);
                byte[] bytes = new byte[1024];
                // read(bytes) 会返回一个读取到的字节数，然后存入length中
                int length;
                while ((length = fileInputStream.read(bytes)) != -1){
                    // 每次writer都需要给出读取字节数组的开始和最后下标，避免当字节数组不是满的情况下将数组中的null写入压缩文件中
                    zipOutputStream.write(bytes,0,length);
                }
                // 关闭zip实体
                zipOutputStream.closeEntry();
                // 关闭字节输入流
                fileInputStream.close();
                // 压缩流由调用该方法的外部关闭
        }
    }
}
