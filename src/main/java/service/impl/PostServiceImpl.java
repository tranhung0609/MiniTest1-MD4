package service.impl;

import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import repository.PostRepository;
import service.IPostService;
import repository.IPostRepository;

import java.util.Optional;

public class PostServiceImpl implements IPostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    IPostRepository iPostRepository;

    @Override
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public void remove(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Iterable<Post> findTop4Likes() {
        return iPostRepository.findTop4New();
    }
}
