package ua.artcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    public String login(@RequestParam String name, @RequestParam String pass, HttpServletRequest request, HttpServletResponse response){
        //TODO get data(User entity) from database


        HttpSession session = request.getSession(true);


        return "page";
    }

}
