import React from 'react';
import styles from './footer.component.scss';

export function FooterComponent() {
  return (
    <div className={styles['footer-container']}>
      <footer className={styles.footer}>
        <div className={styles.footer__items}>
          <nav className={styles.footer_items_left}>
            <a className={styles.footer__item}>Terms</a>
            <a className={styles.footer__item}>Privacy</a>
            <a className={styles.footer__item}>Security</a>
          </nav>
          <nav className={styles.footer__items__right}>
            <a className={styles.footer__item}>Contact</a>
            <a className={styles.footer__item}>Blog</a>
            <a className={styles.footer__item}>About</a>
          </nav>
        </div>
      </footer>
    </div>
  );
}
