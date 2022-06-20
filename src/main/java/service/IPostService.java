package service;

import model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface IPostService extends IGeneralService<Post> {
    Iterable<Post> findTop4Likes();

    Iterable<Post> findTopByCreateAt();
}
