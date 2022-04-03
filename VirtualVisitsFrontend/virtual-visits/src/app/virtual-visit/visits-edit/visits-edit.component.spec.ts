import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VisitsEditComponent } from './visits-edit.component';

describe('VisitsEditComponent', () => {
  let component: VisitsEditComponent;
  let fixture: ComponentFixture<VisitsEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VisitsEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VisitsEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
