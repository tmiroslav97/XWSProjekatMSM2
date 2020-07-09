
import { take, put, call, select } from 'redux-saga/effects';
import { history } from '../../index';

import AdServices from '../../services/AdServices';
import PriceListService from '../../services/PriceListService';

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
    ADD_TERM,
    RATING_AD,
    ADD_COMMENT,
    FETCH_COMMENTS,
    APPROVED_COMMENT,
    FETCH_ALL_COMMENTS_FROM_USER,
    FETCH_ALL_COMMENTS,
    PUT_DISCOUNTS,
    FETCH_DISCOUNTS,
    FETCH_DISCOUNTS_FROM_AGENT, 
    ADD_DISCOUNT,
    EDIT_DISCOUNT,
    DELETE_DISCOUNT,
    ADD_DISCOUNT_TO_AD,
    REMOVE_DISCOUNT_FROM_AD
} from './constants';

import {
    putAds,
    putImageName,
    putAd,
    putImageSrc,
    putCalendar,
    putComments,
    putDiscounts
} from './actions';

import {
    putSuccessMsg
} from '../common/actions';

import {
    imageNameSelector,
    calendarSelector,
    discountsSelector
} from './selectors';


export function* createdAd() {
    const { payload } = yield take(CREATED_AD);
    const data = yield call(AdServices.createdAd, payload);
    yield put(putSuccessMsg(data));
    history.push('/');
}

//pokusaj poziva metode sa slikom 
export function* createdAdPhotos() {
    const { payload } = yield take(CREATED_AD_PHOTOS);
    const data = yield call(AdServices.createdAdPhotos, payload);
    console.log("sagicaaa");
    console.log(data);
    // yield put(putSuccessMsg(data));
    // history.push('/');
}

