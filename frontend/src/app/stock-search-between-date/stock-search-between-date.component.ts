
import { Component, OnInit, ViewChild } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { CandlestickComponent } from '../candlestick/candlestick.component';
import { TreemapComponent } from '../treemap/treemap.component';
import { OrderPipe } from 'ngx-order-pipe';
@Component({

  selector: 'app-stock-search-between-date',

  templateUrl: './stock-search-between-date.component.html',

  styleUrls: ['./stock-search-between-date.component.css']



})

export class StockSearchBetweenDateComponent implements OnInit{



  order: string = 'date'; 
  @ViewChild(CandlestickComponent) child : any;

  constructor(private http: HttpClient) {}



  ngOnInit(): void {}

  start: string;

  end: string;

  ticker: string;

  data: any[];






  onSubmit() {

    const url = `https://projectstockif.azurewebsites.net/api/stocks/stockBetween?ticker=${this.ticker}&start=${this.start}&end=${this.end}`;

    this.http.get(url).subscribe(data => {

      this.data = Object.values(data);

    });
    this.child.ngOnInit();



    setTimeout(() => window.dispatchEvent(new Event('resize')), 5000);

  }

}





