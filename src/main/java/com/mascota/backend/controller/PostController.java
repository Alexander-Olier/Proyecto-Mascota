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
        model.addAttribute("postSave", new Post());
        model.addAttribute("posts", posts);
        return "home";
    }

    @PostMapping("/addPost/{id}")
    public String savePost(@ModelAttribute @PathVariable(value = "id") Integer id, @RequestBody Post postRequest, RedirectAttributes redirectAttributes) {
        Post post = userRepository.findById(id).map(user -> {
            postRequest.setUser(user);
            return postRepository.save(postRequest);
        }).orElseThrow();
        List<Post> allPost =postRepository.findAll();
        redirectAttributes.addFlashAttribute("post", allPost);
        redirectAttributes.addFlashAttribute("user", post.getUser());
        return "redirect:/home/" + post.getUser().getId();
    }

}
