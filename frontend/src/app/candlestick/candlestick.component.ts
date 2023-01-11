import { Component, VERSION ,ViewChild,OnInit } from '@angular/core';

import {ChartComponent,ApexAxisChartSeries,ApexChart,ApexYAxis,ApexXAxis,ApexTitleSubtitle} from "ng-apexcharts";
import { HttpClient } from '@angular/common/http';
import * as moment from 'moment';
export type ChartOptions = {
  series: ApexAxisChartSeries;
  chart: ApexChart;
  xaxis: ApexXAxis;
  yaxis: ApexYAxis;
  title: ApexTitleSubtitle;
};

@Component({
  selector: 'app-candlestick',
  templateUrl: './candlestick.component.html',
  styleUrls: ['./candlestick.component.css']
})
export class CandlestickComponent implements OnInit {
  ngOnInit(){

    
  }
  @ViewChild("chart") chart: ChartComponent;
  public chartOptions: Partial<ChartOptions> | any;
  csdata : any = [];

  constructor(private http: HttpClient) {
    this.http.get('http://localhost:9009/api/stocks/stockBetween?ticker=AAPL&start=2022-11-10&end=2022-12-28').subscribe((stockData : any) => {
      for (const date in stockData) {
        if (stockData.hasOwnProperty(date)) {
          const { '1. open': open, '2. high': high, '3. low': low, '4. close': close } = stockData[date];
          this.csdata.push({
            x: moment(new Date(date)).add(1, 'days'),
            y: [open, high, low, close]
          });
        }
      }
    });

    this.chartOptions = {
      series: [
        {
          name: "candle",
          data: this.csdata
        }
      ],
      chart: {
        type: "candlestick",
        height: 350
      },
      title: {
        text: "CandleStick Chart",
        align: "left"
      },
      xaxis: {
        type: "datetime"
      },
      yaxis: {
        tooltip: {
          enabled: true
        }
      }
    };
}




}