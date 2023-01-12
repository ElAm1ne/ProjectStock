
import { Component, OnInit, ViewChild } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Candlestick2Component } from '../candlestick2/candlestick2.component';
import { OrderPipe } from 'ngx-order-pipe';
import * as moment from 'moment';


@Component({
  selector: 'app-backtestcandle',
  templateUrl: './backtestcandle.component.html',
  styleUrls: ['./backtestcandle.component.css']
})
export class BacktestcandleComponent {
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

  cdata : any[];

  onSubmit() {

  this.child.ngOnInit();

  setTimeout(() => window.dispatchEvent(new Event('resize')), 5000);

  }

}


