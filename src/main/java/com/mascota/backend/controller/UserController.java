package com.mascota.backend.controller;

import com.mascota.backend.model.User;
import com.mascota.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/new")
    public String newUSer(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(RedirectAttributes redirectAttributes, User user) {
        userService.save(user);
        redirectAttributes.addFlashAttribute("user", user);
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        return "home";
    }

   /*
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        userService.delete(id);
        return "correct delete";
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Integer id) {
        try {
            User user = userService.getId(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public String update(@RequestBody User user, @PathVariable Integer id) {
        try {
            User existingUser = userService.getId(id);
            existingUser = user;
            existingUser.setId(id);
            userService.save(existingUser);
            return "Actualizado";
        } catch (NoSuchElementException e) {
            return "ERROR";
        }
    }

*/
}
