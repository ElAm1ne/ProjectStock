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

    List<Stock> findByLastPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    BigDecimal findLastPriceByTickerAndDate(String ticker, Date date);

    List<String> findTickerByDateAndLastPriceGreaterThan(Date date, BigDecimal price);
    List<BigDecimal> findLastPriceByTickerAndDateBetween(String ticker, Date start, Date end);

    List<Stock> findByDateBetween(Date startDate, Date endDate);
}