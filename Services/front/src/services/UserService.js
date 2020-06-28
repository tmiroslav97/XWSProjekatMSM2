import HttpBaseClient from './HttpBaseClient';
import { useDispatch } from 'react-redux';
import { putErrorMsg } from '../store/common/actions';

const FINALPOINTS = {
    END_USER_SERVICE_BASE: '/auth/end-user',
    AGENT_SERVICE_BASE: '/auth/agent'
};

class UserService extends HttpBaseClient {

    fetchAgentsPaginated = async payload => {
        const response = await this.getApiClient().get(
            FINALPOINTS.AGENT_SERVICE_BASE, {
            params: {
                nextPage: payload.nextPage,
                size: payload.size
            }
        }
        );
        return response.data;
    };

    registerAgent = async payload => {
        try {
            const response = await this.getApiClient().post(
                FINALPOINTS.AGENT_SERVICE_BASE,
                payload, {
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