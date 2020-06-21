import React, { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Container, Row, Col, Button } from 'react-bootstrap'
import PaginationContainer from '../Pagination/PaginationContainer';
import CommentsComponent from '../../components/Comment/CommentsComponent';
import EndUsersComponent from '../../components/Users/EndUsersComponent';
import { commentsSelector } from '../../store/ad/selectors';
import { fetchComments, approvedComment } from '../../store/ad/actions';
import SpinnerContainer from '../Common/SpinnerContainer';

const CommentsContainer = () => {
    const dispatch = useDispatch();
    const comments = useSelector(commentsSelector);


    useEffect(() => {
        dispatch(fetchComments());   
    }, []);

    const handleApproved = (id) => {
        console.log("id komentara "+id)
        dispatch(
            approvedComment({
                "id": id
            })
        );
        
    };
    const getComments = () =>{
        let list =[];
        if(comments.isFetch){
            comments.data.map((comment) => {
                let ss = comment.creationDate.substring(0, 10);
                let ss2 = comment.creationDate.substring(11, 16);
                ss = ss2 + " " + ss;
                list.push(
                    <tr key={comment.id}>
                        <td>{ss}</td>
                        <td>{comment.publisherUserFirstName + ' ' + comment.publisherUserLastName}</td>
                        <td>{comment.content}</td>
                        <td>
                            <Button variant="outline-success" 
                            onClick={() => { handleApproved(comment.id); }}
                            >Odobri</Button>
                        </td>
                    </tr>
                );
            })
        }
        return list;
    }

   

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
                        <CommentsComponent getComments={getComments} /> 
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
