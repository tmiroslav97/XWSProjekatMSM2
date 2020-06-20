export function putAds(state, payload) {
    return {
        ...state,
        ads: payload
    };
}

export function putImageName(state, payload) {
    return {
        ...state,
        imageName: payload
    };
}
export function putAd(state, payload) {
    return {
        ...state,
        ad: payload
    };
}
export function putImageSrc(state, payload) {
    return {
        ...state,
        imagesSrc: payload
    };
}
export function putCalendar(state, payload) {
    return {
        ...state,
        calendar: payload
    };
}

