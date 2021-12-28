import React, { PureComponent } from 'react';
import {
    ComposedChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, Legend, Line,
} from 'recharts';

const data = [
    {
        date: '2020/01', revenue: 3000, profit: 2400, amt: 2400,
    },
    {
        date: '2020/02', revenue: 3000, profit: 1398, amt: 2210,
    },
    {
        date: '2020/03', revenue: 2020, profit: 800, amt: 2290,
    },
    {
        date: '2020/04', revenue: 2780, profit: 1908, amt: 2020,
    },
    {
        date: '2020/05', revenue: 3890, profit: 1800, amt: 2181,
    },
    {
        date: '2020/06', revenue: 3390, profit: 1800, amt: 2500,
    },
    {
        date: '2020/07', revenue: 3490, profit: 1300, amt: 2100,
    },
    {
        date: '2020/08', revenue: 4000, profit: 2400, amt: 2400,
    },
    {
        date: '2020/09', revenue: 3000, profit: 1398, amt: 2210,
    },
    {
        date: '2020/10', revenue: 2020, profit: 1200, amt: 2290,
    },
    {
        date: '2020/11', revenue: 2780, profit: 1908, amt: 2020,
    },
    {
        date: '2020/12', revenue: 0, profit: 0, amt: 2181,
    },
];

const months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];


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
export default class Example extends PureComponent {

    render() {
        return (
            <ComposedChart
                width={600}
                height={350}
                data={data}
                margin={{
                    top: 20, right: 10, left: 5, bottom: 5,
                }}
            >
                <CartesianGrid strokeDasharray="3 3" />
                
                <XAxis dataKey="date" tickFormatter={monthTickFormatter}/>   
                <XAxis dataKey="date" axisLine={false} tickLine={false} interval={0} tick={renderQuarterTick} height={1} scale="band" xAxisId="quarter" />
                <YAxis label={{ value: 'Income', angle: -90, position: 'insideLeft' }} />
                <Tooltip />
                <Legend verticalAlign="top" height={36} />
                <Bar barSize={20} dataKey="revenue" fill="#4ca3dd" />
                <Line type="linear" dot={false} dataKey="profit" strokeWidth="4" stroke="#ff7300" />
            </ComposedChart>
        );
    }
}
