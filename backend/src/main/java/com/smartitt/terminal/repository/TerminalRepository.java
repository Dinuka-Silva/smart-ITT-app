package com.smartitt.terminal.repository;

import com.smartitt.terminal.entity.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TerminalRepository extends JpaRepository<Terminal, UUID> {
    Optional<Terminal> findByCode(String code);
}
