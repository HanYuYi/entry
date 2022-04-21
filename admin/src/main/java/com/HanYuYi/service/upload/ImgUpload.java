package com.HanYuYi.service.upload;

import com.HanYuYi.util.RespFormat;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface ImgUpload {
    public static final int TEMP_SIZE = 1024 * 1024 * 2;           // 2M
    public static final int AVATAR_SIZE = 1024 * 20;               // 20KB

    /**
     * 执行上传操作
     * @param request
     * @param type
     */
    RespFormat upload(HttpServletRequest request, String type);

    /**
     * 获取上传状态
     * @return
     */
    RespFormat uploadValidator(HttpServletRequest request);

    /**
     * 获取上传成功后的全路径
     * @return
     */
    Map<String, String> uploadUrl();
}
