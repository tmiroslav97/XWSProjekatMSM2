
import { take, put, call, select } from 'redux-saga/effects';
import { history } from '../../index';

import AdServices from '../../services/AdServices';

import {
    CREATED_AD,
    CREATED_AD_PHOTOS,
    FETCH_ADS,
    FETCH_ADS_FROM_PUBLISHER,
    FETCH_AD,
    UPLOAD_IMAGE,
    SEARCH_AD,
    PUT_IMAGE_SRC,
    PUT_CALENDAR,
    FETCH_CALENDAR,
    ADD_TERM
} from './constants';

import {
    putAds,
    putImageName,
    putAd,
    putImageSrc,
    putCalendar
} from './actions';

import {
    putSuccessMsg
} from '../common/actions';

import {
    imageNameSelector,
    calendarSelector
} from './selectors';


export function* createdAd(){
    const { payload } = yield take(CREATED_AD);
    const data = yield call(AdServices.createdAd, payload); 
    yield put(putSuccessMsg(data));
    history.push('/');
}

//pokusaj poziva metode sa slikom 
export function* createdAdPhotos(){
    const { payload } = yield take(CREATED_AD_PHOTOS);
    const data = yield call(AdServices.createdAdPhotos, payload); 
    console.log("sagicaaa");
    console.log(data);
    // yield put(putSuccessMsg(data));
    // history.push('/');
}

export function* fetchAds() {
    const { payload } = yield take(FETCH_ADS);
    yield put(putAds({ 'isFetch': false }));
    const data = yield call(AdServices.fetchAdsPaginated, payload);
    yield put(putAds({
        'data': data.ads,
        'totalPageCnt': data.totalPageCnt,
        'nextPage': payload.nextPage,
        'size': payload.size,
        'isFetch': true
    }));
}

export function* fetchAdsFromPublisher() {
    const { payload } = yield take(FETCH_ADS_FROM_PUBLISHER);
    yield put(putAds({ 'isFetch': false }));
    const data = yield call(AdServices.fetchAdsPaginatedfFromPublisher, payload);
    yield put(putAds({
        'data': data.ads,
        'totalPageCnt': data.totalPageCnt,
        'nextPage': payload.nextPage,
        'size': payload.size,
        'isFetch': true
    }));
}

export function* fetchAd() {
    const { payload } = yield take(FETCH_AD);
    var id = payload.adId
    yield put(putAd({ 'isFetch': false }));
    const data = yield call(AdServices.fetchAd, id);
    yield put(putAd({
        'data': data,
        'isFetch': true
    }));
}

export function* uploadImage(){
    const { payload } = yield take(UPLOAD_IMAGE);
    const temp = yield select(imageNameSelector);
    yield put(putImageName({ 'isFetch': false }));
    const data = yield call(AdServices.uploadImage, payload); 
    yield temp.push(data);
    yield put(putImageName({
        'data': temp,
        'isFetch': true
    }));
    
}

export function* searchAd(){
    const { payload } = yield take(SEARCH_AD);
    yield put(putAds({ 'isFetch': false }));
    const data = yield call(AdServices.fetchAdsPaginatedSearch, payload.data);
    yield put(putAds({
        'data': data.ads,
        'totalPageCnt': data.totalPageCnt,
        'nextPage': payload.nextPage,
        'size': payload.size,
        'isFetch': true
    }));
    
}

export function* loadImage(){
    const { payload } = yield take(PUT_IMAGE_SRC);
    const data = yield call(AdServices.loadImage, payload); 

    // yield temp.push(data);
    // yield put(putImageName({
    //     'data': temp,
    //     'isFetch': true
    // }));
    
}
//PROVERITI
export function* fetchCalendar() {
    const { payload } = yield take(FETCH_CALENDAR);
    console.log("SAGA "+ payload.id)
    // const temp = yield select(calendarSelector);
    const temp = [];
    console.log(temp);
    yield put(putCalendar({ 'isFetch': false }));
    const data = yield call(AdServices.fetchCalendar, payload.id);
    data.map((term)=>{
        console.log(term);
        temp.push({
            'startDate': term.startDate,
            'endDate': term.endDate
        });
    })
    yield put(putCalendar({
        'data': temp,
        'isFetch': true
    }));
}

export function* addTerm(){
    const { payload } = yield take(ADD_TERM);
    const temp = yield select(calendarSelector);
    yield put(putCalendar({ 'isFetch': false }));
    console.log("sagaaa")
    console.log(payload);
    const data = yield call(AdServices.addTerm, payload); 
    console.log(data.adId);
    console.log(data.startDate);
    console.log(data.endDate);
    yield temp.data.push({
        'startDate': data.startDate,
        'endDate': data.endDate
    });
    yield put(putCalendar({
        'data': temp.data,
        'isFetch': true
    }));    
    console.log(temp);
}
