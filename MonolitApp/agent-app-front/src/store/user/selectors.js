const reducer = 'userReducer';

export const tokenSelector = state => state[reducer].token;
export const endUsersSelector = state => state[reducer].endUsers;