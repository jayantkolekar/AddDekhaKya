/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.ServiceImpl;

import com.project.Entities.UserMstr;
import com.project.Repositories.UserRepository;
import com.project.Service.UserService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
	private UserRepository userRepository;
	
    @Override
	public List<UserMstr> findAll() {
		return userRepository.findAll();
	}
    
    @Override
    public UserMstr create(UserMstr user) {
		return userRepository.save(user);
	}

    @Override
	public UserMstr findUserById(int id) {
		return userRepository.findOne(id);
	}

    @Override
	public UserMstr login(String email, String password) {
		return userRepository.findByEmailAndPassword(email,password);
	}


    @Override
	public UserMstr findUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

    @Override
    public UserMstr registerNewUser(UserMstr user) {
        return userRepository.save(user);
    }

    @Override
    public Map<String, Object> submitUserTest(Integer userId, String inputString, Integer finalProficiencyLevel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserMstr> getUsersWithTestResult(Date fromDate, Date toDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.delete(userId);
    }
    
}
