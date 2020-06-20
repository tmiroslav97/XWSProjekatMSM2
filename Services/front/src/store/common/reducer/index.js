import {
    PUT_ERROR_MSG,
    PUT_SUCCESS_MSG,
    PUT_WARN_MSG,
    PUT_INFO_MSG,
    PUT_TOTAL_PAGE_CNT,
    PUT_NEXT_PAGE
} from '../constants';

import * as computationFunctions from './computation-functions';

const initialState = {
    error: null,
    success: null,
    warn: null,
    info: null,
    nextPage: 0,
    totalPageCnt: 0
};

const commonReducer = (state = initialState, { type, payload }) => {
    if (actionHandler.hasOwnProperty(type)) {
        return actionHandler[type](state, payload);
    }
    return state;
};

const actionHandler = {
    [PUT_ERROR_MSG]: computationFunctions.putError,
    [PUT_SUCCESS_MSG]: computationFunctions.putSuccess,
    [PUT_WARN_MSG]: computationFunctions.putWarn,
    [PUT_INFO_MSG]: computationFunctions.putInfo,
    [PUT_NEXT_PAGE]: computationFunctions.putNextPage,
    [PUT_TOTAL_PAGE_CNT]: computationFunctions.putTotalPageCnt
};

export default commonReducer;