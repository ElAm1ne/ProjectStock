import { Component, OnInit } from '@angular/core';
import { price } from './model/price.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  myprice !: price;
  myprice2!:price;
  ngOnInit(){
    this.myprice= new price('USD',20,10)
    this.myprice2=new price('EUR',40,50) 
  }
  
}
