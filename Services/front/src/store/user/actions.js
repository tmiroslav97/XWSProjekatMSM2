import {
    LOGIN,
    PUT_TOKEN,
    REGISTER_USER,
    SIGN_OUT,
    FETCH_END_USERS_PAGINATED,
    PUT_END_USERS,
    BLOCK_OR_UNBLOCK,
    OBLIGATE_OR_UNOBLIGATE,
    LOG_DEL_OR_REVERT
} from './constants';

//end users
export const putEndUsers = payload => ({
    type: PUT_END_USERS,
    payload
});

export const fetchEndUsersPaginated = payload => ({
    type: FETCH_END_USERS_PAGINATED,
    payload
});

export const blockOrUnblock = payload => ({
    type: BLOCK_OR_UNBLOCK,
    payload
});

export const obligateOrUnobligate = payload => ({
    type: OBLIGATE_OR_UNOBLIGATE,
    payload
});

export const logDelOrRevert = payload => ({
    type: LOG_DEL_OR_REVERT,
    payload
});

//users
export const signOut = payload => ({
    type: SIGN_OUT,
    payload
});

export const registerUser = payload => ({
    type: REGISTER_USER,
    payload
});

export const putToken = payload => ({
    type: PUT_TOKEN,
    payload
});

export const loginUser = payload => ({
    type: LOGIN,
    payload
});