import React, { useState, useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { Container, Row, Col } from 'react-bootstrap'
import RegFirmComponent from '../../components/Authorization/RegFirmComponent';
import FirmService from '../../services/FirmService';
import { putErrorMsg, putSuccessMsg } from '../../store/common/actions';
import SpinnerContainer from '../Common/SpinnerContainer';
import FirmsComponent from '../../components/AgentFirm/FirmsComponent';
import PaginationContainer from '../Pagination/PaginationContainer';
import PaginationSize from '../../components/Pagination/PaginationSize';

const RegFirmContainer = () => {
    const dispatch = useDispatch();

    const [validated, setValidated] = useState(false);
    const [nextPage, setNextPage] = useState(0);
    const [size, setSize] = useState(10);
    const [firms, setFirms] = useState({
        'data': [],
        'totalPageCnt': 0,
        'nextPage': nextPage,
        'size': size,
        'isFetch': false
    });

    useEffect(() => {
        fetchFirmsPaginated({ nextPage, size });
    }, [nextPage, size]);

    const fetchFirmsPaginated = async (payload) => {
        setFirms({
            'data': [],
            'totalPageCnt': firms.totalPageCnt,
            'nextPage': nextPage,
            'size': size,
            'isFetch': false
        });
        const result = await FirmService.fetchFirmsPaginated(payload);
        setFirms({
            'data': result.firms,
            'totalPageCnt': result.totalPageCnt,
            'nextPage': nextPage,
            'size': size,
            'isFetch': true
        });
    }

    const registerFirm = async (payload) => {
        const result = await FirmService.registerFirm(payload);
        if (result === 'Firma uspjesno registrovana.') {
            dispatch(putSuccessMsg(result));
            fetchFirmsPaginated({ nextPage, size });
        } else {
            dispatch(putErrorMsg(result));
        }
    }

    const handleRegisterFirm = (event) => {
        event.preventDefault();
        const form = event.target;
        const data = new FormData(event.target);

        if (form.checkValidity() === false) {
            event.stopPropagation();
            setValidated(true);
        } else {
            registerFirm({
                "email": data.get('email'),
                "firmName": data.get('firmName'),
                "pmb": data.get('pmb'),
                "address": data.get('address')
            });
            setValidated(false);
        }
    };

    const handleLogDelete = async (id) => {
        const result = await FirmService.firmDeleteOrRevert({ "id": id, "status": true });
        if (result === 'Firma uspjesno logicki obrisana.') {
            dispatch(putSuccessMsg(result));
            fetchFirmsPaginated({ nextPage, size });
        } else {
            dispatch(putErrorMsg(result));
        }
    };

    const handleRevert = async (id) => {
        const result = await FirmService.firmDeleteOrRevert({ "id": id, "status": false });
        if (result === 'Firma uspjesno vracena.') {
            dispatch(putSuccessMsg(result));
            fetchFirmsPaginated({ nextPage, size });
        } else {
            dispatch(putErrorMsg(result));
        }
    };


    return (
        <Container>
            <RegFirmComponent onSubmit={handleRegisterFirm} validated={validated} />
            <Container>
                <Row>
                    <Col md={12} xs={12}>
                        <h2 className="border-bottom">Lista firmi</h2>
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
                            firms.isFetch ? <FirmsComponent firms={firms.data} handleLogDelete={handleLogDelete} handleRevert={handleRevert} /> : <SpinnerContainer />
                        }
                    </Col>
                </Row>
                <Row>
                    <Col md={12} xs={12}>
                        <PaginationContainer setNextPage={setNextPage} totalPageCnt={firms.totalPageCnt} nextPage={nextPage} />
                    </Col>
                </Row>
            </Container>
        </Container>
    );
}

export default RegFirmContainer;