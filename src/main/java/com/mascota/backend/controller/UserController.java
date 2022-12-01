package com.mascota.backend.controller;

import com.mascota.backend.model.Post;
import com.mascota.backend.model.User;
import com.mascota.backend.repository.PostRepository;
import com.mascota.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
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
                } else {
                    System.out.println("password incorrect");
                }
            } else {
                System.out.println("NotEqual");
            }
        }
        return "error";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "update";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute User user, @PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        userRepository.save(user);
        redirectAttributes.addFlashAttribute("user", user);
        return "redirect:/home/" + user.getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@ModelAttribute @PathVariable("id") int id) {
        try {
            userRepository.deleteById(id);
            return ("redirect:/");
        } catch (Exception e) {
            return ("error");
        }
    }

    @GetMapping("/user/{id}")
    public String userOne(@ModelAttribute @PathVariable("id") int id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        List<Post>posts = postRepository.findAll();
        List<Post> postsUser = new ArrayList<>();
        for (int i = 0; i<posts.size();i++){
            Post post = posts.get(i);
            if (post.getUser().getId() == id){
                postsUser.add(post);
            }
        }
        model.addAttribute("posts", postsUser);
        return "user";
    }
}
