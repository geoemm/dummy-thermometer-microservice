package com.gemmano.dtm.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gemmano.dtm.entities.DeviceData;

public interface DeviceDataRepository extends MongoRepository<DeviceData, String> {

}
