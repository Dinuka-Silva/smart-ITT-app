package com.smartitt.supervisor.repository;

import com.smartitt.supervisor.entity.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, UUID> {
}
