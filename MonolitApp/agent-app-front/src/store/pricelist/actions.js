import{
    FETCH_PRICE_LISTS,
    FETCH_PRICE_LISTS_FROM_PUBLISHER,
    FETCH_PRICE_LIST,
    PUT_PRICE_LISTS,
    PUT_PRICE_LIST,
    ADD_PRICE_LIST,
    EDIT_PRICE_LIST,
    DELETE_PRICE_LIST
} from './constants';

export const fetchPriceLists = payload => ({
    type: FETCH_PRICE_LISTS,
    payload
});

export const fetchPriceListsFromPublisher = payload => ({
    type: FETCH_PRICE_LISTS_FROM_PUBLISHER,
    payload
});

export const fetchPriceList = payload => ({
    type: FETCH_PRICE_LIST,
    payload
});

export const putPriceLists = payload => ({
    type: PUT_PRICE_LISTS,
    payload
});

export const putPriceList = payload => ({
    type: PUT_PRICE_LIST,
    payload
});

export const addPriceList = payload => ({
    type: ADD_PRICE_LIST,
    payload
});

export const editPriceList = payload => ({
    type: EDIT_PRICE_LIST,
    payload
});

export const deletePriceList = payload => ({
    type: DELETE_PRICE_LIST,
    payload
});