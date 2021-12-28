import React from 'react';
import { Carousel } from 'antd';
const items = [
    {
      key: '1',
      title: 'Robot & Drone Delivery ',
      content: 'We are a logistics company that uses ground robot assistants and drones to help San Francisco users deliver small and medium-sized items.',
    },
    {
      key: '2',
      title: '机器人/无人机快递服务',
      content: '我们是一家使用地面机器人和无人机帮助旧金山市内用户递送中小型规格物件的物流公司。',
    },
  ]
  function AppHero() {
    return (
      <div  className="heroBlock">
        <Carousel>
          {items.map(item => {
            return (
              <div key={item.key} className="container-fluid">
                <div className="content">
                  <h3>{item.title}</h3>
                  <p>{item.content}</p>
                </div>
              </div>  
            );
          })}
        </Carousel>
      </div>
    );
  }
  export default AppHero;