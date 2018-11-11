package com.cheng.shop.book.Web.Servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import com.cheng.shop.book.domain.Book;
import com.cheng.shop.book.service.BookService;
import com.cheng.shop.pager.PageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/BookServlet")
public class BookServlet extends BaseServlet {
    private BookService bookService=new BookService();

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
     * 按分类查询
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String  findByCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1.得到pc，如果页面传递使用页面，没有使用pc=1
        int pc=getPc(request);

        // 2.得到url
        String url = getUrl(request);

        // 3.获取查询条件，本方法就是cid，即分类的id
        String cid = request.getParameter("cid");

        // 4.使用pc和cid调用service的findByCategory得到PageBean
        PageBean<Book> pb = bookService.findByCategory(cid,pc);

        // 5.给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
        pb.setUrl(url);
        request.setAttribute("pb", pb);
        return "f:/jsps/book/list.jsp";
    }

    /**
     * 按作者模糊查询
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String  findByAuthor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1.得到pc，如果页面传递使用页面，没有使用pc=1
        int pc=getPc(request);

        // 2.得到url
        String url = getUrl(request);

        // 3.获取查询条件，本方法就是author，即作者
        String author = request.getParameter("author");
        System.out.println(author);

        // 4.使用pc和author调用service的findByAuthor得到PageBean
        PageBean<Book> pb = bookService.findByAuthor(author,pc);

        // 5.给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
        pb.setUrl(url);
        request.setAttribute("pb", pb);
        return "f:/jsps/book/list.jsp";
    }

    /**
     * 按出版社模糊查询
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String  findByPress(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1.得到pc，如果页面传递使用页面，没有使用pc=1
        int pc=getPc(request);

        // 2.得到url
        String url = getUrl(request);

        // 3.获取查询条件，本方法就是press，即出版社名
        String press = request.getParameter("press");

        // 4.使用pc和press调用service的findByPress得到PageBean
        PageBean<Book> pb = bookService.findByPress(press,pc);

        // 5.给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
        pb.setUrl(url);
        request.setAttribute("pb", pb);
        return "f:/jsps/book/list.jsp";
    }

    /**
     * 按书名模糊查询
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String  findByBname(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1.得到pc，如果页面传递使用页面，没有使用pc=1
        int pc=getPc(request);

        // 2.得到url
        String url = getUrl(request);

        // 3.获取查询条件，本方法就是bname，即书名
        String bname = request.getParameter("bname");

        // 4.使用pc和bname调用service的findByBname得到PageBean
        PageBean<Book> pb = bookService.findByBname(bname,pc);

        // 5.给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
        pb.setUrl(url);
        request.setAttribute("pb", pb);
        return "f:/jsps/book/list.jsp";
    }

    /**
     * 按多条件组合模糊查询
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String  findByCombination(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        return "f:/jsps/book/list.jsp";
    }

    public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bid = request.getParameter("bid");
        Book book = bookService.load(bid);
        request.setAttribute("book", book);
        return "f:/jsps/book/desc.jsp";
    }
}
