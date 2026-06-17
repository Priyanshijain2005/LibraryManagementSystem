package com.library.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.Repository.UserRepository;
import com.library.entity.User;
import com.library.exception.UserNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public User addUser(User user){
        return repo.save(user);
    }

    public List<User> getAllUsers(){
        return repo.findAll();
    }

    public User getUserById(Long id){
        return repo.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));
    }

    public void deleteUser(Long id){
        repo.deleteById(id);
    }
    
    public long countUsers() {
    	return repo.count();
    }
}