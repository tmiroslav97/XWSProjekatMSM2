export function putPriceLists(state, payload) {
    return {
        ...state,
        pricelists: payload
    };
}

export function putPriceList(state, payload) {
    return {
        ...state,
        pricelist: payload
    };
}