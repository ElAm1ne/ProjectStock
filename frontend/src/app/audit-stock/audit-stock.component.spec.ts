import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuditStockComponent } from './audit-stock.component';

describe('AuditStockComponent', () => {
  let component: AuditStockComponent;
  let fixture: ComponentFixture<AuditStockComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AuditStockComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AuditStockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
