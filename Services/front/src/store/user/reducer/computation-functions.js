export function putToken(state, payload) {
    return {
        ...state,
        token: payload
    };
}

export function putEndUsers(state, payload) {
    return {
        ...state,
        endUsers: payload
    };
}
  