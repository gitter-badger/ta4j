package org.investphere.ta4j.fetcher;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Period;

import eu.verdelhan.ta4j.Decimal;
import eu.verdelhan.ta4j.Tick;
import eu.verdelhan.ta4j.TimeSeries;

public class YahooQuoteFetcher extends QuoteFetcher {

    public TimeSeries fetchAndParseDaily(String symbol, int days) throws Exception {
        return fetchAndParse(symbol, days, 60 * 60 * 24, new Period("1D"));
    }

    public BigDecimal fetchBidAskSpread(String symbol) throws Exception {
        String url = "http://download.finance.yahoo.com/d/quotes.csv?s=" + symbol + "&f=b2b3";

        String result = fetchURLasString(url).replaceAll("\r\n", "").replaceAll("\n", "");

        String[] parts = result.split(",");

        return new BigDecimal(parts[0]).subtract(new BigDecimal(parts[1]));
    }

    @Override
    public String fetchQuotes(String symbol, int days, int interval, Period timePeriod) throws Exception {
        String period;

        switch (interval) {
            case 60 * 60 * 24:
                period = "d";
                break;
            case 60 * 60 * 24 * 7:
                period = "w";
                break;
            default:
                throw new Exception();
        }

        DateTime now = new DateTime();
        DateTime startDate = now.minusDays(days);

        int endMonth = now.getMonthOfYear() - 1;
        int endDay = now.getDayOfMonth();
        int endYear = now.getYear();

        int startMonth = startDate.getMonthOfYear() - 1;
        int startDay = startDate.getDayOfMonth();
        int startYear = startDate.getYear();

        String url = String.format("http://ichart.yahoo.com/table.csv?s=%s&a=%d&b=%d&c=%d&d=%d&e=%d&f=%d&g=%s&ignore=.csv",
                symbol, startMonth, startDay, startYear, endMonth, endDay, endYear, period);

        return fetchURLasString(url);
    }

    @Override
    public List<Tick> parseQuotes(String quoteList, int interval, Period timePeriod) {
        List<Tick> quotes = new ArrayList<Tick>();

        String[] lines = dropLines(quoteList, 1);

        for (String line : lines) {
            String[] parts = line.split(",");

            // Date,Open,High,Low,Close,Volume,Adj Close

            DateTime date = DateTime.parse(parts[0]);

            Tick quote = new Tick(timePeriod,
            		date,
                    Decimal.valueOf((parts[1])),
                    Decimal.valueOf((parts[2])),
                    Decimal.valueOf((parts[3])),
                    Decimal.valueOf((parts[6])),
                    Decimal.valueOf((parts[5])));

            quotes.add(quote);
        }

        return quotes;
    }

    public static void main(String[] args) throws Exception {
        YahooQuoteFetcher fetcher = new YahooQuoteFetcher();
        TimeSeries series = fetcher.fetchAndParseDaily("AAPL", 5);

        int count = series.getTickCount();

        System.out.println(fetcher.fetchBidAskSpread("AAPL"));
    }
}