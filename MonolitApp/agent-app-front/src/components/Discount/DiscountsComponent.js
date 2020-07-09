import React from 'react';
import { Row, Col, Button, Table } from 'react-bootstrap';
import FormModalContainer from '../../containers/Common/FormModalContainer';
import EditDiscountComponent from '../../components/Discount/EditDiscountComponent';
import AddDiscountComponent from '../../components/Discount/AddDiscountComponent';

const DiscountComponent = (props) => {

    return (
        <Row>
            <FormModalContainer show={props.editFlag} setShow={props.setEditFlag} 
            name={'Izmena popusta'} footer={false} data={props.discountData} 
            onSubmit={props.handleEditDiscountList} validated={props.validated} 
            component={EditDiscountComponent} 
            />
            <FormModalContainer show={props.addFlag} setShow={props.setAddFlag} 
            name={'Dodavanje popusta'} footer={false}  
            onSubmit={props.handleAddDiscountList} validated={props.validated} 
            component={AddDiscountComponent} 
            />

            <Col md={12} xs={12}>
                <Button onClick={props.addDiscountList}>Dodaj popust</Button>
                <br></br>
                <br></br>
                <Table striped bordered hover>
                    <thead>
                        <tr>
                            <th>Popust u %</th>
                            <th>Broj potrebnih dana rentiranja</th>
                            <th>Izmeni</th>
                            <th>Izbrisi</th>
                        </tr>
                    </thead>
                    <tbody>
                        {props.getDiscountLists()}
                    </tbody>
                </Table>
            </Col>
        </Row>
    );
}

export default DiscountComponent;
