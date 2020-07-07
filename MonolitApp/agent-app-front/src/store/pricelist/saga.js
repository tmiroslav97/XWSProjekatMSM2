
import { take, put, call, select } from 'redux-saga/effects';
import { history } from '../../index';

import PriceListService from '../../services/PriceListService';
import AdServices from '../../services/AdServices';

import {
    FETCH_PRICE_LISTS,
    FETCH_PRICE_LISTS_FROM_PUBLISHER,
    FETCH_PRICE_LIST,
    ADD_PRICE_LIST,
    EDIT_PRICE_LIST,
    DELETE_PRICE_LIST,
    REVERSE_PRICE_LIST
} from './constants';

import {
    putPriceLists,
    putPriceList
} from './actions';

import {
    putSuccessMsg
} from '../common/actions';

import {
    putAd
} from '../ad/actions';

import {
    pricelistsSelector,
    pricelistSelector
} from './selectors';

import {adSelector} from '../ad/selectors';

export function* fetchPriceLists() {
    const { payload } = yield take(FETCH_PRICE_LISTS);
    yield put(putPriceLists({ 'isFetch': false }));
    const data = yield call(PriceListService.fetchPriceLists, payload);
    yield put(putPriceLists({
        'data': data,
        'isFetch': true
    }));
}

export function* fetchPriceListsFromPublisher() {
    const { payload } = yield take(FETCH_PRICE_LISTS_FROM_PUBLISHER);
    yield put(putPriceLists({ 'isFetch': false }));
    const data = yield call(PriceListService.fetchPriceListsFromPublisher, payload);
    yield put(putPriceLists({
        'data': data,
        'isFetch': true
    }));
}

export function* fetchPriceList() {
    const { payload } = yield take(FETCH_PRICE_LIST);
    yield put(putPriceList({ 'isFetch': false }));
    const data = yield call(PriceListService.fetchPriceList, payload);
    yield put(putPriceList({
        'data': data,
        'isFetch': true
    }));
}

export function* addPriceList() {
    const { payload } = yield take(ADD_PRICE_LIST);
    const msg = yield call(PriceListService.addPriceList, payload);
    yield put(putSuccessMsg(msg));
    const temp = yield select(pricelistSelector);
    yield put(putPriceLists({ 'isFetch': false }));
    const data = yield call(PriceListService.fetchPriceListsFromPublisher, temp);
    yield put(putPriceLists({
        'data': data,
        'isFetch': true
    }));
}

export function* editPriceList() {
    const { payload } = yield take(EDIT_PRICE_LIST);
    const msg = yield call(PriceListService.editPriceList, payload);
    yield put(putSuccessMsg(msg));
    yield put(putSuccessMsg(null));
    const temp = yield select(pricelistSelector);
    yield put(putPriceLists({ 'isFetch': false }));
    const data = yield call(PriceListService.fetchPriceListsFromPublisher, temp);
    yield put(putPriceLists({
        'data': data,
        'isFetch': true
    }));
}

export function* deletePriceList() {
    const { payload } = yield take(DELETE_PRICE_LIST);
    const msg = yield call(PriceListService.deletePriceList, payload);
    yield put(putSuccessMsg(msg));
    const temp = yield select(pricelistSelector);
    yield put(putPriceLists({ 'isFetch': false }));
    const data = yield call(PriceListService.fetchPriceListsFromPublisher, temp);
    yield put(putPriceLists({
        'data': data,
        'isFetch': true
    }));
}

export function* reversePricelist() {
    const { payload } = yield take(REVERSE_PRICE_LIST);
    console.log(payload)
    const msg = yield call(AdServices.reversePricelist, payload);
    yield put(putSuccessMsg(msg));
    const temp = yield select(pricelistSelector);
    yield put(putPriceLists({ 'isFetch': false }));
    const data = yield call(PriceListService.fetchPriceListsFromPublisher, temp);
    yield put(putPriceLists({
        'data': data,
        'isFetch': true
    }));
    yield put(putAd({ 'isFetch': false }));
    const data1 = yield call(AdServices.fetchAd, payload.adId);
    yield put(putAd({
        'data': data1,
        'isFetch': true
    }));
}