import React from 'react';
import { Container, Row, Col, Spinner } from 'react-bootstrap'

const SpinnerImageContainer = () => {

    return (
        <Container>
            <Row>
                <Col md={12} xs={12}>
                    <div className="d-flex justify-content-center">
                        <Spinner animation="border" role="status">
                            <span className="sr-only">Loading...</span>
                        </Spinner>
                    </div>
                </Col>
            </Row>
        </Container>
    );
}

export default SpinnerImageContainer;