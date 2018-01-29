package com.rhyno.misedirty.repository;

import com.rhyno.misedirty.model.AirPollution;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirPollutionRepository extends ReactiveCrudRepository<AirPollution, String> {
}
