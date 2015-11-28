package org.investphere.ta4j.dao;

import java.util.Date;
import java.util.List;

import eu.verdelhan.ta4j.Tick;

public interface TickDao {
	List<Tick> findAll();
    List<Tick> find(String source, String symbol);
    List<Tick> find(String source, String Symbol, Date date);
    List<Tick> find(String source, String Symbol, Date initialDate, Date finalDate);
    void save(Tick tick);
    void update(Tick tick);
    void delete(Tick tick);
}
