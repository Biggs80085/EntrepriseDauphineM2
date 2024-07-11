package com.example.user.Service;

import com.example.user.Model.Auser;
import com.example.user.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<Auser> getAllUsers() {
        return userRepository.findAll();
    }

    public Auser getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }


    public Auser createUser(Auser auser){
        return userRepository.save(auser);
    }

    public Auser updateUser(Long id, Auser userDetails){
        Auser user = userRepository.findById(id).orElseThrow(() -> new ErrorResponseException(HttpStatus.NOT_FOUND));
        user.setUserName(userDetails.getUserName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
