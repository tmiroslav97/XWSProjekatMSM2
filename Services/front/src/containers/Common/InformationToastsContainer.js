import React, { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { useToasts } from 'react-toast-notifications';
import { putErrorMsg, putSuccessMsg, putWarnMsg, putInfoMsg } from '../../store/common/actions';
import { errorSelector, successSelector, warnSelector, infoSelector } from '../../store/common/selectors';

const InformationToastsContainer = () => {
    const dispatch = useDispatch();
    const error = useSelector(errorSelector);
    const success = useSelector(successSelector);
    const warn = useSelector(warnSelector);
    const info = useSelector(infoSelector);
    const { addToast } = useToasts();

    useEffect(() => {
        if (error != null) {
            addToast(error, {
                appearance: 'error',
                autoDismiss: true,
            });
            dispatch(putErrorMsg(null));
        }
    }, [error]);

    useEffect(() => {
        if (success != null) {
            addToast(success, {
                appearance: 'success',
                autoDismiss: true,
            });
        }
        dispatch(putSuccessMsg(null));
    }, [success]);

    useEffect(() => {
        if (warn != null) {
            addToast(warn, {
                appearance: 'warning',
                autoDismiss: true,
            });
            dispatch(putWarnMsg(null));
        }
    }, [warn]);

    useEffect(() => {
        if (info != null) {
            addToast(info, {
                appearance: 'info',
                autoDismiss: true,
            });
            dispatch(putInfoMsg(null));
        }
    }, [info]);

    return (
        <div id="toastMsg">
        </div>
    );
};

export default InformationToastsContainer;