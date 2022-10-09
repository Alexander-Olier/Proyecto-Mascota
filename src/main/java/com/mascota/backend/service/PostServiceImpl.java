package com.mascota.backend.service;

import com.mascota.backend.model.Post;
import com.mascota.backend.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements  PostService{
    @Autowired
    private PostRepository postRepository;

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = postRepository.findAll();
        return posts;
    }

    @Override
    public Post getId(Integer id) {
        return postRepository.findById(id).get();
    }

    @Override
    public void delete(Integer id) {
        postRepository.deleteById(id);
    }
}
