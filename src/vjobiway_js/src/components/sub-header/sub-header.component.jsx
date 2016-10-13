import React, { PropTypes } from 'react';
import styles from './sub-header.component.scss';

export function SubHeaderComponent() {
  return (
    <div className={styles['sub-header']}>
      <div className={styles['sub-header__text']}>
        <span>Find your job</span>
      </div>
      <div className={styles['sub-header__form']}>
        <form>
          <select>
            <option>Grodno</option>
            <option>Minsk</option>
            <option>Gomel</option>
          </select>
          <select>
            <option>Design</option>
            <option>Programming</option>
          </select>
          <button>Search</button>
        </form>
      </div>
    </div>
  );
}

