import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MuseumEditComponent } from './museum-edit.component';

describe('MuseumEditComponent', () => {
  let component: MuseumEditComponent;
  let fixture: ComponentFixture<MuseumEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MuseumEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MuseumEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
