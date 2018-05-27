package com.gemmano.dtm.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "sensor_data")
public class DeviceData {

	@Id
	private String id;
	private String deviceName;
	private String data;
	private String timestamp;
}
