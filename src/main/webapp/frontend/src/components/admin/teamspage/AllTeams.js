import React from 'react';
import { Table, Tag } from 'antd';

const columns= [
    {
        title: 'ID',
        dataIndex: 'id',
        key: 'id',
        width: '8%',
        defaultSortOrder: 'ascend',
        sorter: (a, b) => a.id - b.id,
    },
    {
        title: 'Center',
        dataIndex: 'center',
        key: 'center',
        // width: '10%',
        filters: [
            { text: 'A', value: 'A' },
            { text: 'B', value: 'B' },
            { text: 'C', value: 'C' },
        ],
        filterMultiple: true,
        onFilter: (value, record) => record.center.indexOf(value) === 0,
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
                    if (e === 'Robot') {
                        color = 'geekblue';
                    } else if (e === 'Drone') {
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
        title: 'Status',
        key: 'status',
        dataIndex: 'status',
        width: '10%',
        filters: [
            { text: 'Charging', value: 'Charging' },
            { text: 'Away', value: 'Away' },
        ],
        // specify the condition of filtering result
        // here is that finding the method equals to `value`
        filterMultiple: false,
        onFilter: (value, record) => record.status.indexOf(value) === 0,

        // format tag
        render: status => (
            <>
                {status.map(e => {
                    let color = 'black';
                    if (e === 'Charging') {
                        color = 'red';
                    } else if (e === 'Away') {
                        color = 'green';
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
        title: 'Order Number',
        dataIndex: 'ordernumber',
        key: 'ordernumber',
        width: '10%',
        defaultSortOrder: 'ascend',
        // sorter: (a, b) => a.ordernumber - b.ordernumber,
    },
];

class AllTeams extends React.Component {
    constructor(props) {
        super(props);
        this.state = { details: [] };
        this.getDetails = this.getDetails.bind(this);
        this.onChange = this.onChange.bind(this);
    }

    // fetch order details json from backend
    getDetails() {
        fetch( 'http://127.0.0.1:8080/tufastly/admin/teams',
        {
            method: 'GET',
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/json'
            },
            mode: 'cors',
        })
        // fetch('./faketeams.json')
            .then(response => response.json())
            .then(data => {
                this.setState({
                    details: data.details.map(detail => ({
                        id: detail.id,
                        center: detail.center,
                        type: detail.type,
                        status: detail.status,
                        ordernumber: detail.ordernumber
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
                    pagination={{ pageSize: 8 }}
                />
            </div>
        );
    }
}

export default AllTeams;