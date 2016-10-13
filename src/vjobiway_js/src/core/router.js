import { testHistory } from './feature-detector';
import { replace } from './str.utils';
import { flow } from './fn.utils';

function compilePattern(pathPattern) {
  return `^${pathPattern.replace(/\//g, '\\/').replace(/:.+/g, '(.+)')}$`;
}

function patchHistory() {
  const pushState = history.pushState;
  history.pushState = function pushStateWrapper(state, ...args) {
    if (typeof history.onpushstate === 'function') {
      history.onpushstate({ state });
    }
    return pushState.apply(history, [state, ...args]);
  };
}

const clearLastSlash = str => replace(str, /^\//, '');

const clearSlashes = flow(
  clearLastSlash,
  str => replace(str, /\/$/, ''),
);

export const Router = (() => {
  const routes = [];
  let mode = 'hash';
  let root = '/';
  let interval;

  patchHistory();

  function setMode(_mode) {
    if (_mode === 'history' && testHistory()) {
      mode = 'history';
    } else {
      mode = 'hash';
    }
  }

  function config(options) {
    if (!options) {
      throw new Error('options must be provided');
    }

    if (options.mode && options.mode !== mode) {
      setMode(options.mode);
    }

    if (options.root && options.root !== root) {
      root = `/${clearSlashes(options.root)}`;
    }
    return this;
  }

  function getCurrentRoute() {
    let fragment = '';
    if (mode === 'history') {
      fragment = decodeURI(location.pathname);
      if (root !== '/') {
        fragment = fragment.replace(root, '');
      }
    } else {
      const match = window.location.href.match(/#(.*)$/);
      fragment = match ? match[1] : '';
    }
    return fragment;
  }

  function add(re, handler) {
    routes.push({ re: compilePattern(re), handler });
    return this;
  }

  function check(f) {
    const fragment = f || getCurrentRoute();
    for (let i = 0; i < routes.length; i += 1) {
      const match = fragment.match(routes[i].re);
      if (match) {
        match.shift();
        routes[i].handler.apply({}, match);
        return this;
      }
    }
    return this;
  }

  function isActive(path) {
    return clearSlashes(path) === clearSlashes(getCurrentRoute());
  }

  function navigate(path) {
    if (mode === 'history') {
      const p = path === root ? root : `${clearLastSlash(root)}/${clearSlashes(path)}`;
      history.pushState({ path: p }, null, p);
    }
  }

  function listen() {
    history.onpushstate = function onPushState({ state }) {
      console.log('state change', state);
      if (getCurrentRoute() !== state.path) {
        check(state.path);
      }
    };
    navigate(getCurrentRoute());
    check(getCurrentRoute());
    return this;
  }

  return {
    setMode,
    config,
    getCurrentRoute,
    add,
    check,
    isActive,
    listen,
    navigate,
  };
})();

