package com.bezkoder.springjwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.ReaderSubscription;

@Repository
public interface SubscriptionRepository  extends JpaRepository<ReaderSubscription, Long>{

	Optional<ReaderSubscription> findByBookIdAndUserId(Long bookId, Long userId);
}
