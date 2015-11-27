package eu.verdelhan.ta4j.fetcher;

import org.investphere.ta4j.fetcher.YahooQuoteFetcher;
import static org.junit.Assert.*;
import org.junit.Test;

import eu.verdelhan.ta4j.TimeSeries;

public class YahooQuoteFetcherTest {

    @Test
    public void downloadAPPL() {
    	YahooQuoteFetcher yahooFetcher = new YahooQuoteFetcher();
    	try {
			TimeSeries series = yahooFetcher.fetchAndParseDaily("GOOG", 10);
			System.out.println("Downloaded Series from: "+series.getFirstTick().getDateName() + " to: "+series.getLastTick().getEndTime());
			assertNotNull(series.getFirstTick().getClass());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
