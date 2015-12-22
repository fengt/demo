package com.xjw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xjw.entity.User;
import com.xjw.service.UserService;

@Controller
@RequestMapping("/user/*")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping(value={"/","","index"})
    public ModelAndView index(){

        return createModel("user/index").addObject("users", userService.query());
    }
    
    @RequestMapping("new")
    public ModelAndView create(){
    	return createModel("user/new").addObject("user", new User());
    }
    
    @RequestMapping("{id}/edit")
    public ModelAndView edit(@PathVariable int id){
    	return createModel("user/new").addObject("user", userService.findById(id));
    }
    
    @RequestMapping("save")
    public ModelAndView save(@Valid User user,BindingResult result){
    	
    	if(result.hasErrors()){
    		return createModel("user/new");
    	}
    	
    	userService.insert(user);
    	
    	return createModel("redirect:/user/index");
    	
    }
    
    @RequestMapping("update")
    public ModelAndView update(User user){
    	
    	userService.update(user);
    	
    	return createModel("redirect:/user/index");
    }
    
    @RequestMapping("{id}/delete")
    public ModelAndView delete(@PathVariable int id){
    	userService.delete(id);
    	return createModel("redirect:/user/index");
    }
    
}
