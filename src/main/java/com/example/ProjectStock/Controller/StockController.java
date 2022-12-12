package com.example.ProjectStock.Controller;
import com.example.ProjectStock.Modele.Stock;
import com.example.ProjectStock.Service.StockService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/stocks")
    public List<Stock> getAll(){
        return stockService.findAllStocks();
    }
    @GetMapping("/stocks/{id}")
    public Optional<Stock> findById(@PathVariable Long id) {
        return stockService.findById(id);
    }

    @GetMapping("/stocks/ticker/{ticker}")
    public List<Stock> findByTicker(@PathVariable String ticker) {
        return stockService.findByTicker(ticker);
    }

    @GetMapping("/stocks/date")
    public List<Stock> findByDateBetween(@RequestParam("start") Date startDate, @RequestParam("end") Date endDate) {
        return stockService.findByDateBetween(startDate, endDate);
    }

    // Endpoints for getting the list of open, high, low, close, adjusted close,
    // volume, dividend amount, and split coefficient values for a given ticker and date range
    @GetMapping("/stocks/open/{ticker}")
    public List<BigDecimal> getOpenValues(@PathVariable String ticker, @RequestParam("start") Date startDate, @RequestParam("end") Date endDate) {
        return stockService.getOpenValues(ticker, startDate, endDate);
    }

    @GetMapping("/stocks/high/{ticker}")
    public List<BigDecimal> getHighValues(@PathVariable String ticker, @RequestParam("start") Date startDate, @RequestParam("end") Date endDate) {
        return stockService.getHighValues(ticker, startDate, endDate);
    }

    @GetMapping("/stocks/low/{ticker}")
    public List<BigDecimal> getLowValues(@PathVariable String ticker, @RequestParam("start") Date startDate, @RequestParam("end") Date endDate) {
        return stockService.getLowValues(ticker, startDate, endDate);
    }

    @GetMapping("/stocks/close/{ticker}")
    public List<BigDecimal> getCloseValues(@PathVariable String ticker, @RequestParam("start") Date startDate, @RequestParam("end") Date endDate) {
        return stockService.getCloseValues(ticker, startDate, endDate);
    }

    @GetMapping("/stocks/adjusted-close/{ticker}")
    public List<BigDecimal> getAdjustedCloseValues(@PathVariable String ticker, @RequestParam("start") Date startDate, @RequestParam("end") Date endDate) {
        return stockService.getAdjustedCloseValues(ticker, startDate, endDate);
    }

    @GetMapping("/stocks/volume/{ticker}")
    public List<Long> getVolumeValues(@PathVariable String ticker, @RequestParam("start") Date startDate, @RequestParam("end") Date endDate) {
        return stockService.getVolumeValues(ticker, startDate, endDate);
    }
    @GetMapping("/stocks/dividend-amount/{ticker}")
    public List<BigDecimal> getDividendAmountValues(@PathVariable String ticker, @RequestParam("start") Date startDate, @RequestParam("end") Date endDate) {
        return stockService.getDividendAmountValues(ticker, startDate, endDate);
    }

    @GetMapping("/stocks/split-coefficient/{ticker}")
    public List<Double> getSplitCoefficientValues(@PathVariable String ticker, @RequestParam("start") Date startDate, @RequestParam("end") Date endDate) {
        return stockService.getSplitCoefficientValues(ticker, startDate, endDate);
    }

    @GetMapping("/stocks/stock")
    public Stock getStockByTickerAndDate(@RequestParam("ticker") String ticker, @RequestParam("date") String date) throws ParseException {
        try
        {
            return stockService.parseJsonToStock(ticker, new SimpleDateFormat("yyyy-mm-dd").parse(date));
        }
        catch (Exception e)
        {
            Stock stock = new Stock();
            return stock;
        }
    }

    @PostMapping("/stocks/add-stock")
    public String AddStockByTickerAndDate(@RequestParam("ticker") String ticker, @RequestParam("date") String date) throws ParseException {
        return stockService.saveStock(stockService.parseJsonToStock(ticker, new SimpleDateFormat("yyyy-mm-dd").parse(date)));
    }
}