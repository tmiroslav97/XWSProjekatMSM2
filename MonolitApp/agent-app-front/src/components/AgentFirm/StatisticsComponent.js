import React from 'react';
import {TabContent, Container,Row, Col, Card, Button } from 'react-bootstrap';
import AppBar from '@material-ui/core/AppBar';
import Tab from 'react-bootstrap/Tab'
import Tabs from 'react-bootstrap/Tabs'
import AdComponent from '../../components/Ad/AdComponent'
import { adSelector } from '../../store/ad/selectors'; 



const StatisticsComponent = (props) => {



    return (
       
        <Container>
        <Tabs defaultActiveKey="profile" id="uncontrolled-tab-example">
        <Tab eventKey="grade" title="Najbolja prosjecna ocjena">
           {props.ad}
        </Tab>
        <Tab eventKey="mielage" title="Najvise kilometara">
                dsdsadasdas
        </Tab>
        <Tab eventKey="comment" title="Najvise komentara" >
        </Tab>
      </Tabs>
        </Container>
       
    );
}

export default StatisticsComponent;