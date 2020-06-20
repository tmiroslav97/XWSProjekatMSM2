import {
    PUT_TO_CART
} from './constants';

//end users
export const putToCart = payload => ({
    type: PUT_TO_CART,
    payload
});
