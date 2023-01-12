package com.example.ProjectStock.Controller;

import com.example.ProjectStock.Service.StockSearchHistoryService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.ProjectStock.Modele.StockSearchHistory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
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
        Date truncatedDate = DateUtils.truncate(new SimpleDateFormat("yyyy-MM-dd").parse(date), Calendar.DATE);
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


    @GetMapping("/views/totalTicker")
    public Long getTotalViewsForTickerBetweenDates(@RequestParam("ticker") String ticker, @RequestParam String startDate, @RequestParam String endDate) throws ParseException {
        return stockSearchHistoryService.getTotalViewsForTickerBetweenDates(ticker, new SimpleDateFormat("yyyy-MM-dd").parse(startDate), new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
    }
    @GetMapping("/views/top")
    public List<Object[]> getTopViewsByTicker( @RequestParam("start") String start_date, @RequestParam("end") String end_date) throws ParseException{
        return stockSearchHistoryService.getTopViewsByTicker(new SimpleDateFormat("yyyy-MM-dd").parse(start_date), new SimpleDateFormat("yyyy-MM-dd").parse(end_date));
    }

    @GetMapping("/views/topn")
    public List<Object[]> getTopnViewsByTicker( @RequestParam("start") String start_date, @RequestParam("end") String end_date, @RequestParam("n") int n) throws ParseException{
        return stockSearchHistoryService.getTopViewsByTicker(new SimpleDateFormat("yyyy-MM-dd").parse(start_date), new SimpleDateFormat("yyyy-MM-dd").parse(end_date)).stream().limit(n).collect(Collectors.toList());
    }

    @GetMapping("/views/evolutions")
    public HashMap<String, Double> getEvolutionsOnSearches(@RequestParam("end") String end_date) throws ParseException{
        return stockSearchHistoryService.getEvolutionSearches(new SimpleDateFormat("yyyy-MM-dd").parse(end_date));
    }

    @GetMapping("/views/stocksSearchRatios")
    public Map<String, Double> getSearchRatios(@RequestParam("end") String end_date) throws ParseException
    {
        return stockSearchHistoryService.getRatioSearches(new SimpleDateFormat("yyyy-MM-dd").parse(end_date));
    }

}
