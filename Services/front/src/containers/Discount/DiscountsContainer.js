import React, { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Container, Row, Col, Button } from 'react-bootstrap'
import DiscountComponent from '../../components/Discount/DiscountsComponent';
import { discountsSelector } from '../../store/ad/selectors';
import { fetchDiscountsFromAgent, addDiscount, editDiscount, deleteDiscount } from '../../store/ad/actions';
import SpinnerContainer from '../Common/SpinnerContainer';

const DiscountsContainer = () => {

    const dispatch = useDispatch();
    const discounts = useSelector(discountsSelector);

    const [editFlag, setEditFlag] = useState(false);
    const [discountData, setDiscountData] = useState(false);
    const [validated, setValidated] = useState(false);
    const [addFlag, setAddFlag] = useState(false);

    useEffect(() => {
        dispatch(fetchDiscountsFromAgent());   
    }, []);

    const addDiscountList = (event) => {
        setAddFlag(true);
    };

    const handleAddDiscountList = (event)=> {
        event.preventDefault();
        const form = event.target;
        if (form.checkValidity() === false) {
            event.stopPropagation();
            setValidated(true);
            alert("Obavezna polja su oznacena sa *");
        } else {
            setValidated(false);
            setAddFlag(false);
            console.log(form.dayNum.value);
            console.log(form.discount.value);

            dispatch(addDiscount({
                "dayNum": form.dayNum.value,
                "discount": form.discount.value,
            }))
        }
    };

    const editDiscountList = (id) => {
        setEditFlag(true);
        console.log("cenovnik ");
        console.log(id);
        if(discounts.data !== ""){
            discounts.data.map((discount) => {
                if(discount.id === id){
                    setDiscountData(discount);
                }
            });

        };   
    };

    const handleEditDiscountList = (event)=> {
        event.preventDefault();
        const form = event.target;
        console.log("popust se edituje");
        if (form.checkValidity() === false) {
            event.stopPropagation();
            setValidated(true);
        } else {
            setValidated(false);
            setEditFlag(false);

            dispatch(editDiscount({
                "id": discountData.id,
                "dayNum": form.dayNum.value,
                "discount": form.discount.value,
            }))
            
        }
    };

    const deleteDiscountList = (id) => {
        console.log("popust se brise ");
        console.log(id);
        dispatch(deleteDiscount(id));
    };

    const getDiscountLists = () =>{
        let list =[];
        if(discounts.data != ""){
            discounts.data.map((discount) => {
                list.push(
                    <tr key={discount.id}>
                        <td>{discount.discount}</td>
                        <td>{discount.dayNum}</td>
                        <td align="right">
                            <Button variant="outline-success"
                                onClick={() => { editDiscountList(discount.id); }}
                            >Izmeni</Button>
                        </td>
                        <td align="right">
                            <Button variant="outline-success"
                                onClick={() => { deleteDiscountList(discount.id); }}
                            >Izbrisi</Button>
                        </td>
                    </tr>
                );
            })
        }
        return list;
    }
  
    return (
        <Container>
            <Row>
                <Col md={12} xs={12}>
                    <h2 className="border-bottom">Popusti</h2>
                </Col>
            </Row>
            <Row>
                <Col md={12} xs={12}>
                    {
                        discounts.isFetch ? 
                        <DiscountComponent 
                            getDiscountLists={getDiscountLists} 
                            editFlag={editFlag} setEditFlag={setEditFlag}
                            discountData={discountData} setDiscountData={setDiscountData}
                            addFlag={addFlag} setAddFlag={setAddFlag}
                            addDiscountList={addDiscountList}
                            handleAddDiscountList={handleAddDiscountList}
                            handleEditDiscountList={handleEditDiscountList}
                        /> 
                        : <SpinnerContainer />
                    }
                </Col>
            </Row>
            
        </Container >
    );
}

export default DiscountsContainer;
