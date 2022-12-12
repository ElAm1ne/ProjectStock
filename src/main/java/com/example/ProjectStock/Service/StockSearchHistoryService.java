package com.example.ProjectStock.Service;

import com.example.ProjectStock.Modele.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjectStock.Modele.StockSearchHistory;
import com.example.ProjectStock.Repository.StockSearchHistoryEntityRepository;

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
        mappedValue.setDate(stock.getDate());
        if (repository.existsByTickerAndDate(stock.getTicker(), stock.getDate()))
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

    public List<Object[]> getTopNTickersByViews(int n) {

        return repository.getTopNTickersByViews(n);
    }

    public Long getTotalViewsForTickerBetweenDates(String ticker, Date startDate, Date endDate) {
        return repository.getTotalViewsForTickerBetweenDates(ticker, startDate, endDate);
    }

}

