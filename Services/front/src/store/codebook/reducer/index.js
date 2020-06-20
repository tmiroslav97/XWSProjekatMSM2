import {
    PUT_CAR_MANUFACTURERS,
    PUT_CAR_TYPES,
    PUT_FUEL_TYPES,
    PUT_GEARBOX_TYPES,
    PUT_CAR_MODELS,
    PUT_CAR_MANUFACTURERS_PAGINATED,
    PUT_CAR_TYPES_PAGINATED,
    PUT_FUEL_TYPES_PAGINATED,
    PUT_GEARBOX_TYPES_PAGINATED,
    PUT_CAR_MODELS_PAGINATED
} from '../constants';

import * as computationFunctions from './computation-functions';

const initialState = {
    carManufacturers: {
        data: [],
        isFetch: false
    },
    carTypes: {
        data: [],
        isFetch: false
    },
    fuelTypes: {
        data: [],
        isFetch: false
    },
    gearboxTypes: {
        data: [],
        isFetch: false
    },
    carModels: {
        data: [],
        isFetch: false
    },
    carManufacturersPaginated: {
        data: [],
        totalPageCnt: 0,
        nextPage: 0,
        size: 10,
        isFetch: false
    },
    carTypesPaginated: {
        data: [],
        totalPageCnt: 0,
        nextPage: 0,
        size: 10,
        isFetch: false
    },
    fuelTypesPaginated: {
        data: [],
        totalPageCnt: 0,
        nextPage: 0,
        size: 10,
        isFetch: false
    },
    gearboxTypesPaginated: {
        data: [],
        totalPageCnt: 0,
        nextPage: 0,
        size: 10,
        isFetch: false
    },
    carModelsPaginated: {
        data: [],
        totalPageCnt: 0,
        nextPage: 0,
        size: 10,
        isFetch: false
    }
};

const codebookReducer = (state = initialState, { type, payload }) => {
    if (actionHandler.hasOwnProperty(type)) {
        return actionHandler[type](state, payload);
    }
    return state;
};

const actionHandler = {
    [PUT_CAR_MANUFACTURERS]: computationFunctions.putCarManufacturers,
    [PUT_CAR_TYPES]: computationFunctions.putCarTypes,
    [PUT_FUEL_TYPES]: computationFunctions.putFuelTypes,
    [PUT_GEARBOX_TYPES]: computationFunctions.putGearboxTypes,
    [PUT_CAR_MODELS]: computationFunctions.putCarModels,
    [PUT_CAR_MANUFACTURERS_PAGINATED]: computationFunctions.putCarManufacturersPaginated,
    [PUT_CAR_TYPES_PAGINATED]: computationFunctions.putCarTypesPaginated,
    [PUT_FUEL_TYPES_PAGINATED]: computationFunctions.putFuelTypesPaginated,
    [PUT_GEARBOX_TYPES_PAGINATED]: computationFunctions.putGearboxTypesPaginated,
    [PUT_CAR_MODELS_PAGINATED]: computationFunctions.putCarModelsPaginated
};

export default codebookReducer;