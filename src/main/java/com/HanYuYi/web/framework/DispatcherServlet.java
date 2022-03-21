package com.HanYuYi.web.framework;

import com.HanYuYi.web.controllers.Login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    // 存放get请求
    private Map<String, GetDispatcher> getMappings = new HashMap<>();
    // 存放post请求
    private Map<String, PostDispatcher> postMappings = new HashMap<>();
    // 存放Controller
    private List<Class<?>> controllers = List.of(Login.class);

    @Override
    public void init() throws ServletException {
        super.init();

    }

    /**
     * init初始化操作，封装HttpServlet映射
     */
    private void initHandler() {
        for (Class<?> ctrl : controllers) {
            try {
                Object constructor = ctrl.getConstructor().newInstance();
                for (Method method : ctrl.getMethods()) {
                    if (method.getAnnotation(GetMapping.class) != null) {
                        getMappings.put(getType(method));
                    }
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 返回controller get方
     * @param method
     */
    private ModelAndView getType(Method method) {
        String path = method.getAnnotation(GetMapping.class).value();
        return new ModelAndView();
    }

}
