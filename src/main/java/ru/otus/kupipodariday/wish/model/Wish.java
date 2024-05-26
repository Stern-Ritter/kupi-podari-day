package ru.otus.kupipodariday.wish.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.otus.kupipodariday.offer.model.Offer;
import ru.otus.kupipodariday.user.model.User;
import ru.otus.kupipodariday.wishlist.model.Wishlist;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "wishes")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

@NamedEntityGraph(
        name = "Wish",
        attributeNodes = {
                @NamedAttributeNode(value = "owner"),
                @NamedAttributeNode(value = "offers", subgraph = "offers-subgraph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "offers-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode(value = "user", subgraph = "offers-user-subgraph")
                        }
                ),
                @NamedSubgraph(
                        name = "offers-user-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode(value = "wishes"),
                                @NamedAttributeNode(value = "wishlists", subgraph = "user-wishlists-subgraph"),
                                @NamedAttributeNode(value = "offers")
                        }
                ),
                @NamedSubgraph(
                        name = "user-wishlists-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("owner"),
                                @NamedAttributeNode("wishes")
                        }
                ),
        }
)
public class Wish {
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

    @Column(name = "name")
    private String name;

    @Column(name = "link")
    private String link = "";

    @Column(name = "image")
    private String image = "";

    @Column(name = "price")
    private Double price = 1.0;

    @Column(name = "raised")
    private Double raised = 0.0;

    @Column(name = "description")
    private String description = "";

    @Column(name = "copied")
    private Integer copied = 0;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToMany(mappedBy = "wishes")
    private Set<Wishlist> wishlists = new HashSet<>();

    @OneToMany(mappedBy = "wish")
    private Set<Offer> offers = new HashSet<>();
}
