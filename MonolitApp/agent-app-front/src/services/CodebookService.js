import HttpBaseClient from './HttpBaseClient';

const FINALPOINTS = {
    CAR_MANUFACTURER_BASE: '/car-man',
    CAR_TYPE_BASE: '/car-type',
    FUEL_TYPE_BASE: '/fuel-type',
    GEARBOX_TYPE_BASE: '/gb-type',
    CAR_MODEL_BASE: '/car-model'
};

class CodebookService extends HttpBaseClient {

    //for car models
    fetchAllCarModels = async payload => {
        const response = await this.getApiClient().get(
            FINALPOINTS.CAR_MANUFACTURER_BASE + '/' + payload.id + FINALPOINTS.CAR_MODEL_BASE
        );
        return response.data;
    };

    fetchCarModelsPaginated = async payload => {
        const response = await this.getApiClient().get(
            FINALPOINTS.CAR_MODEL_BASE, {
            params: {
                nextPage: payload.nextPage,
                size: payload.size
            }
        }
        );
        return response.data;
    };

    addCarModel = async payload => {
        const response = await this.getApiClient().post(
            FINALPOINTS.CAR_MODEL_BASE,
            payload
        );
        return response.data;
    };

    editCarModel = async payload => {
        const response = await this.getApiClient().put(
            FINALPOINTS.CAR_MODEL_BASE,
            payload
        );
        return response.data;
    };

    deleteCarModel = async payload => {
        const response = await this.getApiClient().delete(
            FINALPOINTS.CAR_MODEL_BASE, {
            params: { 'id': payload }
        }
        );
        return response.data;
    };

    //for gearbox types
    fetchAllGearboxTypes = async payload => {
        const response = await this.getApiClient().get(
            FINALPOINTS.GEARBOX_TYPE_BASE
        );
        return response.data;
    };

    fetchGearboxTypesPaginated = async payload => {
        const response = await this.getApiClient().get(
            FINALPOINTS.GEARBOX_TYPE_BASE, {
            params: {
                nextPage: payload.nextPage,
                size: payload.size
            }
        }
        );
        return response.data;
    };

    addGearboxType = async payload => {
        const response = await this.getApiClient().post(
            FINALPOINTS.GEARBOX_TYPE_BASE,
            payload, {
            headers: { 'Content-Type': 'text/plain;charset=UTF-8' }
        }
        );
        return response.data;
    };

    editGearboxType = async payload => {
        const response = await this.getApiClient().put(
            FINALPOINTS.GEARBOX_TYPE_BASE,
            payload
        );
        return response.data;
    };

    deleteGearboxType = async payload => {
        const response = await this.getApiClient().delete(
            FINALPOINTS.GEARBOX_TYPE_BASE, {
            params: { 'id': payload }
        }
        );
        return response.data;
    };

    //for fuel types
    fetchAllGearboxTypes = async payload => {
        const response = await this.getApiClient().get(
            FINALPOINTS.GEARBOX_TYPE_BASE
        );
        return response.data;
    };

    fetchGearboxTypesPaginated = async payload => {
        const response = await this.getApiClient().get(
            FINALPOINTS.GEARBOX_TYPE_BASE, {
            params: {
                nextPage: payload.nextPage,
                size: payload.size
            }
        }
        );
        return response.data;
    };

    addGearboxType = async payload => {
        const response = await this.getApiClient().post(
            FINALPOINTS.GEARBOX_TYPE_BASE,
            payload, {
            headers: { 'Content-Type': 'text/plain;charset=UTF-8' }
        }
        );
        return response.data;
    };

    editGearboxType = async payload => {
        const response = await this.getApiClient().put(
            FINALPOINTS.GEARBOX_TYPE_BASE,
            payload
        );
        return response.data;
    };

    deleteGearboxType = async payload => {
        const response = await this.getApiClient().delete(
            FINALPOINTS.GEARBOX_TYPE_BASE, {
            params: { 'id': payload }
        }
        );
        return response.data;
    };

    //for fuel types
    fetchAllFuelTypes = async payload => {
        const response = await this.getApiClient().get(
            FINALPOINTS.FUEL_TYPE_BASE
        );
        return response.data;
    };

    fetchFuelTypesPaginated = async payload => {
        const response = await this.getApiClient().get(
            FINALPOINTS.FUEL_TYPE_BASE, {
            params: {
                nextPage: payload.nextPage,
                size: payload.size
            }
        }
        );
        return response.data;
    };

    addFuelType = async payload => {
        const response = await this.getApiClient().post(
            FINALPOINTS.FUEL_TYPE_BASE,
            payload, {
            headers: { 'Content-Type': 'text/plain;charset=UTF-8' }
        }
        );
        return response.data;
    };

    editFuelType = async payload => {
        const response = await this.getApiClient().put(
            FINALPOINTS.FUEL_TYPE_BASE,
            payload
        );
        return response.data;
    };

    deleteFuelType = async payload => {
        const response = await this.getApiClient().delete(
            FINALPOINTS.FUEL_TYPE_BASE, {
            params: { 'id': payload }
        }
        );
        return response.data;
    };

    //for car types
    fetchAllCarTypes = async payload => {
        const response = await this.getApiClient().get(
            FINALPOINTS.CAR_TYPE_BASE
        );
        return response.data;
    };

    fetchCarTypesPaginated = async payload => {
        const response = await this.getApiClient().get(
            FINALPOINTS.CAR_TYPE_BASE, {
            params: {
                nextPage: payload.nextPage,
                size: payload.size
            }
        }
        );
        return response.data;
    };

    addCarType = async payload => {
        const response = await this.getApiClient().post(
            FINALPOINTS.CAR_TYPE_BASE,
            payload, {
            headers: { 'Content-Type': 'text/plain;charset=UTF-8' }
        }
        );
        return response.data;
    };

    editCarType = async payload => {
        const response = await this.getApiClient().put(
            FINALPOINTS.CAR_TYPE_BASE,
            payload
        );
        return response.data;
    };

    deleteCarType = async payload => {
        const response = await this.getApiClient().delete(
            FINALPOINTS.CAR_TYPE_BASE, {
            params: { 'id': payload }
        }
        );
        return response.data;
    };

    //for car manufacturers
    fetchAllCarManufacturers = async payload => {
        const response = await this.getApiClient().get(
            FINALPOINTS.CAR_MANUFACTURER_BASE
        );
        return response.data;
    };

    fetchCarManufacturersPaginated = async payload => {
        const response = await this.getApiClient().get(
            FINALPOINTS.CAR_MANUFACTURER_BASE, {
            params: {
                nextPage: payload.nextPage,
                size: payload.size
            }
        }
        );
        return response.data;
    };

    addCarManufacturer = async payload => {
        const response = await this.getApiClient().post(
            FINALPOINTS.CAR_MANUFACTURER_BASE,
            payload, {
            headers: { 'Content-Type': 'text/plain;charset=UTF-8' }
        }
        );
        return response.data;
    };

    editCarManufacturer = async payload => {
        const response = await this.getApiClient().put(
            FINALPOINTS.CAR_MANUFACTURER_BASE,
            payload
        );
        return response.data;
    };

    deleteCarManufacturer = async payload => {
        const response = await this.getApiClient().delete(
            FINALPOINTS.CAR_MANUFACTURER_BASE, {
            params: { 'id': payload }
        }
        );
        return response.data;
    };

};

export default new CodebookService();