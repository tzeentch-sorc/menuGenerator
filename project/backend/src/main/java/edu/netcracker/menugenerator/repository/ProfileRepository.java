package edu.netcracker.menugenerator.repository;

import edu.netcracker.menugenerator.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findFirstByUserId(long userId);
}
