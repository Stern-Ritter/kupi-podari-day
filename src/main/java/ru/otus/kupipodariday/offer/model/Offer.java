package ru.otus.kupipodariday.offer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedEntityGraphs;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.otus.kupipodariday.user.model.User;
import ru.otus.kupipodariday.wish.model.Wish;

import java.time.LocalDateTime;

@Entity
@Table(name = "offers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "OneOffer",
                attributeNodes = {
                        @NamedAttributeNode(value = "user", subgraph = "user-subgraph"),
                        @NamedAttributeNode(value = "wish", subgraph = "wish-subgraph")
                },
                subgraphs = {
                        @NamedSubgraph(
                                name = "user-subgraph",
                                attributeNodes = {
                                        @NamedAttributeNode(value = "wishes", subgraph = "user-wishes-subgraph"),
                                        @NamedAttributeNode(value = "wishlists", subgraph = "user-wishlists-subgraph"),
                                        @NamedAttributeNode("offers")
                                }
                        ),
                        @NamedSubgraph(
                                name = "wish-subgraph",
                                attributeNodes = {
                                        @NamedAttributeNode("owner"),
                                        @NamedAttributeNode("offers")
                                }
                        ),
                        @NamedSubgraph(
                                name = "user-wishes-subgraph",
                                attributeNodes = {
                                        @NamedAttributeNode(value = "owner")
                                }
                        ),
                        @NamedSubgraph(
                                name = "user-wishlists-subgraph",
                                attributeNodes = {
                                        @NamedAttributeNode("owner"),
                                        @NamedAttributeNode("wishes")
                                }
                        )
                }
        ),
        @NamedEntityGraph(
                name = "ManyOffer",
                attributeNodes = {
                        @NamedAttributeNode(value = "user", subgraph = "user-subgraph"),
                        @NamedAttributeNode(value = "wish", subgraph = "wish-subgraph")
                },
                subgraphs = {
                        @NamedSubgraph(
                                name = "user-subgraph",
                                attributeNodes = {
                                        @NamedAttributeNode("wishes"),
                                        @NamedAttributeNode("wishlists"),
                                        @NamedAttributeNode("offers")
                                }
                        ),
                        @NamedSubgraph(
                                name = "wish-subgraph",
                                attributeNodes = {
                                        @NamedAttributeNode("owner"),
                                        @NamedAttributeNode("offers")
                                }
                        ),
                }
        )
})
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "hidden")
    private Boolean hidden = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wish_id")
    private Wish wish;
}
