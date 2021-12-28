import React, { PureComponent } from 'react';
import {
  AreaChart, Area, XAxis, YAxis, CartesianGrid, Tooltip, Legend,
} from 'recharts';

const data = [
  {
    date: '2020/01', robot: 30, drone: 24, amt: 2400,
  },
  {
    date: '2020/02', robot: 30, drone: 13, amt: 2210,
  },
  {
    date: '2020/03', robot: 20, drone: 8, amt: 2290,
  },
  {
    date: '2020/04', robot: 27, drone: 19, amt: 2020,
  },
  {
    date: '2020/05', robot: 38, drone: 18, amt: 2181,
  },
  {
    date: '2020/06', robot: 33, drone: 18, amt: 2500,
  },
  {
    date: '2020/07', robot: 30, drone: 13, amt: 2100,
  },
  {
    date: '2020/08', robot: 40, drone: 24, amt: 2400,
  },
  {
    date: '2020/09', robot: 30, drone: 13, amt: 2210,
  },
  {
    date: '2020/10', robot: 20, drone: 10, amt: 2290,
  },
  {
    date: '2020/11', robot: 28, drone: 19, amt: 2020,
  },
  {
    date: '2020/12', robot: 0, drone: 0, amt: 2181,
  },
];

const months = [["Jan"], ["Feb"], ["Mar"], ["Apr"], ["May"], ["Jun"], ["Jul"], ["Aug"], ["Sep"], ["Oct"], ["Nov"], ["Dec"]];

// Formart tick on x axis month
const monthTickFormatter = (tick) => {
  const date = new Date(tick);

  return date.getMonth() + 1;
};

// Format quarter tick
const renderQuarterTick = (tickProps) => {
  const { x, y, payload } = tickProps;
  const { value, offset } = payload;
  const date = new Date(value);
  const month = date.getMonth();
  const quarterNo = Math.floor(month / 3) + 1;

  // Format quarter label
  if (month % 3 === 1) {
      return <text x={x + offset} y={y - 4} textAnchor="middle">{`Q${quarterNo}`}</text>;
  }

  const isLast = month === 12;

  // Format red divider between quater labels
  if (month != 0 && month % 3 === 0 || isLast) {
      const pathX = Math.floor(isLast ? x + offset * 2 : x) + 0.5;

      return <path d={`M${pathX},${y - 4}v${-35}`} stroke="red" />;
  }
  return null;
};

export default class OrdersChart extends PureComponent {

  render() {
    return (
      <AreaChart
        width={600}
        height={350}
        data={data}
        margin={{
          top: 30, right: 0, left: 20, bottom: 5,
        }}
      >
        <CartesianGrid strokeDasharray="3 3" />
        <XAxis dataKey="date" tickFormatter={monthTickFormatter} />
        <XAxis dataKey="date" axisLine={false} tickLine={false} interval={0} tick={renderQuarterTick} height={1} scale="band" xAxisId="quarter" />
        <YAxis label={{ value: 'Order', angle: -90, position: 'insideLeft' }} />
        <Tooltip />
        <Area type="linear" dataKey="drone" stackId="1" stroke="#3954E2" fill="#B2C5FA" />
        <Area type="linear" dataKey="robot" stackId="1" stroke="#EB913A" fill="#F8D79A" />
        <Legend verticalAlign="top" height={36} />
      </AreaChart>
    );
  }
}