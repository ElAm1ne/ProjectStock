package com.example.ProjectStock.Service;
import com.example.ProjectStock.Modele.StockSearchHistory;
import com.example.ProjectStock.Modele.StockWithEvolution;
import com.example.ProjectStock.Repository.StockSearchHistoryEntityRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.time.DateUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjectStock.Modele.Stock;
import com.example.ProjectStock.Repository.StockEntityRepository;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class StockService {

    @Autowired
    private StockEntityRepository stockRepository;

    @Autowired
    private StockSearchHistoryEntityRepository stockSearchHistoryRepository;


    RestTemplate restTemplate = new RestTemplate();

    public Optional<Stock> findById(Long id) {

        return stockRepository.findById(id);
    }

    public List<Stock> findAllStocks(){
        return stockRepository.findAll();
    }
    public List<Stock> findByTicker(String ticker) {

        return stockRepository.findByTicker(ticker);
    }

    public List<Stock> findByDateBetween(Date startDate, Date endDate) {
        return stockRepository.findByDateBetween(startDate, endDate);
    }
    public List<Double> getOpenValues(String ticker, Date startDate, Date endDate) {
        return stockRepository.findOpenByTickerAndDateBetween(ticker, startDate, endDate);
    }

    public List<Double> getHighValues(String ticker, Date startDate, Date endDate) {
        return stockRepository.findHighByTickerAndDateBetween(ticker, startDate, endDate);
    }

    public List<Double> getLowValues(String ticker, Date startDate, Date endDate) {
        return stockRepository.findLowByTickerAndDateBetween(ticker, startDate, endDate);
    }

    public List<Double> getCloseValues(String ticker, Date startDate, Date endDate) {
        return stockRepository.findCloseByTickerAndDateBetween(ticker, startDate, endDate);
    }

    public List<Double> getAdjustedCloseValues(String ticker, Date startDate, Date endDate) {
        return stockRepository.findAdjustedCloseByTickerAndDateBetween(ticker, startDate, endDate);
    }

    public List<Long> getVolumeValues(String ticker, Date startDate, Date endDate) {
        return stockRepository.findVolumeByTickerAndDateBetween(ticker, startDate, endDate);
    }

    public List<Double> getDividendAmountValues(String ticker, Date startDate, Date endDate) {
        return stockRepository.findDividendAmountByTickerAndDateBetween(ticker, startDate, endDate);
    }

    public List<Double> getSplitCoefficientValues(String ticker, Date startDate, Date endDate) {
        return stockRepository.findSplitCoefficientByTickerAndDateBetween(ticker, startDate, endDate);
    }

    public Stock parseJsonToStock(String ticker, Date date) throws ParseException {

        String url;
        url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=<ticker>&apikey=1XDCVS7DM3C1XE20".replace("<ticker>", ticker);

        String json = restTemplate.getForObject(url, String.class);
        JSONObject jsonObj = new JSONObject(json).getJSONObject("Time Series (Daily)").getJSONObject(new SimpleDateFormat("yyyy-mm-dd").format(date));
        ObjectMapper mapper = new ObjectMapper();
        Stock stock = null;
        try {
            stock = mapper.readValue(jsonObj.toString(), Stock.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        LocalDate date1 = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Date truncatedDate_i = DateUtils.truncate(new SimpleDateFormat("yyyy-mm-dd").parse(date1.toString()), Calendar.DATE);
        stock.setDate(truncatedDate_i);
        stock.setTicker(ticker);


        return stock;
    }

    public HashMap<Date, Stock> parseJsonToStockBetweenDates(String ticker, Date start_date, Date end_date) throws ParseException {

        String url;
        url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=<ticker>&apikey=1XDCVS7DM3C1XE20".replace("<ticker>", ticker);

        String json = restTemplate.getForObject(url, String.class);
        LocalDate date1 = start_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date2 = end_date.toInstant().atZone   (ZoneId.systemDefault()).toLocalDate();
        long daysBetween = ChronoUnit.DAYS.between(date1, date2);
        HashMap<Date, Stock> stockMap = new HashMap<>();
        for (int i = 0; i <= daysBetween; i++){
            LocalDate i_date = date1.plusDays(i);
            JSONObject jsonObj = new JSONObject(json).getJSONObject("Time Series (Daily)").getJSONObject(i_date.toString());
            ObjectMapper mapper = new ObjectMapper();
            Stock stock = null;
            try {
                stock = mapper.readValue(jsonObj.toString(), Stock.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            Date truncatedDate_i = DateUtils.truncate(new SimpleDateFormat("yyyy-MM-dd").parse(date1.plusDays(i).toString()), Calendar.DATE);
            stock.setDate(truncatedDate_i);
            stock.setTicker(ticker);

            stockMap.put(truncatedDate_i, stock);
        }

        return stockMap;
    }


    public HashMap<String, Double> getEvolutionsByClosePrice(Date end_date) throws ParseException
    {
        List<String> Tickers_in_db = new ArrayList<>();
        Tickers_in_db = stockSearchHistoryRepository.findDistinctTicker();
        HashMap<String, Double> Evolution_by_ticker = new HashMap<String, Double>();

        for (int i = 0; i < Tickers_in_db.size(); i++) {
            String ticker = Tickers_in_db.get(i);
            Date start_date_l = DateUtils.truncate(new SimpleDateFormat("yyyy-MM-dd").parse(end_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(-1).toString()), Calendar.DATE);
            Date end_date_l = DateUtils.truncate(new SimpleDateFormat("yyyy-MM-dd").parse(end_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString()), Calendar.DATE);
            HashMap<Date, Stock> Stock_data = parseJsonToStockBetweenDates(ticker, start_date_l, end_date_l);
            Evolution_by_ticker.put(ticker, (Stock_data.get(end_date_l).getClose() - Stock_data.get(start_date_l).getClose())/Stock_data.get(start_date_l).getClose());
        }

        return Evolution_by_ticker;

    }
    public Stock getMostmove(Date end_date) throws ParseException
    {

        HashMap<String, Double> Evolution_by_ticker = new HashMap<String, Double>();
        Evolution_by_ticker = getEvolutionsByClosePrice(end_date);
        String Max_Ticker = Collections.max(Evolution_by_ticker.entrySet(), Map.Entry.comparingByValue()).getKey();

        Stock Max_Mover_Stock = parseJsonToStock(Max_Ticker, end_date);

        return Max_Mover_Stock;

    }

    public Stock getLeastmove(Date end_date) throws ParseException
    {

        HashMap<String, Double> Evolution_by_ticker = new HashMap<String, Double>();
        Evolution_by_ticker = getEvolutionsByClosePrice(end_date);
        String Min_Ticker = Collections.min(Evolution_by_ticker.entrySet(), Map.Entry.comparingByValue()).getKey();

        Stock Max_Mover_Stock = parseJsonToStock(Min_Ticker, end_date);
        StockWithEvolution stockWithEvolution = new StockWithEvolution();

        return Max_Mover_Stock;

    }

    public String saveStock(Stock stock)
    {
        try {
            stockRepository.save(stock);
            return "Saved stock successfully";
        }
        catch (Exception e)
        {
            return "Problem while saving stock";
        }
    }
}
