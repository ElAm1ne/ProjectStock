
import { Component, OnInit, ViewChild } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Candlestick2Component } from '../candlestick2/candlestick2.component';
import { OrderPipe } from 'ngx-order-pipe';
@Component({
  selector: 'app-backtest',
  templateUrl: './backtest.component.html',
  styleUrls: ['./backtest.component.css']
})
export class BacktestComponent implements OnInit {
  order: string = 'date'; 

  @ViewChild(Candlestick2Component) child : any;

  constructor(private http: HttpClient) {}



  ngOnInit(): void {}

  start: string;

  end: string;

  tickers: string;

  percentages: string;

  data: any[];

  amount : string;

  
  onSubmit() {

    const url = `http://localhost:9009/api/stocks/backtest?valptf=${this.amount}&tickers=${this.tickers}&percentages=${this.percentages}&start=${this.start}&end=${this.end}`;
    this.http.get(url).subscribe(data => {

      this.data = Object.values(data);

    });

    this.child.ngOnInit();

    setTimeout(() => window.dispatchEvent(new Event('resize')), 4000);

  }

}



