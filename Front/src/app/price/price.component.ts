import { Component, Input, OnInit } from '@angular/core';
import { price } from '../model/price.model';

@Component({
  selector: 'app-price',
  templateUrl: './price.component.html',
  styleUrls: ['./price.component.scss']
})
export class PriceComponent implements OnInit{
  @Input()  prices!:price;
  
  titre !: string;
  prixj !: number;
  prixv !: number;
  vue!: number;
  ngOnInit(){
    this.titre= "Euros";
    this.prixj=10;
    this.prixv=10;
    this.vue=0;
  }
  augmentevue(){
    this.vue++
  
  }}