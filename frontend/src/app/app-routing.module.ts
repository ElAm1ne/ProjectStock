import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StocksearchResearchComponent } from './stocksearch-research/stocksearch-research.component';

const routes: Routes = [
  {path: 'StockSearchHistories', component: StocksearchResearchComponent},
  {path: '', redirectTo: "StockSearchHistories", pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
