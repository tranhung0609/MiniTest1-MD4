package repository;

import model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {
    @Modifying
    @Query(value = "select * from Post order by createAt desc limit 4 ", nativeQuery = true)
    Iterable<Post> findTop4New();

    @Modifying
    @Query(value = "select * from post order by likes asc", nativeQuery = true)
    Iterable<Post> findAllByLikesAsc();

}
