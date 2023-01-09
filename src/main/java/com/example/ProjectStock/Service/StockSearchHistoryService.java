package com.example.ProjectStock.Service;

import com.example.ProjectStock.Modele.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.ProjectStock.Modele.StockSearchHistory;
import com.example.ProjectStock.Repository.StockSearchHistoryEntityRepository;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;

@Service
public class StockSearchHistoryService {

    @Autowired
    private StockSearchHistoryEntityRepository repository;


    public List<StockSearchHistory> findAll(){
        if (repository.findAll().isEmpty()){
            StockSearchHistory hist = new StockSearchHistory();
            List<StockSearchHistory> empty = new ArrayList<>();
            return empty;
        }
        else {
            return repository.findAll();
        }
    }
    public void saveHistory(Stock stock){
        StockSearchHistory mappedValue = new StockSearchHistory();
        mappedValue.setTicker(stock.getTicker());
        Date date_now = new Date();
        Date truncatedDate = DateUtils.truncate(new Date(), Calendar.DATE);
        mappedValue.setDate(truncatedDate);
        if (repository.existsByTickerAndDate(stock.getTicker(), truncatedDate))
        {
            repository.updateViews(mappedValue.getTicker(), mappedValue.getDate());
        }
        else
        {
            mappedValue.setViews(1L);
            repository.save(mappedValue);
        }
    }
    public Optional<StockSearchHistory> findById(Long id) {

        return repository.findById(id);
    }

    public Optional<StockSearchHistory> findByTickerAndDate(String ticker, Date date)
    {
        return repository.findByTickerAndDate(ticker, date);
    }
    public List<StockSearchHistory> findByTicker(String ticker) {

        return repository.findByTicker(ticker);
    }

    public List<StockSearchHistory> findByDate(Date date) {

        return repository.findByDate(date);
    }

    public List<StockSearchHistory> findByViews(Long views) {

        return repository.findByViews(views);
    }

    public Long getTotalViewsBetweenDates(Date startDate, Date endDate) {
        if (repository.getTotalViewsBetweenDates(startDate, endDate) == null){
            return 0L;
        }
        return repository.getTotalViewsBetweenDates(startDate, endDate);
    }



    public Long getTotalViewsForTickerBetweenDates(String ticker, Date startDate, Date endDate) {
        if (repository.getTotalViewsForTickerBetweenDates(ticker, startDate, endDate) == null){
            return 0L;
        }
        return repository.getTotalViewsForTickerBetweenDates(ticker, startDate, endDate);
    }

    public List<Object[]> getTopViewsByTicker(Date startDate, Date endDate){
        return repository.findTopViewsByTicker(startDate, endDate);
    }

    public HashMap<String, Double> getEvolutionSearches(Date end_date) throws ParseException {
        List<String> Tickers_in_db = repository.findDistinctTicker();
        HashMap<String, Double> returned_hashmap_S3 = new HashMap<String, Double>();
        HashMap<String, Double> returned_hashmap_S2 = new HashMap<String, Double>();

        HashMap<String, Double> returned_hashmap = new HashMap<String, Double>();
        int n = 7;

        Date end_date_s3 = DateUtils.truncate(new SimpleDateFormat("yyyy-MM-dd").parse(end_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString()), Calendar.DATE);
        Date start_date_s3 = DateUtils.truncate(new SimpleDateFormat("yyyy-MM-dd").parse(end_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(-n).toString()), Calendar.DATE);
        Date end_date_s2 = DateUtils.truncate(new SimpleDateFormat("yyyy-MM-dd").parse(start_date_s3.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(-1).toString()), Calendar.DATE);
        Date start_date_s2 = DateUtils.truncate(new SimpleDateFormat("yyyy-MM-dd").parse(end_date_s2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(-n).toString()), Calendar.DATE);

        Long Total_Views_S3 = 0L;
        Long Total_Views_S2 = 0L;
        for (int i = 0; i < Tickers_in_db.size(); i++){
            String Ticker = Tickers_in_db.get(i);
            Long TotalViews3 = getTotalViewsForTickerBetweenDates(Ticker,start_date_s3, end_date_s3 );
            Long TotalViews2 = getTotalViewsForTickerBetweenDates(Ticker,start_date_s2, end_date_s2 );

            Total_Views_S3 += TotalViews3;
            Total_Views_S2 += TotalViews2;

            returned_hashmap_S3.put(Ticker, TotalViews3.doubleValue());
            returned_hashmap_S2.put(Ticker, TotalViews2.doubleValue());

            returned_hashmap.put(Ticker, 0.0);
        }
        for (Map.Entry<String, Double> set : returned_hashmap_S3.entrySet()){
            set.setValue(set.getValue()/Total_Views_S3);
        }

        for (Map.Entry<String, Double> set : returned_hashmap_S2.entrySet()){
            if (Total_Views_S2 == 0L){
                set.setValue(0.0);
            }
            else{
                set.setValue(set.getValue()/Total_Views_S2);
            }


        }

        for (Map.Entry<String, Double> set : returned_hashmap.entrySet()){
            set.setValue(returned_hashmap_S3.get(set.getKey()) - returned_hashmap_S2.get(set.getKey()));
        }


        return returned_hashmap;

    }

    public HashMap<String, Double> getRatioSearches(Date end_date) throws ParseException {
        List<String> Tickers_in_db = repository.findDistinctTicker();
        HashMap<String, Double> returned_hashmap = new HashMap<String, Double>();
        int n = 7;
        Date start_date_s = DateUtils.truncate(new SimpleDateFormat("yyyy-MM-dd").parse(end_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(-n).toString()), Calendar.DATE);
        Date end_date_s = DateUtils.truncate(new SimpleDateFormat("yyyy-MM-dd").parse(end_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString()), Calendar.DATE);

        Long Total_Views_S = 0L;
        for (int i = 0; i < Tickers_in_db.size(); i++){
            String Ticker = Tickers_in_db.get(i);
            Long TotalViews = getTotalViewsForTickerBetweenDates(Ticker,start_date_s, end_date_s );

            Total_Views_S += TotalViews;

            returned_hashmap.put(Ticker, TotalViews.doubleValue());


        }

        for (Map.Entry<String, Double> set : returned_hashmap.entrySet()){
            set.setValue(set.getValue()/Total_Views_S);
        }



        return returned_hashmap;

    }

}

