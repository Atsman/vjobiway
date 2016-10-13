export function flow(...fns) {
  return fns.reduce((prev, next) => (...args) => next(prev.apply({}, args)));
}

