package com.zg.zgshop.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Author: zg
 * @Date: 2019/3/13 14:30
 */
public class StringUtils {

    /**
     * 修改文件名，防止上传的图片文件名重复
     * @param fileName
     * @return
     */
    public static String renameFileName(String fileName) {
        int dotIndex = fileName.indexOf(".");
        String suffix = fileName.substring(dotIndex);
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + new Random().nextInt(1000) + suffix;
    }

}
