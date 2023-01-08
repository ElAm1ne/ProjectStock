package com.example.ProjectStock.Controller;

import com.example.ProjectStock.Service.StockSearchHistoryService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.ProjectStock.Modele.StockSearchHistory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    public Optional<StockSearchHistory> findByTickerAndDate(@PathVariable String ticker, @PathVariable String date) throws ParseException {
        Date truncatedDate = DateUtils.truncate(new SimpleDateFormat("yyyy-mm-dd").parse(date), Calendar.DATE);
        return stockSearchHistoryService.findByTickerAndDate(ticker, truncatedDate);
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
