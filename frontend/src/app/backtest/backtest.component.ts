
import { Component, OnInit, ViewChild } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { OrderPipe } from 'ngx-order-pipe';
import * as moment from 'moment';

@Component({
  selector: 'app-backtest',
  templateUrl: './backtest.component.html',
  styleUrls: ['./backtest.component.css']
})
export class BacktestComponent implements OnInit {
  order: string = 'date'; 
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

    const url = `https://projectstockif.azurewebsites.net/api/stocks/backtest?valptf=${this.amount}&tickers=${this.tickers}&percentages=${this.percentages}&start=${this.start}&end=${this.end}`;
    let res = this.http.get(url);
    
    res.subscribe(data => {

      this.data = Object.values(data);

    });


  }

}



