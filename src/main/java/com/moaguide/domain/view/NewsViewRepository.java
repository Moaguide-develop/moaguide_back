package com.moaguide.domain.view;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsViewRepository extends JpaRepository<NewsView, Long> {

}
