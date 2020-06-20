const reducer = 'codebookReducer';

export const carManufacturersSelector = state => state[reducer].carManufacturers;
export const carTypesSelector = state => state[reducer].carTypes;
export const fuelTypesSelector = state => state[reducer].fuelTypes;
export const gearboxTypesSelector = state => state[reducer].gearboxTypes;
export const carModelsSelector = state => state[reducer].carModels;

export const carManufacturersPaginatedSelector = state => state[reducer].carManufacturersPaginated;
export const carTypesPaginatedSelector = state => state[reducer].carTypesPaginated;
export const fuelTypesPaginatedSelector = state => state[reducer].fuelTypesPaginated;
export const gearboxTypesPaginatedSelector = state => state[reducer].gearboxTypesPaginated;
export const carModelsPaginatedSelector = state => state[reducer].carModelsPaginated;