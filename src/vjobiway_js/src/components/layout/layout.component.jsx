import React, { PropTypes } from 'react';
import { HeaderComponent } from '../header';
import style from './layout.component.scss';

export function LayoutComponent(props) {
  return (
    <div className={style.layout}>
      <HeaderComponent />
      {props.children}
    </div>
  );
}

LayoutComponent.propTypes = {
  children: PropTypes.element,
};
