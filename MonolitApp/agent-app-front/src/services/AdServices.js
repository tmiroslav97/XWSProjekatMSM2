import HttpBaseClient from './HttpBaseClient';

const FINALPOINTS = {
    AD_BASE: '/ad',
    IMAGE_BASE: 'image',
    CALENDAR_BASE: '/calendar'
    
};

class AdServices extends HttpBaseClient {
    
    createdAdPhotos = async payload => {
        console.log("********************")
        console.log(payload);
        console.log("********************")
        const response = await this.getApiClient().post(
            FINALPOINTS.AD_BASE + "/withImages", 
            // payload,
            { 
                params: {
                    photos0: payload.photos0,
                    photos1: payload.photos1,
                    photos2: payload.photos2,
                    photos3: payload.photos3,
                    data: payload.data
                } 
            }
            // {
            //     headers : {
            //         'Content-Type': 'multipart/form-data',
            //     },
            // }
        );
        
        return response.data;
    };

    createdAd = async payload => {
        console.log("********************")
        console.log(payload);
        const response = await this.getApiClient().post(
            FINALPOINTS.AD_BASE,
            payload,
            {
                headers: {
                    'Content-Type': 'application/json; charset=utf-8'
                }
            }
            
        );
        
        return response.data;
    };

    uploadImage = async payload => {
        const response = await this.getApiClient().post(
            FINALPOINTS.IMAGE_BASE + "/upload",
            payload,
            {
                headers : {
                    'Content-Type': 'multipart/form-data',
                },
            }
            );
        
        return response.data;
    };

    fetchAdsPaginated = async payload => {
        const response = await this.getApiClient().get(
            FINALPOINTS.AD_BASE, {
                params: {
                    nextPage: payload.nextPage,
                    size: payload.size
                }
            }
        );

        return response.data;
    };

    fetchAdsPaginatedfFromPublisher = async payload => {
        const response = await this.getApiClient().get(
            FINALPOINTS.AD_BASE + "/publisher", {
                params: {
                    nextPage: payload.nextPage,
                    size: payload.size
                }
            }
        );

        return response.data;
    };

    fetchAd = async payload => {
        console.log("SERVICE AD")
        console.log(payload)
        const response = await this.getApiClient().get(
            FINALPOINTS.AD_BASE + "/"  + payload
        
        );

        return response.data;
    };

    fetchAdsPaginatedSearch = async payload => {
        console.log("SERVICEEE SEARCH")
        console.log(payload);
        const response = await this.getApiClient().get(
            FINALPOINTS.AD_BASE + "/search", {
                params: {
                    location: payload.location,
                    startDate: payload.startDate,
                    endDate: payload.endDate,
                    nextPage: payload.nextPage,
                    size: payload.size
                }
            }
        );
            console.log(response);
        return response.data;
    };

    loadImage = async payload => {
        console.log("SERVICEEE LOAD SRC")
        console.log(payload);
        const response = await this.getApiClient().get(
            FINALPOINTS.IMAGE_BASE + "/getSrc", {
                params: {
                    ad_id: payload.ad_id,
                    name: payload.name,
                   
                }
            }
        );
            console.log(response);
        return response.data;
    };

    fetchCalendar = async payload => {
        console.log("FETCH AD")
        console.log(payload)
        const response = await this.getApiClient().get(
            FINALPOINTS.CALENDAR_BASE + "/" + payload
        );
        return response.data;
    };

    addTerm = async payload => {
        console.log("********* DODAVANJE TERM-A ***********")
        console.log(payload);
        const response = await this.getApiClient().post(
            FINALPOINTS.CALENDAR_BASE,
            payload,
            {
                headers: {
                    'Content-Type': 'application/json; charset=utf-8'
                }
            }

        );

        return response.data;
    };
}

    

export default new AdServices();