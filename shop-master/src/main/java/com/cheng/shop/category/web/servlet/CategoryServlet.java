package com.cheng.shop.category.web.servlet;

import cn.itcast.servlet.BaseServlet;
import com.cheng.shop.category.domain.Category;
import com.cheng.shop.category.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/CategoryServlet")
public class CategoryServlet extends BaseServlet {
    private CategoryService categoryService=new CategoryService();

    /**
     * 查询所有分类
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /**
         * 1.通过service得到所有的分类
         * 2.保存到request中，转发到left.jsp
         */
        List<Category> parent=categoryService.findAll();
        request.setAttribute("parents",parent);
        return "f:/jsps/left.jsp";
    }

}
