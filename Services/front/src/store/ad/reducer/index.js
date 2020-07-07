import * as computationFunctions from './computation-functions';

import {
    PUT_ADS,
    PUT_IMAGE_NAME,
    PUT_AD,
    PUT_IMAGE_SRC,
    PUT_CALENDAR,
    PUT_SEARCH_DATA,
    PUT_COMMENTS
} from '../constants';

const initialState = {
    ads: {
        id: 0,
        data: [],
        totalPageCnt: 0,
        nextPage: 0,
        size: 10,
        sort:  'name asc',
        isFetch: false
    },
    imageName: {
        data: [],
        isFetch: false
    },
    ad: {
        data: [],
        isFetch: false
    },
    imagesSrc: {
        src: null,
        ad_id: 0,
        name: null,
        isFetch: false
    },
    calendar:{
        data: [],
        isFetch: false
    },
    searchData:{
        location: '',
        startDate: '',
        endDate: '',
        carManufacturer: '',
        carModel: '',
        carType: '',
        mileage: '',
        mileageKM: '',
        gearboxType: '',
        fuelType: '',
        childrenSeatNum: 0,
        cdw: false,
        startPrice: 0,
        endPrice: 0,
        advancedSearch: false,
        nextPage: 0,
        size : 10,
        sort: 'name asc'
    },
    comments:{
        data: [],
        // totalPageCnt: 0,
        // nextPage: 0,
        // size: 10,
        isFetch: false
    }

};

const adReducer = (state = initialState, { type, payload }) => {
    if (actionHandler.hasOwnProperty(type)) {
        return actionHandler[type](state, payload);
    }
    return state;
};

const actionHandler = {
    [PUT_ADS]: computationFunctions.putAds,
    [PUT_IMAGE_NAME]: computationFunctions.putImageName,
    [PUT_AD]: computationFunctions.putAd,
    [PUT_IMAGE_SRC]: computationFunctions.putImageSrc,
    [PUT_CALENDAR]: computationFunctions.putCalendar,
    [PUT_SEARCH_DATA]: computationFunctions.putSearchData,
    [PUT_COMMENTS]: computationFunctions.putComments
};

export default adReducer;