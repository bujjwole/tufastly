import React from 'react';
import { Button } from 'antd';
import { Row, Col } from 'antd';

const items = [
    {
      key: '1',
      icon: <i className="fas fa-helicopter"></i>,
      title: 'DRONE DELIVERY',    
    },
    {
      key: '2',
      icon: <i className="fas fa-robot"></i>,
      title: 'ROBOT DELIVERY',
    },
  ]

function AppAbout() {
    return (
        <div className="block aboutBlock">
              <div className="container-fluid">
                 <div className="titleHolder">
                  <h2>About Us</h2>
                 </div>
     <Row gutter={[24, 16]}>
     {items.map(item => {
            return (
              <Col md={{ span: 12 }} key={item.key}>
                <div className="content">
                  <div className="icon">
                    {item.icon}
                  </div>
                  <h3>{item.title}</h3>
                  <p>{item.content}</p>
                  <div className="btnHolder">
                  <Button type="primary" size="large">Learn More</Button>
                </div>
                </div>
              </Col>
            );
          })}
        </Row>
      </div>
    </div>
    );
  }
export default AppAbout;