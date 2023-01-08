import { Component, OnInit } from '@angular/core';
import * as Highcharts from 'highcharts';
declare var require: any;
@Component({
  selector: 'app-courbes',
  templateUrl: './courbes.component.html',
  styleUrls: ['./courbes.component.scss']
})
export class CourbesComponent implements OnInit {
  public options: any = {
    Chart: {
      type: 'area',
      height: 700
    },
    title: {
      text: 'Evolution du prix'
    },
    credits: {
      enabled: false
    },
    xAxis: {
      categories: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet'],
      tickmarkPlacement: 'on',
      title: {
          enabled: false
      }
  },
    series: [{
      name: 'Asia',
      data: [502, 635, 809, 947, 1402, 3634, 5268]
  }, {
      name: 'Europe',
      data: [163, 203, 276, 408, 547, 729, 628]
  }, {
      name: 'America',
      data: [18, 31, 54, 156, 339, 818, 1201]
  }]
  }
  constructor() { }
  ngOnInit() {
    Highcharts.chart('container', this.options);
  }
}