import React, { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Container, Row, Col, Button } from 'react-bootstrap'
import PaginationContainer from '../Pagination/PaginationContainer';
import PaginationSize from '../../components/Pagination/PaginationSize';
import CarModelComponent from '../../components/Codebooks/CarModelComponent';
import { carModelsPaginatedSelector, carManufacturersSelector } from '../../store/codebook/selectors';
import { fetchCarModels, fetchAllCarManufacturers, addCarModel, editCarModel, deleteCarModel, putCarModelsPaginated } from '../../store/codebook/actions';
import FormModalContainer from '../Common/FormModalContainer';
import DeleteModalContainer from '../Common/DeleteModalContainer';
import CodebookAdFormComponent from '../../components/Codebooks/CodebookAdFormComponent';
import CodebookEditFormComponent from '../../components/Codebooks/CodebookEditFormComponent';
import SpinnerContainer from '../Common/SpinnerContainer';

const CarModelContainer = () => {
    const dispatch = useDispatch();
    const carModels = useSelector(carModelsPaginatedSelector);
    const carManufacturers = useSelector(carManufacturersSelector);
    const [nextPage, setNextPage] = useState(carModels.nextPage);
    const [size, setSize] = useState(carModels.size);
    const [validated, setValidated] = useState(false);
    const [showAdForm, setShowAdForm] = useState(false);
    const [showEditForm, setShowEditForm] = useState(false);
    const [showDeleteModal, setShowDeleteModal] = useState(false);
    const [selectedItem, setSelectedItem] = useState(null);

    useEffect(() => {
        dispatch(
            fetchCarModels({
                nextPage,
                size
            })
        );
        dispatch(
            fetchAllCarManufacturers()
        );
        return () => {
            dispatch(putCarModelsPaginated({
                'data': [],
                'totalPageCnt': 0,
                'nextPage': nextPage,
                'size': size,
                'isFetch': false
            }));
        };
    }, [nextPage, size]);

    const handleAddCarModel = (event) => {
        event.preventDefault();
        const form = event.target;
        const data = new FormData(event.target);

        if (form.checkValidity() === false) {
            event.stopPropagation();
            setValidated(true);
        } else {
            dispatch(
                addCarModel({
                    "name": data.get('name'),
                    "carManufacturer": data.get('selCarMan')

                })
            );
            setValidated(false);
            setShowAdForm(false);
        }
    };

    const handleEditCarModel = (event) => {
        event.preventDefault();
        const form = event.target;
        const data = new FormData(event.target);

        if (form.checkValidity() === false) {
            event.stopPropagation();
            setValidated(true);
        } else {
            dispatch(
                editCarModel({
                    "id": data.get('id'),
                    "name": data.get('name'),
                    "carManufacturer": data.get('selCarMan')
                })
            );
            setValidated(false);
            setShowEditForm(false);
        }
    };

    const handleDeleteCarModel = () => {
        setShowDeleteModal(false);
        dispatch(
            deleteCarModel(selectedItem)
        );
    };

    const handleEdit = (fuelType) => {
        setSelectedItem(fuelType);
        setShowEditForm(true);
    };

    const handleDelete = (id) => {
        setSelectedItem(id);
        setShowDeleteModal(true);
    };


    return (
        <Container>
            <FormModalContainer show={showAdForm} setShow={setShowAdForm} name={'Model automobila'} footer={false} data={carManufacturers.data} onSubmit={handleAddCarModel} validated={validated} component={CodebookAdFormComponent} />
            <FormModalContainer show={showEditForm} setShow={setShowEditForm} name={'Model automobila'} footer={false} data={carManufacturers.data} onSubmit={handleEditCarModel} selectedItem={selectedItem} validated={validated} component={CodebookEditFormComponent} />
            <DeleteModalContainer show={showDeleteModal} setShow={setShowDeleteModal} onDelete={handleDeleteCarModel} />
            <Row>
                <Col md={6} xs={12}>
                    <h2 className="border-bottom">Šifarnik modela automobila</h2>
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
                        carModels.isFetch ? <CarModelComponent carModels={carModels.data} handleEdit={handleEdit} handleDelete={handleDelete} /> : <SpinnerContainer />
                    }
                </Col>
            </Row>
            <Row>
                <Col md={6} xs={12}>
                    <PaginationContainer setNextPage={setNextPage} totalPageCnt={carModels.totalPageCnt} nextPage={nextPage} />
                </Col>
            </Row>
        </Container >
    );
}

export default CarModelContainer;
