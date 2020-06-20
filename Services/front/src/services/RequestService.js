import HttpBaseClient from './HttpBaseClient';

const FINALPOINTS = {
    REQUEST_BASE: '/carreq'
};

class RequestService extends HttpBaseClient {

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
    };

};

export default new RequestService();