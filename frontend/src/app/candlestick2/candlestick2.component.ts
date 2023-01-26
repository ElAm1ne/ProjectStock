import { Component, VERSION ,ViewChild,OnInit, Input } from '@angular/core';

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
  selector: 'app-candlestick2',
  templateUrl: './candlestick2.component.html',
  styleUrls: ['./candlestick2.component.css']
})
export class Candlestick2Component implements OnInit {
  @ViewChild("chart") chart: ChartComponent;
  public chartOptions: Partial<ChartOptions> | any;
  cpdata : any = [];
  @Input() start: string;
  @Input() end: string;
  @Input() tickers: string;
  @Input() percentages: string;
  @Input() amount: string;
  constructor(private http: HttpClient) {}

  ngOnInit() {
    console.log(this.amount);
    this.cpdata = [];
    const url = `https://projectstockif.azurewebsites.net/api/stocks/backtest?valptf=${this.amount}&tickers=${this.tickers}&percentages=${this.percentages}&start=${this.start}&end=${this.end}`;
    let res = this.http.get(url);
    res.subscribe((stockData : any) => {
      for (const date in stockData) {
        if (stockData.hasOwnProperty(date)) {
          const { '1. open': open, '2. high': high, '3. low': low, '4. close': close } = stockData[date];
          this.cpdata.push({
            x: moment(new Date(date)).add(1, 'days'),
            y: [open, high, low, close]
          });
        }
      }
    });
      console.log(this.cpdata);
      this.chartOptions = {
        series: [
          {
            name: "candle",
            data: this.cpdata
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
