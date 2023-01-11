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
  selector: 'app-candlestick',
  templateUrl: './candlestick.component.html',
  styleUrls: ['./candlestick.component.css']
})
export class CandlestickComponent implements OnInit {
  @ViewChild("chart") chart: ChartComponent;
  public chartOptions: Partial<ChartOptions> | any;
  csdata : any = [];
  @Input() ticker: string;
  @Input() start: string;
  @Input() end: string;
  constructor(private http: HttpClient) {}

  ngOnInit(){
    this.csdata = [];
    console.log(this.ticker);
    console.log(this.start);
    console.log(this.end);
    this.http.get(`http://localhost:9009/api/stocks/stockBetween?ticker=${this.ticker}&start=${this.start}&end=${this.end}`).subscribe((stockData : any) => {
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