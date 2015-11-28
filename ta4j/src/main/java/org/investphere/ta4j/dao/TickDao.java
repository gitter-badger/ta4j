package org.investphere.ta4j.dao;

import java.util.List;

import eu.verdelhan.ta4j.Tick;

public interface TickDao {
	List<Tick> findAll();
//    List<Tick> findBySymbol();
//    List<Tick> findBySource();
//    List<Tick> findBySourceAndSymbol();
//    List<Tick> findBySourceSymbolAndDates();
//    List<Tick> findBySourceSymbolAndInitialDate();
    void save(Tick tick);
//    void update(Tick tick);
//    void delete(Tick tick);
}
