import React from 'react';
import { Table, Tag } from 'antd';

const columns = [
    {
        title: 'Order Number',
        dataIndex: 'orderNumber',
        key: 'orderNumber',
        width: '10%',
        defaultSortOrder: 'descend',
        sorter: (a, b) => a.orderNumber - b.orderNumber,
    },
    {
        title: 'Date',
        dataIndex: 'date',
        key: 'date',
        width: '15%',
        defaultSortOrder: 'descend',
    },
    {
        title: 'Pickup Address',
        dataIndex: 'pickupaddress',
        key: 'pickupaddress',
    },
    {
        title: 'Delivery Address',
        dataIndex: 'deliveryaddress',
        key: 'deliveryaddress',
    },
    {
        title: 'Type',
        key: 'type',
        dataIndex: 'type',
        width: '10%',
        filters: [
            { text: 'Robot', value: 'robot' },
            { text: 'Drone', value: 'drone' },
        ],
        // specify the condition of filtering result
        // here is that finding the method equals to `value`
        filterMultiple: false,
        onFilter: (value, record) => record.type.indexOf(value) === 0,

        // format tag
        render: type => (
            <>
                {type.map(e => {
                    let color = 'black';
                    if (e === 'robot') {
                        color = 'geekblue';
                    } else if (e === 'drone') {
                        color = 'orange';
                    }
                    return (
                        <Tag color={color} key={e}>
                            {e.toUpperCase()}
                        </Tag>
                    );
                })}
            </>
        ),
    },
    {
        title: 'Price (USD)',
        dataIndex: 'price',
        key: 'price',
        width: '12%',
        sorter: (a, b) => a.revenue - b.revenue,
    },
];

class HistoryDetails extends React.Component {
    constructor(props) {
        super(props);
        this.state = { details: [] };
        this.getDetails = this.getDetails.bind(this);
        this.onChange = this.onChange.bind(this);
    }

    // fetch order details json from backend
    getDetails() {
        // fetch( 'http://127.0.0.1:8080/tufastly/admin/orders',
        // {
        //     method: 'GET',
        //     headers: {
        //         'Access-Control-Allow-Origin': '*',
        //         'Content-Type': 'application/json'
        //     },
        //     mode: 'cors',
        // })
        fetch('/user/fakeorders.json')
            .then(response => response.json())
            .then(data => {
                this.setState({
                    details: data.details.map(detail => ({
                        orderNumber: detail.orderNumber,
                        date: detail.date,
                        name: detail.name,
                        pickupaddress: detail.pickupaddress,
                        deliveryaddress: detail.deliveryaddress,
                        type: detail.type,
                        price: detail.price
                    }))
                })
            })
            .catch((error) => {
                console.error('error', error)
            })
    }

    // Handle sorters and filters
    onChange(sorter, filters) {
        console.log('params', sorter, filters);
    }

    // call getDetails()
    componentDidMount() {
        this.getDetails();
    };

    render() {
        return (
            <div className='details'>
                <Table
                    columns={columns}
                    dataSource={this.state.details}
                    onChange={this.state.onChange}
                    pagination={{ pageSize: 12 }}
                />
            </div>
        );
    }
}

export default HistoryDetails;