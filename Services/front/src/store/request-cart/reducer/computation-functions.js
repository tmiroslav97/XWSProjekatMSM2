export function putToCart(state, payload) {
    return {
        ...state,
        cart: payload
    };
}
