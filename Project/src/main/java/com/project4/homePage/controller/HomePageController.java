package com.project4.homePage.controller;

import com.project4.admin.models.*;
import com.project4.admin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
public class HomePageController {
    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/homepage")
    public String showStyleList(Model model) {
        String keyword = "";
        return search(model, 1, keyword);
    }

    @GetMapping("/homepage/details_category/{categoryname}/{page}")
    public String listPage(Model model, @PathVariable("categoryname") String categoryname, @PathVariable("page") int cur) {
        Page<Product> page = productService.findNameP(categoryname, cur);
        int totalItem = (int) page.getTotalElements();
        int totalPage = page.getTotalPages();
        List<Product> lists = page.getContent();
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("cur", cur);
        model.addAttribute("categoryname", categoryname);
        model.addAttribute("lists", lists);
        List<Brand> list = brandService.listAll();
        model.addAttribute("list", list);
        List<Category> listt = categoryService.listAll();
        model.addAttribute("listt", listt);
        List<User> userList = userService.listAll();
        model.addAttribute("userList", userList);
        List<Product> list1 = productService.listAll();
        model.addAttribute("list1", list1);
        return "Homepage/details_category_home";
    }

    @GetMapping("/homepage/details_brand/{brandName}/{page}")
    public String listPagee(Model model, @PathVariable("brandName") String brandName, @PathVariable("page") int cur) {
        Page<Product> page = productService.findNameB(brandName, cur);
        int totalItem = (int) page.getTotalElements();
        int totalPage = page.getTotalPages();
        List<Product> lists = page.getContent();
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("cur", cur);
        model.addAttribute("brandName", brandName);
        model.addAttribute("lists", lists);
        List<Brand> list = brandService.listAll();
        model.addAttribute("list", list);
        List<Category> listt = categoryService.listAll();
        model.addAttribute("listt", listt);
        List<User> userList = userService.listAll();
        model.addAttribute("userList", userList);
        List<Product> list1 = productService.listAll();
        model.addAttribute("list1", list1);
        return "Homepage/details_brand_home";
    }

    @GetMapping("/homepage/infor/{productId}/{brandName}/{page}")
    public String listProduct(Model model, @PathVariable("productId") Integer productId, @PathVariable("brandName") String brandName, @PathVariable("page") int cur) {
        Product products = productService.get(productId);
        model.addAttribute("productId", productId);
        model.addAttribute("products", products);
        Page<Product> page = productService.findNameB(brandName, cur);
        int totalItem = (int) page.getTotalElements();
        int totalPage = page.getTotalPages();
        List<Product> lists = page.getContent();
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("cur", cur);
        model.addAttribute("brandName", brandName);
        model.addAttribute("lists", lists);
        List<Brand> list = brandService.listAll();
        model.addAttribute("list", list);
        List<Category> listt = categoryService.listAll();
        model.addAttribute("listt", listt);
        List<User> userList = userService.listAll();
        model.addAttribute("userList", userList);
        List<Product> list1 = productService.listAll();
        model.addAttribute("list1", list1);
        return "Homepage/infor";
    }

    @GetMapping("/homepage/page/{pageNumber}")
    public String search(Model model, @PathVariable("pageNumber") int currentPage, @Param("keyword") String keyword) {
        Page<Product> page = productService.findNameProducts(currentPage, keyword);
        int totalItem = (int) page.getTotalElements();
        int totalPage = page.getTotalPages();
        List<Product> listsss = page.getContent();
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("keyword", keyword);
        model.addAttribute("listsss", listsss);
        List<Brand> list = brandService.listAll();
        model.addAttribute("list", list);
        List<Category> listt = categoryService.listAll();
        model.addAttribute("listt", listt);
        List<User> userList = userService.listAll();
        model.addAttribute("userList", userList);
        System.out.println(totalItem);
        List<Product> list1 = productService.listAll();
        model.addAttribute("list1", list1);
        return "Homepage/homePage";
    }

    @PostMapping("/homepage/user/login")
    public String checklogin(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
        if (userService.chek(email, password)) {
            session.setAttribute("UserNames", email);
            return "redirect:/homepage";
        } else {
            model.addAttribute("message", " incorrect password or incorrect email");
            session.setAttribute("UserNames", email);
        }
        return "homepage/login";
    }

