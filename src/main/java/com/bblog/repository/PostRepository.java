@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUserId(Integer userId);
    Page<Post> findByUserId(Integer userId, Pageable pageable);
    Page<Post> findByTitleContaining(String keyword, Pageable pageable);
}