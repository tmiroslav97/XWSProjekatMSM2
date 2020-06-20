import React from 'react';
import { Row, Col, Container, Carousel } from 'react-bootstrap';
import AdComponent from "../components/Ad/AdComponent";
import AdListContainer from './Ad/AdListContainer';

const HomePage = () => {
    return (
        <Container>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <center><h1>Rent A Car</h1></center>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <Carousel>
                        <Carousel.Item>
                            <img
                                className="d-block w-100"
                                src="img/rent1.png"
                                alt="First slide"
                            />
                            <Carousel.Caption>
                                <h3>Dobro dosli na rent a car!</h3>
                            </Carousel.Caption>
                        </Carousel.Item>
                        <Carousel.Item>
                            <img
                                className="d-block w-100"
                                src="img/rent2.png"
                                alt="Second slide"
                            />
                            <Carousel.Caption>
                                <h3>Izaberite najbolje auto</h3>
                            </Carousel.Caption>
                        </Carousel.Item>
                        <Carousel.Item>
                            <img
                                className="d-block w-100"
                                src="img/rent3.png"
                                alt="Third slide"
                            />
                            <Carousel.Caption>
                                <h3>Nasa vozila su najmodernija i najnovija</h3>
                            </Carousel.Caption>
                        </Carousel.Item>
                    </Carousel>
                    
                    <AdListContainer/>
                   
                   
                </Col>
            </Row>
        </Container>
    );
}

export default HomePage;