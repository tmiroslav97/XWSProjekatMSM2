import HttpBaseClient from './HttpBaseClient';

const FINALPOINTS = {
    PRICE_LIST_BASE: '/pad/pricelist',
    DISCOUNT_BASE: 'pad/discount-list'
};

class PriceListService extends HttpBaseClient {


    fetchPriceLists = async payload => {

        console.log(payload)
        const response = await this.getApiClient().get(
            FINALPOINTS.PRICE_LIST_BASE
        );

        return response.data;
    };

    fetchPriceListsFromPublisher = async () => {
        const response = await this.getApiClient().get(
            FINALPOINTS.PRICE_LIST_BASE + '/publisher'
        );

        return response.data;
    };

    fetchPriceList = async payload => {
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

    fetchAllDicounts = async payload => {
        console.log("FETCH DISCOUNTS")
        const response = await this.getApiClient().get(
            FINALPOINTS.DISCOUNT_BASE 
        );
        return response.data;
    };

    fetchAllDicountsFromAgent = async payload => {
        console.log("FETCH DISCOUNTS")
        const response = await this.getApiClient().get(
            FINALPOINTS.DISCOUNT_BASE + "/agent"
        );
        return response.data;
    };

    fetchAllDicountsFromAd = async payload => {
        console.log("FETCH DISCOUNTS")
        const response = await this.getApiClient().get(
            FINALPOINTS.DISCOUNT_BASE,
            {
                params: { 'id': payload }
            }
        );
        return response.data;
    };

    addDiscount = async payload => {
        console.log("add discount")
        const response = await this.getApiClient().post(
            FINALPOINTS.DISCOUNT_BASE, 
            payload
        );
        return response.data;
    };

    editDiscount = async payload => {
        console.log("edit discount")
        const response = await this.getApiClient().put(
            FINALPOINTS.DISCOUNT_BASE, 
            payload
        );
        return response.data;
    };

    deleteDiscount = async payload => {
        console.log("delete discount")
        const response = await this.getApiClient().delete(
            FINALPOINTS.DISCOUNT_BASE,
            {
                params: { 'id': payload }
            }
        );
        return response.data;
    };

    addDiscountToAd = async payload => {
        console.log("add discount to ad")
        const response = await this.getApiClient().post(
            FINALPOINTS.DISCOUNT_BASE + "/add-discount-to-ad/"
             + payload.discountId + "/" + payload.adId
        );
        return response.data;
    };

    removeDiscountFromAd = async payload => {
        console.log("add discount to ad")
        const response = await this.getApiClient().post(
            FINALPOINTS.DISCOUNT_BASE + "/remove-discount-from-ad/"
             + payload.discountId + "/" + payload.adId
        );
        return response.data;
    };

}

export default new PriceListService();