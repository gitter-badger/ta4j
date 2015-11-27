package eu.verdelhan.ta4j.fetcher;

import org.investphere.ta4j.fetcher.YahooQuoteFetcher;
import org.junit.Test;

import eu.verdelhan.ta4j.TimeSeries;

public class YahooQuoteFetcherTest {

    @Test
    public void downloadAPPL() {
    	YahooQuoteFetcher yahooFetcher = new YahooQuoteFetcher();
    	try {
			TimeSeries series = yahooFetcher.fetchAndParseDaily("APPL", 10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
