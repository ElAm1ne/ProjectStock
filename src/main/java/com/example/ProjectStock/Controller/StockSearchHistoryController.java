package com.example.ProjectStock.Controller;

import com.example.ProjectStock.Service.StockSearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.ProjectStock.Modele.StockSearchHistory;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stock-history")
public class StockSearchHistoryController {
    @Autowired
    private StockSearchHistoryService stockSearchHistoryService;

    @GetMapping("")
    public List<StockSearchHistory> getAll() {
        return stockSearchHistoryService.findAll();
    }
    @GetMapping("/{ticker}/{date}")
    public Optional<StockSearchHistory> findByTickerAndDate(@PathVariable String ticker, @PathVariable Date date) {
        return stockSearchHistoryService.findByTickerAndDate(ticker, date);
    }

    @GetMapping("/{ticker}")
    public List<StockSearchHistory> findByTicker(@PathVariable String ticker) {
        return stockSearchHistoryService.findByTicker(ticker);
    }

    @GetMapping("/views/total")
    public Long getTotalViewsBetweenDates(@RequestParam Date startDate, @RequestParam Date endDate) {
        return stockSearchHistoryService.getTotalViewsBetweenDates(startDate, endDate);
    }

    @GetMapping("/views/top")
    public List<Object[]> getTopNTickersByViews(@RequestParam int n) {
        return stockSearchHistoryService.getTopNTickersByViews(n);
    }

    @GetMapping("/{ticker}/views/total")
    public Long getTotalViewsForTickerBetweenDates(@PathVariable String ticker, @RequestParam Date startDate, @RequestParam Date endDate) {
        return stockSearchHistoryService.getTotalViewsForTickerBetweenDates(ticker, startDate, endDate);
    }
}
