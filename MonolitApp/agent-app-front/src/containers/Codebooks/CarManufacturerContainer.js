import React, { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Container, Row, Col, Button } from 'react-bootstrap'
import PaginationContainer from '../Pagination/PaginationContainer';
import PaginationSize from '../../components/Pagination/PaginationSize';
import CarManufacturerComponent from '../../components/Codebooks/CarManufacturerComponent';
import { carManufacturersPaginatedSelector } from '../../store/codebook/selectors';
import { fetchCarManufacturers, addCarManufacturer, editCarManufacturer, deleteCarManufacturer, putCarManufacturersPaginated } from '../../store/codebook/actions';
import FormModalContainer from '../Common/FormModalContainer';
import DeleteModalContainer from '../Common/DeleteModalContainer';
import CodebookAdFormComponent from '../../components/Codebooks/CodebookAdFormComponent';
import CodebookEditFormComponent from '../../components/Codebooks/CodebookEditFormComponent';
import SpinnerContainer from '../Common/SpinnerContainer';

const CarManufacturerContainer = () => {
    const dispatch = useDispatch();
    const carManufacturers = useSelector(carManufacturersPaginatedSelector);
    const [nextPage, setNextPage] = useState(carManufacturers.nextPage);
    const [size, setSize] = useState(carManufacturers.size);
    const [validated, setValidated] = useState(false);
    const [showAdForm, setShowAdForm] = useState(false);
    const [showEditForm, setShowEditForm] = useState(false);
    const [showDeleteModal, setShowDeleteModal] = useState(false);
    const [selectedItem, setSelectedItem] = useState(null);

    useEffect(() => {
        dispatch(
            fetchCarManufacturers({
                nextPage,
                size
            })
        );
        return () => {
            dispatch(putCarManufacturersPaginated({
                'data': [],
                'totalPageCnt': 0,
                'nextPage': nextPage,
                'size': size,
                'isFetch': false
            }));
        };
    }, [nextPage, size]);

    const handleAddCarManufacturer = (event) => {
        event.preventDefault();
        const form = event.target;
        const data = new FormData(event.target);

        if (form.checkValidity() === false) {
            event.stopPropagation();
            setValidated(true);
        } else {
            dispatch(
                addCarManufacturer(
                    data.get('name')
                )
            );
            setValidated(false);
            setShowAdForm(false);
        }
    };

    const handleEditCarManufaturer = (event) => {
        event.preventDefault();
        const form = event.target;
        const data = new FormData(event.target);

        if (form.checkValidity() === false) {
            event.stopPropagation();
            setValidated(true);
        } else {
            dispatch(
                editCarManufacturer({
                    "id": data.get('id'),
                    "name": data.get('name')
                })
            );
            setValidated(false);
            setShowEditForm(false);
        }
    };

    const handleDeleteCarManufacturer = () => {
        setShowDeleteModal(false);
        dispatch(
            deleteCarManufacturer(selectedItem)
        );
    };

    const handleEdit = (carManufacturer) => {
        setSelectedItem(carManufacturer);
        setShowEditForm(true);
    };

    const handleDelete = (id) => {
        setSelectedItem(id);
        setShowDeleteModal(true);
    };


    return (
        <Container>
            <FormModalContainer show={showAdForm} setShow={setShowAdForm} name={'Proizvođač automobila'} footer={false} onSubmit={handleAddCarManufacturer} validated={validated} component={CodebookAdFormComponent} />
            <FormModalContainer show={showEditForm} setShow={setShowEditForm} name={'Proizvođač automobila'} footer={false} onSubmit={handleEditCarManufaturer} selectedItem={selectedItem} validated={validated} component={CodebookEditFormComponent} />
            <DeleteModalContainer show={showDeleteModal} msg={'(Postoje povezani modeli automobila sa ovim entitetom)'} setShow={setShowDeleteModal} onDelete={handleDeleteCarManufacturer} />
            <Row>
                <Col md={6} xs={12}>
                    <h2 className="border-bottom">Šifarnik proizvođača automobila</h2>
                </Col>
            </Row>
            <Row>
                <Col md={2} xs={12}>
                    <Button className="mb-5" variant="outline-primary" onClick={() => setShowAdForm(true)}>Dodaj</Button>
                </Col>
            </Row>
            <Row>
                <Col md={12} xs={12}>
                    <PaginationSize size={size} setSize={setSize} />
                </Col>
            </Row>
            <Row>
                <Col md={12} xs={12}>
                    {
                        carManufacturers.isFetch ? <CarManufacturerComponent carManufacturers={carManufacturers.data} handleEdit={handleEdit} handleDelete={handleDelete} /> : <SpinnerContainer />
                    }
                </Col>
            </Row>
            <Row>
                <Col md={6} xs={12}>
                    <PaginationContainer setNextPage={setNextPage} totalPageCnt={carManufacturers.totalPageCnt} nextPage={nextPage} />
                </Col>
            </Row>
        </Container >
    );
}

export default CarManufacturerContainer;
