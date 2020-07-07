import React, { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Container, Row, Col, Button } from 'react-bootstrap'
import PricelistComponent from '../../components/Pricelists/PricelistComponent';
import EndUsersComponent from '../../components/Users/EndUsersComponent';
import { pricelistsSelector } from '../../store/pricelist/selectors';
import { fetchPriceListsFromPublisher, addPriceList, editPriceList, deletePriceList } from '../../store/pricelist/actions';
import SpinnerContainer from '../Common/SpinnerContainer';

const PricelistContainer = () => {

    const dispatch = useDispatch();
    const pricelists = useSelector(pricelistsSelector);
    const [editFlag, setEditFlag] = useState(false);
    const [pricelistData, setPricelistData] = useState(false);
    const [validated, setValidated] = useState(false);
    const [addFlag, setAddFlag] = useState(false);

    useEffect(() => {
        dispatch(fetchPriceListsFromPublisher());   
    }, []);

    const addPricelist = (event) => {
        setAddFlag(true);
    };

    const handleAddPricelist = (event)=> {
        event.preventDefault();
        const form = event.target;
        if (form.checkValidity() === false) {
            event.stopPropagation();
            setValidated(true);
            alert("Obavezna polja su oznacena sa *");
        } else {
            setValidated(false);
            setAddFlag(false);
            console.log(form.pricePerDay.value);
            console.log(form.pricePerKm.value);
            console.log(form.pricePerKmCDW.value);
            dispatch(addPriceList({
                "pricePerKm": form.pricePerKm.value,
                "pricePerKmCDW": form.pricePerKmCDW.value,
                "pricePerDay": form.pricePerDay.value
            }))
        }
    };

    const editPricelist = (id) => {
        setEditFlag(true);
        console.log("cenovnik ");
        console.log(id);
        if(pricelists.data !== ""){
            pricelists.data.map((pricelist) => {
                if(pricelist.id === id){
                    console.log("cenovnik");
                    console.log(pricelist)
                    setPricelistData(pricelist);
                }
            });

        };   
    };

    const handleEditPricelist = (event)=> {
        event.preventDefault();
        const form = event.target;
        console.log("cenovnik");
        if (form.checkValidity() === false) {
            event.stopPropagation();
            setValidated(true);
        } else {
            setValidated(false);
            setEditFlag(false);

            dispatch(editPriceList({
                "id": pricelistData.id,
                "pricePerKm": form.pricePerKm.value,
                "pricePerKmCDW": form.pricePerKmCDW.value,
                "pricePerDay": form.pricePerDay.value
            }))
            
        }
    };


    const deletePricelist = (id) => {
        console.log("cenovnik ");
        console.log(id);
        dispatch(deletePriceList(id));

    };

    const getPricelists = () =>{
        let list =[];
        if(pricelists.data != ""){
            pricelists.data.map((pricelist) => {
                let ss = pricelist.creationDate.substring(0, 10);
                let ss2 = pricelist.creationDate.substring(11, 16);
                ss = ss + " " + ss2;

                list.push(
                    <tr key={pricelist.id}>
                        <td>{ss}</td>
                        <td>{pricelist.pricePerDay}</td>
                        <td>{pricelist.pricePerKm}</td>
                        <td>{pricelist.pricePerKmCDW}</td>
                        <td align="right">
                            <Button variant="outline-success"
                                onClick={() => { editPricelist(pricelist.id); }}
                            >Izmeni</Button>
                        </td>
                        <td align="right">
                            <Button variant="outline-success"
                                onClick={() => { deletePricelist(pricelist.id); }}
                            >Izbrisi</Button>
                        </td>
                    </tr>);
            })
        }
        return list;
    }
  

    return (
        <Container>
            <Row>
                <Col md={12} xs={12}>
                    <h2 className="border-bottom">Lista cenovnika</h2>
                </Col>
            </Row>
            
            <Row>
                <Col md={12} xs={12}>
                    {
                        pricelists.isFetch ? 
                        <PricelistComponent 
                            getPricelists={getPricelists} 
                            editFlag={editFlag} setEditFlag={setEditFlag}
                            pricelistData={pricelistData} setPricelistData={setPricelistData}
                            addFlag={addFlag} setAddFlag={setAddFlag}
                            addPricelist={addPricelist}
                            handleAddPricelist={handleAddPricelist}
                            handleEditPricelist={handleEditPricelist}

                        /> 
                        : <SpinnerContainer />
                    }
                </Col>
            </Row>
            
        </Container >
    );
}

export default PricelistContainer;
