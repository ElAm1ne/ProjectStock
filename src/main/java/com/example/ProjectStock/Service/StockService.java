package com.example.ProjectStock.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjectStock.Modele.Stock;
import com.example.ProjectStock.Repository.StockEntityRepository;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StockService {

    @Autowired
    private StockEntityRepository stockRepository;
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
    public List<BigDecimal> getOpenValues(String ticker, Date startDate, Date endDate) {
        return stockRepository.findOpenByTickerAndDateBetween(ticker, startDate, endDate);
    }

    public List<BigDecimal> getHighValues(String ticker, Date startDate, Date endDate) {
        return stockRepository.findHighByTickerAndDateBetween(ticker, startDate, endDate);
    }

    public List<BigDecimal> getLowValues(String ticker, Date startDate, Date endDate) {
        return stockRepository.findLowByTickerAndDateBetween(ticker, startDate, endDate);
    }

    public List<BigDecimal> getCloseValues(String ticker, Date startDate, Date endDate) {
        return stockRepository.findCloseByTickerAndDateBetween(ticker, startDate, endDate);
    }

    public List<BigDecimal> getAdjustedCloseValues(String ticker, Date startDate, Date endDate) {
        return stockRepository.findAdjustedCloseByTickerAndDateBetween(ticker, startDate, endDate);
    }

    public List<Long> getVolumeValues(String ticker, Date startDate, Date endDate) {
        return stockRepository.findVolumeByTickerAndDateBetween(ticker, startDate, endDate);
    }

    public List<BigDecimal> getDividendAmountValues(String ticker, Date startDate, Date endDate) {
        return stockRepository.findDividendAmountByTickerAndDateBetween(ticker, startDate, endDate);
    }

    public List<Double> getSplitCoefficientValues(String ticker, Date startDate, Date endDate) {
        return stockRepository.findSplitCoefficientByTickerAndDateBetween(ticker, startDate, endDate);
    }

    public Stock parseJsonToStock(String ticker, Date date) {

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
        stock.setDate(date);
        stock.setTicker(ticker);


        return stock;
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
