package edu.netcracker.menugenerator.repository;

import edu.netcracker.menugenerator.entity.Menu;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Slice<Menu> findAllByCurrentIsFalseAndUserId(long id, Pageable pageable);
    Slice<Menu> findAllByCurrentIsFalseAndUserIdAndNameContaining(long id, String name, Pageable pageable);

    Optional<Menu> findMenuByCurrentIsTrueAndUserId(long id);
}
