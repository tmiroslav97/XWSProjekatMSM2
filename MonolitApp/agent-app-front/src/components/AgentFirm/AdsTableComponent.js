import React from 'react';
import { TabContent, Container, Row, Col, Card, Button, Table } from 'react-bootstrap';
import AppBar from '@material-ui/core/AppBar';
import Tab from 'react-bootstrap/Tab'
import Tabs from 'react-bootstrap/Tabs'
import AdComponent from '../../components/Ad/AdComponent'
import { adSelector } from '../../store/ad/selectors';
import Accordion from 'react-bootstrap/Accordion'
import '../AgentFirm/table.css'

const AdsTableComponent = (props) => {

  var oc=-1;  
  var km=-1;
  var kom=-1;

  if(props.flag===1){
    oc=props.flag;
  }else if(props.flag===2){
      km=props.flag;
  }else if(props.flag===3){
    kom=props.flag;
  }
 
  return (

    <Container>

            <Table responsive  className="mt-5">
              <thead>
                <tr>
                  <th>Naziv oglasa</th>
                  <th>Proizvodjac automobila</th>
                  <th>Model automobila</th>
                  <th id={"th" + oc}>Ocjena</th>
                  <th id={"th" + km}>Kilometraza</th>
                  <th id={"th" + kom}>Broj komentara</th> 
                </tr>
              </thead>
              <tbody>

                <tr > 
                  <td>{props.ad.name}</td>
                  <td>{props.ad.carManufacturer}</td>
                  <td>{props.ad.carModel}</td>
                  <td>{props.ad.averageGrade}</td>
                  <td>{props.ad.mileage}</td>
                  <td>255</td>
                </tr>

              </tbody>
            </Table>


     </Container>





  );
}

export default AdsTableComponent;