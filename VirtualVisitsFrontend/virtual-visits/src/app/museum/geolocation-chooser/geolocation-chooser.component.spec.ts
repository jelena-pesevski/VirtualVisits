import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GeolocationChooserComponent } from './geolocation-chooser.component';

describe('GeolocationChooserComponent', () => {
  let component: GeolocationChooserComponent;
  let fixture: ComponentFixture<GeolocationChooserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GeolocationChooserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GeolocationChooserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
