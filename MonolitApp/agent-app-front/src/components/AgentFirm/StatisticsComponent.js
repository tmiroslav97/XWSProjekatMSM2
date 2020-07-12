import React from 'react';
import {  Container, Row, Col, Card, Table, ButtonGroup, Button } from 'react-bootstrap';
import AdsTableComponent from '../AgentFirm/AdsTableComponent'
// import ButtonGroup from '@material-ui/core/ButtonGroup';


const StatisticsComponent = (props) => {

  console.log(props.ad)
  return (

   <Container className="mt-3">
     <Row>
       <Col>

     
          <Button onClick={props.handleGrade } variant="outline-success" block>Najveca ocjena</Button>{' '}
          <Button onClick={props.handleMileage} variant="outline-warning" block>Predjena kilometraza</Button>{' '}
          <Button onClick={props.handleComment} variant="outline-danger" block>Komentari</Button>{' '}

          {
            props.isFetchAd ? <AdsTableComponent ad={props.ad} flag={props.flag}></AdsTableComponent> : null
          }
       </Col>
     </Row>
   </Container>


  );
}

export default StatisticsComponent;