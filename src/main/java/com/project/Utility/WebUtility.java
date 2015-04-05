/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.Utility;

import com.project.Entities.UserMstr;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author JAYANT KOLEKAR
 */
public class WebUtility {
     /**
     * This method is used for retrieving the reference of the service bean from context.
     * @param request: The request object
     * @param name: The name of the service bean.
     * @return
     */
    public static Object getBean(HttpServletRequest request,String name) {
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.
                                getSession().getServletContext());
        return ctx.getBean(name);
    }
    
    /**
     * This method is used for converting date in String Object to Date Object.
     * @param dateString: Date in String Format.
     * @return 
     */
    public static Date getDate(String dateString){
        if(dateString != null){
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); 
            Date date = null;
            try {
                date = df.parse(dateString);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return date;
        }
        else{
            return null;
        }
    }
    
    /**
     * This method is used for creating a userName from the User's first name and date of birth.
     * @param firstName: User's first name.
     * @param dateOfBirth: User's Date OF  Birth,
     * @return 
     */
    public static String createUserName(String firstName, Date dateOfBirth){
        String userName = null;
        if(firstName.length() >= 3 ){
            userName = firstName.substring(0, 3);
        }
        else{
            userName = firstName;
        }
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YY");
        String birthDate = dateFormat.format(dateOfBirth);
        String[] birthDateArray = birthDate.split("-");
        userName += birthDateArray[0] + birthDateArray[1] + birthDateArray[2];
        
        return userName;
    }

    /**
     * This method is used for creating new User Session.
     * @param user: UserMstr Object.
     * @param request: Request Object. 
     */
   public static void createNewUserSession(UserMstr user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("User_"+user.getUserID(), user);
    }

    /**
     * This method is used for clearing the user session.
     * @param request: Request Object.
     * @param userId: User Id. 
     */
    public static void clearSession(HttpServletRequest request, String userId) {
        HttpSession session = request.getSession();
        session.removeAttribute("User_"+userId);
        session.removeAttribute("QuestionList_"+userId);
    }
    
    /**
     * This method is used for creating new PagedListHolder Session.
     * @param pagedListHolder: PagedListHolder Object.
     * @param userId: User Id.
     * @param request: Request Object. 
     */
   public static void createNewPlaceHolderSession(PagedListHolder pagedListHolder, Integer userId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("QuestionList_"+userId, pagedListHolder);
   }
   
   /**
     * This method is used for retrieving PagedListHolder Session.
     * @param userId: User Id.
     * @param request: Request Object. 
     * @return 
     */
   public static PagedListHolder getPlaceHolderSession(Integer userId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (PagedListHolder)session.getAttribute("QuestionList_"+userId);
    }
   
    /**
     * This method is used for retrieving User Session.
     * @param userId: User Id.
     * @param request: Request Object. 
     * @return 
     */
   public static UserMstr getUserSession(Integer userId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (UserMstr)session.getAttribute("User_"+userId);
    }
}
