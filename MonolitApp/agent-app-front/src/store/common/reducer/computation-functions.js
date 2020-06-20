export function putError(state, payload) {
    return {
        ...state,
        error: payload
    };
}

export function putSuccess(state, payload) {
    return {
        ...state,
        success: payload
    };
}


export function putWarn(state, payload) {
    return {
        ...state,
        warn: payload
    };
}

export function putInfo(state, payload) {
    return {
        ...state,
        info: payload
    };
}

export function putNextPage(state, payload) {
    return {
        ...state,
        nextPage: payload
    };
}

export function putTotalPageCnt(state, payload) {
    return {
        ...state,
        totalPageCnt: payload
    };
}