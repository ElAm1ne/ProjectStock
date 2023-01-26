package com.example.ProjectStock.Modele;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "stocks")
@Data

public class Stock {
    public Stock() {
        this.id = 0L;
        this.ticker = "";
        this.date = new Date();
        this.open = 0.0;
        this.high = 0.0;
        this.low = 0.0;
        this.close = 0.0;
        this.adjustedClose = 0.0;
        this.volume = 0;
        this.dividendAmount = 0.0;
        this.splitCoefficient = 0.0;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ticker")
    private String ticker;

    @Column(name = "date")
    private Date date;
    @JsonProperty("1. open")
    @Column(name = "open")
    private Double open;
    @JsonProperty("2. high")
    @Column(name = "high")
    private Double high;
    @JsonProperty("3. low")
    @Column(name = "low")
    private Double low;
    @JsonProperty("4. close")
    @Column(name = "close")
    private Double close;
    @JsonProperty("5. adjusted close")
    @Column(name = "adjusted_close")
    private Double adjustedClose;
    @JsonProperty("6. volume")
    @Column(name = "volume")
    private long volume;
    @JsonProperty("7. dividend amount")
    @Column(name = "dividend_amount")
    private Double dividendAmount;
    @JsonProperty("8. split coefficient")
    @Column(name = "split_coefficient")
    private Double splitCoefficient;
}
