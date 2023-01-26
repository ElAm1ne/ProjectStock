import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuditStockComponent } from './audit-stock/audit-stock.component';
import { BacktestComponent } from './backtest/backtest.component';
import { BacktestcandleComponent } from './backtestcandle/backtestcandle.component';
import { CreateStockComponent } from './create-stock/create-stock.component';
import { HomeComponent } from './home/home.component';
import { StockSearchBetweenDateComponent } from './stock-search-between-date/stock-search-between-date.component';
import { StocksearchResearchComponent } from './stocksearch-research/stocksearch-research.component';

const routes: Routes = [
  {path: 'StockSearchHistories', component: StocksearchResearchComponent},
 // {path: '', redirectTo: "StockSearchHistories", pathMatch: 'full'}, 
  {path: 'stockSearchHistory', component: CreateStockComponent},
  {path: 'stockBetweenDate', component: StockSearchBetweenDateComponent},
  {path: 'backtestCandle', component: BacktestcandleComponent},
  {path: "backTest", component: BacktestComponent},
  {path: 'auditStock', component: AuditStockComponent},
  {path: 'home', component: HomeComponent},
  {path: '', redirectTo: "home", pathMatch: 'full'}, 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }