package org.example.module4.adapter.web;

import lombok.RequiredArgsConstructor;
import org.example.module4.adapter.web.dto.rq.CommentRequest;
import org.example.module4.adapter.web.dto.rs.CommentResponse;
import org.example.module4.aspect.CommentOwnershipRequired;
import org.example.module4.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment/")
@RequiredArgsConstructor
public class CommentController {
    CommentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> getCommentById(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CommentResponse> createComment(@RequestBody CommentRequest dto) {
        return ResponseEntity.ok(commentService.add(dto));
    }

    @CommentOwnershipRequired
    @PutMapping("/{userId}/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long userId,
                                                         @RequestBody String comment,
                                                         @PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.update(commentId, userId, comment));
    }

    @CommentOwnershipRequired
    @DeleteMapping("/{userId}/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long userId, @PathVariable Long commentId) {
        commentService.delete(commentId, userId);
        return ResponseEntity.noContent().build();
    }
}
