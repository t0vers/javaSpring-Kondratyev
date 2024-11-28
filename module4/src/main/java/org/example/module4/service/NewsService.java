package org.example.module4.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.module4.adapter.repository.NewsCategoryRepository;
import org.example.module4.adapter.repository.NewsRepository;
import org.example.module4.adapter.repository.UserRepository;
import org.example.module4.adapter.web.dto.rq.NewsRequest;
import org.example.module4.adapter.web.dto.rs.NewsFullResponse;
import org.example.module4.adapter.web.dto.rs.NewsSmallResponse;
import org.example.module4.adapter.web.error.NotFoundException;
import org.example.module4.domain.NewsCategoryEntity;
import org.example.module4.domain.NewsEntity;
import org.example.module4.domain.UserEntity;
import org.example.module4.service.mapper.NewsMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class NewsService {
    private final UserRepository userRepository;
    private final NewsCategoryRepository categoryRepository;
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    public NewsFullResponse add(NewsRequest newsRequest) {
        Optional<UserEntity> user = userRepository.findById(newsRequest.userID());
        Optional<NewsCategoryEntity> category = categoryRepository.findById(newsRequest.categoryID());
        if (user.isEmpty()) {
            throw new NotFoundException("not found user by id");
        }
        if (category.isEmpty()) {
            throw new NotFoundException("not found category by id");
        }
        NewsEntity news = new NewsEntity(newsRequest.content(), user.get(), category.get());
        newsRepository.save(news);
        return newsMapper.newsToFullResponse(news);
    }

    public List<NewsSmallResponse> getAll(Pageable pageable) {
        return newsRepository.findAll(pageable).map(newsMapper::newsToSmallResponse).getContent();
    }

    public NewsFullResponse getById(Long id) {
        Optional<NewsEntity> news = newsRepository.findById(id);
        if (news.isEmpty()) {
            throw new NotFoundException("not found news by id");
        }
        return newsMapper.newsToFullResponse(news.get());
    }

    public void delete(Long id, Long userId) {
        newsRepository.deleteById(id);
    }

    public NewsFullResponse update(Long id, Long userId, String newContent) {
        Optional<NewsEntity> news = newsRepository.findById(id);
        if (news.isEmpty()) {
            throw new NotFoundException("not found news by id");
        }
        NewsEntity updatedNews = news.get();
        updatedNews.setContent(newContent);
        newsRepository.save(updatedNews);
        return newsMapper.newsToFullResponse(updatedNews);
    }
}
