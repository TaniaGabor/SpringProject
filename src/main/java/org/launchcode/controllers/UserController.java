package org.launchcode.controllers;
import org.launchcode.models.User;
import org.launchcode.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller

public class UserController {
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/user")
    public String welcome( ) {
        return "user";
    }
    @GetMapping("/users/view")
    public String displayAllUsers(@RequestParam(required = false) Integer categoryId, Model model){
        if(categoryId == null) {
            model.addAttribute("title", "All users!");
            model.addAttribute("userlist",userRepository.findAll());
        }
        return "users/view";

    }
    @GetMapping("/users/delete")
    public String displayDeleteEventForm(Model model){
        model.addAttribute("title", "Delete Events");
        model.addAttribute("userlist",userRepository.findAll());
        return "users/delete";
    }

    @PostMapping("/users/delete")
    public String processDeleteEventForm(@RequestParam(required = false) int[] eventIds){

        if(eventIds != null) {
            for (int id : eventIds) {
               userRepository.deleteById((long) id);
            }
        }
        return "/users/delete";
    }

    @GetMapping("/users/update")
    public String renderCreateEventForm(Model model){
        model.addAttribute("title", "Update User");
        model.addAttribute("oldEmail", new User());
        model.addAttribute("newEmail", new User());
        //User user= userRepository.findByEmail(email);
       // model.addAttribute("user", user);
     //   model.addAttribute("emailNew",new User());
        return "users/update";
    }

   @PostMapping("users/update")
    public String createEvent(@ModelAttribute User newEmail) {
        String[] emailsArray = newEmail.getEmail().split(",");
        String oldEmailString = emailsArray[0];
        String newEmailString = emailsArray[1];
        User oldUser = userRepository.findByEmail(oldEmailString);
        oldUser.setEmail(newEmailString);
        userRepository.save(oldUser);
        return "user";
    }
}
