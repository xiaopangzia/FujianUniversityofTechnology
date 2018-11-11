package com.zhiyou.controller;

import com.zhiyou.model.Product;
import com.zhiyou.service.FileService;
import com.zhiyou.util.QiniuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/product")
public class productController {

    List<Product> productList = new ArrayList<>();

    @Autowired
    private FileService fileService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("product", productList);
        return "Product/product_list";
    }

    @GetMapping("/add")
    public String add() {
        return "product/product_add";
    }

/*   @PostMapping("/add")
    @ResponseBody
    public Map<String,Object> add(Product product, MultipartFile file) throws IOException {
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        product.setId(uuid);
        //上传文件
        String fileName = file.getOriginalFilename();
        File f = new File("D:\\"+fileName);
        file.transferTo(f);

        productList.add(product);
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("msg", "添加商品成功");
        return response;
        //return "redirect:/product/list";
    }*/
/*  //使用服务的方式
    @PostMapping("/add")
    //返回数据
    @ResponseBody
    //{"msg":"添加产品成功","code":200}
    public Map<String, Object> add(Product product, MultipartFile file, HttpSession session) throws IOException {
        //生成一个产品唯一id
        String id = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        product.setId(id);
        //上传图片返回图片地址
        //String fileUrl = QiniuUtil.upload(file);
        String fileUrl = fileService.upload(file);
        product.setImgUrl(fileUrl);
        list.add(product);
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("msg", "添加产品成功");
        return response;
    }*/

    @PostMapping("/add")
    @ResponseBody
    public Map<String,Object> add(Product product, HttpServletRequest request,MultipartFile file) throws IOException {

        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        String fileUrl = QiniuUtil.upload(file);
        product.setImage(fileUrl);
        product.setId(uuid);
        productList.add(product);
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("msg", "添加商品成功");
        return response;
        //return "redirect:/product/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        ListIterator<Product> iterator = productList.listIterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getId().equals(id)) {
                productList.remove(product);
                break;
            }
        }
        return "redirect:/product/list";
    }

}
