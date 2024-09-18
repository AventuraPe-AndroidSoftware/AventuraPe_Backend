package com.upc.aventurape.platform.profiles.infrastructure.persistence.jpa.repositories;

import com.upc.aventurape.platform.profiles.domain.model.aggregates.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByName(String name);
    List<Profile> findByCategory(String category);
    List<Profile> findByLocation(String location);
}
