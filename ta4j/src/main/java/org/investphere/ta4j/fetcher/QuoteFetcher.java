package org.investphere.ta4j.fetcher;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.joda.time.Period;

import eu.verdelhan.ta4j.Tick;
import eu.verdelhan.ta4j.TimeSeries;

public abstract class QuoteFetcher {

    abstract public String fetchQuotes(String symbol, int days, int interval, Period timePeriod) throws Exception;

    abstract public List<Tick> parseQuotes(String quoteList, int interval, Period timePeriod);

    public TimeSeries fetchAndParse(String symbol, int days, int interval) throws Exception {
    	return fetchAndParse(symbol, days, interval, new Period().days(1));
    }

    public TimeSeries fetchAndParse(String symbol, int days, int interval, Period timePeriod) throws Exception {
        String requestResult = fetchQuotes(symbol, days, interval, timePeriod);
        List<Tick> parsed = parseQuotes(requestResult, interval, timePeriod);

        return new TimeSeries(symbol, parsed);
    }

    protected String fetchURLasString(String url) throws IOException, ParseException {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = httpclient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);
        EntityUtils.consume(entity);
        httpGet.releaseConnection();
        return body;
    }

    protected String[] dropLines(String quoteList, int n) {
        String[] lines = quoteList.split("\n");
        lines = Arrays.copyOfRange(lines, n, lines.length);
        return lines;
    }
}