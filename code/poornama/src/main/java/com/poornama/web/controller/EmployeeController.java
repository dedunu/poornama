package com.poornama.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by dedunu on 10/24/14.
 */
@Controller
@RequestMapping("/employee/")
public class EmployeeController {
    @RequestMapping(value="create",method = RequestMethod.GET)
    public String createForm(Model model,HttpSession session){
        return "user/dashboard";
    }

    @RequestMapping(value="create",method = RequestMethod.POST)
    public String createEmployee(Model model,HttpSession session){
        return "user/dashboard";
    }
}