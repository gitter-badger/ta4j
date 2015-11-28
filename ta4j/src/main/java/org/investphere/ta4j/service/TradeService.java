package org.investphere.ta4j.service;

import org.investphere.ta4j.dao.mongo.TradeMongoDaoImpl;
import org.investphere.ta4j.dao.redis.TradeRedisDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeService {
	@Autowired
	TradeRedisDaoImpl redisDao;

	@Autowired
	TradeMongoDaoImpl mongoDao;


}
