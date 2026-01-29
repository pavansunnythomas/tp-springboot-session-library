package com.pavan.tp_library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavan.tp_library.model.entity.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    Optional<BookEntity> findByIsbn(String isbn);

    List<BookEntity> findByTitleContainingIgnoreCase(String title);

    List<BookEntity> findByAuthorContainingIgnoreCase(String author);

    List<BookEntity> findByCategoryIgnoreCase(String category);

    List<BookEntity> findByLanguageIgnoreCase(String language);

    List<BookEntity> findByAvailableTrue();

    boolean existsByIsbn(String isbn);
}
