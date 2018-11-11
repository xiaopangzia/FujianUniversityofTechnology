package com.cheng.shop.order.web.servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import com.cheng.shop.cart.domain.CartItem;
import com.cheng.shop.cart.service.CartItemService;
import com.cheng.shop.order.domain.Order;
import com.cheng.shop.order.domain.OrderItem;
import com.cheng.shop.order.service.OrderService;
import com.cheng.shop.pager.PageBean;
import com.cheng.shop.user.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(value = "/OrderServlet", name = "OrderServlet")
public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderService();
    private CartItemService cartItemService = new CartItemService();

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
     * 按用户查询
     * 我的订单
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String  myOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1.得到pc，如果页面传递使用页面，没有使用pc=1
        int pc=getPc(request);

        // 2.得到url
        String url = getUrl(request);

        // 3.从当前session获取
        User user = (User) request.getSession().getAttribute("sessionUser");

        // 4.使用pc和cid调用service的findByCategory得到PageBean
        PageBean<Order> pb = orderService.myOrder(user.getUid(),pc);

        // 5.给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
        pb.setUrl(url);
        request.setAttribute("pb", pb);
        return "f:/jsps/order/list.jsp";
    }

    public String  createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cartItemIds = request.getParameter("cartItemIds");
        List<CartItem> cartItemList = cartItemService.loadCartItem(cartItemIds);

        //創建Order
        Order order = new Order();
        order.setOid(CommonUtils.uuid());//设置主键
        order.setAddress(request.getParameter("address"));//设置地址
        order.setStatus(1);//设置状态
        order.setOrdertime(String.format("%tF %<tT", new Date()));//设置时间
        User owner = (User) request.getSession().getAttribute("sessionUser");
        order.setOwner(owner);//设置uid

        BigDecimal total = new BigDecimal("0");
        for (CartItem cartItem : cartItemList) {
            total = total.add(new BigDecimal(cartItem.getSubtotal() + ""));
        }
        order.setTotal(total.doubleValue());//设置总计

        //創建List<OrderItem>
        List<OrderItem> itemList = new ArrayList<OrderItem>();
        for (CartItem cartItem : cartItemList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderItemId(CommonUtils.uuid());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setSubtotal(cartItem.getSubtotal());
            orderItem.setBook(cartItem.getBook());
            orderItem.setOrder(order);
            itemList.add(orderItem);
        }
        order.setOrderItemList(itemList);

        //调用service完成添加
        orderService.createOrder(order);

        //保存订单
        request.setAttribute("order", order);
        cartItemService.batchDelete(cartItemIds);
        return "f:/jsps/order/ordersucc.jsp";
    }

    /**
     * 加载订单条目
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String load(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oid = req.getParameter("oid");
        Order order = orderService.load(oid);
        String btn = req.getParameter("btn");
        req.setAttribute("order", order);
        req.setAttribute("btn", btn);
        return "f:/jsps/order/desc.jsp";
    }

    public String cancel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oid = req.getParameter("oid");
        int status = orderService.findStatus(oid);
        if (status != 1) {
            req.setAttribute("code", "error");
            req.setAttribute("msg", "状态不对，不能取消订单");
            return "f:/jsps/msg.jsp";
        }
        orderService.updateStatus(oid, 5);
        req.setAttribute("code", "success");
        req.setAttribute("msg", "订单已取消");
        return "f:/jsps/msg.jsp";
    }

    public String confirm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oid = req.getParameter("oid");
        int status = orderService.findStatus(oid);
        if (status != 3) {
            req.setAttribute("code", "error");
            req.setAttribute("msg", "状态不对，不能确认收货");
            return "f:/jsps/msg.jsp";
        }
        orderService.updateStatus(oid, 4);
        req.setAttribute("code", "success");
        req.setAttribute("msg", "订单已确认收货");
        return "f:/jsps/msg.jsp";
    }

}
