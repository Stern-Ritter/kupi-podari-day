package ru.otus.kupipodariday.offer.storage;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.kupipodariday.offer.model.ManyOfferView;
import ru.otus.kupipodariday.offer.model.Offer;
import ru.otus.kupipodariday.offer.model.OneOfferView;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    @EntityGraph(value = "ManyOffer", type = EntityGraph.EntityGraphType.LOAD)
    List<ManyOfferView> findOffersByOrderByCreatedAtDesc();

    @EntityGraph(value = "OneOffer", type = EntityGraph.EntityGraphType.LOAD)
    Optional<OneOfferView> findOfferById(Long id);
}
