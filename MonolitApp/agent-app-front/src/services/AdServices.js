import HttpBaseClient from './HttpBaseClient';

const FINALPOINTS = {
    AD_BASE: '/ad',
    IMAGE_BASE: 'image',
    CALENDAR_BASE: '/calendar',
    PUBLISHER_BASE: '/publisher',
    COMMENT_BASE: '/comment'

};

class AdServices extends HttpBaseClient {

    syncData = async payload => {
        try {
            const response = await this.getApiClient().post(
                FINALPOINTS.AD_BASE + '/sync',
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
                headers: {
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
            FINALPOINTS.PUBLISHER_BASE + "/publisher", {
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
            FINALPOINTS.AD_BASE + "/" + payload

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

    ratingAd = async payload => {
        console.log("********* DODAVANJE OCENE ***********")
        console.log(payload);
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
        console.log("********* DODAVANJE KOMENTARA ***********")
        console.log(payload);
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
        console.log("FETCH COMMENTS")
        const response = await this.getApiClient().get(
            FINALPOINTS.COMMENT_BASE + "/all-unapproved"
        );
        return response.data;
    };

    fetchAllCommentForAd = async payload => {
        console.log("FETCH COMMENTS")
        const response = await this.getApiClient().get(
            FINALPOINTS.COMMENT_BASE + "/" + payload.id
        );
        return response.data;
    };

    fetchAllCommentForAdAndUser = async payload => {
        console.log("FETCH COMMENTS")
        const response = await this.getApiClient().get(
            FINALPOINTS.COMMENT_BASE + "/from-user/" + payload.id
        );
        return response.data;
    };

    approvedCommentForAd = async payload => {
        console.log("APPROVED COMMENTS")
        const response = await this.getApiClient().get(
            FINALPOINTS.COMMENT_BASE + "/approved/" + payload.id
        );
        return response.data;
    };


    fetchBestGradeAd = async payload => {
        console.log("Best grade ad service")
        console.log(payload)
        const response = await this.getApiClient().get(
            FINALPOINTS.PUBLISHER_BASE + "/best-average-grade", {
            params: {
                email: payload
            }
        }
        );
        console.log(response);
        return response.data;
    };

    fetchMaxMileageAd = async payload => {
        console.log("Max mileage service")
        console.log(payload)
        const response = await this.getApiClient().get(
            FINALPOINTS.PUBLISHER_BASE + "/max-mileage", {
            params: {
                email: payload
            }
        }
        );
        console.log(response);
        return response.data;
    };

    fetchMaxCommentsAd = async payload => {
        console.log("Max comments service")
        console.log(payload)
        const response = await this.getApiClient().get(
            FINALPOINTS.PUBLISHER_BASE + "/max-comments", {
            params: {
                email: payload
            }
        }
        );
        console.log(response);
        return response.data;
    };
}



export default new AdServices();