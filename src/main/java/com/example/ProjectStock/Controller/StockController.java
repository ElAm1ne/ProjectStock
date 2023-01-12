package com.example.ProjectStock.Controller;
import com.example.ProjectStock.Modele.Stock;
import com.example.ProjectStock.Service.StockSearchHistoryService;
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
@CrossOrigin
@RequestMapping("/api")
public class StockController {

    @Autowired
    private StockService stockService;
    @Autowired
    private StockSearchHistoryService stockHistoryService;

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
    public List<Double> getOpenValues(@PathVariable String ticker, @RequestParam("start") Date startDate, @RequestParam("end") Date endDate) {
        return stockService.getOpenValues(ticker, startDate, endDate);
    }

    @GetMapping("/stocks/high/{ticker}")
    public List<Double> getHighValues(@PathVariable String ticker, @RequestParam("start") Date startDate, @RequestParam("end") Date endDate) {
        return stockService.getHighValues(ticker, startDate, endDate);
    }

    @GetMapping("/stocks/low/{ticker}")
    public List<Double> getLowValues(@PathVariable String ticker, @RequestParam("start") Date startDate, @RequestParam("end") Date endDate) {
        return stockService.getLowValues(ticker, startDate, endDate);
    }

    @GetMapping("/stocks/close/{ticker}")
    public List<Double> getCloseValues(@PathVariable String ticker, @RequestParam("start") Date startDate, @RequestParam("end") Date endDate) {
        return stockService.getCloseValues(ticker, startDate, endDate);
    }

    @GetMapping("/stocks/adjusted-close/{ticker}")
    public List<Double> getAdjustedCloseValues(@PathVariable String ticker, @RequestParam("start") Date startDate, @RequestParam("end") Date endDate) {
        return stockService.getAdjustedCloseValues(ticker, startDate, endDate);
    }

    @GetMapping("/stocks/volume/{ticker}")
    public List<Long> getVolumeValues(@PathVariable String ticker, @RequestParam("start") Date startDate, @RequestParam("end") Date endDate) {
        return stockService.getVolumeValues(ticker, startDate, endDate);
    }
    @GetMapping("/stocks/dividend-amount/{ticker}")
    public List<Double> getDividendAmountValues(@PathVariable String ticker, @RequestParam("start") Date startDate, @RequestParam("end") Date endDate) {
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
            Stock stock = stockService.parseJsonToStock(ticker, new SimpleDateFormat("yyyy-MM-dd").parse(date));
            stockHistoryService.saveHistory(stock);
            return stock;
        }
        catch (Exception e)
        {
            Stock stock = new Stock();
            return stock;
        }
    }

    @GetMapping("/stocks/stockBetween")
    public HashMap<Date, Stock> getStockHistoryByTickerAndDate(@RequestParam("ticker") String ticker, @RequestParam("start") String start_date, @RequestParam("end") String end_date) throws ParseException {
        try
        {
            HashMap<Date, Stock> stock_map = stockService.parseJsonToStockBetweenDates(ticker, new SimpleDateFormat("yyyy-MM-dd").parse(start_date), new SimpleDateFormat("yyyy-MM-dd").parse(end_date));
            List<Stock> values_stock = new ArrayList<>(stock_map.values());
            stockHistoryService.saveHistory(values_stock.get(0));
            return stock_map;
        }
        catch (Exception e)
        {
            HashMap<Date, Stock> stock_map = new HashMap<Date, Stock>();
            return stock_map;
        }
    }


    @PostMapping("/stocks/add-stock")
    public String AddStockByTickerAndDate(@RequestParam("ticker") String ticker, @RequestParam("date") String date) throws ParseException {
        return stockService.saveStock(stockService.parseJsonToStock(ticker, new SimpleDateFormat("yyyy-MM-dd").parse(date)));
    }

    @GetMapping("/stocks/stocksDailyEvolution")
    public Map<String, Double> getEvolutions(@RequestParam("end") String end_date) throws ParseException
    {
        return stockService.getEvolutionsByClosePrice(new SimpleDateFormat("yyyy-MM-dd").parse(end_date));
    }

    @GetMapping("/stocks/stocksMostMoving")
    public Map<String, Double> getMostMoving(@RequestParam("end") String end_date) throws ParseException
    {
        return stockService.getEvolutionsByClosePrice(new SimpleDateFormat("yyyy-MM-dd").parse(end_date));
    }

    @GetMapping("stocks/backtest")
    public HashMap<Date, Stock> BackTestTickersBetween(@RequestParam("tickers") String tickers, @RequestParam("percentages") String percent, @RequestParam("start") String start, @RequestParam("end") String end) throws ParseException {
        try
        {
            HashMap<Date, Stock> stock_map = stockService.Backtest(tickers, percent, new SimpleDateFormat("yyyy-MM-dd").parse(start), new SimpleDateFormat("yyyy-MM-dd").parse(end));
            List<Stock> values_stock = new ArrayList<>(stock_map.values());
            stockHistoryService.saveHistory(values_stock.get(0));
            return stock_map;
        }
        catch (Exception e)
        {
            HashMap<Date, Stock> stock_map = new HashMap<Date, Stock>();
            return stock_map;
        }
    }


}