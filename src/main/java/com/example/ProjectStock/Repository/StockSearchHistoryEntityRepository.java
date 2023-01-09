package com.example.ProjectStock.Repository;
import com.example.ProjectStock.Modele.Stock;
import com.example.ProjectStock.Modele.StockSearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface StockSearchHistoryEntityRepository extends JpaRepository<StockSearchHistory, Long> {

    List<StockSearchHistory> findAll();
    Optional<StockSearchHistory> findById(Long id);

    List<StockSearchHistory> findByTicker(String ticker);

    List<StockSearchHistory> findByDate(Date date);

    List<StockSearchHistory> findByViews(Long views);

    Optional<StockSearchHistory> findByTickerAndDate(String ticker, Date date);



    @Modifying
    @Transactional
    @Query("UPDATE StockSearchHistory s SET s.views = s.views + 1 WHERE s.date = ?2 and s.ticker = ?1")
    void updateViews(String ticker, Date date);

    boolean existsByTickerAndDate(String ticker, Date date);
    @Query("SELECT SUM(h.views) FROM StockSearchHistory h WHERE h.date BETWEEN :startDate AND :endDate")
    Long getTotalViewsBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT h.ticker, SUM(h.views) AS totalViews FROM StockSearchHistory h GROUP BY h.ticker ORDER BY totalViews DESC")
    List<Object[]> getTopNTickersByViews(@Param("n") int n);

    @Query("SELECT SUM(h.views) FROM StockSearchHistory h WHERE h.ticker = :ticker AND h.date BETWEEN :startDate AND :endDate")
    Long getTotalViewsForTickerBetweenDates(@Param("ticker") String ticker, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT e.ticker, SUM(e.views) as totalViews FROM StockSearchHistory e WHERE e.date BETWEEN :startDate AND :endDate GROUP BY e.ticker ORDER BY totalViews DESC")
    List<Object[]> findTopViewsByTicker(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "SELECT *  FROM stock_search_history WHERE views = (SELECT MAX(views) FROM stock_search_history) AND date >= DATEADD(DD, -5, cast(GETDATE() as date))", nativeQuery = true)
    StockSearchHistory findMostSearchedStock();




}