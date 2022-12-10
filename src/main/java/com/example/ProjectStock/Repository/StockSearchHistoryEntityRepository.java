package com.example.ProjectStock.Repository;
import com.example.ProjectStock.Modele.StockSearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface StockSearchHistoryEntityRepository extends JpaRepository<StockSearchHistory, Long> {

    Optional<StockSearchHistory> findById(Long id);

    List<StockSearchHistory> findByTicker(String ticker);

    List<StockSearchHistory> findByName(String name);

    List<StockSearchHistory> findByDate(Date date);

    List<StockSearchHistory> findByViews(Long views);

    @Query("SELECT SUM(h.views) FROM StockSearchHistory h WHERE h.date BETWEEN :startDate AND :endDate")
    Long getTotalViewsBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT h.ticker, SUM(h.views) AS totalViews FROM StockSearchHistory h GROUP BY h.ticker ORDER BY totalViews DESC")
    List<Object[]> getTopNTickersByViews(@Param("n") int n);

    @Query("SELECT SUM(h.views) FROM StockSearchHistory h WHERE h.ticker = :ticker AND h.date BETWEEN :startDate AND :endDate")
    Long getTotalViewsForTickerBetweenDates(@Param("ticker") String ticker, @Param("startDate") Date startDate, @Param("endDate") Date endDate);


}