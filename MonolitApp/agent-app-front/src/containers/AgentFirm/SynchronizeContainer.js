import React, { useState } from 'react';
import SynchronizeComponent from '../../components/AgentFirm/SynchronizeComponent';
import { useDispatch } from 'react-redux';
import AdServices from '../../services/AdServices';
import { putSuccessMsg, putErrorMsg } from '../../store/common/actions';

const SynchronizeContainer = () => {
    const dispatch = useDispatch();
    const [validated, setValidated] = useState(false);

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
        const result = await AdServices.syncData(payload);
        if (result === 'Sinhronizacija uspjesno obavljena.') {
            dispatch(putSuccessMsg(result));
        } else {
            dispatch(putErrorMsg(result));
        }
    }

    return (
        <SynchronizeComponent onSubmit={handleSync} validated={validated} />
    )

}

export default SynchronizeContainer;
