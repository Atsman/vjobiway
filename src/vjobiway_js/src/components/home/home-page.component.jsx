import React from 'react';
import { LayoutComponent } from '../layout';
import { SubHeaderComponent } from '../sub-header';

export function HomePageComponent() {
  return (
    <LayoutComponent>
      <div>
        <SubHeaderComponent />
        <div>Home page</div>
      </div>
    </LayoutComponent>
  );
}
