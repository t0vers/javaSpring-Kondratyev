package org.example.module4.service.mapper;

import org.example.module4.adapter.web.dto.rs.CommentResponse;
import org.example.module4.domain.CommentEntity;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public CommentResponse commentToResponse(CommentEntity comment){
        return new CommentResponse(
                comment.getContent(),
                comment.getUserEntity().getName(),
                comment.getNewsEntity().getId()
        );
    }
}
