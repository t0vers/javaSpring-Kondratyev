package org.example.module4.adapter.web;

import lombok.RequiredArgsConstructor;
import org.example.module4.adapter.web.dto.rq.CommentRequest;
import org.example.module4.adapter.web.dto.rs.CommentResponse;
import org.example.module4.aspect.CommentOwnershipRequired;
import org.example.module4.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment/")
@RequiredArgsConstructor
public class CommentController {
    CommentService commentService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER', 'ROLE_MODERATOR')")
    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> getCommentById(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getById(id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER', 'ROLE_MODERATOR')")
    @PostMapping
    public ResponseEntity<CommentResponse> createComment(@RequestBody CommentRequest dto) {
        return ResponseEntity.ok(commentService.add(dto));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER', 'ROLE_MODERATOR')")
    @CommentOwnershipRequired
    @PutMapping("/{userId}/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long userId,
                                                         @RequestBody String comment,
                                                         @PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.update(commentId, userId, comment));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER', 'ROLE_MODERATOR')")
    @CommentOwnershipRequired
    @DeleteMapping("/{userId}/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long userId, @PathVariable Long commentId) {
        commentService.delete(commentId, userId);
        return ResponseEntity.noContent().build();
    }
}
