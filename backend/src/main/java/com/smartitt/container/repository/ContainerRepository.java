package com.smartitt.container.repository;

import com.smartitt.container.entity.Container;
import com.smartitt.container.entity.ContainerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContainerRepository extends JpaRepository<Container, UUID> {
    List<Container> findByTripId(UUID tripId);
    Optional<Container> findByContainerNumberAndTripId(String containerNumber, UUID tripId);
    boolean existsByContainerNumberAndTripId(String containerNumber, UUID tripId);
    boolean existsByContainerNumber(String containerNumber);
    Optional<Container> findFirstByContainerNumberOrderByCreatedAtDesc(String containerNumber);
    List<Container> findByContainerNumber(String containerNumber);
    List<Container> findByStatus(ContainerStatus status);
}
