import HttpBaseClient from './HttpBaseClient';

const FINALPOINTS = {
    REQUEST_BASE: '/req'
};

class RequestService extends HttpBaseClient {


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

};

export default new RequestService();