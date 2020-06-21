import React, { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Container, Row, Col, Button } from 'react-bootstrap'
import PaginationContainer from '../Pagination/PaginationContainer';
import CommentsComponent from '../../components/Comment/CommentsComponent';
import EndUsersComponent from '../../components/Users/EndUsersComponent';
import { commentsSelector } from '../../store/ad/selectors';
import { fetchComments } from '../../store/ad/actions';
import SpinnerContainer from '../Common/SpinnerContainer';

const CommentsContainer = () => {
    const dispatch = useDispatch();
    const comments = useSelector(commentsSelector);


    useEffect(() => {
        dispatch(fetchComments());   
    }, []);

    // const handleBlock = (id) => {
    //     dispatch(
    //         blockOrUnblock({
    //             "id": id,
    //             "status": false
    //         })
    //     );
    // };

   

    return (
        <Container>
            <Row>
                <Col md={12} xs={12}>
                    <h2 className="border-bottom">Lista neodobrenih komentara</h2>
                </Col>
            </Row>
            {/* <Row>
                <Col md={12} xs={12}>
                    <PaginationSize size={size} setSize={setSize} />
                </Col>
            </Row> */}
            <Row>
                <Col md={12} xs={12}>
                    {
                        comments.isFetch ? 
                        <CommentsComponent /> 
                        : <SpinnerContainer />
                    }
                </Col>
            </Row>
            {/* <Row>
                <Col md={12} xs={12}>
                    <PaginationContainer setNextPage={setNextPage} totalPageCnt={endUsers.totalPageCnt} nextPage={nextPage} />
                </Col>
            </Row> */}
        </Container >
    );
}

export default CommentsContainer;
