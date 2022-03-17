import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VirtualVisitContainerComponent } from './virtual-visit-container.component';

describe('VirtualVisitContainerComponent', () => {
  let component: VirtualVisitContainerComponent;
  let fixture: ComponentFixture<VirtualVisitContainerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VirtualVisitContainerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VirtualVisitContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
