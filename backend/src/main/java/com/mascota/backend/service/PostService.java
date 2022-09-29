package com.mascota.backend.service;

import com.mascota.backend.model.Post;

import java.util.List;

public interface PostService {
    public Post save(Post post);

    public List<Post> getAll();

    public Post getId(Integer id);

    public void delete(Integer id);
}
