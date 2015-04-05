/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.Controller;

import com.project.Entities.UserMstr;
import com.project.Service.UserService;
import com.project.Utility.WebUtility;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author Jayant Kolekar
 */
@Controller
public class AdminController extends MultiActionController {
    
     /**
     * This method is used for displaying base/welcome page.
     * @param request: HttpServletRequest Object.
     * @param response: HttpServletResponse Object.
     * @return
     * @throws Exception 
     */
    @RequestMapping("/index.htm")
    public ModelAndView index(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return new ModelAndView("index");
    }
    
    /**
     * This method is used for displaying user list with the marks obtained.
     * @param request: HttpServletRequest Object.
     * @param response: HttpServletResponse Object.
     * @return
     * @throws Exception 
     */
    @RequestMapping("/admin.htm")
    public ModelAndView admin(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        UserService userService = (UserService)WebUtility.getBean(request, "userService");
        String fromStringDate = request.getParameter("fromDate");
        String toStringDate = request.getParameter("toDate");
        Date fromDate = WebUtility.getDate(fromStringDate);
        Date toDate = WebUtility.getDate(toStringDate);
        List<UserMstr> userDetailsList = userService.getUsersWithTestResult(fromDate,toDate);
        
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("FromDate",fromStringDate);
            modelAndView.addObject("ToDate",toStringDate);
            modelAndView.addObject("UserDetailsList",userDetailsList);
            modelAndView.setViewName("Admin");
            return modelAndView;
        
    }
    
    /**
     * This method is used for deleting user.
     * @param request: HttpServletRequest Object.
     * @param response: HttpServletResponse Object.
     * @return
     * @throws Exception 
     */
    @RequestMapping("/deleteUser.htm")
    public ModelAndView deleteUser(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        UserService userService = (UserService)WebUtility.getBean(request, "userService");
        String userIdStr = request.getParameter("userId");
        Integer userId = null;
        ModelAndView modelAndView = new ModelAndView();
        if(userIdStr != null && !userIdStr.isEmpty()){
            userId = Integer.parseInt(userIdStr);
        }
        try{
            userService.deleteUser(userId);
            modelAndView.setViewName("Success");
        }catch(Exception exception){
            modelAndView.setViewName("Failure");
            exception.printStackTrace();
        }
        return modelAndView;
    }
    
    /**
     * This method is used for displaying user list with the marks obtained.
     * @param request: HttpServletRequest Object.
     * @param response: HttpServletResponse Object.
     * @return
     * @throws Exception 
     */
    @RequestMapping("/login.htm")
    public ModelAndView login(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("ErrorMessage","Please enter valid user name or password.");
            modelAndView.setViewName("Login");
            return modelAndView;
    }
    
   
    
    /**
     * 
     * @return 
     */
    private List<Integer> initializeQuestionsPerPage(){
        List <Integer> noOfQuestionsPerpage = new ArrayList<Integer>();
        noOfQuestionsPerpage.add(1);
        noOfQuestionsPerpage.add(5);
        noOfQuestionsPerpage.add(10);
        return noOfQuestionsPerpage;
    
    }
    
    /**
     * This method is used for initializing question set time.
     * @return 
     */
    private List<Integer> initializeTotalQuestionsAndTime(){
        List <Integer> totalQuestionsAndTime =  new ArrayList<Integer>();
            totalQuestionsAndTime.add(10);
            totalQuestionsAndTime.add(15);
            totalQuestionsAndTime.add(20);
            totalQuestionsAndTime.add(25);
            totalQuestionsAndTime.add(30);
            totalQuestionsAndTime.add(35);
            totalQuestionsAndTime.add(40);
            totalQuestionsAndTime.add(45);
            totalQuestionsAndTime.add(50);
            totalQuestionsAndTime.add(55);
            totalQuestionsAndTime.add(60);
        return totalQuestionsAndTime;
    
    }
    
      
}
