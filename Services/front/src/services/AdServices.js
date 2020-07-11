import HttpBaseClient from './HttpBaseClient';

const FINALPOINTS = {
    AD_SEARCH_BASE: '/ad-search',
    AD_BASE: '/ad/ad',
    IMAGE_BASE: '/ad/image',
    CALENDAR_BASE: 'ad/calendar',
    COMMENT_BASE: 'ad/comment',


};

class AdServices extends HttpBaseClient {

    createdAdPhotos = async payload => {

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
                headers: {
                    'Content-Type': 'multipart/form-data',
                },
            }
        );

        return response.data;
    };

    getImageBase64 = async payload => {
        const response = await this.getApiClient().get(
            FINALPOINTS.IMAGE_BASE + "/" + payload,
        );

        return response.data;
    };

    fetchAdsPaginated = async payload => {
        const response = await this.getApiClient().get(
            FINALPOINTS.AD_SEARCH_BASE + '/ad', {
            params: {
                nextPage: payload.nextPage,
                size: payload.size,
                sort: payload.sort
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
 
        const response = await this.getApiClient().get(
            FINALPOINTS.AD_BASE + "/" + payload

        );

        return response.data;
    };

    fetchAdsPaginatedSearch = async payload => {
        const response = await this.getApiClient().get(
            FINALPOINTS.AD_SEARCH_BASE + '/ad/search', {
            params: {
                location: payload.location,
                startDate: payload.startDate,
                endDate: payload.endDate,
                carManufacturer: payload.carManufacturer,
                carModel: payload.carModel,
                carType: payload.carType,
                mileage: payload.mileage,
                mileageKM: payload.mileageKM,
                gearboxType: payload.gearboxType,
                fuelType: payload.fuelType,
                childrenSeatNum: payload.childrenSeatNum,
                cdw: payload.cdw,
                startPrice: payload.startPrice,
                endPrice: payload.endPrice,
                advancedSearch: payload.advancedSearch,
                nextPage: payload.nextPage,
                size: payload.size,
                sort: payload.sort
            }
        }
        );
        return response.data;
    };

    loadImage = async payload => {

        const response = await this.getApiClient().get(
            FINALPOINTS.IMAGE_BASE + "/getSrc", {
            params: {
                ad_id: payload.ad_id,
                name: payload.name,

            }
        }
        );
        return response.data;
    };

    fetchCalendar = async payload => {
        const response = await this.getApiClient().get(
            FINALPOINTS.CALENDAR_BASE + "/" + payload
        );
        return response.data;
    };

    addOccupationTerm = async payload => {
        try {
            const response = await this.getApiClient().post(
                FINALPOINTS.CALENDAR_BASE + "/occupation",
                payload,
                {
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    }
                }

            );
            return response.data;
        } catch (error) {
            return error.response.data;
        }
    };

    addTerm = async payload => {
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

    ratingAd = async payload => {
        const response = await this.getApiClient().post(
            FINALPOINTS.AD_BASE + "/rating",
            payload,
            {
                headers: {
                    'Content-Type': 'application/json; charset=utf-8'
                }
            }

        );

        return response.data;
    };

    addCommentForAd = async payload => {
        const response = await this.getApiClient().post(
            FINALPOINTS.COMMENT_BASE,
            payload,
            {
                headers: {
                    'Content-Type': 'application/json; charset=utf-8'
                }
            }
        );

        return response.data;
    };

    fetchAllUnapprovedCommentForAd = async payload => {
        const response = await this.getApiClient().get(
            FINALPOINTS.COMMENT_BASE + "/all-unapproved"
        );
        return response.data;
    };

    fetchAllCommentForAd = async payload => {
        const response = await this.getApiClient().get(
            FINALPOINTS.COMMENT_BASE + "/" + payload.id
        );
        return response.data;
    };

    fetchAllCommentForAdAndUser = async payload => {
        const response = await this.getApiClient().get(
            FINALPOINTS.COMMENT_BASE + "/from-user/" + payload.id
        );
        return response.data;
    };

    approvedCommentForAd = async payload => {
        const response = await this.getApiClient().get(
            FINALPOINTS.COMMENT_BASE + "/approved/" + payload.id
        );
        return response.data;
    };

    reversePricelist = async payload => {
        const response = await this.getApiClient().post(
            FINALPOINTS.AD_BASE + "/reverse-pricelist",
            payload
        );
        return response.data;
    };

    fetchBestGradeAd = async payload => {
        const response = await this.getApiClient().get(
            FINALPOINTS.AD_BASE + "/best-average-grade", {
            params: {
                email: payload
            }
        }
        );
        return response.data;
    };

    fetchMaxMileageAd = async payload => {

        const response = await this.getApiClient().get(
            FINALPOINTS.AD_BASE + "/max-mileage", {
            params: {
                email: payload
            }
        }
        );
        return response.data;
    };

    fetchMaxCommentsAd = async payload => {

        const response = await this.getApiClient().get(
            FINALPOINTS.AD_BASE + "/max-comments", {
            params: {
                email: payload
            }
        }
        );
        return response.data;
    };

}



export default new AdServices();