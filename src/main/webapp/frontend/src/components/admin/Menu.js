import React from 'react';
import { Menu } from 'antd';
import { Link } from 'react-router-dom';

const Menus = (props) => {
  const { handleClick } = props;
  return (
    <div className="adminheader">
      <div className="logo">
        <i className="fas fa-shipping-fast"></i>
        <i className="text">TuFastly</i>
      </div>
      <div className='title'>
        Admin Management
      </div>
      <div className="adminmenu">
        <Menu className="menu" theme="dark" mode="horizontal" defaultSelectedKeys={['1']}>
          <Menu.Item key="1" onClick={handleClick}>
            <Link to="/admin/orders">
              <span>Orders</span>
            </Link>
          </Menu.Item>
          <Menu.Item key="2" onClick={handleClick}>
            <Link to="/admin/teams">
              <span>Teams</span>
            </Link>
          </Menu.Item>
          <Menu.Item key="3" onClick={handleClick}>
            <Link to="/admin/users">
              <span>Users</span>
            </Link>
          </Menu.Item>
        </Menu>
      </div>
    </div>
  );
}

export default Menus;