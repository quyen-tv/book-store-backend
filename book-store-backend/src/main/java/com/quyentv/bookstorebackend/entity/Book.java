package com.quyentv.bookstorebackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(
        name = "books",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"title", "author"})})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String author;

    @Column(columnDefinition = "TEXT")
    String description;

    @PositiveOrZero
    @Column(nullable = false)
    Double price;

    @PositiveOrZero
    @Column(nullable = false)
    Integer stock;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @Builder.Default
    Set<Category> categories = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "book_id")
    @Builder.Default
    Set<BookImage> images = new HashSet<>();

    @Column(unique = true, nullable = false, length = 13)
    String isbn;

    @Column(nullable = false)
    String publisher;

    @Column(name = "publication_date", nullable = false)
    LocalDate publicationDate;

    @Column(name = "page_count")
    Integer pageCount;

    @Column(length = 50)
    String language;

    @Column(length = 50, nullable = false)
    String format;

    @PositiveOrZero
    Double discount;

    @Builder.Default
    @PositiveOrZero
    @Column(name = "sold_count", nullable = false)
    Integer soldCount = 0;

    @Column(updatable = false)
    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;
}
