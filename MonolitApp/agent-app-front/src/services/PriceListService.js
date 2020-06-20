import HttpBaseClient from './HttpBaseClient';

const FINALPOINTS = {
    PRICE_LIST_BASE: '/pricelist'
};

class PriceListService extends HttpBaseClient {


    fetchPriceLists = async payload => {
        
        console.log(payload)
        const response = await this.getApiClient().get(
            FINALPOINTS.PRICE_LIST_BASE 
        );

        return response.data;
    };

    fetchPriceListsFromPublisher = async payload => {
        
        console.log(payload)
        const response = await this.getApiClient().get(
            FINALPOINTS.PRICE_LIST_BASE + "/publisher"
        );

        return response.data;
    };

    fetchPriceList = async payload => {
        
        console.log(payload)
        const response = await this.getApiClient().get(
            FINALPOINTS.PRICE_LIST_BASE + "/" + payload.id
        );

        return response.data;
    };

    addPriceList = async payload => {
        const response = await this.getApiClient().post(
            FINALPOINTS.PRICE_LIST_BASE,
            payload
        );
        return response.data;
    };

    editPriceList = async payload => {
        const response = await this.getApiClient().put(
            FINALPOINTS.PRICE_LIST_BASE,
            payload
        );
        return response.data;
    };

    deletePriceList = async payload => {
        const response = await this.getApiClient().delete(
            FINALPOINTS.PRICE_LIST_BASE, {
                params: { 'id': payload }
            }
        );
        return response.data;
    };

}

export default new PriceListService();