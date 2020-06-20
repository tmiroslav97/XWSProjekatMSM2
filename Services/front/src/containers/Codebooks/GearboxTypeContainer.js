import React, { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Container, Row, Col, Button } from 'react-bootstrap'
import PaginationContainer from '../Pagination/PaginationContainer';
import PaginationSize from '../../components/Pagination/PaginationSize';
import GearboxTypeComponent from '../../components/Codebooks/GearboxTypeComponent';
import { gearboxTypesPaginatedSelector } from '../../store/codebook/selectors';
import { fetchGearboxTypes, addGearboxType, editGearboxType, deleteGearboxType, putGearboxTypesPaginated } from '../../store/codebook/actions';
import FormModalContainer from '../Common/FormModalContainer';
import DeleteModalContainer from '../Common/DeleteModalContainer';
import CodebookAdFormComponent from '../../components/Codebooks/CodebookAdFormComponent';
import CodebookEditFormComponent from '../../components/Codebooks/CodebookEditFormComponent';
import SpinnerContainer from '../Common/SpinnerContainer';

const GearboxTypeContainer = () => {
    const dispatch = useDispatch();
    const gearboxTypes = useSelector(gearboxTypesPaginatedSelector);
    const [nextPage, setNextPage] = useState(gearboxTypes.nextPage);
    const [size, setSize] = useState(gearboxTypes.size);
    const [validated, setValidated] = useState(false);
    const [showAdForm, setShowAdForm] = useState(false);
    const [showEditForm, setShowEditForm] = useState(false);
    const [showDeleteModal, setShowDeleteModal] = useState(false);
    const [selectedItem, setSelectedItem] = useState(null);

    useEffect(() => {
        dispatch(
            fetchGearboxTypes({
                nextPage,
                size
            })
        );
        return () => {
            dispatch(putGearboxTypesPaginated({
                'data': [],
                'totalPageCnt': 0,
                'nextPage': nextPage,
                'size': size,
                'isFetch': false
            }));
        };
    }, [nextPage, size]);

    const handleAddGearboxType = (event) => {
        event.preventDefault();
        const form = event.target;
        const data = new FormData(event.target);

        if (form.checkValidity() === false) {
            event.stopPropagation();
            setValidated(true);
        } else {
            dispatch(
                addGearboxType(
                    data.get('name')
                )
            );
            setValidated(false);
            setShowAdForm(false);
        }
    };

    const handleEditGearboxType = (event) => {
        event.preventDefault();
        const form = event.target;
        const data = new FormData(event.target);

        if (form.checkValidity() === false) {
            event.stopPropagation();
            setValidated(true);
        } else {
            dispatch(
                editGearboxType({
                    "id": data.get('id'),
                    "name": data.get('name')
                })
            );
            setValidated(false);
            setShowEditForm(false);
        }
    };

    const handleDeleteGearboxType = () => {
        setShowDeleteModal(false);
        dispatch(
            deleteGearboxType(selectedItem)
        );
    };

    const handleEdit = (gearboxType) => {
        setSelectedItem(gearboxType);
        setShowEditForm(true);
    };

    const handleDelete = (id) => {
        setSelectedItem(id);
        setShowDeleteModal(true);
    };


    return (
        <Container>
            <FormModalContainer show={showAdForm} setShow={setShowAdForm} name={'Tip mjenjaca'} footer={false} onSubmit={handleAddGearboxType} validated={validated} component={CodebookAdFormComponent} />
            <FormModalContainer show={showEditForm} setShow={setShowEditForm} name={'Tip mjenjaca'} footer={false} onSubmit={handleEditGearboxType} selectedItem={selectedItem} validated={validated} component={CodebookEditFormComponent} />
            <DeleteModalContainer show={showDeleteModal} setShow={setShowDeleteModal} onDelete={handleDeleteGearboxType} />
            <Row>
                <Col md={6} xs={12}>
                    <h2 className="border-bottom">Šifarnik tipova mjenjača</h2>
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
                        gearboxTypes.isFetch ? <GearboxTypeComponent gearboxTypes={gearboxTypes.data} handleEdit={handleEdit} handleDelete={handleDelete} /> : <SpinnerContainer />
                    }
                </Col>
            </Row>
            <Row>
                <Col md={6} xs={12}>
                    <PaginationContainer setNextPage={setNextPage} totalPageCnt={gearboxTypes.totalPageCnt} nextPage={nextPage} />
                </Col>
            </Row>
        </Container >
    );
}

export default GearboxTypeContainer;