import { Component, OnInit } from '@angular/core';
import { StockSearchHistory } from '../stock-search-history';
import { StocksearchService } from '../stocksearch.service';

@Component({
  selector: 'app-stocksearch-research',
  templateUrl: './stocksearch-research.component.html',
  styleUrls: ['./stocksearch-research.component.css']
})
export class StocksearchResearchComponent implements OnInit {
  StockSearchHistories: StockSearchHistory[];
  constructor(private stocksearchService: StocksearchService) {}

  ngOnInit(): void {
      this.stockSearchList() ; 

  }

  private stockSearchList(){
    this.stocksearchService.getStockSearchList().subscribe(data => {
      this.StockSearchHistories = data;});
  }


}
