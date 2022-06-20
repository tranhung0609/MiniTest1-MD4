package repository;

import javafx.geometry.Pos;
import model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Iterable<Post> findAllByTittleContaining(String name);

    Iterable<Post> findAllByOrderByLikes();

    @Query(value = "select * from post order by id desc LIMIT 4", nativeQuery = true)
    Iterable<Post> getTop4New();

    @Query(value = "select * from post where likes > 6", nativeQuery = true)
    Iterable<Post> getLikes();

    @Query(value = "select * from post where title like ?1 and (createAt between ?2 and ?3)", nativeQuery = true)
    Page<Post> findAllByTittleContainingAndCreateAtBetween(String title, String begin, String end, Pageable pageable);

    @Query(value = "select * from post where createAt between ?1 and ?2", nativeQuery = true)
    Page<Post> findAllByCreateAtBetween(String begin, String end, Pageable pageable);

    Page<Post> findAllByTittleContaining(String title, Pageable pageable);
}
