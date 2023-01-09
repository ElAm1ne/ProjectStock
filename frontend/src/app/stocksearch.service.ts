import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StockSearchHistory } from './stock-search-history';

@Injectable({
  providedIn: 'root'
})
export class StocksearchService {
  private baseUrl = "http://localhost:9009/api/stock-history"; 
  constructor(private httpClient: HttpClient) { }
  getStockSearchList() : Observable<StockSearchHistory[]>{
    return this.httpClient.get<StockSearchHistory[]>(`${this.baseUrl}`)
  }
}
 