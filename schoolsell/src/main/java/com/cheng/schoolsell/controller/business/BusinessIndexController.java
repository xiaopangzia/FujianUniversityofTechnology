package com.cheng.schoolsell.controller.business;

import com.cheng.schoolsell.entity.BusinessInfo;
import com.cheng.schoolsell.entity.ProductInfo;
import com.cheng.schoolsell.entity.ShopSale;
import com.cheng.schoolsell.repository.ShopSaleRepository;
import com.cheng.schoolsell.service.OrderService;
import com.cheng.schoolsell.service.ProductInfoService;
import com.cheng.schoolsell.service.SaleService;
import com.cheng.schoolsell.utils.CookieUtil;
import com.cheng.schoolsell.utils.ResultVoUtil;
import com.cheng.schoolsell.vo.ProductVO;
import com.cheng.schoolsell.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.asm.Advice;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-09-25
 * Time: 下午4:27
 */
@Controller
@RequestMapping("/business")
@Api(tags = "商户端主页")
public class BusinessIndexController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private SaleService saleService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/index")
    @ApiOperation(value = "跳转到商户端主页")
    public ModelAndView businessIndex(HttpServletRequest request,
                                      Map<String,Object> map) {

        List<ProductVO> productVOList =
                productInfoService.getShopProduct(request);
        if (productVOList == null) {
            map.put("productVO", null);
        }

        map.put("productVO", productVOList);

        return new ModelAndView("business/index",map);
    }

    @GetMapping("/show/product/{productId}")
    @ApiOperation(value = "展示商品日销量")
    @ResponseBody
    public ResultVO getProductECharts(@PathVariable("productId") String productId) {
        List<ShopSale> shopSales = saleService.getShopSaleByProductId(productId);
        Map<String, Object> map = new HashMap<>(3);
        if (shopSales.size() == 0) {
            map.put("dates", null);
        }else {
            List<LocalDate> dates = new ArrayList<>();
            List<BigDecimal> sales = new ArrayList<>();
            for (ShopSale shopSale : shopSales) {
                dates.add(shopSale.getSaleTime());
                sales.add(shopSale.getTurnover());
            }

            map.put("dates", dates);
            map.put("sales",sales);
            map.put("name", shopSales.get(0).getProductName());
        }

        return ResultVoUtil.success(map);
    }

    @GetMapping("/show/order/{shopId}")
    @ApiOperation(value = "展示商品日销量")
    @ResponseBody
    public ResultVO getShowOrder(@PathVariable("shopId") String shopId,
                                 HttpServletRequest request) {

        Map<String, Object> map = orderService.getOrderNumDay(shopId);

        return ResultVoUtil.success(map);
    }

    @GetMapping("/show/sale/{shopId}")
    @ApiOperation(value = "展示商品日营业额")
    @ResponseBody
    public ResultVO getShowSale(@PathVariable("shopId") String shopId) {
        List<ShopSale> shopSales = saleService.getSaleForDay(shopId);
        Map<String, Object> map = new HashMap<>(3);
        List<LocalDate> dates = new ArrayList<>();
        List<BigDecimal> sales= new ArrayList<>();
        if (shopSales.size() == 0) {
            map.put("dates", null);
        }else {
            Map<LocalDate, BigDecimal> map1 = new HashMap<>();
            for (ShopSale shopSale : shopSales) {
                if (!map1.containsKey(shopSale.getSaleTime())) {
                    dates.add(shopSale.getSaleTime());
                    map1.put(shopSale.getSaleTime(), shopSale.getTurnover());
                }
            }

            for (LocalDate date : dates) {
                BigDecimal amount = new BigDecimal(0).setScale(2);
                for (ShopSale shopSale : shopSales) {
                    if (date.equals(shopSale.getSaleTime())) {
                        amount = amount.add(shopSale.getTurnover());
                    }
                }
                sales.add(amount);
            }
            map.put("dates", dates);
            map.put("sales", sales);

        }
        return ResultVoUtil.success(map);
    }
}
