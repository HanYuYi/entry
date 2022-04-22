package com.HanYuYi.service.upload;

import com.HanYuYi.util.RespFormat;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

public interface ImgUpload {
    public static final String OS_SEPARATOR = File.separator;
    public static final int MAX_AVATAR_SIZE = 1024 * 1024 * 2;                  // 头像最大限制 2M
    public static final int TEMP_AVATAR_SIZE = 1024 * 1024 * 1;                 // 头像临时文件大小限制 1M
    public static final int AVATAR_SIZE = 1024 * 20;                            // 最大文件上传值 20K

    public static final String TEMP_URL = OS_SEPARATOR + "WEB-INF" + OS_SEPARATOR + "tempUpload";        // 临时头像存储目录
    public static final String AVATAR_URL = OS_SEPARATOR + "WEB-INF" + OS_SEPARATOR + "upload";          // 头像存储目录

    /**
     * 执行上传操作
     * @param request
     */
    RespFormat upload(HttpServletRequest request);

    /**
     * 获取上传成功后的全路径
     * @return
     */
    String uploadID();
}
