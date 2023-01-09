import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StocksearchResearchComponent } from './stocksearch-research.component';

describe('StocksearchResearchComponent', () => {
  let component: StocksearchResearchComponent;
  let fixture: ComponentFixture<StocksearchResearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StocksearchResearchComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StocksearchResearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
