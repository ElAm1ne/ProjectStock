package com.example.ProjectStock.Tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.ProjectStock.Modele.Stock;
import com.example.ProjectStock.Repository.StockEntityRepository;
import com.example.ProjectStock.Service.StockService;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StockServiceTest {
    @Mock
    private StockEntityRepository stockRepository;
    @InjectMocks
    private StockService stockService;

    @Mock
    private StockService stockServiceMock;
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllStocksTest() {
        Stock stock1 = new Stock();
        stock1.setTicker("AAPL");

        Stock stock2 = new Stock();
        stock2.setTicker("GOOG");

        when(stockRepository.findAll()).thenReturn(Arrays.asList(stock1, stock2));

        List<Stock> stocks = stockService.findAllStocks();

        verify(stockRepository, times(1)).findAll();
        assertEquals(2, stocks.size());
    }

    @Test
    public void testParseJsonToStockBetweenDates() {
        String ticker = "AAPL";
        Date start = null;
        Date end = null;
        try {
            start = new SimpleDateFormat("yyyy-MM-dd").parse("2022-11-15");
            end = new SimpleDateFormat("yyyy-MM-dd").parse("2022-11-16");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        HashMap<Date, Stock> expectedStocks = new HashMap<>();
        expectedStocks.put(start,new Stock(0L, "AAPL", start, 152.215, 153.59,148.5613,150.04,149.850555986024,89868332L,0.0,1.0));
        expectedStocks.put(end,new Stock(0L,"AAPL", end, 149.13, 149.87, 147.29,148.79,148.602134265266,64218266L,0.0,1.0));

        HashMap<Date, Stock> actualStocks = null;

        try {
            when(stockServiceMock.parseJsonToStockBetweenDates("AAPL", start, end)).thenReturn(expectedStocks);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        try {
            actualStocks = stockServiceMock.parseJsonToStockBetweenDates(ticker, start, end);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expectedStocks, actualStocks);
    }
}
