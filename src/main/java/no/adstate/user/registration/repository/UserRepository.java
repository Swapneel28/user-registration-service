package no.adstate.user.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import no.adstate.user.registration.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
