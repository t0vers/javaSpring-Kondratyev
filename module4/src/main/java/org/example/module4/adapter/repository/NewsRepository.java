package org.example.module4.adapter.repository;

import org.example.module4.domain.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity,Long>, JpaSpecificationExecutor<NewsEntity> {
}
