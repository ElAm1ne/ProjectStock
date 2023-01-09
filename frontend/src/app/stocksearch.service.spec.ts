import { TestBed } from '@angular/core/testing';

import { StocksearchService } from './stocksearch.service';

describe('StocksearchService', () => {
  let service: StocksearchService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StocksearchService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
