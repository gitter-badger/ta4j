package org.investphere.ta4j.dao.redis;

import java.util.List;

import org.investphere.ta4j.dao.TickDao;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import eu.verdelhan.ta4j.Tick;

@Repository
public class TickRedisDaoImpl implements TickDao {
	
	private RedisTemplate<String, String> template;

	@Override
	public List<Tick> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Tick tick) {
		// TODO Auto-generated method stub
		
	}


}
