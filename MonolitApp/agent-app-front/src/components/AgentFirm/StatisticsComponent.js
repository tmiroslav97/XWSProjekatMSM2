import React from 'react';
import {TabContent, Container,Row, Col, Card, Button } from 'react-bootstrap';
import AppBar from '@material-ui/core/AppBar';
import Tab from 'react-bootstrap/Tab'
import Tabs from 'react-bootstrap/Tabs'
import AdComponent from '../../components/Ad/AdComponent'
import { adSelector } from '../../store/ad/selectors'; 



const StatisticsComponent = (props) => {


console.log(props.ad)
    return (
       
        <Container>
        <Tabs>
        <Tab eventKey="grade" title="Najbolja prosjecna ocjena">
          <div>
              <p>{props.ad.name}</p>
              <p>{props.ad.mileage}</p>
              <p>{props.ad.name}</p>
          </div>
        </Tab>
        <Tab eventKey="mielage" title="Najvise kilometara">
        {/* {props.ad.mileage} */}
        </Tab>
        <Tab eventKey="comment" title="Najvise komentara" >
        </Tab>
      </Tabs>
        </Container>
       
    );
}

export default StatisticsComponent;