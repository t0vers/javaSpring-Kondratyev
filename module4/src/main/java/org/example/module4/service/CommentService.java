package org.example.module4.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.module4.adapter.repository.CommentRepository;
import org.example.module4.adapter.repository.NewsRepository;
import org.example.module4.adapter.repository.UserRepository;
import org.example.module4.adapter.web.dto.rq.CommentRequest;
import org.example.module4.adapter.web.dto.rs.CommentResponse;
import org.example.module4.adapter.web.error.NotFoundException;
import org.example.module4.domain.CommentEntity;
import org.example.module4.domain.NewsEntity;
import org.example.module4.domain.UserEntity;
import org.example.module4.service.mapper.CommentMapper;
import org.example.module4.service.mapper.NewsMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final NewsRepository newsRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    public CommentResponse add(CommentRequest commentRequest) {
        Optional<UserEntity> user = userRepository.findById(commentRequest.userId());
        Optional<NewsEntity> news = newsRepository.findById(commentRequest.newsId());
        if (user.isEmpty()) {
            throw new NotFoundException("not found user by id");
        }
        if (news.isEmpty()) {
            throw new NotFoundException("not found news by id");
        }

        CommentEntity commentEntity = new CommentEntity(commentRequest.content(), user.get(), news.get());
        commentRepository.save(commentEntity);
        return commentMapper.commentToResponse(commentEntity);
    }

    public CommentResponse getById(Long id){
        Optional<CommentEntity> comment = commentRepository.findById(id);
        if (comment.isEmpty()){
            throw new NotFoundException("not found comment by id");
        }
        return commentMapper.commentToResponse(comment.get());
    }

    public void delete(Long id, Long userId) {
        commentRepository.deleteById(id);
    }

    public CommentResponse update(Long id, Long userId, String newContent) {
        Optional<CommentEntity> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            throw new NotFoundException("not found comment by id");
        }
        CommentEntity updatedComment = comment.get();
        updatedComment.setContent(newContent);
        commentRepository.save(updatedComment);
        return commentMapper.commentToResponse(updatedComment);
    }
}
