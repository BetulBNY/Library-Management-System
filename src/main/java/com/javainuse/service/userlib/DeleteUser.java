package com.javainuse.service.userlib;

import com.javainuse.model.User;
import com.javainuse.repository.UserRepo;
import com.javainuse.service.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@RequiredArgsConstructor
@Service
public class DeleteUser {

    private final UserRepo userRepo;
    /*
    private DeleteUser(UserRepo userRepo){
        this.userRepo = userRepo;
    }*/
    public String deleteUser(Long userId){
        Optional<User> userOptional = userRepo.findById(userId);
        if(userOptional.isPresent()){
            User deletedUser = userOptional.get();
            deletedUser.getRole();
            userRepo.deleteById(userId);
            return deletedUser.getUserName();
        }
        return null;
    }
}
