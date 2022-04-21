package com.HanYuYi.servlet.userBase;

import com.HanYuYi.util.DataFormatConversion;
import com.HanYuYi.util.RespFormat;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/uploadFile")
public class UploadFile extends HttpServlet {
    private static final int TEMP_SIZE = 1024 * 1024 * 2;           // 2M
    private static final int AVATAR_SIZE = 1024 * 20;               // 20KB

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RespFormat respBody = new RespFormat();
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json;charset=UTF-8");
        // 检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(req)) {
            respBody.setStatus(RespFormat.ERROR_STATUS);
            respBody.setMessage("表单必须包含 enctype=multipart/form-data");
            String deserialization = DataFormatConversion.Deserialization(respBody);
            writer.write(deserialization);
            writer.flush();
            return;
        }

        ServletContext servletContext = req.getServletContext();

        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置临时存储 - 超过后将存于临时目录中
        factory.setSizeThreshold(TEMP_SIZE);
        factory.setRepository(new File(servletContext.getRealPath("/WEB_INF/temp")));

        // 设置永久存储
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(AVATAR_SIZE);
        upload.setHeaderEncoding("UTF-8");
        servletContext.getRealPath("/WEB_INF/avatar");

    }
}
