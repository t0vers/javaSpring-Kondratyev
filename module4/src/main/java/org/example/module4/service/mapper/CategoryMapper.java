package org.example.module4.service.mapper;

import org.example.module4.adapter.web.dto.rs.CategoryResponse;
import org.example.module4.domain.NewsCategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryResponse categoryToResponse(NewsCategoryEntity category) {
        return new CategoryResponse(category.getId(), category.getCategoryName());
    }
}
