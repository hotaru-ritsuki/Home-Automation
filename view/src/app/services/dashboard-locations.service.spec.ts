import { TestBed } from '@angular/core/testing';

import { DashboardLocationsService } from './dashboard-locations.service';

describe('DashboardLocationsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DashboardLocationsService = TestBed.get(DashboardLocationsService);
    expect(service).toBeTruthy();
  });
});
