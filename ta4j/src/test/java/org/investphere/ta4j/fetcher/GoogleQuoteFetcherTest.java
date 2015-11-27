package org.investphere.ta4j.fetcher;

import static org.junit.Assert.assertNotNull;

import org.investphere.ta4j.fetcher.YahooQuoteFetcher;
import org.junit.Test;

import eu.verdelhan.ta4j.TimeSeries;

public class GoogleQuoteFetcherTest {

    @Test
    public void downloadAPPL() {
    	GoogleQuoteFetcher fetcher = new GoogleQuoteFetcher();
    	try {
			TimeSeries series = fetcher.fetchAndParseDaily("GOOG", 10);
			System.out.println("Downloaded Series from: "+series.getFirstTick().getDateName() + " to: "+series.getLastTick().getEndTime());
			assertNotNull(series.getFirstTick().getClass());
			System.out.println("Last tick: "+series.getLastTick());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
