import { qs } from './dom';

function initializeComponent() {
  if (this.getInitialState) {
    this.state = this.getInitialState();
  }
  this.renderToEl();
}

function ensureElement() {
  if (!this.el) {
    this.el = qs(this.selector);
    if (!this.el) {
      throw new Error('element not found, selector: ', this.selector);
    }
  }
}

const ComponentPrototype = {
  setState(state) {
    if (this.state !== state) {
      this.state = state;
      this.renderToEl();
    }
  },
  initialize() {},
  renderToEl() {
    this.el.innerHTML = this.render();
    if (this.afterRender) {
      this.afterRender();
    }
  },
  render() { return ''; },
};

export function createComponent(options) {
  function constructor(data) {
    this.options = options;
    ensureElement.apply(this);
    initializeComponent.apply(this);
    this.initialize(Object.assign({}, options, data));
  }
  constructor.prototype = Object.assign({}, ComponentPrototype, options);
  return constructor;
}

/*

export const Component = createComponent({
  selector: 'component-container',

  initialize() {
  },

  render() {
  }
});

*/
