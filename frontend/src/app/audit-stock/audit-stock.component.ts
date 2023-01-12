
import { Component ,OnInit } from '@angular/core';
import { StockSearchHistory } from '../stock-search-history';
import { StocksearchService } from '../stocksearch.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-audit-stock',
  templateUrl: './audit-stock.component.html',
  styleUrls: ['./audit-stock.component.css']
})
export class AuditStockComponent implements OnInit {

ngOnInit() : void {}
startDate: string;
endDate: string;
ticker: string;
data: any;
topn: any;
constructor(private http: HttpClient) {}

onSubmit() {
  this.http.get(`http://localhost:9009/api/stock-history/views/totalTicker?ticker=${this.ticker}&start=${this.startDate}&end=${this.endDate}`)
  .subscribe(data => {
  this.data = data;
  });
  this.http.get(`http://localhost:9009/api/stock-history/views/topn?start=${this.startDate}&end=${this.endDate}&n=10`)
  .subscribe(topn => {
  this.topn = Object.values(topn);
  });
  }
}