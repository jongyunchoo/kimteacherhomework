package org.example.again1.repository;

import org.example.again1.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findAllByOredeByModifiedAtDesc(Pageable pageable);
}
