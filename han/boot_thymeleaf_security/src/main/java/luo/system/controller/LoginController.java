package luo.system.controller;

import luo.system.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    SecurityService securityService;


    /**
     * 登录页面
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(Model model
            , @RequestParam(value = "error", required = false) boolean error
            , @RequestParam(value = "logout", required = false) boolean logout) {
        //如果已经登陆跳转到个人首页
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication:"+authentication.getName());
        if (error == true)
            model.addAttribute("error", "用户名/密码错误");
        if (logout == true)
            model.addAttribute("logout", logout);
        return "login";
    }
    @RequestMapping(value = {"/"})
    public String login() {
        return "home";
    }
    @RequestMapping(value = {"/welcome"})
    public String welcome() {
        return "welcome";
    }
    @Value(value = "${spring.thymeleaf.cache}")
    boolean flag;
}
