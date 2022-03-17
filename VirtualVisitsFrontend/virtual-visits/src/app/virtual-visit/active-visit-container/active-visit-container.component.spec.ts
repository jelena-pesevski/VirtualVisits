import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActiveVisitContainerComponent } from './active-visit-container.component';

describe('ActiveVisitContainerComponent', () => {
  let component: ActiveVisitContainerComponent;
  let fixture: ComponentFixture<ActiveVisitContainerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActiveVisitContainerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ActiveVisitContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
