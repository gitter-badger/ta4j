package org.investphere.ta4j.service;

import org.investphere.ta4j.dao.mongo.TickMongoDaoImpl;
import org.investphere.ta4j.dao.redis.TickRedisDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TickService {
	
	@Autowired
	TickRedisDaoImpl redisDao;

	@Autowired
	TickMongoDaoImpl mongoDao;

}
