package com.cheng.shop.admin.category.web.servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import com.cheng.shop.category.domain.Category;
import com.cheng.shop.category.service.CategoryService;
import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "admin/AdminCategoryServlet",value = "/admin/AdminCategoryServlet")
public class AdminCategoryServlet extends BaseServlet {
    private CategoryService categoryService = new CategoryService();

    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("parents",categoryService.findAll());
        return "f:/adminjsps/admin/category/list.jsp";
    }

    /**
     * 添加一级分类
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String addParent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Category parent = CommonUtils.toBean(request.getParameterMap(), Category.class);
        parent.setCid(CommonUtils.uuid());
        categoryService.add(parent);
        return findAll(request, response);
    }

    /**
     * 添加二级分类第一步
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String addChildPre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");
        List<Category> parents = categoryService.findParents();
        request.setAttribute("pid", pid);
        request.setAttribute("parents", parents);
        return "f:/adminjsps/admin/category/add2.jsp";
    }

    /**
     * 添加二级分类第二步
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String addChild(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Category child = CommonUtils.toBean(req.getParameterMap(), Category.class);
        child.setCid(CommonUtils.uuid());
        //手动映射pid
        String pid = req.getParameter("pid");
        Category parent = new Category();
        parent.setCid(pid);
        child.setParent(parent);
        categoryService.addChild(child);
        return findAll(req, resp);
    }

    /**
     * 修改一级分类第一步
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String editParentPre(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cid = req.getParameter("cid");
        Category parents = categoryService.load(cid);
        req.setAttribute("parents", parents);
        return "f:/adminjsps/admin/category/edit.jsp";
    }

    /**
     * 修改一级分类第二步
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String editParent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Category parents = CommonUtils.toBean(req.getParameterMap(), Category.class);
        categoryService.edit(parents);
        return findAll(req, resp);
    }

    /**
     * 修改二级分类第一步
     * @param request
     * @param response
     * @return
     */
    public String editChildPre(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String cid = request.getParameter("cid");
        Category child = categoryService.load(cid);
        request.setAttribute("child", child);
        request.setAttribute("parents", categoryService.findParents());
        return "f:/adminjsps/admin/category/edit2.jsp";
    }

    /**
     * 修改二级分类
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String editChild(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        Category child = CommonUtils.toBean(request.getParameterMap(), Category.class);
        String pid = request.getParameter("pid");
        Category parent = new Category();
        parent.setCid(pid);
        child.setParent(parent);
        categoryService.edit(child);
        return findAll(request, response);
    }

    /**
     * 删除一级分类
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String deleteParent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cid = req.getParameter("cid");
        int number = categoryService.findChildCountByPid(cid);
        if (number > 0) {
            req.setAttribute("code", "error");
            req.setAttribute("msg", "该分类下还有"+number+"个二级分类");
            return "f:/adminjsps/msg.jsp";
        }else {
            categoryService.delete(cid);
        }
        return findAll(req, resp);
    }

    /**
     * 删除二级分类
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String deleteChild(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        int count = categoryService.findBookCount(cid);
        if (count > 0) {
            request.setAttribute("code", "error");
            request.setAttribute("msg","该二级分类下还有"+count+"本图书");
            return "f:/adminjsps/msg.jsp";
        }
        categoryService.delete(cid);
        return findAll(request, response);
    }

}
