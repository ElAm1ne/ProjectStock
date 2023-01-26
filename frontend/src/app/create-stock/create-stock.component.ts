import { Component ,OnInit } from '@angular/core';
import { StockSearchHistory } from '../stock-search-history';
import { StocksearchService } from '../stocksearch.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-create-stock',
  templateUrl: './create-stock.component.html',
  styleUrls: ['./create-stock.component.css']
})
export class CreateStockComponent implements OnInit {
  
  ngOnInit() : void {}
  date: string;
  ticker: string;
  data: any;

  constructor(private http: HttpClient) {}



  onSubmit() {
    this.http.get(`https://projectstockif.azurewebsites.net/api/stocks/stock?ticker=${this.ticker}&date=${this.date}`)
      .subscribe(data => {
        this.data = data;
      });
  }

}