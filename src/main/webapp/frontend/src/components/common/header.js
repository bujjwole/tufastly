import React from 'react';
import { Menu } from 'antd';
import { Link } from 'react-router-dom';

function AppHeader(props) {
  const { handleClick } = props;

  return (
    <div className="container-fluid">
      <div className="header">
        <div className="logo">
          <i className="fas fa-shipping-fast"></i>
          <i className="text">TuFastly</i>
        </div>
        <Menu className="homemenu" theme="dark" mode="horizontal" defaultSelectedKeys={['HOME']}>
          <Menu.Item key="HOME" onClick={handleClick}>
            <Link to="/home">
              <span>HOME</span>
            </Link>
          </Menu.Item>

          <Menu.Item key="ADMIN" onClick={handleClick}>
            <Link to="/admin/orders">
              <span>ADMIN</span>
            </Link>
          </Menu.Item>
          <Menu.Item key="LOGIN" onClick={handleClick}>
            <Link to="/user/shipping">
              <span>LOG IN</span>
            </Link>
          </Menu.Item>
        </Menu>
      </div>
    </div>
  );
}
export default AppHeader;