export function* fetchAds() {
    const { payload } = yield take(FETCH_ADS);
    console.log(payload)

    yield put(putAds({ 'isFetch': false }));
    const data = yield call(AdServices.fetchAdsPaginated, payload);
    yield put(putAds({
        'data': data.ads,
        'totalPageCnt': data.totalPageCnt,
        'nextPage': payload.nextPage,
        'size': payload.size,
        'sort': payload.sort,
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

export function* uploadImage() {
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

export function* searchAd() {
    const { payload } = yield take(SEARCH_AD);
    yield put(putAds({ 'isFetch': false }));
    const data = yield call(AdServices.fetchAdsPaginatedSearch, payload.data);
    yield put(putAds({
        'data': data.ads,
        'totalPageCnt': data.totalPageCnt,
        'nextPage': payload.data.nextPage,
        'size': payload.data.size,
        'sort': payload.data.sort,
        'isFetch': true
    }));

}

export function* loadImage() {
    const { payload } = yield take(PUT_IMAGE_SRC);
    const data = yield call(AdServices.loadImage, payload);

    // yield temp.push(data);
    // yield put(putImageName({
    //     'data': temp,
    //     'isFetch': true
    // }));

}

export function* fetchCalendar() {
    const { payload } = yield take(FETCH_CALENDAR);
    console.log("SAGA " + payload.id)
    // const temp = yield select(calendarSelector);
    const temp = [];
    console.log(temp);
    yield put(putCalendar({ 'isFetch': false }));
    const data = yield call(AdServices.fetchCalendar, payload.id);
    data.map((term) => {
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

export function* addTerm() {
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

export function* ratingAd() {
    const { payload } = yield take(RATING_AD);

    console.log("sagaaa")
    console.log(payload);
    const data = yield call(AdServices.ratingAd, payload);
    console.log(data);
    yield put(putSuccessMsg(data));

}

export function* addComment() {
    const { payload } = yield take(ADD_COMMENT);

    console.log("sagaaa")
    console.log(payload);
    const data = yield call(AdServices.addCommentForAd, payload);
    console.log(data);
    yield put(putSuccessMsg(data));

}

export function* fetchComments() {
    const { payload } = yield take(FETCH_COMMENTS);
    yield put(putComments({ 'isFetch': false }));
    const data = yield call(AdServices.fetchAllUnapprovedCommentForAd, payload);
    console.log(data);
    yield put(putComments({
        'data': data,
        // 'totalPageCnt': data.totalPageCnt,
        // 'nextPage': payload.nextPage,
        // 'size': payload.size,
        'isFetch': true
    }));
}

export function* fetchAllCommentsFromUser() {
    const { payload } = yield take(FETCH_ALL_COMMENTS_FROM_USER);
    yield put(putComments({ 'isFetch': false }));
    const data = yield call(AdServices.fetchAllCommentForAdAndUser, payload);
    console.log(data);
    yield put(putComments({
        'data': data,
        // 'totalPageCnt': data.totalPageCnt,
        // 'nextPage': payload.nextPage,
        // 'size': payload.size,
        'isFetch': true
    }));
}

export function* fetchAllComments() {
    const { payload } = yield take(FETCH_ALL_COMMENTS);
    yield put(putComments({ 'isFetch': false }));
    const data = yield call(AdServices.fetchAllCommentForAd, payload);
    console.log(data);
    yield put(putComments({
        'data': data,
        // 'totalPageCnt': data.totalPageCnt,
        // 'nextPage': payload.nextPage,
        // 'size': payload.size,
        'isFetch': true
    }));
}

export function* approvedComment() {
    const { payload } = yield take(APPROVED_COMMENT);
    const data = yield call(AdServices.approvedCommentForAd, payload);
    console.log(data);
    yield put(putSuccessMsg(data));
    yield put(putComments({ 'isFetch': false }));
    const data2 = yield call(AdServices.fetchAllUnapprovedCommentForAd, payload);
    console.log(data2);
    yield put(putComments({
        'data': data2,
        // 'totalPageCnt': data.totalPageCnt,
        // 'nextPage': payload.nextPage,
        // 'size': payload.size,
        'isFetch': true
    }));
}

export function* fetchDiscounts(){
    const { payload } = yield take(FETCH_DISCOUNTS);
    yield put(putDiscounts({ 'isFetch': false }));
    const data = yield call(PriceListService.fetchAllDicounts, payload);
    console.log(data);
    yield put(putDiscounts({
        'data': data,
        'isFetch': true
    }));
}

export function* fetchDiscountsFromAgent(){
    const { payload } = yield take(FETCH_DISCOUNTS_FROM_AGENT);
    yield put(putDiscounts({ 'isFetch': false }));
    const data = yield call(PriceListService.fetchAllDicountsFromAgent, payload);
    yield put(putDiscounts({
        'data': data,
        'isFetch': true
    }));
}

export function* addDiscount(){
    const { payload } = yield take(ADD_DISCOUNT);
    const msg = yield call(PriceListService.addDiscount, payload);
    yield put(putSuccessMsg(msg));
    const temp = yield select(discountsSelector);
    yield put(putDiscounts({ 'isFetch': false }));
    const data = yield call(PriceListService.fetchAllDicountsFromAgent, temp);
    yield put(putDiscounts({
        'data': data,
        'isFetch': true
    }));
}

export function* editDiscount(){
    const { payload } = yield take(EDIT_DISCOUNT);
    const msg = yield call(PriceListService.editDiscount, payload);
    yield put(putSuccessMsg(msg));
    const temp = yield select(discountsSelector);
    yield put(putDiscounts({ 'isFetch': false }));
    const data = yield call(PriceListService.fetchAllDicountsFromAgent, temp);
    yield put(putDiscounts({
        'data': data,
        'isFetch': true
    }));
}

export function* deleteDiscount(){
    const { payload } = yield take(DELETE_DISCOUNT);
    const msg = yield call(PriceListService.deleteDiscount, payload);
    yield put(putSuccessMsg(msg));
    
    const temp = yield select(discountsSelector);
    yield put(putDiscounts({ 'isFetch': false }));
    const data = yield call(PriceListService.fetchAllDicountsFromAgent, temp);
    yield put(putDiscounts({
        'data': data,
        'isFetch': true
    }));
}

export function* addDiscountToAd(){
    const { payload } = yield take(ADD_DISCOUNT_TO_AD);
    const msg = yield call(PriceListService.addDiscountToAd, payload);
    yield put(putSuccessMsg(msg));
    
    const temp = yield select(discountsSelector);
    yield put(putDiscounts({ 'isFetch': false }));
    const data = yield call(PriceListService.fetchAllDicountsFromAgent, temp);
    yield put(putDiscounts({
        'data': data,
        'isFetch': true
    }));
}

export function* removeDiscountFromAd(){
    const { payload } = yield take(REMOVE_DISCOUNT_FROM_AD);
    const msg = yield call(PriceListService.removeDiscountFromAd, payload);
    yield put(putSuccessMsg(msg));
    
    const temp = yield select(discountsSelector);
    yield put(putDiscounts({ 'isFetch': false }));
    const data = yield call(PriceListService.fetchAllDicountsFromAgent, temp);
    yield put(putDiscounts({
        'data': data,
        'isFetch': true
    }));
}
