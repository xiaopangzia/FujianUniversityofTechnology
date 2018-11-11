package com.cheng.shop.admin.book;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import com.cheng.shop.book.domain.Book;
import com.cheng.shop.book.service.BookService;
import com.cheng.shop.category.domain.Category;
import com.cheng.shop.category.service.CategoryService;
import com.cheng.shop.pager.PageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "admin/AdminBookServlet", value = "/admin/AdminBookServlet")
public class AdminBookServlet extends BaseServlet {
    private CategoryService categoryService = new CategoryService();
    private BookService bookService = new BookService();

    //获取当前页码
    private int getPc(HttpServletRequest req) {
        int pc = 1;
        String params = req.getParameter("pc");
        if (params != null && ! params.trim().isEmpty()){
            try {
                pc = Integer.parseInt(params);
            } catch (RuntimeException e) {

            }
        }
        return pc;
    }

    //获取url /shop/bookServlet+method=findByCategory&cid=xxx
    //截取url 页面中的分页导航需要使用它做为超链接的目标
    private String getUrl(HttpServletRequest request) {
        String url=request.getRequestURI()+"?"+ request.getQueryString();
        //如果有pc参数，截取掉
        int index=url.lastIndexOf("&pc=");
        if (index!=-1){
            url=url.substring(0,index);
        }
        return url;
    }

    /**
     * 查找二级分类下的图书
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1.得到pc，如果页面传递使用页面，没有使用pc=1
        int pc=getPc(request);

        // 2.得到url
        String url = getUrl(request);

        // 3.获取查询条件，本方法就是cid，即分类的id
        String cid = request.getParameter("cid");

        // 4.使用pc和cid调用service的findByCategory得到PageBean
        PageBean<Book> pb = bookService.findByCategory(cid,pc);

        // 5.给PageBean设置url，保存PageBean，转发到jsp
        pb.setUrl(url);
        request.setAttribute("pb", pb);
        return "f:/adminjsps/admin/book/list.jsp";
    }

    /**
     * 查找所有分类
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findCategoryAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 1.通过service得到所有的分类
         * 2.保存到request中，转发到left.jsp
         */
        List<Category> parent=categoryService.findAll();
        request.setAttribute("parents",parent);
        return "f:/adminjsps/admin/book/left.jsp";
    }

    /**
     * 查询图书详细信息
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String load(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bid = req.getParameter("bid");
        Book book = bookService.load(bid);
        req.setAttribute("book", book);
        req.setAttribute("parents",categoryService.findParents());
        String pid = book.getCategory().getParent().getCid();
        req.setAttribute("children", categoryService.findChildren(pid));
        return "f:/adminjsps/admin/book/desc.jsp";
    }

    /**
     * 添加图书：第一步
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String addPre(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        /*
         * 1. 获取所有一级分类，保存之
         * 2. 转发到add.jsp，该页面会在下拉列表中显示所有一级分类
         */
        List<Category> parents = categoryService.findParents();
        req.setAttribute("parents", parents);
        return "f:/adminjsps/admin/book/add.jsp";
    }

    public String ajaxFindChildren(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        /*
         * 1. 获取pid
         * 2. 通过pid查询出所有2级分类
         * 3. 把List<Category>转换成json，输出给客户端
         */
        String pid = req.getParameter("pid");
        List<Category> children = categoryService.findChildren(pid);
        String json = toJson(children);
        resp.getWriter().print(json);
        return null;
    }

    // {"cid":"fdsafdsa", "cname":"fdsafdas"}
    private String toJson(Category category) {
        StringBuilder sb = new StringBuilder("{");
        sb.append("\"cid\"").append(":").append("\"").append(category.getCid()).append("\"");
        sb.append(",");
        sb.append("\"cname\"").append(":").append("\"").append(category.getCname()).append("\"");
        sb.append("}");
        return sb.toString();
    }

    // [{"cid":"fdsafdsa", "cname":"fdsafdas"}, {"cid":"fdsafdsa", "cname":"fdsafdas"}]
    private String toJson(List<Category> categoryList) {
        StringBuilder sb = new StringBuilder("[");
        for(int i = 0; i < categoryList.size(); i++) {
            sb.append(toJson(categoryList.get(i)));
            if(i < categoryList.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * 按多条件组合模糊查询
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String  findByCombination(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1.得到pc，如果页面传递使用页面，没有使用pc=1
        int pc=getPc(request);

        // 2.得到url
        String url = getUrl(request);

        // 3.获取查询条件，本方法就是bname,author,press封装到Book中，即书名,作者，出版社
        Book criteria = CommonUtils.toBean(request.getParameterMap(), Book.class);

        // 4.使用pc和criteria，调用service的findByCombination得到PageBean
        PageBean<Book> pb = bookService.findByCombination(criteria,pc);

        // 5.给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
        pb.setUrl(url);
        request.setAttribute("pb", pb);
        return "f:/adminjsps/admin/book/list.jsp";
    }

    /**
     * 按图名查
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findByBname(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
         * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
         */
        int pc = getPc(req);
        /*
         * 2. 得到url：...
         */
        String url = getUrl(req);
        /*
         * 3. 获取查询条件，本方法就是cid，即分类的id
         */
        String bname = req.getParameter("bname");
        /*
         * 4. 使用pc和cid调用service#findByCategory得到PageBean
         */
        PageBean<Book> pb = bookService.findByBname(bname, pc);
        /*
         * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
         */
        pb.setUrl(url);
        req.setAttribute("pb", pb);
        return "f:/adminjsps/admin/book/list.jsp";
    }

    /**
     * 按出版社查询
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findByPress(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
         * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
         */
        int pc = getPc(req);
        /*
         * 2. 得到url：...
         */
        String url = getUrl(req);
        /*
         * 3. 获取查询条件，本方法就是cid，即分类的id
         */
        String press = req.getParameter("press");
        /*
         * 4. 使用pc和cid调用service#findByCategory得到PageBean
         */
        PageBean<Book> pb = bookService.findByPress(press, pc);
        /*
         * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
         */
        pb.setUrl(url);
        req.setAttribute("pb", pb);
        return "f:/adminjsps/admin/book/list.jsp";
    }

    /**
     * 按作者查
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findByAuthor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
         * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
         */
        int pc = getPc(req);
        /*
         * 2. 得到url：...
         */
        String url = getUrl(req);
        /*
         * 3. 获取查询条件，本方法就是cid，即分类的id
         */
        String author = req.getParameter("author");
        /*
         * 4. 使用pc和cid调用service#findByCategory得到PageBean
         */
        PageBean<Book> pb = bookService.findByAuthor(author, pc);
        /*
         * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
         */
        pb.setUrl(url);
        req.setAttribute("pb", pb);
        return "f:/adminjsps/admin/book/list.jsp";
    }

    /**
     * 修改图书信息
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String edit(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        Book book = CommonUtils.toBean(request.getParameterMap(), Book.class);
        Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
        book.setCategory(category);

        bookService.edit(book);
        request.setAttribute("msg", "修改图书成功");
        return "f:/adminjsps/msg.jsp";
    }

    public String delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bid = req.getParameter("bid");
        Book book = bookService.load(bid);
        //获取真实的路径
        String savepath = this.getServletContext().getRealPath("/");
        //删除图片
        new File(savepath, book.getImage_w()).delete();
        new File(savepath, book.getImage_b()).delete();
        bookService.delete(bid);
        req.setAttribute("msg", "删除图书成功");
        return "f:/adminjsps/msg.jsp";

    }
}
