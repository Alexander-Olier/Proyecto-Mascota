package com.mascota.backend.controller;

import com.mascota.backend.model.User;
import com.mascota.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/add")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        userRepository.save(user);
        redirectAttributes.addFlashAttribute("user", user);
        return "redirect:/home/" + user.getId();
    }
    @GetMapping("/user/{id}")
    public String userOne(Model model){
        return "user";
    }
    //login
    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/")
    public String authLogin(@ModelAttribute @RequestBody User userRequest, RedirectAttributes redirectAttributes) {
        List<User> users = userRepository.findAll();
        String mail = userRequest.getMail();
        String password = userRequest.getPassword();
        for (int x = 0; x < users.size(); x++) {
            User u = users.get(x);
            if (u.getMail().equals(mail)) {
                if (u.getPassword().equals(password)) {
                    redirectAttributes.addFlashAttribute("user", u);
                    return "redirect:/home/" + u.getId();
                }else{
                    System.out.println("password incorrect");
                }
            } else {
                System.out.println("NotEqual");
            }
        }
        return "error";
    }

}
