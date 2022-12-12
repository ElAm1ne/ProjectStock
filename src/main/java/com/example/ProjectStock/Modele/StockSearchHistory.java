package com.example.ProjectStock.Modele;
import javax.persistence.*;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "stock_search_history")
@Data
@NoArgsConstructor
public class StockSearchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ticker")
    private String ticker;

    @Column(name = "date")
    private Date date;

    @Column(name = "views")
    private Long views;
}
