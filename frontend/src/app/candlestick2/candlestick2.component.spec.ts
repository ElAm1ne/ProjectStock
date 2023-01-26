import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Candlestick2Component } from './candlestick2.component';

describe('Candlestick2Component', () => {
  let component: Candlestick2Component;
  let fixture: ComponentFixture<Candlestick2Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Candlestick2Component ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Candlestick2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
