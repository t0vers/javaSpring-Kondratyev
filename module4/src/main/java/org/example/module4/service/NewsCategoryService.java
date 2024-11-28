package org.example.module4.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.module4.adapter.repository.NewsCategoryRepository;
import org.example.module4.adapter.web.dto.rs.CategoryResponse;
import org.example.module4.domain.NewsCategoryEntity;
import org.example.module4.service.mapper.CategoryMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NewsCategoryService {
    private final NewsCategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryResponse> getAll(Pageable pageable) {
        List<NewsCategoryEntity> categoryList = categoryRepository.findAll(pageable).getContent();
        List<CategoryResponse> categoryNames = new ArrayList<>();
        for (NewsCategoryEntity category : categoryList) {
            categoryNames.add(categoryMapper.categoryToResponse(category));
        }
        return categoryNames;
    }

    public CategoryResponse add(String name) {
        NewsCategoryEntity category = new NewsCategoryEntity(name);
        categoryRepository.save(category);
        return categoryMapper.categoryToResponse(category);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
