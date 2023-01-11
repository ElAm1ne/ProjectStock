import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateStockComponent } from './create-stock/create-stock.component';
import { StocksearchResearchComponent } from './stocksearch-research/stocksearch-research.component';

const routes: Routes = [
  {path: 'StockSearchHistories', component: StocksearchResearchComponent},
  {path: '', redirectTo: "StockSearchHistories", pathMatch: 'full'}, 
  {path: 'stockSearchHistory', component: CreateStockComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
