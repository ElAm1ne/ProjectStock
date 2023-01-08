package com.example.ProjectStock.Service;

import com.example.ProjectStock.Modele.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.ProjectStock.Modele.StockSearchHistory;
import com.example.ProjectStock.Repository.StockSearchHistoryEntityRepository;
import org.apache.commons.lang3.time.DateUtils;
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
        return repository.getTotalViewsBetweenDates(startDate, endDate);
    }



    public Long getTotalViewsForTickerBetweenDates(String ticker, Date startDate, Date endDate) {
        return repository.getTotalViewsForTickerBetweenDates(ticker, startDate, endDate);
    }

    public List<Object[]> getTopViewsByTicker(Date startDate, Date endDate){
        return repository.findTopViewsByTicker(startDate, endDate);
    }

}

