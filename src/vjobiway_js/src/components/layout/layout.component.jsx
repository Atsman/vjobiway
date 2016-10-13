import React, { PropTypes } from 'react';
import { HeaderComponent } from '../header';
import { FooterComponent } from '../footer';
import style from './layout.component.scss';

export function LayoutComponent(props) {
  return (
    <div className={style.layout}>
      <HeaderComponent />
      {props.children}
      <FooterComponent />
    </div>
  );
}

LayoutComponent.propTypes = {
  children: PropTypes.element,
};
