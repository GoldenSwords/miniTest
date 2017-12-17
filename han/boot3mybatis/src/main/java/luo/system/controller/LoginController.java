package luo.system.controller;

import luo.system.datasource.DataSourceHolder;
import luo.system.datasource.DatabaseType;
import luo.system.service.UrlsManager;
import luo.system.service.UsersManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    UrlsManager urlsManager;
    @Autowired
    UsersManager usersManager;
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
    @RequestMapping(value = {"/logout"})
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }
    @RequestMapping(value = {"/","/welcome"})
    public String welcome(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> roleList = (List<GrantedAuthority>) auth.getAuthorities();
        model.addAttribute("roleList",urlsManager.getUrlsResources(roleList));
        model.addAttribute("userList",usersManager.findAll());
        System.out.println(DataSourceHolder.getDataSource());
        DataSourceHolder.setDataSource(DatabaseType.Fhadmin);
        System.out.println(DataSourceHolder.getDataSource());
        model.addAttribute("userList2",usersManager.findAllAdmin());
        return "welcome";
    }
    @Value(value = "${spring.thymeleaf.cache}")
    boolean flag;
}
