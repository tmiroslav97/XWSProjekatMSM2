import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { Container } from 'react-bootstrap';
import { putSuccessMsg, putErrorMsg } from '../../store/common/actions';
import AdServices from '../../services/AdServices';
import SynchronizeComponent from '../../components/AgentFirm/SynchronizeComponent';
import SpinnerContainer from '../Common/SpinnerContainer';

const SynchronizeContainer = () => {
    const dispatch = useDispatch();
    const [validated, setValidated] = useState(false);
    const [isSync, setIsSync] = useState(false);

    const handleSync = (event) => {
        event.preventDefault();
        const form = event.target;
        const data = new FormData(event.target);

        if (form.checkValidity() === false) {
            event.stopPropagation();
            setValidated(true);
        } else {
            syncData(data.get('identifier'),);
            setValidated(false);
        }
    };

    const syncData = async (payload) => {
        setIsSync(true);
        const result = await AdServices.syncData(payload);
        if (result === 'Sinhronizacija uspjesno obavljena.') {
            dispatch(putSuccessMsg(result));
        } else {
            dispatch(putErrorMsg(result));
        }
        setIsSync(false);
    }

    return (
        <Container>
            { !isSync ?
                <SynchronizeComponent onSubmit={handleSync} validated={validated} />
                : <SpinnerContainer />
            }
        </Container>
    )

}

export default SynchronizeContainer;
