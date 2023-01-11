import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StockSearchBetweenDateComponent } from './stock-search-between-date.component';

describe('StockSearchBetweenDateComponent', () => {
  let component: StockSearchBetweenDateComponent;
  let fixture: ComponentFixture<StockSearchBetweenDateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StockSearchBetweenDateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StockSearchBetweenDateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
