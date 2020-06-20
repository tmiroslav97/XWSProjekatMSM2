const reducer = 'adReducer';

export const adsSelector = state => state[reducer].ads;
export const imageNameSelector = state => state[reducer].imageName.data;
export const adSelector = state => state[reducer].ad;
export const imageSrcSelector = state => state[reducer].imagesSrc;
export const calendarSelector = state => state[reducer].calendar;
export const searchDataSelector = state => state[reducer].searchData;