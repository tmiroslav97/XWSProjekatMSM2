import HttpBaseClient from './HttpBaseClient';

const FINALPOINTS = {
    END_USER_SERVICE_BASE: '/auth/end-user'
};

class UserService extends HttpBaseClient {

    fetchEndUsersPaginated = async payload => {
        const response = await this.getApiClient().get(
            FINALPOINTS.END_USER_SERVICE_BASE, {
            params: {
                nextPage: payload.nextPage,
                size: payload.size
            }
        }
        );
        return response.data;
    };

    blockOrUnblock = async payload => {
        const response = await this.getApiClient().put(
            FINALPOINTS.END_USER_SERVICE_BASE + '/' + payload.id,
            payload.status, {
            headers: {
                'action': 'block',
                'Content-Type': 'application/json;charset=UTF-8'
            }
        }
        );
        return response.data;
    };

    obligateOrUnobligate = async payload => {
        const response = await this.getApiClient().put(
            FINALPOINTS.END_USER_SERVICE_BASE + '/' + payload.id,
            payload.status, {
            headers: {
                'action': 'obligate',
                'Content-Type': 'application/json;charset=UTF-8'
            }
        }
        );
        return response.data;
    };

    logDelOrRevert = async payload => {
        const response = await this.getApiClient().put(
            FINALPOINTS.END_USER_SERVICE_BASE + '/' + payload.id,
            payload.status, {
            headers: {
                'action': 'log-del',
                'Content-Type': 'application/json;charset=UTF-8'
            }
        }
        );
        return response.data;
    };

};

export default new UserService();