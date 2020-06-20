import {
    PUT_SUCCESS_MSG,
    PUT_ERROR_MSG,
    PUT_WARN_MSG,
    PUT_INFO_MSG,
    PUT_NEXT_PAGE,
    PUT_TOTAL_PAGE_CNT
} from './constants';

export const putTotalPageCnt = payload => ({
    type: PUT_TOTAL_PAGE_CNT,
    payload
});

export const putNextPage = payload => ({
    type: PUT_NEXT_PAGE,
    payload
});

export const putInfoMsg = payload => ({
    type: PUT_INFO_MSG,
    payload
});

export const putWarnMsg = payload => ({
    type: PUT_WARN_MSG,
    payload
});

export const putSuccessMsg = payload => ({
    type: PUT_SUCCESS_MSG,
    payload
});

export const putErrorMsg = payload => ({
    type: PUT_ERROR_MSG,
    payload
});