package no.adstate.user.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import no.adstate.user.registration.entity.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

}
