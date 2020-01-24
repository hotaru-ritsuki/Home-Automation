import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardGraphicsComponent } from './dashboard-graphics.component';

describe('DashboardGraphicsComponent', () => {
  let component: DashboardGraphicsComponent;
  let fixture: ComponentFixture<DashboardGraphicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DashboardGraphicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DashboardGraphicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
