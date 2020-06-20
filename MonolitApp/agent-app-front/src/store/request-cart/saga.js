import { take, put, call, select } from 'redux-saga/effects';
import { history } from '../../index';
import jwt_decode from 'jwt-decode';


import { 
    PUT_TO_CART 
} from './constants';

import {
    putToCart
} from './actions';

import {
    cartSelector
} from './selectors';

import {
    putSuccessMsg
} from '../common/actions';

//end users
export function* putToCartFunction() {
    const { payload } = yield take(PUT_TO_CART);
    put(putSuccessMsg('Oglas dodat u korpu!'))
}