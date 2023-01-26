
import { Component, VERSION ,ViewChild,OnInit, Input } from '@angular/core';

import {ChartComponent,ApexAxisChartSeries,ApexChart,ApexYAxis,ApexXAxis,ApexTitleSubtitle} from "ng-apexcharts";
import { HttpClient } from '@angular/common/http';
import * as moment from 'moment';
import { catchError, map } from 'rxjs';
export type ChartOptions = {
  series: ApexAxisChartSeries;
  chart: ApexChart;
  xaxis: ApexXAxis;
  yaxis: ApexYAxis;
  title: ApexTitleSubtitle;
};
@Component({
  selector: 'app-treemap',
  templateUrl: './treemap.component.html',
  styleUrls: ['./treemap.component.css']
})
export class TreemapComponent implements OnInit {
  @ViewChild("chart") chart: ChartComponent;
  public chartOptions: Partial<ChartOptions> | any;
  tmdata : any[] = [];
  @Input() start: string;
  @Input() end: string;
  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    console.log(this.start);
    console.log(this.end);
    this.tmdata = [];
    let elm : any;
    const apiUrl = `https://projectstockif.azurewebsites.net/api/stock-history/views/topn?start=${this.start}&end=${this.end}&n=10`;
    this.http.get(apiUrl).pipe(
      map((data : any) => 
        {
          console.log("data", data)
          return data.map((entry: any[]) => {
            return {x: entry[0], y: entry[1]}; 
          });
        }),
        catchError(err => {
          console.error(err);
          return []
        })
    )



    this.chartOptions = {
      series: {
        name : "Searches",
        data : this.tmdata
      },
      chart: {
        type: "treemap",
        height: 350
      },
      title: {
        text: "TreeMap of search histories",
        align: "left"
      },
      legend: {
        show: false
      }
    };
    

  }

  }
