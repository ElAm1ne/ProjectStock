package com.example.ProjectStock.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjectStock.Modele.StockSearchHistory;
import com.example.ProjectStock.Repository.StockSearchHistoryEntityRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StockSearchHistoryService {

    @Autowired
    private StockSearchHistoryEntityRepository repository;

    public Optional<StockSearchHistory> findById(Long id) {

        return repository.findById(id);
    }

    public List<StockSearchHistory> findByTicker(String ticker) {

        return repository.findByTicker(ticker);
    }

    public List<StockSearchHistory> findByName(String name) {

        return repository.findByName(name);
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

