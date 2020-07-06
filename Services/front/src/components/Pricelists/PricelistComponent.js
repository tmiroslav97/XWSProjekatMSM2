import React from 'react';
import { Row, Col, Button, Table } from 'react-bootstrap';
import FormModalContainer from '../../containers/Common/FormModalContainer';
import EditPricelistComponent from '../../components/Pricelists/EditPricelistComponent';
import AddPricelistComponent from '../../components/Pricelists/AddPricelistcomponent.js';

const PricelistComponent = (props) => {

    return (
        <Row>
            <FormModalContainer show={props.editFlag} setShow={props.setEditFlag} 
            name={'Izmena cenovnika'} footer={false} data={props.pricelistData} 
            onSubmit={props.handleEditPricelist} validated={props.validated} 
            component={EditPricelistComponent} 
            />
            <FormModalContainer show={props.addFlag} setShow={props.setAddFlag} 
            name={'Dodavanje cenovnika'} footer={false}    //data={props.pricelistData} 
            onSubmit={props.handleAddPricelist} validated={props.validated} 
            component={AddPricelistComponent} 
            />

            <Col md={12} xs={12}>
                <Button onClick={props.addPricelist}>Dodaj cenovnik</Button>
                <br></br>
                <br></br>
                <Table striped bordered hover>
                    <thead>
                        <tr>
                            <th >Datum kreiranja</th>
                            <th >Cena po danu</th>
                            <th >Cena po km</th>
                            <th >Cena po km (CDW)</th>
                            <th>Izmeni</th>
                            <th>Izbrisi</th>
                        </tr>
                    </thead>
                    <tbody>
                        {props.getPricelists()}
                    </tbody>
                </Table>
            </Col>
        </Row>
    );
}

export default PricelistComponent;
