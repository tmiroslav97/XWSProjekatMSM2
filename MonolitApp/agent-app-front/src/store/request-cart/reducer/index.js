import {
    PUT_TO_CART
} from '../constants';

import * as computationFunctions from './computation-functions';

const initialState = {
    cart: []
};

const requestCartReducer = (state = initialState, { type, payload }) => {
    if (actionHandler.hasOwnProperty(type)) {
        return actionHandler[type](state, payload);
    }
    return state;
};

const actionHandler = {
    [PUT_TO_CART]: computationFunctions.putToCart,
};

export default requestCartReducer;