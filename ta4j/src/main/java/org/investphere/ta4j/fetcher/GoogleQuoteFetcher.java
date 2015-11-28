package org.investphere.ta4j.fetcher;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Period;

import eu.verdelhan.ta4j.Decimal;
import eu.verdelhan.ta4j.Tick;
import eu.verdelhan.ta4j.TimeSeries;

public class GoogleQuoteFetcher extends QuoteFetcher {
	
    public TimeSeries fetchAndParseDaily(String symbol, int days) throws Exception {
        return fetchAndParse(symbol, days, 60 * 60 * 24, new Period().withDays(1));
    }


	public String fetchQuotes(String symbol, int days, int interval) throws Exception {

		return this.fetchQuotes(symbol, days, interval, new Period().days(1));
	}

	@Override
	public String fetchQuotes(String symbol, int days, int interval, Period timePeriod) throws Exception {

		String url = "http://www.google.com/finance/getprices?i=" + interval + "&p=" + days
				+ "d&f=d,o,h,l,c,v&df=cpct&q=" + symbol;

		return fetchURLasString(url);
	}

	
	public List<Tick> parseQuotes(String quoteList, int interval) {
		return parseQuotes(quoteList, interval, new Period().days(1));
	}
		
	@Override
	public List<Tick> parseQuotes(String quoteList, int interval, Period timePeriod) {
		String[] lines = dropLines(quoteList, 6);

		List<Tick> quotes = new ArrayList<Tick>();

		for (String line : lines) {
			if (line.startsWith("TIMEZONE_OFFSET")) {
				continue;
			}

			String[] parts = line.split(",");

			String dateStr = parts[0];

			DateTime date;

			if (dateStr.startsWith("a")) {
				final String intPart = dateStr.substring(1);
				final int timestamp = Integer.parseInt(intPart);
				date = new DateTime((long) timestamp * 1000L);
			} else {
				DateTime previousDate = quotes.get(quotes.size() - 1).getBeginTime();
				date = previousDate.plusSeconds(interval);
			}

			
            Tick quote = new Tick(timePeriod,
            		date,
                    Decimal.valueOf((parts[4])),
                    Decimal.valueOf((parts[2])),
                    Decimal.valueOf((parts[3])),
                    Decimal.valueOf((parts[1])),
                    Decimal.valueOf((parts[5])),
                    "GO");


			quotes.add(quote);
		}

		return quotes;
	}
}
