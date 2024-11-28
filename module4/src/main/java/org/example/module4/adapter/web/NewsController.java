package org.example.module4.adapter.web;

import lombok.RequiredArgsConstructor;
import org.example.module4.adapter.web.dto.rq.NewsRequest;
import org.example.module4.adapter.web.dto.rs.NewsFullResponse;
import org.example.module4.adapter.web.dto.rs.NewsSmallResponse;
import org.example.module4.aspect.NewsOwnershipRequired;
import org.example.module4.service.NewsService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news/")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @GetMapping
    public ResponseEntity<List<NewsSmallResponse>> getAll(Pageable pageable) {
        return ResponseEntity.ok(newsService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsFullResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(newsService.getById(id));
    }

    @PostMapping
    public ResponseEntity<NewsFullResponse> add(@RequestBody NewsRequest dto) {
        return ResponseEntity.ok(newsService.add(dto));
    }

    @NewsOwnershipRequired
    @PutMapping("/{authorId}/{newsId}")
    public ResponseEntity<NewsFullResponse> update(@PathVariable Long authorId, @PathVariable Long newsId, @RequestBody String updatedContent) {
        return ResponseEntity.ok(newsService.update(newsId, authorId, updatedContent));
    }

    @NewsOwnershipRequired
    @DeleteMapping("/{authorId}/{newsId}")
    public ResponseEntity<Void> delete(@PathVariable Long authorId, @PathVariable Long newsId) {
        newsService.delete(newsId, authorId);
        return ResponseEntity.noContent().build();
    }
}
