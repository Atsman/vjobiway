export function byId(id, container = document) {
  return container.getElementById(id);
}

export function byClassName(className, container = document) {
  return [...container.getElementsByClassName(className)];
}

export function qs(selector, container = document) {
  return container.querySelector(selector);
}
