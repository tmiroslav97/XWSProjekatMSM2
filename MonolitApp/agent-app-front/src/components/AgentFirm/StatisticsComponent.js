import React from 'react';
import { TabContent, Container, Row, Col, Card, Button, Table } from 'react-bootstrap';
import AppBar from '@material-ui/core/AppBar';
import Tab from 'react-bootstrap/Tab'
import Tabs from 'react-bootstrap/Tabs'
import AdComponent from '../../components/Ad/AdComponent'
import { adSelector } from '../../store/ad/selectors';



const StatisticsComponent = (props) => {


  console.log(props.ad)
  return (

    <Container>
      <Tabs activeKey={props.key} onSelect={(k)=>{props.handleTabClick(k);}}>
        <Tab eventKey="grade" title="Najbolja prosjecna ocjena">
          <div>
            <Table responsive>
              <thead>
                <tr>
                  <th>Naziv oglasa</th>
                  <th>Proizvodjac automobila</th>
                  <th>Ocjena</th>
                </tr>
              </thead>
              <tbody>

                <tr key={props.ad.id}>
                  <td>{props.ad.name}</td>
                  <td>{props.ad.carManufacturer}</td>
                  <td>{props.ad.averageGrade}</td>
                </tr>

              </tbody>
            </Table>
          </div>
        </Tab>
        <Tab eventKey="mileage" title="Najvise kilometara">
        <div>
            <Table responsive>
              <thead>
                <tr>
                  <th>Naziv oglasa</th>
                  <th>Proizvodjac automobila</th>
                  <th>Kilometraza</th>
                </tr>
              </thead>
              <tbody>

                <tr key={props.ad.id}>
                  <td>{props.ad.name}</td>
                  <td>{props.ad.carManufacturer}</td>
                  <td>{props.ad.mileage}</td>
                </tr>

              </tbody>
            </Table>
          </div>
        </Tab>
        <Tab eventKey="comment" title="Najvise komentara" >
        </Tab>
      </Tabs>
    </Container>

  );
}

export default StatisticsComponent;