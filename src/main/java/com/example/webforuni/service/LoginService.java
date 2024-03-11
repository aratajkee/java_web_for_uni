package com.example.webforuni.service;

import com.example.webforuni.repository.LoginRepository;
import com.example.webforuni.user.model.Login;
import com.example.webforuni.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final UserService userService;
    private final LoginRepository loginRepository;

    @Autowired
    public LoginService(UserService service, LoginRepository repository) {
        this.userService = service;
        this.loginRepository = repository;
    }

    public boolean askLogin(User user) {
        for (User userExist : userService.readAll()) {
            final boolean emailExist = userExist.getEmail().equals(user.getEmail());
            final boolean passwordExist = userExist.getPassword().equals(user.getPassword());

            if(emailExist && passwordExist) {
                updateCounter(userExist);
                return true;
            }
        }
        return false;
    }

    public Integer getCounter() {
        return loginRepository.findById(1).isPresent()
                ? loginRepository.findById(1).get().getCounter()
                : 0;
    }

    private void updateCounter(User user) {
        Integer cVal = getCounter() + 1;

        if (loginRepository.findById(1).isPresent()) {
            loginRepository.delete(loginRepository.findById(1).get());
            loginRepository.save(new Login(1,cVal, user.getEmail(), user.getRole()));
        } else {
            loginRepository.save(new Login(1, cVal, user.getEmail(), user.getRole()));
        }
    }

    public boolean checkRole(String role) {
        if (loginRepository.findById(1).isPresent()) {
            String currentUserRole = loginRepository.findById(1).get().getRole();
            return currentUserRole.equals(role);
        }
        throw new RuntimeException("Error reading login from login BD!");
    }


    /**
     * @return current user role
     */
    public String getRole() {
        return loginRepository.findById(1).isPresent()
                ? loginRepository.findById(1).get().getRole()
                : "Error";
    }

    private String getEmail() {
        return loginRepository.findById(1).isPresent()
                ? loginRepository.findById(1).get().getUserEmail()
                : null;
    }

    /**
     * @return current user
     */
    public User getCurrentUser() {
        final String currentEmail = getEmail();
        for (User user : userService.readAll()) {
            if (user.getEmail().equals(currentEmail)){
                return user;
            }
        }
        return null;
    }

    public void logOut() {
        final int logVal = getCounter();
        if (loginRepository.findById(1).isPresent()) {
            loginRepository.delete(loginRepository.findById(1).get());
            loginRepository.save(new Login(1, logVal));
        }
    }

}
