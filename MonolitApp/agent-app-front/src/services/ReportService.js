import HttpBaseClient from './HttpBaseClient';

const FINALPOINTS = {
    REPORT_BASE: '/rep'
};

class ReportService extends HttpBaseClient {

    submitReport = async payload => {
        try {
            const response = await this.getApiClient().post(
                FINALPOINTS.REPORT_BASE,
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

export default new ReportService();