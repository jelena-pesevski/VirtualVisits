import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MuseumDetailsContainerComponent } from './museum-details-container.component';

describe('MuseumDetailsContainerComponent', () => {
  let component: MuseumDetailsContainerComponent;
  let fixture: ComponentFixture<MuseumDetailsContainerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MuseumDetailsContainerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MuseumDetailsContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
