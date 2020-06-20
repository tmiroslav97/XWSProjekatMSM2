import {
    FETCH_CAR_MANUFACTURERS,
    FETCH_ALL_CAR_MANUFACTURERS,
    PUT_CAR_MANUFACTURERS,
    PUT_CAR_MANUFACTURERS_PAGINATED,
    ADD_CAR_MANUFACTURER,
    EDIT_CAR_MANUFACTURER,
    DELETE_CAR_MANUFACTURER,
    FETCH_CAR_TYPES,
    FETCH_ALL_CAR_TYPES,
    PUT_CAR_TYPES,
    PUT_CAR_TYPES_PAGINATED,
    ADD_CAR_TYPE,
    EDIT_CAR_TYPE,
    DELETE_CAR_TYPE,
    FETCH_GEARBOX_TYPES,
    FETCH_ALL_GEARBOX_TYPES,
    PUT_GEARBOX_TYPES,
    PUT_GEARBOX_TYPES_PAGINATED,
    ADD_GEARBOX_TYPE,
    EDIT_GEARBOX_TYPE,
    DELETE_GEARBOX_TYPE,
    FETCH_FUEL_TYPES,
    FETCH_ALL_FUEL_TYPES,
    PUT_FUEL_TYPES,
    PUT_FUEL_TYPES_PAGINATED,
    ADD_FUEL_TYPE,
    EDIT_FUEL_TYPE,
    DELETE_FUEL_TYPE,
    FETCH_CAR_MODELS,
    FETCH_ALL_CAR_MODELS,
    PUT_CAR_MODELS,
    PUT_CAR_MODELS_PAGINATED,
    ADD_CAR_MODEL,
    EDIT_CAR_MODEL,
    DELETE_CAR_MODEL
} from './constants';

//for car models
export const deleteCarModel = payload => ({
    type: DELETE_CAR_MODEL,
    payload
});

export const editCarModel = payload => ({
    type: EDIT_CAR_MODEL,
    payload
});

export const addCarModel = payload => ({
    type: ADD_CAR_MODEL,
    payload
});

export const putCarModelsPaginated = payload => ({
    type: PUT_CAR_MODELS_PAGINATED,
    payload
});

export const putCarModels = payload => ({
    type: PUT_CAR_MODELS,
    payload
});

export const fetchAllCarModels = payload => ({
    type: FETCH_ALL_CAR_MODELS,
    payload
});

export const fetchCarModels = payload => ({
    type: FETCH_CAR_MODELS,
    payload
});

//for gearbox types
export const deleteGearboxType = payload => ({
    type: DELETE_GEARBOX_TYPE,
    payload
});

export const editGearboxType = payload => ({
    type: EDIT_GEARBOX_TYPE,
    payload
});

export const addGearboxType = payload => ({
    type: ADD_GEARBOX_TYPE,
    payload
});

export const putGearboxTypesPaginated = payload => ({
    type: PUT_GEARBOX_TYPES_PAGINATED,
    payload
});

export const putGearboxTypes = payload => ({
    type: PUT_GEARBOX_TYPES,
    payload
});

export const fetchAllGearboxTypes = payload => ({
    type: FETCH_ALL_GEARBOX_TYPES,
    payload
});

export const fetchGearboxTypes = payload => ({
    type: FETCH_GEARBOX_TYPES,
    payload
});

//for fuel types
export const deleteFuelType = payload => ({
    type: DELETE_FUEL_TYPE,
    payload
});

export const editFuelType = payload => ({
    type: EDIT_FUEL_TYPE,
    payload
});

export const addFuelType = payload => ({
    type: ADD_FUEL_TYPE,
    payload
});

export const putFuelTypesPaginated = payload => ({
    type: PUT_FUEL_TYPES_PAGINATED,
    payload
});

export const putFuelTypes = payload => ({
    type: PUT_FUEL_TYPES,
    payload
});

export const fetchAllFuelTypes = payload => ({
    type: FETCH_ALL_FUEL_TYPES,
    payload
});

export const fetchFuelTypes = payload => ({
    type: FETCH_FUEL_TYPES,
    payload
});

//for car types
export const deleteCarType = payload => ({
    type: DELETE_CAR_TYPE,
    payload
});

export const editCarType = payload => ({
    type: EDIT_CAR_TYPE,
    payload
});

export const addCarType = payload => ({
    type: ADD_CAR_TYPE,
    payload
});

export const putCarTypesPaginated = payload => ({
    type: PUT_CAR_TYPES_PAGINATED,
    payload
});

export const putCarTypes = payload => ({
    type: PUT_CAR_TYPES,
    payload
});

export const fetchAllCarTypes = payload => ({
    type: FETCH_ALL_CAR_TYPES,
    payload
});

export const fetchCarTypes = payload => ({
    type: FETCH_CAR_TYPES,
    payload
});

//for car manufacturers
export const deleteCarManufacturer = payload => ({
    type: DELETE_CAR_MANUFACTURER,
    payload
});

export const editCarManufacturer = payload => ({
    type: EDIT_CAR_MANUFACTURER,
    payload
});

export const addCarManufacturer = payload => ({
    type: ADD_CAR_MANUFACTURER,
    payload
});

export const putCarManufacturersPaginated = payload => ({
    type: PUT_CAR_MANUFACTURERS_PAGINATED,
    payload
});

export const putCarManufacturers = payload => ({
    type: PUT_CAR_MANUFACTURERS,
    payload
});

export const fetchAllCarManufacturers = payload => ({
    type: FETCH_ALL_CAR_MANUFACTURERS,
    payload
});

export const fetchCarManufacturers = payload => ({
    type: FETCH_CAR_MANUFACTURERS,
    payload
});