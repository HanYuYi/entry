package com.HanYuYi.service.upload;

import com.HanYuYi.util.DataFormatConversion;
import com.HanYuYi.util.RespFormat;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ImgUploadImpl implements ImgUpload {

    /**
     * 执行上传操作
     * @param type
     */
    @Override
    public RespFormat upload(HttpServletRequest request, String type) {
        RespFormat validateResult = uploadValidator(request);
        if (validateResult.getStatus() == 1) {

        }
        return validateResult;
    }

    /**
     * 获取上传状态
     * @return
     */
    @Override
    public RespFormat uploadValidator(HttpServletRequest request) {
        RespFormat respBody = new RespFormat();
        if (!ServletFileUpload.isMultipartContent(request)) {
            respBody.setStatus(RespFormat.ERROR_STATUS);
            respBody.setMessage("表单必须包含 enctype=multipart/form-data");
            return respBody;
        }
        respBody.setStatus(RespFormat.SUCCESS_STATUS);
        respBody.setMessage("表单类型验证成功");
        return respBody;
    }

    /**
     * 获取上传成功后的全路径
     * @return
     */
    @Override
    public Map<String, String> uploadUrl() {
        return null;
    }
}
