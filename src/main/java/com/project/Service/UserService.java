/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.Service;

import com.project.Entities.UserMstr;
import com.project.Repositories.UserRepository;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jayant Kolekar
 */

public interface UserService {
    /**
     * This method is used for registering new User, 
     * @param user: User Object.
     * @return 
     */
    public UserMstr registerNewUser(UserMstr user);
    
    /**
     * This method is used for calculating the exam result and storing the answers of the corresponding user.
     * @param userId: User Id
     * @param inputString: Answers submitted by the user.
     * @param finalProficiencyLevel: Final Proficiency Level of the candidate.
     * @return 
     */
    public Map<String,Object> submitUserTest(Integer userId, String inputString,Integer finalProficiencyLevel);

    /**
     * This method is used for displaying user list with the marks obtained.
     * @param fromDate: From Date.
     * @param toDate: To Date.
     * @return 
     */
    public List<UserMstr> getUsersWithTestResult(Date fromDate, Date toDate);

    /**
     * This method is used for deleting user records for corresponding userId.
     * @param userId: User Id.
     */
    public void deleteUser(Integer userId);
    
    public List<UserMstr> findAll();
    
    public UserMstr findUserById(int id);
    
    public UserMstr login(String email, String password);
    
    public UserMstr findUserByEmail(String email);
    
    public UserMstr create(UserMstr user);
    
 }
