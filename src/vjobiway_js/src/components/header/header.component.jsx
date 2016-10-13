import React, { PropTypes } from 'react';
import { Link, IndexLink } from 'react-router';
import style from './header.component.scss';

export function HeaderComponent() {
  return (
    <div className={style['header-container']}>
      <header className={style.header}>
        <div className={style.header__logo} />
        <nav className={style.header__nav}>
          <IndexLink className={style.header__item} activeClassName={style['header__item--active']} to={'/'}>Home</IndexLink>
          <Link className={style.header__item} activeClassName={style['header__item--active']} to={'/vacancies'}>Vacancies</Link>
          <Link className={style.header__item} activeClassName={style['header__item--active']} to={'/companies'}>Companies</Link>
          <Link className={style.header__item} activeClassName={style['header__item--active']} to={'/about'}>About</Link>
        </nav>
      </header>
    </div>
  );
}
