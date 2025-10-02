package com.javainuse.service.userlib;

import com.javainuse.model.User;
import com.javainuse.repository.UserRepo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateUser {

    private final UserRepo userRepo;

    private UpdateUser(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepo.findByUserName(username);
    }

    public boolean isCurrentUserLibrarian() {
        User currentUser = getCurrentUser();
        return currentUser.getRole() != null && currentUser.getRole().equalsIgnoreCase("LIBRARIAN");
    }
    public User updateUserData(Long userId, User neweUserDate){
        if(!isCurrentUserLibrarian()){
            throw new RuntimeException("You can't update!!");
        }
        Optional<User> oldUserData = userRepo.findById(userId);
        if(oldUserData.isPresent()){
            User updatedUserData = oldUserData.get();
            updatedUserData.setUserName(neweUserDate.getUserName());
            updatedUserData.setUserPassword(neweUserDate.getUserPassword());
            updatedUserData.setContactNumber(neweUserDate.getContactNumber());
            return userRepo.save(updatedUserData);
        }return null;
    }

}
