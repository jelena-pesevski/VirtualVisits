import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActiveVisitsContainerComponent } from './active-visits-container.component';

describe('ActiveVisitsContainerComponent', () => {
  let component: ActiveVisitsContainerComponent;
  let fixture: ComponentFixture<ActiveVisitsContainerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActiveVisitsContainerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ActiveVisitsContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
