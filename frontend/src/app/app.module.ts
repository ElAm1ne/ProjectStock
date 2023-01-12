import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { StocksearchResearchComponent } from './stocksearch-research/stocksearch-research.component';
import { CreateStockComponent } from './create-stock/create-stock.component';
import { FormsModule } from '@angular/forms';
import { CandlestickComponent } from './candlestick/candlestick.component';
import { NgApexchartsModule } from "ng-apexcharts";
import { SearchComponent } from './search/search.component';
import { DatePickerComponent } from './date-picker/date-picker.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatDatepickerModule} from '@angular/material/datepicker'
import {MatButtonModule} from '@angular/material/button'
import {MatFormFieldModule} from '@angular/material/form-field'
import {MatInputModule} from '@angular/material/input'
import { MatNativeDateModule } from '@angular/material/core';
import { StockSearchBetweenDateComponent } from './stock-search-between-date/stock-search-between-date.component';
import { AuditStockComponent } from './audit-stock/audit-stock.component';
import { HomeComponent } from './home/home.component';
import { TreemapComponent } from './treemap/treemap.component';
import { BacktestComponent } from './backtest/backtest.component';
import { KeyValuePipe } from '@angular/common';
import { OrderPipe } from 'ngx-order-pipe';
import { OrderModule } from 'ngx-order-pipe';
import { Candlestick2Component } from './candlestick2/candlestick2.component';
import { BacktestcandleComponent } from './backtestcandle/backtestcandle.component';


@NgModule({
  declarations: [
    AppComponent,
    StocksearchResearchComponent,
    CreateStockComponent,
    CandlestickComponent,
    SearchComponent,
    DatePickerComponent,
    StockSearchBetweenDateComponent,
    AuditStockComponent,
    HomeComponent,
    TreemapComponent,
    BacktestComponent,
    Candlestick2Component,
    BacktestcandleComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule, NgApexchartsModule, BrowserAnimationsModule,MatDatepickerModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatNativeDateModule,
    OrderModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
