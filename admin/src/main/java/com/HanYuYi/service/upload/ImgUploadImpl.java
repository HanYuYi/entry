package com.HanYuYi.service.upload;

import com.HanYuYi.util.RespFormat;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

public class ImgUploadImpl implements ImgUpload {

    private String uuid = null;

    /**
     * 执行上传操作
     * @param request
     * @return
     */
    @Override
    public RespFormat upload(HttpServletRequest request) {
        RespFormat uoloadResult = uploadValidator(request);
        if (uoloadResult.getStatus() == RespFormat.ERROR_STATUS) {
            return uoloadResult;
        }
        // 开始上传操作
        ServletFileUpload uploader = beforeUpload(request);
        String uploadUrl = request.getServletContext().getRealPath(AVATAR_URL);
        File uploadDir = new File(uploadUrl);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        // 解析请求内容，提取文件数据
        try {
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = uploader.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
                        String fileName = new File(item.getName()).getName();
                        String[] fileNameSplit = fileName.split(".");
                        String filePath = uploadUrl + OS_SEPARATOR + uuid + fileNameSplit[fileNameSplit.length - 1];
                        System.out.println("filePath: " + filePath);
                        File file = new File(filePath);
                        item.write(file);
                        uoloadResult.setStatus(RespFormat.SUCCESS_STATUS);
                        uoloadResult.setMessage("文件上传成功");
                    }
                }
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
            uoloadResult.setStatus(RespFormat.ERROR_STATUS);
            uoloadResult.setMessage(e.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            uoloadResult.setStatus(RespFormat.ERROR_STATUS);
            uoloadResult.setMessage(ex.getMessage());
        }
        return uoloadResult;
    }

    /**
     * 检测上传文件的表单格式
     * @param request
     * @return
     */
    private RespFormat uploadValidator(HttpServletRequest request) {
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
     * 上传前的准备工作，并返回上传器
     * @param request
     * @return
     */
    private ServletFileUpload beforeUpload(HttpServletRequest request) {
        // 超过一定大小后放入临时目录
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(TEMP_AVATAR_SIZE);
        ServletContext servletContext = request.getServletContext();
        factory.setRepository(new File(servletContext.getRealPath(TEMP_URL)));
        // 限制最大上传的大小
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_AVATAR_SIZE);
        // 设置编码
        upload.setHeaderEncoding("UTF-8");
        return upload;
    }

    /**
     * 获取上传成功后的全路径
     * @return
     */
    @Override
    public String uploadID() {
        return uuid;
    }
}
