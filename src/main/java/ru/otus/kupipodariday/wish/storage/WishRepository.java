package ru.otus.kupipodariday.wish.storage;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.kupipodariday.wish.model.Wish;
import ru.otus.kupipodariday.wish.model.WishView;

import java.util.List;
import java.util.Optional;

public interface WishRepository extends JpaRepository<Wish, Long> {
    @Query(value = "SELECT id FROM wishes ORDER BY created_at DESC LIMIT :limit", nativeQuery = true)
    List<Long> findLastWishIds(int limit);

    @Query(value = "SELECT id FROM wishes ORDER BY copied DESC, created_at DESC LIMIT :limit", nativeQuery = true)
    List<Long> findTopWishIds(int limit);

    @EntityGraph(value = "Wish", type = EntityGraph.EntityGraphType.LOAD)
    List<WishView> findWishesByIdIsInOrderByCreatedAtDesc(List<Long> ids);

    @EntityGraph(value = "Wish", type = EntityGraph.EntityGraphType.LOAD)
    List<WishView> findWishesByIdIsInOrderByCopiedDescCreatedAtDesc(List<Long> ids);

    @EntityGraph(value = "Wish", type = EntityGraph.EntityGraphType.LOAD)
    List<WishView> findWishesByOwnerUsernameOrderByCreatedAtDesc(String ownerUsername);

    @EntityGraph(value = "Wish", type = EntityGraph.EntityGraphType.LOAD)
    Optional<WishView> findWishById(Long id);
}
