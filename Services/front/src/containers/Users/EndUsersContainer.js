import React, { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Container, Row, Col, Button } from 'react-bootstrap'
import PaginationContainer from '../Pagination/PaginationContainer';
import PaginationSize from '../../components/Pagination/PaginationSize';
import EndUsersComponent from '../../components/Users/EndUsersComponent';
import { endUsersSelector } from '../../store/user/selectors';
import { fetchEndUsersPaginated, putEndUsers, blockOrUnblock, obligateOrUnobligate, logDelOrRevert } from '../../store/user/actions';
import SpinnerContainer from '../Common/SpinnerContainer';

const EndUsersContainer = () => {
    const dispatch = useDispatch();
    const endUsers = useSelector(endUsersSelector);
    const [nextPage, setNextPage] = useState(endUsers.nextPage);
    const [size, setSize] = useState(endUsers.size);


    useEffect(() => {
        dispatch(
            fetchEndUsersPaginated({
                nextPage,
                size
            })
        );
        return () => {
            dispatch(putEndUsers({
                'data': [],
                'totalPageCnt': 0,
                'nextPage': nextPage,
                'size': size,
                'isFetch': false
            }));
        };
    }, [nextPage, size]);

    const handleBlock = (id) => {
        dispatch(
            blockOrUnblock({
                "id": id,
                "status": false
            })
        );
    };

    const handleUnblock = (id) => {
        dispatch(
            blockOrUnblock({
                "id": id,
                "status": true
            })
        );
    };

    const handleObligate = (id) => {
        dispatch(
            obligateOrUnobligate({
                "id": id,
                "status": true
            })
        );
    };

    const handleUnObligate = (id) => {
        dispatch(
            obligateOrUnobligate({
                "id": id,
                "status": false
            })
        );
    };

    const handleLogDelete = (id) => {
        dispatch(
            logDelOrRevert({
                "id": id,
                "status": true
            })
        );
    };

    const handleRevert = (id) => {
        dispatch(
            logDelOrRevert({
                "id": id,
                "status": false
            })
        );
    };

    return (
        <Container>
            <Row>
                <Col md={12} xs={12}>
                    <h2 className="border-bottom">Lista krajnjih korisnika</h2>
                </Col>
            </Row>
            <Row>
                <Col md={12} xs={12}>
                    <PaginationSize size={size} setSize={setSize} />
                </Col>
            </Row>
            <Row>
                <Col md={12} xs={12}>
                    {
                        endUsers.isFetch ? <EndUsersComponent endUsers={endUsers.data} handleBlock={handleBlock} handleUnblock={handleUnblock} handleObligate={handleObligate} handleUnObligate={handleUnObligate} handleLogDelete={handleLogDelete} handleRevert={handleRevert} /> : <SpinnerContainer />
                    }
                </Col>
            </Row>
            <Row>
                <Col md={12} xs={12}>
                    <PaginationContainer setNextPage={setNextPage} totalPageCnt={endUsers.totalPageCnt} nextPage={nextPage} />
                </Col>
            </Row>
        </Container >
    );
}

export default EndUsersContainer;
