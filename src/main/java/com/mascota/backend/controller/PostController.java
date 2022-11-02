package com.mascota.backend.controller;

import com.mascota.backend.model.Post;
import com.mascota.backend.model.User;
import com.mascota.backend.repository.PostRepository;
import com.mascota.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/home/{id}")
    public String home(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "home";
    }

    @PostMapping("/add/{id}/posts")
    public Post savePost(@PathVariable(value = "id") Integer id, @RequestBody Post postRequest) {
        Post post = userRepository.findById(id).map(user -> {
            postRequest.setUser(user);
            return postRepository.save(postRequest);
        }).orElseThrow();
        return postRepository.save(post);
    }

}
