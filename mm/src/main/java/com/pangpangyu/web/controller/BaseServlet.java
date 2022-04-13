package com.pangpangyu.web.controller;

import com.pangpangyu.service.store.*;
import com.pangpangyu.service.store.impl.*;
import com.pangpangyu.service.system.DeptService;
import com.pangpangyu.service.system.ModuleService;
import com.pangpangyu.service.system.RoleService;
import com.pangpangyu.service.system.UserService;
import com.pangpangyu.service.system.impl.DeptServiceImpl;
import com.pangpangyu.service.system.impl.ModuleServiceImpl;
import com.pangpangyu.service.system.impl.RoleServiceImpl;
import com.pangpangyu.service.system.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class BaseServlet extends HttpServlet {
    protected CompanyService companyService;
    protected DeptService deptService;
    protected UserService userService;
    protected CourseService courseService;
    protected CatalogService catalogService;
    protected QuestionService questionService;
    protected QuestionItemService questionItemService;
    protected RoleService roleService;
    protected ModuleService moduleService;

    @Override
    public void init() throws ServletException {
        companyService = new CompanyServiceImpl();
        deptService = new DeptServiceImpl();
        userService = new UserServiceImpl();
        courseService = new CourseServiceImpl();
        catalogService = new CatalogServiceImpl();
        questionService = new QuestionServiceImpl();
        questionItemService = new QuestionItemServiceImpl();
        roleService = new RoleServiceImpl();
        moduleService = new ModuleServiceImpl();
    }
}
