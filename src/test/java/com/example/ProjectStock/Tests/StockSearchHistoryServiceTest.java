package com.example.ProjectStock.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.example.ProjectStock.Modele.Stock;
import com.example.ProjectStock.Repository.StockSearchHistoryEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.ProjectStock.Modele.StockSearchHistory;
import com.example.ProjectStock.Service.StockSearchHistoryService;

class StockSearchHistoryServiceTest {

    @InjectMocks
    private StockSearchHistoryService stockSearchHistoryService;

    @Mock
    private StockSearchHistoryEntityRepository stockSearchHistoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getTotalViewsForTickerBetweenDates_Success() {
        // Given
        String ticker = "AAPL";
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2022-11-15");
            endDate = new SimpleDateFormat("yyyy-MM-dd").parse("2022-11-16");

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Long expectedTotalViews = 10L;

        List<StockSearchHistory> stockSearchHistories = new ArrayList<>();
        StockSearchHistory stockSearchHistory = new StockSearchHistory();
        stockSearchHistory.setTicker(ticker);
        stockSearchHistory.setDate(startDate);
        stockSearchHistory.setViews(5L);
        stockSearchHistories.add(stockSearchHistory);

        StockSearchHistory stockSearchHistory2 = new StockSearchHistory();
        stockSearchHistory2.setTicker(ticker);
        stockSearchHistory2.setDate(endDate);
        stockSearchHistory2.setViews(5L);
        stockSearchHistories.add(stockSearchHistory2);

        when(stockSearchHistoryService.getTotalViewsForTickerBetweenDates(ticker, startDate, endDate)).thenReturn(expectedTotalViews);

        // When
        Long totalViews = stockSearchHistoryService.getTotalViewsForTickerBetweenDates(ticker, startDate, endDate);

        // Then
        assertEquals(expectedTotalViews, totalViews);
    }


    @Test
    public void testFindAll() {
        StockSearchHistory history1 = new StockSearchHistory();
        history1.setTicker("AAPL");
        history1.setDate(new Date());
        history1.setViews(10L);
        StockSearchHistory history2 = new StockSearchHistory();
        history2.setTicker("GOOG");
        history2.setDate(new Date());
        history2.setViews(20L);
        List<StockSearchHistory> expectedHistories = Arrays.asList(history1, history2);
        when(stockSearchHistoryRepository.findAll()).thenReturn(expectedHistories);

        List<StockSearchHistory> actualHistories = stockSearchHistoryService.findAll();

        assertEquals(expectedHistories, actualHistories);
    }
}