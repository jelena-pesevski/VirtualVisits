import { TestBed } from '@angular/core/testing';

import { VirtualVisitService } from './virtual-visit.service';

describe('VirtualVisitService', () => {
  let service: VirtualVisitService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VirtualVisitService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
