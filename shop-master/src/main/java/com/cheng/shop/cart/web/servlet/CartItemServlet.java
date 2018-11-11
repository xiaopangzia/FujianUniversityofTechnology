package com.cheng.shop.cart.web.servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import com.cheng.shop.book.domain.Book;
import com.cheng.shop.cart.domain.CartItem;
import com.cheng.shop.cart.service.CartItemService;
import com.cheng.shop.user.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(value = "/CartItemServlet",name = "CartItemServlet")
public class CartItemServlet extends BaseServlet {
    private CartItemService cartItemService=new CartItemService();

    /**
     * 通过Session中User的uid查询购物车条目
     * 我的购物车
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String myCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.得到uid
        User user = (User) request.getSession().getAttribute("sessionUser");
        String uid = user.getUid();
        // 2.通过service查询当前用户的所有购物车条目
        List<CartItem> listUser = cartItemService.myCart(uid);
        // 3.保存起来，转发到cart/list.jsp
        request.setAttribute("cartItemList", listUser);
        return "f:/jsps/cart/list.jsp";
    }

    /**
     * 加载多个CartItem
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String loadCartItems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取cartItemIds参数
        String cartItemIds = request.getParameter("cartItemIds");
        double total = Double.parseDouble(request.getParameter("total"));
        // 2.通过service得到List<cartItem>
        List<CartItem> cartItemList = cartItemService.loadCartItem(cartItemIds);
        // 3.保存，然后转发到/cart/showitem.jsp
        request.setAttribute("cartItemList", cartItemList);
        request.setAttribute("total", total);
        request.setAttribute("cartItemIds", cartItemIds);
        return "f:/jsps/cart/showitem.jsp";
    }

    /**
     * 添加数量
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String updateQuantity(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cartItemId = req.getParameter("cartItemId");
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        CartItem cartItem = cartItemService.updateQuantity(cartItemId, quantity);

        //   给客户端返回一个json对象
        StringBuilder stringBuilder = new StringBuilder("{");
        stringBuilder.append("\"quantity\"").append(':').append(cartItem.getQuantity());
        stringBuilder.append(',');
        stringBuilder.append("\"subtotal\"").append(':').append(cartItem.getSubtotal());
        stringBuilder.append('}');

        resp.getWriter().print(stringBuilder);
        return null;
    }

    //批量删除
    public String batchDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 1.调用cartItemIds参数
         * 2.调用service方法完成工作
         * 3.返回list.jsp
         */
        String cartItemIds = req.getParameter("cartItemIds");
        cartItemService.batchDelete(cartItemIds);
        return myCart(req, resp);
    }

    //添加购物车条目
    public String  addCartItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.封装表单数据到CartItem(bid，quantity)
        Map map=req.getParameterMap();
        CartItem cartItem = CommonUtils.toBean(map, CartItem.class);
        Book book = CommonUtils.toBean(map, Book.class);
        User user = (User) req.getSession().getAttribute("sessionUser");
        cartItem.setBook(book);
        cartItem.setUser(user);

        // 2.调用service完成添加
        cartItemService.addCartItem(cartItem);

        // 3.查询出当前用户的所有条目，转发到list.jsp显示
        return myCart(req, resp);
    }
}
