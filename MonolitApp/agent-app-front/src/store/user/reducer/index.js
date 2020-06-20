import {
    PUT_TOKEN,
    PUT_END_USERS
} from '../constants';

import * as computationFunctions from './computation-functions';

const initialState = {
    token: localStorage.getItem('token'),
    endUsers: {
        data: [],
        totalPageCnt: 0,
        nextPage: 0,
        size: 10,
        isFetch: false
    }
};

const userReducer = (state = initialState, { type, payload }) => {
    if (actionHandler.hasOwnProperty(type)) {
        return actionHandler[type](state, payload);
    }
    return state;
};

const actionHandler = {
    [PUT_TOKEN]: computationFunctions.putToken,
    [PUT_END_USERS]: computationFunctions.putEndUsers
};

export default userReducer;