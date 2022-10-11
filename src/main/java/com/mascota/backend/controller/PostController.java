package com.mascota.backend.controller;

import com.mascota.backend.model.Post;
import com.mascota.backend.model.User;
import com.mascota.backend.service.PostService;
import com.mascota.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping
public class PostController {
    @Autowired
    private PostService postService;
    private UserService userService;

    @GetMapping("/")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model) {
        List<Post> posts = postService.getAll();
        model.addAttribute("posts", posts);
        return "home";
    }
}
