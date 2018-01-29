package com.rhyno.misedirty.repository;

import com.rhyno.misedirty.model.AirPollution;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirPollutionRepository extends MongoRepository<AirPollution, String> {
}
