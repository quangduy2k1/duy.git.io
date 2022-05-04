package com.project4.admin.controller;

import com.project4.admin.models.Order;
import com.project4.admin.models.OrderDetail;
import com.project4.admin.models.Product;
import com.project4.admin.models.User;
import com.project4.admin.service.OrderDetailService;
import com.project4.admin.service.OrderService;
import com.project4.admin.service.ProductService;
import com.project4.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private UserService userService;
@Autowired
private ProductService productService;
    @GetMapping("/order")
    public String showList(Model model) {
        List<Order> orders = orderService.listAll();
        model.addAttribute("order", orders);
        List<User>users=userService.listAll();
        model.addAttribute("user",users);
        return "admin/order";
    }

    @RequestMapping("order/details/{orderId}")
    public String showLists(Model model, @PathVariable("orderId") Integer orderId) {
        Page<OrderDetail> orderDetail = orderDetailService.findIds(orderId, 1);
        model.addAttribute("lists", orderDetail);
        List<Product>products=productService.listAll();
        model.addAttribute("pro",products);
        return "admin/order_details";
    }
}
