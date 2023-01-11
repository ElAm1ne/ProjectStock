import { Component ,OnInit } from '@angular/core';
import { StockSearchHistory } from '../stock-search-history';
@Component({
  selector: 'app-create-stock',
  templateUrl: './create-stock.component.html',
  styleUrls: ['./create-stock.component.css']
})
export class CreateStockComponent implements OnInit {
onSubmit() {
throw new Error('Method not implemented.');
}
  stockSearchHistory: StockSearchHistory = new  StockSearchHistory()
  constructor() {}
  ngOnInit() : void {}

}
