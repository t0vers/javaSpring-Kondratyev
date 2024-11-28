package org.example.module4.adapter.web.dto.rq;

public record CommentRequest(String content, Long userId, Long newsId) {
}
