package com.quyentv.bookstorebackend.specification;

import com.quyentv.bookstorebackend.entity.Book;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    private BookSpecification() {}

    public static Specification<Book> titleContains(String title) {
        return (root, query, cb) -> {
            if (title == null || title.isBlank()) {
                return cb.conjunction();
            }
            return cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
        };
    }

    public static Specification<Book> authorContains(String author) {
        return (root, query, cb) -> {
            if (author == null || author.isBlank()) {
                return cb.conjunction();
            }
            return cb.like(cb.lower(root.get("author")), "%" + author.toLowerCase() + "%");
        };
    }

    public static Specification<Book> priceGreaterThanOrEq(Double priceMin) {
        return (root, query, cb) -> priceMin == null ? cb.conjunction() : cb.ge(root.get("price"), priceMin);
    }

    public static Specification<Book> priceLessThanOrEq(Double priceMax) {
        return (root, query, cb) -> priceMax == null ? cb.conjunction() : cb.le(root.get("price"), priceMax);
    }

    public static Specification<Book> categoryEquals(String categoryName) {
        return (root, query, cb) -> categoryName == null
                ? cb.conjunction()
                : cb.isMember(
                        categoryName, root.join("categories", JoinType.LEFT).get("name"));
    }
}
