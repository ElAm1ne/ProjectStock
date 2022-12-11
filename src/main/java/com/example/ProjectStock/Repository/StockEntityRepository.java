package com.example.ProjectStock.Repository;
import com.example.ProjectStock.Modele.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface StockEntityRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findById(Long id);
    List<Stock> findByTicker(String ticker);

    List<Stock> findByDateBetween(Date startDate, Date endDate);

    // Method for retrieving a list of open values for a given ticker and date range
    List<BigDecimal> findOpenByTickerAndDateBetween(String ticker, Date startDate, Date endDate);

    // Method for retrieving a list of high values for a given ticker and date range
    List<BigDecimal> findHighByTickerAndDateBetween(String ticker, Date startDate, Date endDate);

    // Method for retrieving a list of low values for a given ticker and date range
    List<BigDecimal> findLowByTickerAndDateBetween(String ticker, Date startDate, Date endDate);

    // Method for retrieving a list of close values for a given ticker and date range
    List<BigDecimal> findCloseByTickerAndDateBetween(String ticker, Date startDate, Date endDate);

    // Method for retrieving a list of adjusted close values for a given ticker and date range
    List<BigDecimal> findAdjustedCloseByTickerAndDateBetween(String ticker, Date startDate, Date endDate);

    // Method for retrieving a list of volume values for a given ticker and date range
    List<Long> findVolumeByTickerAndDateBetween(String ticker, Date startDate, Date endDate);

    // Method for retrieving a list of dividend amount values for a given ticker and date range
    List<BigDecimal> findDividendAmountByTickerAndDateBetween(String ticker, Date startDate, Date endDate);

    // Method for retrieving a list of split coefficient values for a given ticker and date range
    List<Double> findSplitCoefficientByTickerAndDateBetween(String ticker, Date startDate, Date endDate);

}