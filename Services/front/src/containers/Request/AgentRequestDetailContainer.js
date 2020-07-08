import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Button } from 'react-bootstrap'
import AgentRequestsComponent from '../../components/Request/AgentRequestsComponent';
import SpinnerContainer from '../Common/SpinnerContainer';
import RequestService from '../../services/RequestService';
import { useDispatch } from 'react-redux';
import AgentRequestDetailComponent from '../../components/Request/AgentRequestDetailComponent';
import { putSuccessMsg, putErrorMsg } from '../../store/common/actions';
import ReportSubmitComponent from '../../components/Report/ReportSubmitComponent';
import FormModalContainer from '../Common/FormModalContainer';

const AgentRequestDetailContainer = (props) => {
    const id = props.match.params.id;
    const [isFetch, setIsFetch] = useState(false);
    const [request, setRequest] = useState();
    const [showForm, setShowForm] = useState(false);
    const [validated, setValidated] = useState(false);
    const [selectedAd, setSelectedAd] = useState();

    const fetchRequest = async () => {
        setIsFetch(false);
        const result = await RequestService.fetchRequest({ "id": id });
        setRequest(result);
        setIsFetch(true);

    }

    const submitReport = async (payload) => {
        console.log("Sinhroni poziv za submit report")
        const result = await RequestService.submitReport(payload);
        fetchRequest();
    }

    useEffect(() => {
        fetchRequest();
    }, [id]);

    const handleSubmitReport = (event) => {
        console.lod("HANDE SUBMIT REP")
        console.log(selectedAd);
        event.preventDefault();
        const form = event.target;
        let data = null;
        console.log(form);
        
        // const data = new FormData(event.target);

        if (form.checkValidity() === false) {
            event.stopPropagation();
            setValidated(true);
        } else {
            data = {
                "distanceTraveled": data.get('distanceTraveled'),
                "description": data.get('description'),
                "adId": selectedAd
            };
            console.log("Podaci:")
            console.log(data);
            // submitReport(data);
            setValidated(false);
        }
    }


    return (
        <Container>
            <FormModalContainer show={showForm} setShow={setShowForm} name={'Izvjestaj'} footer={false} onSubmit={handleSubmitReport} validated={validated} component={ReportSubmitComponent} />
            <Row>
                <Col md={12} xs={12}>
                    {
                        isFetch ? <AgentRequestDetailComponent request={request} setShow={setShowForm} setSelectedAd={setSelectedAd} /> : <SpinnerContainer />
                    }
                </Col>
            </Row>
        </Container >
    );
}

export default AgentRequestDetailContainer;
