package com.thoughtworks.mvc.web;

import com.thoughtworks.mvc.ActionInjector;
import com.thoughtworks.mvc.annotations.Mapping;
import com.thoughtworks.mvc.container.GuiceContainer;
import com.thoughtworks.mvc.container.IocContainer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class DispatcherServlet extends HttpServlet {

    private IocContainer iocContainer;

    @Override
    public void init() throws ServletException {
        iocContainer = new GuiceContainer();
        iocContainer.init(getInitParameter("module"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) {
        String reqPath = req.getRequestURI();
        Method reqMethod = iocContainer.getMethodByMappingUrl(reqPath);
        Map<String, String> parameterMap = req.getParameterMap();
        Object action = iocContainer.inject(reqMethod.getDeclaringClass());
        ActionInjector actionInjector = new ActionInjector(action, parameterMap);
        action = actionInjector.inject();
        try {
            reqMethod.invoke(action);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        } catch (InvocationTargetException e) {
            throw new IllegalArgumentException(e);
        }
        dispatch(req, resp, reqMethod.getAnnotation(Mapping.class).forward());
    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp, String page) {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
    }
}
