import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BacktestcandleComponent } from './backtestcandle.component';

describe('BacktestcandleComponent', () => {
  let component: BacktestcandleComponent;
  let fixture: ComponentFixture<BacktestcandleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BacktestcandleComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BacktestcandleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
