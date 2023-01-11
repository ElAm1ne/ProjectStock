
import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CandlestickComponent } from '../candlestick/candlestick.component';
@Component({
  selector: 'app-stock-search-between-date',
  templateUrl: './stock-search-between-date.component.html',
  styleUrls: ['./stock-search-between-date.component.css']

})
export class StockSearchBetweenDateComponent implements OnInit{


  @ViewChild(CandlestickComponent) child : any;
  constructor(private http: HttpClient) {}

  ngOnInit(): void {}
  start: string;
  end: string;
  ticker: string;
  data: any[];




  onSubmit() {
    const url = `http://localhost:9009/api/stocks/stockBetween?ticker=${this.ticker}&start=${this.start}&end=${this.end}`;
    this.http.get(url).subscribe(data => {
      this.data = Object.values(data);
    });
    this.child.ngOnInit();
    setTimeout(() => window.dispatchEvent(new Event('resize')), 2000);
  }
}








