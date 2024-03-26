package com.example.webforuni.controllers.page_controllers;

import com.example.webforuni.service.LoginService;
import com.example.webforuni.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {

    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;

    @GetMapping(value = "/uploadAvatar")
    public String open(Model model) {
        return "upload_avatar_form";
    }

    @PostMapping(value = "/uploadAvatar/new", params = "action=save")
    public String uploadAvatar(Model model, @RequestParam("file")MultipartFile file){
        String message = "";
        try{
            userService.addAvatar(loginService.getCurrentUser().getId(), file);
            message = "Uploaded image " + file.getOriginalFilename() + " !";
            model.addAttribute("message", message);
        } catch (Exception e) {
            message = "Error uploading image " + file.getOriginalFilename() + "!" +  " Error: " + e.getMessage();
            model.addAttribute("message", message);
        }
        return "upload_avatar_form";
    }

    @PostMapping(value = "/uploadAvatar/new", params = "action=cancel")
    public String cancel(){
        return "redirect:/userPage";
    }

}
