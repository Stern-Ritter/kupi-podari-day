package ru.otus.kupipodariday.wishlist.storage;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.kupipodariday.wishlist.model.Wishlist;
import ru.otus.kupipodariday.wishlist.model.WishlistView;

import java.util.List;
import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    @EntityGraph(value = "Wishlist", type = EntityGraph.EntityGraphType.LOAD)
    List<WishlistView> findWishlistsByOrderByCreatedAtDesc();
    @EntityGraph(value = "Wishlist", type = EntityGraph.EntityGraphType.LOAD)
    Optional<WishlistView> findWishlistById(Long id);
}
