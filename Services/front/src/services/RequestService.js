import HttpBaseClient from './HttpBaseClient';

const FINALPOINTS = {
    REQUEST_BASE: '/carreq'
};

class RequestService extends HttpBaseClient {

    fetchRequest = async payload => {
        const response = await this.getApiClient().get(
            FINALPOINTS.REQUEST_BASE + '/' + payload.id,
            {
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                }
            }
        );
        return response.data;
    };

    fetchAllByEndUserIdAndStatus = async payload => {
        const response = await this.getApiClient().get(
            FINALPOINTS.REQUEST_BASE + '/end-user',
            {
                headers: {
                    'status': payload.status,
                    'Content-Type': 'application/json;charset=UTF-8'
                }
            }
        );
        return response.data;
    };

    fetchAllByAgentIdAndStatus = async payload => {
        const response = await this.getApiClient().get(
            FINALPOINTS.REQUEST_BASE + '/publisher-user',
            {
                headers: {
                    'status': payload.status,
                    'Content-Type': 'application/json;charset=UTF-8'
                }
            }
        );
        return response.data;
    };

    submitReq = async payload => {
        try {
            const response = await this.getApiClient().post(
                FINALPOINTS.REQUEST_BASE,
                payload,
                {
                    headers: {
                        'Content-Type': 'application/json;charset=UTF-8'
                    }
                }
            );
            return response.data;
        } catch (error) {
            return error.response.data;
        }
    };

    acceptRequest = async payload => {
        try {
            const response = await this.getApiClient().put(
                FINALPOINTS.REQUEST_BASE,
                payload,
                {
                    headers: {
                        'Content-Type': 'application/json;charset=UTF-8'
                    }
                }
            );
            return response.data;
        } catch (error) {
            return error.response.data;
        }
    };

    quitRequest = async payload => {
        try {
            const response = await this.getApiClient().put(
                FINALPOINTS.REQUEST_BASE + '/end-user',
                payload,
                {
                    headers: {
                        'Content-Type': 'application/json;charset=UTF-8'
                    }
                }
            );
            return response.data;

        } catch (error) {
            return error.response.data;
        }
    };

    fetchCoordinates = async payload => {
        try {
            const response = await this.getApiClient().get(
                FINALPOINTS.REQUEST_BASE + '/coords',
                {
                    headers: {
                        'Content-Type': 'application/json;charset=UTF-8'
                    }
                }
            );
            return response.data;

        } catch (error) {
            return error.response.data;
        }
    };
};

export default new RequestService();