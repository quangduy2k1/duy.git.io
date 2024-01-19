package com.project4.admin.order;

import com.project4.common.entites.Order;
import com.project4.common.entites.OrderDetail;
import com.project4.common.entites.Product;
import com.project4.common.entites.User;
import com.project4.admin.product.ProductService;
import com.project4.admin.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/admin")
public record OrderController(OrderService orderService,
                              OrderDetailService orderDetailService,
                              UserService userService,
                              ProductService productService) {
    private static final String GET_LIST_ORDER = "order";
    private static final String GET_DETAILS_ORDER = "order/details/{orderId}";

    @GetMapping(GET_LIST_ORDER)
    public String showList(Model model) {
        List<Order> orders = orderService.listAll();
        model.addAttribute("order", orders);
        List<User> users = userService.listAll();
        model.addAttribute("user", users);
        return "admin/order";
    }

    @GetMapping(GET_DETAILS_ORDER)
    public String showLists(Model model, @PathVariable("orderId") Integer orderId) {
        Page<OrderDetail> orderDetail = orderDetailService.findIds(orderId, 1);
        model.addAttribute("lists", orderDetail);
        List<Product> products = productService.listAll();
        model.addAttribute("pro", products);
        return "admin/order_details";
    }
}
