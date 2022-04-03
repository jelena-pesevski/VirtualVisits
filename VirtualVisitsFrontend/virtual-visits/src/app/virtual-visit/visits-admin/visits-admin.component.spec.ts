import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VisitsAdminComponent } from './visits-admin.component';

describe('VisitsAdminComponent', () => {
  let component: VisitsAdminComponent;
  let fixture: ComponentFixture<VisitsAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VisitsAdminComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VisitsAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
