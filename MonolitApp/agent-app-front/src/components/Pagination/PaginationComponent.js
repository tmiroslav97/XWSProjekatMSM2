import React from 'react';
import { Row, Col, Pagination } from 'react-bootstrap'

const PaginationComponent = (props) => {

    return (
        <Row>
            <Col md={12} xs={12}>
                <Pagination onClick={props.onClick} className="pagination mb-5">
                    <Pagination.First />
                    <Pagination.Prev />
                    {props.items}
                    <Pagination.Next />
                    <Pagination.Last />
                </Pagination>
            </Col>
        </Row>
    );
}

export default PaginationComponent;
