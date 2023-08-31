package com.Assignment.Service;

import com.Assignment.Repository.UserRepository;
import com.Assignment.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
//    characterEncoding=utf8

    public  void insertUser(User user){
        userRepository.save(user);
    }

    public boolean findByUseremail(String email){
        return  userRepository.existsByEmail(email);
    }

    public  User findByEmail(String username){
        return  userRepository.findByEmail(username);
    }
}
