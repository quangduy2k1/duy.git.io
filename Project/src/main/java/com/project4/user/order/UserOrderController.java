package com.project4.user.order;

import com.project4.common.entites.Order;
import com.project4.common.entites.OrderDetail;
import com.project4.common.entites.Product;
import com.project4.common.entites.User;
import com.project4.user.authen.UserAuthenService;
import com.project4.user.product.UserProductService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/api/v1/user")
public record UserOrderController(UserAuthenService userAuthenService,
                                  UserProductService userProductService,
                                  UserOrderService userOrderService,
                                  UserOrderDetailService userOrderDetailService) {
    private static final String ADD_TO_CART = "/addToCart/{id}";
    private static final String GET_LIST_CART = "/homepage/cart";
    private static final String DELETE_CART = "/delete/{key}";
    private static final String CREATE_CART = "/homepage/saveCart";
    private static final String GET_DETAILS_CART = "/homepage/details/{orderId}";

    @GetMapping(ADD_TO_CART)
    public String addToCart(@PathVariable int id, Model model, HttpSession session) {
        List<User> userList = userAuthenService.listAll();
        model.addAttribute("userList", userList);
        Product p = userProductService.findById(id);
        List<Product> list1 = userProductService.listAll();
        model.addAttribute("list1", list1);
        if (session.getAttribute("prodsess") == null) {

            HashMap<Integer, Float> cart = new HashMap<>();
            cart.put(p.getProductId(), p.getPrice());
            session.setAttribute("prodsess", cart);
            model.addAttribute("cart", cart);
            float sum = 0;
            for (Float val : cart.values()) {
                sum += val;
            }
            model.addAttribute("sum", sum);
            session.setAttribute("sum", sum);
        } else {
            HashMap<Integer, Float> cart = (HashMap<Integer, Float>) session.getAttribute("prodsess");
            cart.put(p.getProductId(), p.getPrice());
            session.setAttribute("prodsess", cart);
            model.addAttribute("cart", cart);

            float sum = 0;
            for (float val : cart.values()) {
                sum += val;
            }
            model.addAttribute("sum", sum);
            session.setAttribute("sum", sum);
        }
        model.addAttribute("orders", new Order());
        return "homepage/cart";

    }

    @GetMapping(GET_LIST_CART)
    public String cart(HttpSession session, Model model) {
        List<User> userList = userAuthenService.listAll();
        model.addAttribute("userList", userList);
        List<Order> order = userOrderService.listAll();
        model.addAttribute("orders", order);
        List<User> list2 = userAuthenService.listAll();
        model.addAttribute("lists", list2);
        String s2 = String.valueOf(session.getAttribute("UserNames"));
        for (User user : userAuthenService.listAll()) {
            if (s2.equals(user.getEmail()) == true) {
                int number = user.getUserId();
                model.addAttribute("id", number);
            }
        }
        return "homepage/show-cart";
    }

    @DeleteMapping(DELETE_CART)
    public String deleteFromCart(@PathVariable("key") Integer key, HttpSession session, Model model) {
        HashMap<Integer, Float> cart = (HashMap<Integer, Float>) session.getAttribute("prodsess");
        cart.remove(key);
        float sum = 0;
        for (float val : cart.values()) {
            sum += val;
        }
        session.setAttribute("prodsess", cart);
        model.addAttribute("sum", sum);
        session.removeAttribute("sum");
        model.addAttribute("cart", cart);
        session.setAttribute("sum", sum);
        List<Product> list1 = userProductService.listAll();
        model.addAttribute("list1", list1);
        return "homepage/cart";
    }

    @PostMapping(CREATE_CART)
    public String saveCart(RedirectAttributes ra, HttpSession session, Model model) {
        ra.addFlashAttribute("message", " Thank You");
        List<Product> list1 = userProductService.listAll();
        List<User> userList = userAuthenService.listAll();
        model.addAttribute("userList", userList);
        String s2 = String.valueOf(session.getAttribute("UserNames"));
        Order ord = new Order();
        for (User user : userAuthenService.listAll()) {
            if (s2.equals(user.getEmail()) == true) {
                ord.setOrderdate(new Date());
                ord.setUser(userAuthenService.get(user.getUserId()));
                ord.setStatus("confirmed!");
                ord.setAddress("123_Haibatrung");
                ord.setTotal((Float) session.getAttribute("sum"));
                List<OrderDetail> details = new ArrayList<>();
                HashMap<Integer, Float> cart = (HashMap<Integer, Float>) session.getAttribute("prodsess");
                Set set = cart.entrySet();
                Iterator iterator = set.iterator();
                while (iterator.hasNext()) {
                    Map.Entry mentry = (Map.Entry) iterator.next();
                    System.out.print("key is: " + mentry.getKey() + " & Value is: ");
                    System.out.println(mentry.getValue());
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(ord);
                    orderDetail.setQty("1");
                    orderDetail.setProduct(userProductService.findById((Integer) mentry.getKey()));
                    orderDetail.setPrice(String.valueOf(mentry.getValue()));
                    details.add(orderDetail);
                }
                ord.setOrderDetails(details);
                userOrderService.save(ord);
                cart.clear();
            }
        }
        return "redirect:/homepage/cart";
    }

    @GetMapping(GET_DETAILS_CART)
    public String detalisCatrt(Model model, @PathVariable("orderId") Integer orderId) {
        List<User> userList = userAuthenService.listAll();
        model.addAttribute("userList", userList);
        Page<OrderDetail> orderDetail = userOrderDetailService.findIds(orderId, 1);
        model.addAttribute("lists", orderDetail);
        List<Product> list1 = userProductService.listAll();
        model.addAttribute("hihi", list1);
        return "homepage/details_cart";
    }
}
