import React from 'react';
import { Container, Row, Col, Form } from 'react-bootstrap'

const PaginationSize = (props) => {

    return (
        <Container>
            <Row>
                <Col md={{ span: 4 }} xs={12}>
                    <Form.Row>
                        <Form.Label >Stavki po stranici:</Form.Label>
                        <Form.Group as={Col} md={3} xs={3} >
                            <Form.Control as="select" id="selPageSize" value={props.size} onChange={({ currentTarget }) => {
                                props.setSize(currentTarget.value);
                            }}>
                                <option key="10" value="10">10</option>
                                <option key="15" value="15">15</option>
                                <option key="20" value="20">20</option>
                            </Form.Control>
                        </Form.Group>
                    </Form.Row>
                </Col>
            </Row>
        </Container>
    );
}

export default PaginationSize;
