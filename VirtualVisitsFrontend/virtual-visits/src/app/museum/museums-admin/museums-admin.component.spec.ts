import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MuseumsAdminComponent } from './museums-admin.component';

describe('MuseumsAdminComponent', () => {
  let component: MuseumsAdminComponent;
  let fixture: ComponentFixture<MuseumsAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MuseumsAdminComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MuseumsAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
