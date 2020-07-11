import React, { useState, useEffect } from 'react';
import { Container, Row, Col, CardDeck, ListGroup, Tab, Nav, Card, Button } from 'react-bootstrap';
import GradeIcon from '@material-ui/icons/Grade';
import ListItemAvatar from '@material-ui/core/ListItemAvatar';
import Avatar from '@material-ui/core/Avatar';
import ConfirmationNumberIcon from '@material-ui/icons/ConfirmationNumber';
import CommentIcon from '@material-ui/icons/Comment';
import { useDispatch, useSelector } from 'react-redux';
import { adSelector } from '../store/ad/selectors';
import { tokenSelector } from '../store/user/selectors';
import jwt_decode from 'jwt-decode';
import { putAd, fetchBestGrade, fetchMaxMileage, fetchMaxComments } from '../store/ad/actions';
import AdsTableComponent from '../components/AgentFirm/AdsTableComponent';


const PanelHomeContainer = ({ match },) => {
    const token = useSelector(tokenSelector);
    const dispatch = useDispatch();
    const ad = useSelector(adSelector);
    const isFetchAd = ad.isFetch;
    const email = jwt_decode(token).sub;
    const [flag, setFlag] = useState(1);

    useEffect(() => {
        return () => {
            dispatch(putAd({
                'data': [],
                'isFetch': false
            }));
        };
    }, []);


    const handleGrade = () => {
        setFlag(1);
        dispatch(
            fetchBestGrade(email)
        );

    }

    const handleMileage  = () => {
        setFlag(2);
        dispatch(
            fetchMaxMileage(email)
        );
    }

    const handleComment  = () => {
        setFlag(3);
        dispatch(
            fetchMaxComments(email)
        );
    }
    const roles = jwt_decode(token).roles;

    return (
        
        <Container>
            
            <Row>
                
                <Col md={12} xs={12}>
               { roles.includes('ROLE_AGENT') ?
                    
                         <>
                        <center><h2 className="border-bottom">Statistika</h2></center>
                 
                         <CardDeck  className="mt-5">
                        <Card >
                            <Card.Img variant="top"  />
                            <Card.Body>
                            <Card.Title>  
                                <ListItemAvatar>
                                    <Avatar>
                                        <GradeIcon />
                                    </Avatar>
                                </ListItemAvatar>
                            </Card.Title>
                            <Card.Text>
                                Oglas sa najvecom prosjecnom ocjenom {' '}
                            </Card.Text>
                                 <Button onClick={ handleGrade } variant="outline-success" block>-{'>'}</Button>
                            </Card.Body>
                        </Card>
                        <Card>
                            <Card.Img variant="top" />
                            <Card.Body>
                            <Card.Title>
                                <ListItemAvatar>
                                    <Avatar>
                                        <ConfirmationNumberIcon />
                                    </Avatar>
                                </ListItemAvatar>
                            </Card.Title>
                            <Card.Text>
                                Oglas sa najvecom  kilometrazom{' '}
                            </Card.Text>
                            <Button onClick={handleMileage} variant="outline-warning" block>-{'>'}</Button>
                            </Card.Body>
                        </Card>
                        
                        <Card>
                            <Card.Img variant="top" />
                            <Card.Body>
                            <Card.Title>
                            <ListItemAvatar>
                                    <Avatar>
                                        <CommentIcon />
                                    </Avatar>
                                </ListItemAvatar>
                            </Card.Title>
                            <Card.Text>
                              Oglas sa najvecim brojem komentara{' '}
                            </Card.Text>
                            <Button onClick={handleComment} variant="outline-danger" block>-{'>'}</Button>
                            </Card.Body>
                        </Card>
                    </CardDeck>
                     {
                        isFetchAd ? 

                         <AdsTableComponent ad={ad} flag={flag}></AdsTableComponent>

                    
                     
                     : null
                    }
                    </>
                    :   <center><h2 className="border-bottom">Pocetna stranica panela</h2></center>

                    

                }
               
                </Col>
            </Row>
        </Container >
    );
}

export default PanelHomeContainer;
