package org.investphere.ta4j.dao.mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import eu.verdelhan.ta4j.Tick;

public class TickMongoDaoImpl {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void saveTick(Tick tick) {
		if (!mongoTemplate.collectionExists(Tick.class)) {
			mongoTemplate.createCollection(Tick.class);
		}
		mongoTemplate.insert(tick, "tickTable");
	}
	
	public List<Tick> findTicks() {
		return mongoTemplate.findAll(Tick.class, "tickTable");
	}

}
