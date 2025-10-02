package com.javainuse.service;
import com.javainuse.repository.UserRepo;
import com.javainuse.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;


    public UserService(UserRepo userRepo){this.userRepo = userRepo;}

    ////////////////
    // getCurrentUser--> Bu metod, güvenlik bağlamında mevcut oturumu açık olan kullanıcıyı alır. Spring Security'nin SecurityContextHolder sınıfı kullanılarak güncel oturum bilgilerine erişilir. Daha sonra bu oturum bilgilerinden kullanıcı adı alınır ve bu kullanıcı adıyla veritabanında kullanıcı aranır. Bulunan kullanıcı nesnesi döndürülür.
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepo.findByUserName(username);
    }

    public boolean isCurrentUserLibrarian() {
        User currentUser = getCurrentUser();
        return currentUser.getRole() != null && currentUser.getRole().equalsIgnoreCase("LIBRARIAN");
    }

    public boolean isCurrentUserCustomer() {
        User currentUser = getCurrentUser();
        return currentUser.getRole() != null && currentUser.getRole().equalsIgnoreCase("CUSTOMER");
    }
    //Bu metodlar, güvenlik kontrolleri yapmak ve kullanıcıların yetkilerine göre farklı işlemler gerçekleştirmek için
    // kullanılabilir. Örneğin, belirli bir işlemi yalnızca kütüphaneci olan kullanıcıların yapmasını istiyorsanız
    // isCurrentUserLibrarian() metodunu kullanarak bu kontrolü yapabilirsiniz.
    /////////////////////////////////

    // READ
    // Butun userlerI getirsin
    public List<User> getAllUsers(){
        if(isCurrentUserLibrarian()){
            return userRepo.findAll();}
        throw new RuntimeException("Can't access!");
        }


    // girilen id li userin bilgilerini gorme ( sadece librarian erisebilir)
    public User getOneUser(Long userId){
        if(isCurrentUserLibrarian()){
            return userRepo.findById(userId).orElse(null);
        }
        throw new RuntimeException("Can't access!");
        }
}












