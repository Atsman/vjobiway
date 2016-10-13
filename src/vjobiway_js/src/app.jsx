import React from 'react';
import ReactDOM from 'react-dom';
import { createStore, combineReducers } from 'redux';
import { Provider } from 'react-redux';
import { Router, IndexRoute, Route, browserHistory } from 'react-router';
import { syncHistoryWithStore, routerReducer } from 'react-router-redux';
import { reducers } from './reducers';
import { HomePageComponent } from './components/home';
import './static/scss/index.scss';

const store = createStore(
  combineReducers({
    routing: routerReducer,
  })
);

const history = syncHistoryWithStore(browserHistory, store);

document.write('<div id="main"></div>');

function App(props) {
  return (
    <div className="app">
      {props.children}
    </div>
  );
}

ReactDOM.render(
  <Provider store={store}>
    <Router history={history}>
      <Router path="/" component={App}>
        <IndexRoute component={HomePageComponent} />
        {/* <Router path="/vacancies" component={VacanciesPageComponent} />
        <Router path="/companies" component={CompaniesPageComponent} />
        <Router path="/about" component={AboutPageComponent} /> */}
      </Router>
    </Router>
  </Provider>,
  document.getElementById('main'),
);
