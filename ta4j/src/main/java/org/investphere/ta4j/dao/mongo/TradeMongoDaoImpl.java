package org.investphere.ta4j.dao.mongo;

import java.util.List;

import org.investphere.ta4j.dao.TradeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import eu.verdelhan.ta4j.Trade;

public class TradeMongoDaoImpl implements TradeDao{
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void save(Trade trade) {
		if (!mongoTemplate.collectionExists(Trade.class)) {
			mongoTemplate.createCollection(Trade.class);
		}
		mongoTemplate.insert(trade, "tradeTable");
	}
	
	public List<Trade> findAll() {
		return mongoTemplate.findAll(Trade.class, "tradeTable");
	}

}
