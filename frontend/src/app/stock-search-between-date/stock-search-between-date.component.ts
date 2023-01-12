
import { Component, OnInit, Output,EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-stock-search-between-date',
  templateUrl: './stock-search-between-date.component.html',
  styleUrls: ['./stock-search-between-date.component.css']

})
export class StockSearchBetweenDateComponent implements OnInit{

 


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
  }
}








