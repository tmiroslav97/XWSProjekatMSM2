const reducer = 'commonReducer';

export const errorSelector = state => state[reducer].error;
export const successSelector = state => state[reducer].success;
export const warnSeletor = state => state[reducer].warn;
export const infoSelector = state => state[reducer].info;

export const totalPageCntSelector = state => state[reducer].totalPageCnt;
export const nextPageSelector = state => state[reducer].nextPage;