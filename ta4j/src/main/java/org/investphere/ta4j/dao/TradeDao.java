package org.investphere.ta4j.dao;

import java.util.List;

import eu.verdelhan.ta4j.Trade;

public interface TradeDao {
	List<Trade> findAll();
//    List<Tick> findBySymbol();
//    List<Tick> findBySource();
//    List<Tick> findBySourceAndSymbol();
//    List<Tick> findBySourceSymbolAndDates();
//    List<Tick> findBySourceSymbolAndInitialDate();
    void save(Trade trade);
//    void updateTick(Tick tick);
//    void deleteTick(Tick tick);
}
