package org.investphere.ta4j.dao.redis;

import java.util.Date;
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
		template.opsForValue().set("MD:"+tick.getSource(), tick.getOpenPrice()+":"+tick.getMaxPrice()+":"+tick.getMinPrice()+":"+tick);		
	}

	@Override
	public List<Tick> find(String source, String symbol, Date date) {
		String ops = template.opsForValue().get("MD:"+source+":"+symbol);
		return null;
	}

	@Override
	public List<Tick> find(String source, String symbol) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tick> find(String source, String Symbol, Date initialDate, Date finalDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Tick tick) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Tick tick) {
		// TODO Auto-generated method stub
		
	}


}
