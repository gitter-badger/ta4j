package org.investphere.ta4j.dao.mySQL;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.investphere.ta4j.dao.TickDao;
import org.springframework.jdbc.core.JdbcTemplate;

import eu.verdelhan.ta4j.Tick;

public class TickMySQLDaoImpl implements TickDao {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

	static final String SAVE = "INSERT INTO tick (SOURCE, DATE, OPEN, HIGH, LOW, CLOSE, VOLUME, PERIOD) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	static final String FINDALL = "SELECT * FROM tick";
	static final String FINDBYSOURCE = "SELECT * FROM tick WHERE SOURCE = (?)";

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Tick> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tick> find(String source, String symbol) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tick> find(String source, String Symbol, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tick> find(String source, String Symbol, Date initialDate, Date finalDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Tick tick) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(SAVE, new Object[] { 
				tick.getSource(), 
				tick.getBeginTime(),
				tick.getOpenPrice(),
				tick.getMaxPrice(),
				tick.getClosePrice(),
				tick.getVolume(),
				tick.getTimePeriod()});
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
