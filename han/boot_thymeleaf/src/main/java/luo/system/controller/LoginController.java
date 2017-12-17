package luo.system.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class LoginController {

    @RequestMapping(value = "/")
    public String home(Map<String, Object> model) {
        model.put("message", "Hello World");
        model.put("title", "Hello Home");
        model.put("date", new Date());
        return "home";
    }
    @Value(value = "${spring.thymeleaf.cache}")
    boolean flag ;
    @RequestMapping(value = {"/config"})
    public String welcome(Map<String, Object> model){
        model.put("message",flag+"fsssf");
        return "welcome";
    }
    @RequestMapping(value = {"/index"})
    public String index(Map<String, Object> model){
        model.put("message", "Hello Worldsss ");
        model.put("title", "Hello Homessss damn fauc");
        model.put("date", new Date());
        return "welcome";
    }
    @RequestMapping(value = {"/model"})
    public @ResponseBody Map<String, Object> model(){
        Map<String, Object> model = new HashMap<String, Object>();
        List<String> lf = new ArrayList<String>();
        model.put("message", "Hello Worldsss ");
        model.put("title", "Hello Homessss damn fauc");
        model.put("date", new Date());
        return model;
    }

}
