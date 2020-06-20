import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import Form3CreateAd from '../../components/Ad/Form3CreateAd';
import { pricelistsSelector } from '../../store/pricelist/selectors';
import { fetchPriceListsFromPublisher } from '../../store/pricelist/actions';
import { Form, Col, Container, Button, InputGroup, Card, ButtonGroup, Table } from 'react-bootstrap';

const Form3CreateAdContainer = (props) => {
    const dispatch = useDispatch();
    const [validated, setValidated] = useState(false);
    // const [activeToggle, setActiveToggle] = useState(1);

    const pricelists = useSelector(pricelistsSelector);

    useEffect(() => {
        dispatch(
            fetchPriceListsFromPublisher()
        );
    }, []);

    const getPriceLists = () => {
        const list = [];
        if (pricelists.isFetch) {
            console.log("CENOVNICI")
            console.log(pricelists);
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
                            {props.id === pricelist.id ?
                                <td align="right">
                                    <Button variant="outline-success"
                                        onClick={() => { handlePriceListDeleteId(pricelist.id); }}
                                    >Ukloni</Button>
                                </td>
                                :
                                <td align="right">
                                    <Button variant="outline-primary"
                                        onClick={() => { handlePriceListChooseId(pricelist.id); }}
                                    >Izaberi</Button>
                                </td>
                            }
    
    
                        </tr>);
                })
            }
            

        }
        return list;
    }

    const handlePriceListChooseId = (id) => {
        props.setId(id);
    }
    const handlePriceListDeleteId = (id) => {
        props.setId();
    }

    const handleForm3 = (event) => {
        event.preventDefault();
        const form = event.target;
        if (form.checkValidity() === false) {
            event.stopPropagation();
            setValidated(true);
        } else {
            let data = {    
                "pricePerKm":props.pricePerKm,
                "pricePerKmCWD":props.pricePerKmCDW,
                "pricePerDay":props.pricePerDay,
                "id":props.id
            }
            console.log("FORMA 3");
            console.log(data);
            setValidated(false);
            props.handleNext();
        }
    };

    const handlePricePerDay = (event) => {
        props.setPricePerDay(event.target.value);
    };

    const handlePricePerKm = (event) => {
        props.setPricePerKm(event.target.value);
    };

    const handlePricePerKmCDW = (event) => {
        props.setPricePerKmCDW(event.target.value);
    };

    const handleId = (event) => {
        props.setId(event.target.value);
    };

    const handleActiveToggle0 = (event) => {
        props.setActiveToggle(0);
    };

    const handleActiveToggle1 = (event) => {
        props.setActiveToggle(1);
    };

    return (
        <Form3CreateAd
            onSubmit={handleForm3}
            validated={validated}
            activeStep={props.activeStep}
            steps={props.steps}
            isStepOptional={props.isStepOptional}
            handleBack={props.handleBack}
            handleSkip={props.handleSkip}
            handleReset={props.handleReset}

            cdw={props.cdw}
            distanceLimitFlag={props.distanceLimitFlag}
            pricePerDay={props.pricePerDay} setPricePerDay={props.setPricePerDay}
            pricePerKm={props.pricePerKm}
            pricePerKmCDW={props.pricePerKmCDW}
            id={props.id}

            handlePricePerDay={handlePricePerDay}
            handlePricePerKm={handlePricePerKm}
            handlePricePerKmCDW={handlePricePerKmCDW}
            handleId={handleId}
            
            activeToggle={props.activeToggle} setActiveToggle={props.setActiveToggle}
            handleActiveToggle0={handleActiveToggle0}
            handleActiveToggle1={handleActiveToggle1}
            getPriceLists={getPriceLists}

        />
    );
}
export default Form3CreateAdContainer;