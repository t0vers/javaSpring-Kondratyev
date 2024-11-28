package org.example.module4.adapter.repository;

import org.example.module4.domain.NewsCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsCategoryRepository extends JpaRepository<NewsCategoryEntity,Long> {

}
