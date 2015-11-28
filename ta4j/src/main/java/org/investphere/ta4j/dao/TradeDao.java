package org.investphere.ta4j.dao;

import java.util.Date;
import java.util.List;

import eu.verdelhan.ta4j.Trade;

public interface TradeDao {
	List<Trade> findAll();
    List<Trade> find(String symbol);
    List<Trade> find(String source, String symbol);
    List<Trade> find(String source, String Symbol, Date date);
    List<Trade> find(String source, String Symbol, Date initialDate, Date finalDate);
    void save(Trade trade);
    void update(Trade trade);
    void delete(Trade trade);
}
