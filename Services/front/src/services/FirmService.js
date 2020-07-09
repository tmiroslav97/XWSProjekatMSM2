import HttpBaseClient from './HttpBaseClient';

const FINALPOINTS = {
    FIRM_SERVICE_BASE: '/auth/firm'
};
class FirmService extends HttpBaseClient {

    firmDeleteOrRevert = async payload => {
        try {
            const response = await this.getApiClient().put(
                FINALPOINTS.FIRM_SERVICE_BASE + '/' + payload.id,
                payload.status, {
                headers: {
                    'action': 'log-del',
                    'Content-Type': 'application/json;charset=UTF-8'
                }
            }
            );
            return response.data;
        } catch (error) {
            return error.response.data;
        }
    };

    fetchFirmsPaginated = async payload => {
        const response = await this.getApiClient().get(
            FINALPOINTS.FIRM_SERVICE_BASE, {
            params: {
                nextPage: payload.nextPage,
                size: payload.size
            }
        }
        );
        return response.data;
    };

    registerFirm = async payload => {
        try {
            const response = await this.getApiClient().post(
                FINALPOINTS.FIRM_SERVICE_BASE,
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
};

export default new FirmService();