package com.gemmano.dtm.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gemmano.dtm.entities.DeviceData;

public interface DeviceDataRepository extends MongoRepository<DeviceData, String> {

    public List<DeviceData> findByDeviceName(String deviceName);
}
