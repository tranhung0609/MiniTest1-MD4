package service;

import model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface IGeneralService<T> { // interface sử dụng generic mô tả các phương chung mà tất cả các service cần có
    Iterable<T> findAll();

    Optional<T> findById(int id);

    Post save(T t);

    void remove(int id);

    Iterable<Post> findAllByTittleContaining(String name);

    Iterable<Post> findAllByOrderByLikes();

    @Query(value = "select * from post order by id desc LIMIT 4", nativeQuery = true)
    Iterable<Post> getTop4New();
    @Query(value = "select * from post where likes > 6",nativeQuery = true)
    Iterable<Post> getLikes();
    Page<Post> findAll(Pageable pageable);
}
