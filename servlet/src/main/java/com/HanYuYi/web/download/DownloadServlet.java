package com.HanYuYi.web.download;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 下载文件
 * 练习到的知识点：getRealPath，getMimeType, content-disposition
 *
 */
@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String filename = req.getParameter("filename");
        // 获取ServletContext
        ServletContext servletContext = getServletContext();
        // 获取下载文件的路径
        String realPath = servletContext.getRealPath("/imgs/" + filename);
        // 获取文件的mime
        String mimeType = servletContext.getMimeType(filename);

        // 设置下载文件的头
        resp.setHeader("content-type", mimeType);
        // 设置下载文件的名称，如果是中文名称，不同浏览器要用不同编码
        resp.setHeader("content-disposition", "attachment;filename=" + filename);

        FileInputStream fileInputStream = new FileInputStream(realPath);
        ServletOutputStream outputStream = resp.getOutputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = fileInputStream.read(bytes)) != -1) {
            System.out.println(len);
            outputStream.write(bytes, 0, len);
        }
        fileInputStream.close();
    }
}
