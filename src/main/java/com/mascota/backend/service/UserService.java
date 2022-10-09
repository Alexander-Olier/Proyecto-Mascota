package com.mascota.backend.service;

import com.mascota.backend.model.User;

import java.util.List;

public interface UserService {
    public User save(User user);
    public List<User> getAll();
    public User getId(Integer id);

    public void delete(Integer id);

}
