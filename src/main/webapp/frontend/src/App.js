import React from 'react';
import './App.css';
import 'antd/dist/antd.css';

import AppHeader from './components/common/header';
import AppHome from './views/home';
import Admin from './components/admin/Admin';

import { Layout } from 'antd';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import ShippingView from './views/shippingview';

const { Header, Content } = Layout;
function App() {
  return (
    <Router>
      <Layout className="mainLayout">
        <Switch>
          <Route path="/home">
            <Header>
              <AppHeader />
            </Header>
            <Content>
              <AppHome />
            </Content>
          </Route>
          <Route path='/admin/orders'>
            <Admin />
          </Route>
          <Route path='/user/shipping'>
            <ShippingView />
          </Route>
        </Switch>
      </Layout>
    </Router>
  );
}

export default App;

