package service.impl;

import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.PostRepository;
import service.IGeneralService;
import service.IPostService;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PostServiceImpl implements IGeneralService<Post> {
    @Autowired
    PostRepository postRepository;

    //    @Autowired
//    IPostService iPostService;
    @Override
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> findById(int id) {
        return postRepository.findById(id);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void remove(int id) {
        postRepository.deleteById(id);
    }

    @Override
    public Iterable<Post> findAllByTittleContaining(String name) {
        return postRepository.findAllByTittleContaining(name);
    }

    @Override
    public Iterable<Post> findAllByOrderByLikes() {
        return postRepository.findAllByOrderByLikes();
    }


    @Override
    public Iterable<Post> getTop4New() {
        return postRepository.getTop4New();
    }

    @Override
    public Iterable<Post> getLikes() {
        return postRepository.getLikes();
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Page<Post> findByTittleAndCreateAt(String tittle, String begin, String end, Pageable pageable) {
        if (tittle == null) {
            return postRepository.findAllByCreateAtBetween(begin, end, pageable);
        } else if (begin == null || end == null) {
            return postRepository.findAllByTittleContaining(tittle, pageable);
        } else {
            return postRepository.findAllByTittleContainingAndCreateAtBetween("%" + tittle + "%", begin, end, pageable);
        }

    }

}