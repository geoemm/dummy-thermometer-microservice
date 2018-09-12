package com.gemmano.dtm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.gemmano.dtm.entities.DeviceData;
import com.gemmano.dtm.repositories.DeviceDataRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Consumer {

	@Autowired
	private DeviceDataRepository deviceDataRepository;
	
	@KafkaListener(topics = "${spring.kafka.topic.json}")
	public void receive(@Payload DeviceData data,
						@Headers MessageHeaders headers) {
		log.info("{}",data);
		deviceDataRepository.save(data);
	}
}
