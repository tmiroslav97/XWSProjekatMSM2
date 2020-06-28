import React, { useState, useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { Container, Row, Col } from 'react-bootstrap'
import RegAgentComponent from '../../components/Authorization/RegAgentComponent';
import UserService from '../../services/UserService';
import { putErrorMsg, putSuccessMsg } from '../../store/common/actions';
import SpinnerContainer from '../Common/SpinnerContainer';
import AgentsComponent from '../../components/AgentFirm/AgentsComponent';
import PaginationContainer from '../Pagination/PaginationContainer';
import PaginationSize from '../../components/Pagination/PaginationSize';

const RegAgentContainer = () => {
    const dispatch = useDispatch();

    const [validated, setValidated] = useState(false);
    const [nextPage, setNextPage] = useState(0);
    const [size, setSize] = useState(10);
    const [agents, setAgents] = useState({
        'data': [],
        'totalPageCnt': 0,
        'nextPage': nextPage,
        'size': size,
        'isFetch': false
    });

    useEffect(() => {
        fetchAgentsPaginated({ nextPage, size });
    }, [nextPage, size]);

    const fetchAgentsPaginated = async (payload) => {
        setAgents({
            'data': [],
            'totalPageCnt': agents.totalPageCnt,
            'nextPage': nextPage,
            'size': size,
            'isFetch': false
        });
        const result = await UserService.fetchAgentsPaginated(payload);
        setAgents({
            'data': result.agents,
            'totalPageCnt': result.totalPageCnt,
            'nextPage': nextPage,
            'size': size,
            'isFetch': true
        });
    }

    const registerAgent = async (payload) => {
        const result = await UserService.registerAgent(payload);
        if (result === 'Agent uspjesno registrovan.') {
            dispatch(putSuccessMsg(result));
            fetchAgentsPaginated({ nextPage, size });
        } else {
            dispatch(putErrorMsg(result));
        }
    }

    const handleRegisterAgent = (event) => {
        event.preventDefault();
        const form = event.target;
        const data = new FormData(event.target);

        if (form.checkValidity() === false) {
            event.stopPropagation();
            setValidated(true);
        } else {
            registerAgent({
                "email": data.get('email'),
                "firstName": data.get('firstName'),
                "lastName": data.get('lastName')
            });
            setValidated(false);
        }
    };

    const handleLogDelete = async (id) => {
        const result = await UserService.agentDeleteOrRevert({ "id": id, "status": true });
        if (result === 'Agent uspjesno logicki obrisan.') {
            dispatch(putSuccessMsg(result));
            fetchAgentsPaginated({ nextPage, size });
        } else {
            dispatch(putErrorMsg(result));
        }
    };

    const handleRevert = async (id) => {
        const result = await UserService.agentDeleteOrRevert({ "id": id, "status": false });
        if (result === 'Agent uspjesno logicki obrisan.') {
            dispatch(putSuccessMsg(result));
            fetchAgentsPaginated({ nextPage, size });
        } else {
            dispatch(putErrorMsg(result));
        }
    };


    return (
        <Container>
            <RegAgentComponent onSubmit={handleRegisterAgent} validated={validated} btnName={'Registruj agenta'} />
            <Container>
                <Row>
                    <Col md={12} xs={12}>
                        <h2 className="border-bottom">Lista agenata</h2>
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
                            agents.isFetch ? <AgentsComponent agents={agents.data} handleLogDelete={handleLogDelete} handleRevert={handleRevert} /> : <SpinnerContainer />
                        }
                    </Col>
                </Row>
                <Row>
                    <Col md={12} xs={12}>
                        <PaginationContainer setNextPage={setNextPage} totalPageCnt={agents.totalPageCnt} nextPage={nextPage} />
                    </Col>
                </Row>
            </Container>
        </Container>
    );
}

export default RegAgentContainer;