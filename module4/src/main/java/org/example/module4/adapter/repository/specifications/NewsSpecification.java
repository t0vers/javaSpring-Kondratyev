package org.example.module4.adapter.repository.specifications;

import org.example.module4.domain.NewsEntity;
import org.springframework.data.jpa.domain.Specification;

public class NewsSpecification {
    public static Specification<NewsEntity> hasCategory(Long categoryId) {
        return (root, query, criteriaBuilder) -> categoryId != null
                ? criteriaBuilder.equal(root.get("newsCategoryEntity").get("id"), categoryId) : criteriaBuilder.conjunction();
    }

    public static Specification<NewsEntity> hasAuthor(Long authorId) {
        return (root, query, criteriaBuilder) -> authorId != null ?
                criteriaBuilder.equal(root.get("userEntity").get("id"), authorId) : criteriaBuilder.conjunction();
    }

    public static Specification<NewsEntity> hasId(Long id) {
        return ((root, query, criteriaBuilder) -> id != null ? criteriaBuilder.equal(root.get("id"), id) : criteriaBuilder.conjunction());
    }
}
