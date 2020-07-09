import HttpBaseClient from './HttpBaseClient';

const FINALPOINTS = {
    MSG_BASE: '/msg'
};

class MessageService extends HttpBaseClient {

    sendFirstMessage = async payload => {
        try {
            const response = await this.getApiClient().post(
                FINALPOINTS.MSG_BASE + '/first',
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


};

export default new MessageService();