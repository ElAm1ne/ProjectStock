package com.example.ProjectStock.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjectStock.Modele.Stock;
import com.example.ProjectStock.Repository.StockEntityRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockEntityRepository repository;

    public Optional<Stock> findById(Long id) {
        return repository.findById(id);
    }

    public List<Stock> findByTicker(String ticker) {
        return repository.findByTicker(ticker);
    }

    public List<Stock> findByLastPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return repository.findByLastPriceBetween(minPrice, maxPrice);
    }

    public BigDecimal findLastPriceByTickerAndDate(String ticker, Date date) {
        return repository.findLastPriceByTickerAndDate(ticker, date);
    }

    public List<String> findTickerByDateAndLastPriceGreaterThan(Date date, BigDecimal price) {
        return repository.findTickerByDateAndLastPriceGreaterThan(date, price);
    }

    public List<BigDecimal> findLastPriceByTickerAndDateBetween(String ticker, Date start, Date end) {
        return repository.findLastPriceByTickerAndDateBetween(ticker, start, end);
    }

    public List<Stock> findByDateBetween(Date startDate, Date endDate) {
        return repository.findByDateBetween(startDate, endDate);
    }

}
