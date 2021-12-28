import React from 'react';
import { Layout } from 'antd';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Orders from './orderspage/Orders';
import Menu from './Menu';
import Teams from './teamspage/Teams';


const { Header, Content } = Layout;


function Admin() {
  return (
    <Router>
      <Layout className="adminlayout">
        <Header style={{ position: 'fixed', zIndex: 1, width: '100%' }}>
          <Menu />
        </Header>
        <Content className="admincontent" style={{ padding: '0 20px', marginTop: 80 }}>
          <Switch>
            <Route path="/admin/orders">
              <Orders />
            </Route>
            <Route path="/admin/teams">
              <Teams />
            </Route>
            <Route path="/admin/users">
              Users Mangement
            </Route>
          </Switch>
        </Content>
      </Layout>      
    </Router>
  );
}

export default Admin;
