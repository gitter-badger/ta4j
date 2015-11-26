package org.investphere.ta4j.fetcher;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.joda.time.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.verdelhan.ta4j.TimeSeries;

public class QuoteCollection {

    private static Logger logger = LoggerFactory.getLogger(QuoteCollection.class.getName());
    private Map<String, TimeSeries> quotes;

    public void QuoteCollection() {
    }

    public Map<String, TimeSeries> getQuotes() {
        return quotes;
    }

    public void fetchAllQuotes(QuoteFetcher fetcher) throws Exception {
        logger.info("Starting quote fetching");

        Properties properties = new Properties();
        properties.load(ClassLoader.getSystemResourceAsStream("application.properties"));

        String symbolPath = properties.getProperty("symbolPath");
        int days = Integer.parseInt(properties.getProperty("days"));
        int interval = Integer.parseInt(properties.getProperty("interval"));
        Period timePeriod = new Period("1D");

        Path paths = Paths.get(symbolPath);
        List<String> symbols = Files.readAllLines(paths, StandardCharsets.UTF_8);

        for (String symbol : symbols) {
            symbol = symbol.replaceAll("\\s", "");

            logger.info("Fetching quotes for " + symbol);
            TimeSeries quoteList = fetcher.fetchAndParse(symbol, days, interval, timePeriod);
            quotes.put(symbol, quoteList);
        }

        logger.info("Done fetching quotes");
    }

    public void fetchAllGoogleQuotes() throws Exception {
        fetchAllQuotes(new GoogleQuoteFetcher());
    }
}