    @GetMapping("/homepage/user/registration")
    public String show(Model model) {
        List<Product> list1 = productService.listAll();
        model.addAttribute("list1", list1);
        List<Brand> list = brandService.listAll();
        model.addAttribute("list", list);
        List<Category> listt = categoryService.listAll();
        model.addAttribute("listt", listt);
        model.addAttribute("users", new User());
        return "Homepage/registration";
    }

    @GetMapping("/homepage/user/login")
    public String shows(Model model, HttpSession session) {
        List<Product> list1 = productService.listAll();
        model.addAttribute("list1", list1);
        List<Brand> list = brandService.listAll();
        model.addAttribute("list", list);
        List<Category> listt = categoryService.listAll();
        model.addAttribute("listt", listt);
        session.removeAttribute("UserNames");
        return "homepage/login";
    }

    @PostMapping("/homepage/user/save")
    public String savee(User user, RedirectAttributes ra, @RequestParam("email") String email, HttpSession session) throws IOException {
        userService.save(user);
        ra.addFlashAttribute("message", " The User has been saved successfully");
        session.setAttribute("UserNames", email);
        return "redirect:/homepage";
    }

    @GetMapping("/homepage/contact")
    public String contact(Model model) {
        List<Product> list1 = productService.listAll();
        model.addAttribute("list1", list1);
        List<User> listu = userService.listAll();
        model.addAttribute("listu", listu);
        List<Brand> list = brandService.listAll();
        model.addAttribute("list", list);
        List<Category> listt = categoryService.listAll();
        model.addAttribute("listt", listt);
        model.addAttribute("feedback", new FeedBack());
        return "homepage/contact";
    }

    @PostMapping("homepage/contact/save")
    public String save(FeedBack feedBack, RedirectAttributes ra) {
        feedbackService.save(feedBack);
        ra.addFlashAttribute("message", " Thank You");
        return "redirect:/homepage";
    }

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id, Model model, HttpSession session) {
        List<User> userList = userService.listAll();
        model.addAttribute("userList", userList);
        Product p = productService.findById(id);
        List<Product> list1 = productService.listAll();
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

    @RequestMapping("homepage/cart")
    public String cart(HttpSession session, Model model) {
        List<User> userList = userService.listAll();
        model.addAttribute("userList", userList);
        List<Order> order = orderService.listAll();
        model.addAttribute("orders", order);
        List<User> list2 = userService.listAll();
        model.addAttribute("lists", list2);
        String s2 = String.valueOf(session.getAttribute("UserNames"));
        for (User user : userService.listAll()) {
            if (s2.equals(user.getEmail()) == true) {
                int number = user.getUserId();
                model.addAttribute("id", number);
            }
        }
        return "homepage/show-cart";
    }

    @RequestMapping("/delete/{key}")
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
        List<Product> list1 = productService.listAll();
        model.addAttribute("list1", list1);
        return "homepage/cart";
    }

    @RequestMapping("homepage/saveCart")
    public String saveCart(RedirectAttributes ra, HttpSession session, Model model) {
        ra.addFlashAttribute("message", " Thank You");
        List<Product> list1 = productService.listAll();
        List<User> userList = userService.listAll();
        model.addAttribute("userList", userList);
        String s2 = String.valueOf(session.getAttribute("UserNames"));
        Order ord = new Order();
        for (User user : userService.listAll()) {
            if (s2.equals(user.getEmail()) == true) {
                ord.setOrderdate(new Date());
                ord.setUser(userService.get(user.getUserId()));
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
                    orderDetail.setProduct(productService.findById((Integer) mentry.getKey()));
                    orderDetail.setPrice(String.valueOf(mentry.getValue()));
                    details.add(orderDetail);
                }
                ord.setOrderDetails(details);
                orderService.save(ord);
                cart.clear();
            }
        }
        return "redirect:/homepage/cart";
    }

    @RequestMapping("homepage/details/{orderId}")
    public String detalisCatrt(Model model, @PathVariable("orderId") Integer orderId) {
        List<User> userList = userService.listAll();
        model.addAttribute("userList", userList);
        Page<OrderDetail> orderDetail = orderDetailService.findIds(orderId, 1);
        model.addAttribute("lists", orderDetail);
        List<Product> list1 = productService.listAll();
        model.addAttribute("hihi", list1);
        return "homepage/details_cart";
    }
}
