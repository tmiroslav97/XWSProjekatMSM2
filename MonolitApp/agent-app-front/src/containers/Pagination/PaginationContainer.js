import React from 'react';
import { Pagination } from 'react-bootstrap'
import PaginationComponent from '../../components/Pagination/PaginationComponent';

const PaginationContainer = (props) => {

    let items = [];

    for (let i = 0; i < props.totalPageCnt; i++) {
        items.push(
            <Pagination.Item key={i} active={i === props.nextPage}>
                {i + 1}
            </Pagination.Item>
        );
    }


    const handlePagination = (event) => {
        event.preventDefault();
        let clicked = event.target.text;
        if (clicked !== undefined && props.totalPageCnt > 0) {
            if (clicked.includes('First')) {
                props.setNextPage(0);
            } else if (clicked.includes('Last')) {
                props.setNextPage(props.totalPageCnt - 1);
            } else if (clicked.includes('Next')) {
                if (props.nextPage < props.totalPageCnt - 1) {
                    props.setNextPage(props.nextPage + 1);
                }
            } else if (clicked.includes('Previous')) {
                if (props.nextPage > 0) {
                    props.setNextPage(props.nextPage - 1);
                }
            } else {
                props.setNextPage(clicked-1);
            }
        }
    }

    return (
        <PaginationComponent onClick={handlePagination} items={items}></PaginationComponent>
    );
}

export default PaginationContainer;
