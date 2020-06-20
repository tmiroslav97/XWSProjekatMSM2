import{
    CREATED_AD,
    CREATED_AD_PHOTOS,
    FETCH_ADS,
    FETCH_ADS_FROM_PUBLISHER,
    FETCH_AD,
    PUT_ADS,
    UPLOAD_IMAGE,
    PUT_IMAGE_NAME,
    PUT_AD,
    SEARCH_AD,
    PUT_IMAGE_SRC,
    PUT_CALENDAR,
    FETCH_CALENDAR,
    ADD_TERM,
    PUT_SEARCH_DATA
} from './constants';

export const putSearchData = payload => ({
    type: PUT_SEARCH_DATA,
    payload
});

export const createdAd = payload => ({
    type: CREATED_AD,
    payload
});

export const createdAdPhotos = payload => ({
    type: CREATED_AD_PHOTOS,
    payload
});

export const fetchAds = payload => ({
    type: FETCH_ADS,
    payload
});

export const fetchAdsFromPublisher = payload => ({
    type: FETCH_ADS_FROM_PUBLISHER,
    payload
});

export const fetchAd = payload => ({
    type: FETCH_AD,
    payload
});

export const putAds = payload => ({
    type: PUT_ADS,
    payload
});

export const uploadImage = payload => ({
    type: UPLOAD_IMAGE,
    payload
});

export const putImageName = payload => ({
    type: PUT_IMAGE_NAME,
    payload
});
export const putAd = payload => ({
    type: PUT_AD,
    payload
});

export const searchAd = payload => ({
    type: SEARCH_AD,
    payload
});

export const putImagesSrc = payload => ({
    type: PUT_IMAGE_SRC,
    payload
});

export const putCalendar = payload => ({
    type: PUT_CALENDAR,
    payload
});

export const fetchCalendar = payload => ({
    type: FETCH_CALENDAR,
    payload
});

export const addTerm = payload => ({
    type: ADD_TERM,
    payload
});
