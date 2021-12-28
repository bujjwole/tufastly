import React from 'react';
import { Menu } from 'antd';
import { Link } from 'react-router-dom';

function UserViewHeader(props) {
  const { handleClick } = props;

  return (
    <div className="container-fluid">
      <div className="header">
        <div className="logo">
          <i className="fas fa-shipping-fast"></i>
          <i className="text">TuFastly</i>
        </div>
        <Menu className="userviewmenu" theme="dark" mode="horizontal" defaultSelectedKeys={['HOME']}>

        <Menu.Item key="shipping" onClick={handleClick}>
            <Link to="/user/shipping">
              <span>SHIPPING</span>
            </Link>
          </Menu.Item>

          <Menu.Item key="order_history" onClick={handleClick}>
            <Link to="/user/orders">
              <span>ORDER HISTORY</span>
            </Link>
          </Menu.Item>

          <Menu.Item key="my_profile" onClick={handleClick}>
            <Link to="/user/profile">
              <span>MY PROFILE</span>
            </Link>
          </Menu.Item>

          <Menu.Item key="logout" onClick={handleClick}>
            <Link to="/home">
              <span>LOG OUT</span>
            </Link>
          </Menu.Item>
        </Menu>
      </div>
    </div>
  );
}
export default UserViewHeader;