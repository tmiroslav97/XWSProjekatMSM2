import { take, put, call, select } from 'redux-saga/effects';

import CodebookService from '../../services/CodebookService';

import {
    FETCH_CAR_MANUFACTURERS,
    FETCH_ALL_CAR_MANUFACTURERS,
    ADD_CAR_MANUFACTURER,
    EDIT_CAR_MANUFACTURER,
    DELETE_CAR_MANUFACTURER,
    FETCH_CAR_TYPES,
    FETCH_ALL_CAR_TYPES,
    ADD_CAR_TYPE,
    EDIT_CAR_TYPE,
    DELETE_CAR_TYPE,
    FETCH_GEARBOX_TYPES,
    FETCH_ALL_GEARBOX_TYPES,
    ADD_GEARBOX_TYPE,
    EDIT_GEARBOX_TYPE,
    DELETE_GEARBOX_TYPE,
    FETCH_FUEL_TYPES,
    FETCH_ALL_FUEL_TYPES,
    ADD_FUEL_TYPE,
    EDIT_FUEL_TYPE,
    DELETE_FUEL_TYPE,
    FETCH_CAR_MODELS,
    FETCH_ALL_CAR_MODELS,
    ADD_CAR_MODEL,
    EDIT_CAR_MODEL,
    DELETE_CAR_MODEL
} from './constants';

import {
    putCarManufacturers,
    putCarTypes,
    putFuelTypes,
    putGearboxTypes,
    putCarModels,
    putCarManufacturersPaginated,
    putCarTypesPaginated,
    putFuelTypesPaginated,
    putGearboxTypesPaginated,
    putCarModelsPaginated
} from './actions';

import {
    carManufacturersPaginatedSelector,
    carTypesPaginatedSelector,
    gearboxTypesPaginatedSelector,
    fuelTypesPaginatedSelector,
    carModelsPaginatedSelector
} from './selectors';

import {
    putSuccessMsg
} from '../common/actions';


//for car models
export function* fetchAllCarModels() {
    const { payload } = yield take(FETCH_ALL_CAR_MODELS);
    yield put(putCarModels({ 'isFetch': false }));
    const data = yield call(CodebookService.fetchAllCarModels, payload);
    yield put(putCarModels({
        'data': data,
        'isFetch': true
    }));
}

export function* fetchCarModelsPaginated() {
    const { payload } = yield take(FETCH_CAR_MODELS);
    yield put(putCarModelsPaginated({ 'isFetch': false }));
    const data = yield call(CodebookService.fetchCarModelsPaginated, payload);
    yield put(putCarModelsPaginated({
        'data': data.carModels,
        'totalPageCnt': data.totalPageCnt,
        'nextPage': payload.nextPage,
        'size': payload.size,
        'isFetch': true
    }));
}

export function* addCarModel() {
    const { payload } = yield take(ADD_CAR_MODEL);
    const msg = yield call(CodebookService.addCarModel, payload);
    yield put(putSuccessMsg(msg));
    const temp = yield select(carModelsPaginatedSelector);
    yield put(putCarModelsPaginated({ 'isFetch': false }));
    const data = yield call(CodebookService.fetchCarModelsPaginated, { "nextPage": temp.nextPage, "size": temp.size });
    yield put(putCarModelsPaginated({
        'data': data.carModels,
        'totalPageCnt': data.totalPageCnt,
        'nextPage': temp.nextPage,
        'size': temp.size,
        'isFetch': true
    }));
}

export function* editCarModel() {
    const { payload } = yield take(EDIT_CAR_MODEL);
    const msg = yield call(CodebookService.editCarModel, payload);
    yield put(putSuccessMsg(msg));
    const temp = yield select(carModelsPaginatedSelector);
    yield put(putCarModelsPaginated({ 'isFetch': false }));
    const data = yield call(CodebookService.fetchCarModelsPaginated, { "nextPage": temp.nextPage, "size": temp.size });
    yield put(putCarModelsPaginated({
        'data': data.carModels,
        'totalPageCnt': data.totalPageCnt,
        'nextPage': temp.nextPage,
        'size': temp.size,
        'isFetch': true
    }));
}

export function* deleteCarModel() {
    const { payload } = yield take(DELETE_CAR_MODEL);
    const msg = yield call(CodebookService.deleteCarModel, payload);
    yield put(putSuccessMsg(msg));
    const temp = yield select(carModelsPaginatedSelector);
    yield put(putCarModelsPaginated({ 'isFetch': false }));
    const data = yield call(CodebookService.fetchCarModelsPaginated, { "nextPage": temp.nextPage, "size": temp.size });
    yield put(putCarModelsPaginated({
        'data': data.carModels,
        'totalPageCnt': data.totalPageCnt,
        'nextPage': temp.nextPage,
        'size': temp.size,
        'isFetch': true
    }));
}

//for gearbox types
export function* fetchAllGearboxTypes() {
    const { payload } = yield take(FETCH_ALL_GEARBOX_TYPES);
    yield put(putGearboxTypes({ 'isFetch': false }));
    const data = yield call(CodebookService.fetchAllGearboxTypes, payload);
    yield put(putGearboxTypes({
        'data': data,
        'isFetch': true
    }));
}

export function* fetchGearboxTypesPaginated() {
    const { payload } = yield take(FETCH_GEARBOX_TYPES);
    yield put(putGearboxTypesPaginated({ 'isFetch': false }));
    const data = yield call(CodebookService.fetchGearboxTypesPaginated, payload);
    yield put(putGearboxTypesPaginated({
        'data': data.gearboxTypes,
        'totalPageCnt': data.totalPageCnt,
        'nextPage': payload.nextPage,
        'size': payload.size,
        'isFetch': true
    }));
}

export function* addGearboxType() {
    const { payload } = yield take(ADD_GEARBOX_TYPE);
    const msg = yield call(CodebookService.addGearboxType, payload);
    yield put(putSuccessMsg(msg));
    const temp = yield select(gearboxTypesPaginatedSelector);
    yield put(putGearboxTypesPaginated({ 'isFetch': false }));
    const data = yield call(CodebookService.fetchGearboxTypesPaginated, { "nextPage": temp.nextPage, "size": temp.size });
    yield put(putGearboxTypesPaginated({
        'data': data.gearboxTypes,
        'totalPageCnt': data.totalPageCnt,
        'nextPage': temp.nextPage,
        'size': temp.size,
        'isFetch': true
    }));
}

export function* editGearboxType() {
    const { payload } = yield take(EDIT_GEARBOX_TYPE);
    const msg = yield call(CodebookService.editGearboxType, payload);
    yield put(putSuccessMsg(msg));
    yield put(putSuccessMsg(null));
    const temp = yield select(gearboxTypesPaginatedSelector);
    yield put(putGearboxTypesPaginated({ 'isFetch': false }));
    const data = yield call(CodebookService.fetchGearboxTypesPaginated, { "nextPage": temp.nextPage, "size": temp.size });
    yield put(putGearboxTypesPaginated({
        'data': data.gearboxTypes,
        'totalPageCnt': data.totalPageCnt,
        'nextPage': temp.nextPage,
        'size': temp.size,
        'isFetch': true
    }));
}

export function* deleteGearboxType() {
    const { payload } = yield take(DELETE_GEARBOX_TYPE);
    const msg = yield call(CodebookService.deleteGearboxType, payload);
    yield put(putSuccessMsg(msg));
    const temp = yield select(gearboxTypesPaginatedSelector);
    yield put(putGearboxTypesPaginated({ 'isFetch': false }));
    const data = yield call(CodebookService.fetchGearboxTypesPaginated, { "nextPage": temp.nextPage, "size": temp.size });
    yield put(putGearboxTypesPaginated({
        'data': data.gearboxTypes,
        'totalPageCnt': data.totalPageCnt,
        'nextPage': temp.nextPage,
        'size': temp.size,
        'isFetch': true
    }));
}

//for fuel types
export function* fetchAllFuelTypes() {
    const { payload } = yield take(FETCH_ALL_FUEL_TYPES);
    yield put(putFuelTypes({ 'isFetch': false }));
    const data = yield call(CodebookService.fetchAllFuelTypes, payload);
    yield put(putFuelTypes({
        'data': data,
        'isFetch': true
    }));
}

export function* fetchFuelTypesPaginated() {
    const { payload } = yield take(FETCH_FUEL_TYPES);
    yield put(putFuelTypesPaginated({ 'isFetch': false }));
    const data = yield call(CodebookService.fetchFuelTypesPaginated, payload);
    yield put(putFuelTypesPaginated({
        'data': data.fuelTypes,
        'totalPageCnt': data.totalPageCnt,
        'nextPage': payload.nextPage,
        'size': payload.size,
        'isFetch': true
    }));
}

export function* addFuelType() {
    const { payload } = yield take(ADD_FUEL_TYPE);
    const msg = yield call(CodebookService.addFuelType, payload);
    yield put(putSuccessMsg(msg));
    const temp = yield select(fuelTypesPaginatedSelector);
    yield put(putFuelTypesPaginated({ 'isFetch': false }));
    const data = yield call(CodebookService.fetchFuelTypesPaginated, { "nextPage": temp.nextPage, "size": temp.size });
    yield put(putFuelTypesPaginated({
        'data': data.fuelTypes,
        'totalPageCnt': data.totalPageCnt,
        'nextPage': temp.nextPage,
        'size': temp.size,
        'isFetch': true
    }));
}

export function* editFuelType() {
    const { payload } = yield take(EDIT_FUEL_TYPE);
    const msg = yield call(CodebookService.editFuelType, payload);
    yield put(putSuccessMsg(msg));
    const temp = yield select(fuelTypesPaginatedSelector);
    yield put(putFuelTypesPaginated({ 'isFetch': false }));
    const data = yield call(CodebookService.fetchFuelTypesPaginated, { "nextPage": temp.nextPage, "size": temp.size });
    yield put(putFuelTypesPaginated({
        'data': data.fuelTypes,
        'totalPageCnt': data.totalPageCnt,
        'nextPage': temp.nextPage,
        'size': temp.size,
        'isFetch': true
    }));
}

export function* deleteFuelType() {
    const { payload } = yield take(DELETE_FUEL_TYPE);
    const msg = yield call(CodebookService.deleteFuelType, payload);
    yield put(putSuccessMsg(msg));
    const temp = yield select(fuelTypesPaginatedSelector);
    yield put(putFuelTypesPaginated({ 'isFetch': false }));
    const data = yield call(CodebookService.fetchFuelTypesPaginated, { "nextPage": temp.nextPage, "size": temp.size });
    yield put(putFuelTypesPaginated({
        'data': data.fuelTypes,
        'totalPageCnt': data.totalPageCnt,
        'nextPage': temp.nextPage,
        'size': temp.size,
        'isFetch': true
    }));
}

//for car types
export function* fetchAllCarTypes() {
    const { payload } = yield take(FETCH_ALL_CAR_TYPES);
    yield put(putCarTypes({ 'isFetch': false }));
    const data = yield call(CodebookService.fetchAllCarTypes, payload);
    yield put(putCarTypes({
        'data': data,
        'isFetch': true
    }));
}

export function* fetchCarTypesPaginated() {
    const { payload } = yield take(FETCH_CAR_TYPES);
    yield put(putCarTypesPaginated({ 'isFetch': false }));
    const data = yield call(CodebookService.fetchCarTypesPaginated, payload);
    yield put(putCarTypesPaginated({
        'data': data.carTypes,
        'totalPageCnt': data.totalPageCnt,
        'nextPage': payload.nextPage,
        'size': payload.size,
        'isFetch': true
    }));
}

export function* addCarType() {
    const { payload } = yield take(ADD_CAR_TYPE);
    const msg = yield call(CodebookService.addCarType, payload);
    yield put(putSuccessMsg(msg));
    const temp = yield select(carTypesPaginatedSelector);
    yield put(putCarTypesPaginated({ 'isFetch': false }));
    const data = yield call(CodebookService.fetchCarTypesPaginated, { "nextPage": temp.nextPage, "size": temp.size });
    yield put(putCarTypesPaginated({
        'data': data.carTypes,
        'totalPageCnt': data.totalPageCnt,
        'nextPage': temp.nextPage,
        'size': temp.size,
        'isFetch': true
    }));
}

export function* editCarType() {
    const { payload } = yield take(EDIT_CAR_TYPE);
    const msg = yield call(CodebookService.editCarType, payload);
    yield put(putSuccessMsg(msg));
    const temp = yield select(carTypesPaginatedSelector);
    yield put(putCarTypesPaginated({ 'isFetch': false }));
    const data = yield call(CodebookService.fetchCarTypesPaginated, { "nextPage": temp.nextPage, "size": temp.size });
    yield put(putCarTypesPaginated({
        'data': data.carTypes,
        'totalPageCnt': data.totalPageCnt,
        'nextPage': temp.nextPage,
        'size': temp.size,
        'isFetch': true
    }));
}

export function* deleteCarType() {
    const { payload } = yield take(DELETE_CAR_TYPE);
    const msg = yield call(CodebookService.deleteCarType, payload);
    yield put(putSuccessMsg(msg));
    const temp = yield select(carTypesPaginatedSelector);
    yield put(putCarTypesPaginated({ 'isFetch': false }));
    const data = yield call(CodebookService.fetchCarTypesPaginated, { "nextPage": temp.nextPage, "size": temp.size });
    yield put(putCarTypesPaginated({
        'data': data.carTypes,
        'totalPageCnt': data.totalPageCnt,
        'nextPage': temp.nextPage,
        'size': temp.size,
        'isFetch': true
    }));
}

//for car manufacturers
export function* fetchAllCarManufacturers() {
    const { payload } = yield take(FETCH_ALL_CAR_MANUFACTURERS);
    yield put(putCarManufacturers({ 'isFetch': false }));
    const data = yield call(CodebookService.fetchAllCarManufacturers, payload);
    yield put(putCarManufacturers({
        'data': data,
        'isFetch': true
    }));
}

export function* fetchCarManufacturersPaginated() {
    const { payload } = yield take(FETCH_CAR_MANUFACTURERS);
    yield put(putCarManufacturersPaginated({ 'isFetch': false }));
    const data = yield call(CodebookService.fetchCarManufacturersPaginated, payload);
    yield put(putCarManufacturersPaginated({
        'data': data.carManufacturers,
        'totalPageCnt': data.totalPageCnt,
        'nextPage': payload.nextPage,
        'size': payload.size,
        'isFetch': true
    }));
}

export function* addCarManufacturer() {
    const { payload } = yield take(ADD_CAR_MANUFACTURER);
    const msg = yield call(CodebookService.addCarManufacturer, payload);
    yield put(putSuccessMsg(msg));
    const temp = yield select(carManufacturersPaginatedSelector);
    yield put(putCarManufacturersPaginated({ 'isFetch': false }));
    const data = yield call(CodebookService.fetchCarManufacturersPaginated, { "nextPage": temp.nextPage, "size": temp.size });
    yield put(putCarManufacturersPaginated({
        'data': data.carManufacturers,
        'totalPageCnt': data.totalPageCnt,
        'nextPage': temp.nextPage,
        'size': temp.size,
        'isFetch': true
    }));
}

export function* editCarManufacturer() {
    const { payload } = yield take(EDIT_CAR_MANUFACTURER);
    const msg = yield call(CodebookService.editCarManufacturer, payload);
    yield put(putSuccessMsg(msg));
    const temp = yield select(carManufacturersPaginatedSelector);
    yield put(putCarManufacturersPaginated({ 'isFetch': false }));
    const data = yield call(CodebookService.fetchCarManufacturersPaginated, { "nextPage": temp.nextPage, "size": temp.size });
    yield put(putCarManufacturersPaginated({
        'data': data.carManufacturers,
        'totalPageCnt': data.totalPageCnt,
        'nextPage': temp.nextPage,
        'size': temp.size,
        'isFetch': true
    }));
}

export function* deleteCarManufacturer() {
    const { payload } = yield take(DELETE_CAR_MANUFACTURER);
    const msg = yield call(CodebookService.deleteCarManufacturer, payload);
    yield put(putSuccessMsg(msg));
    const temp = yield select(carManufacturersPaginatedSelector);
    yield put(putCarManufacturersPaginated({ 'isFetch': false }));
    const data = yield call(CodebookService.fetchCarManufacturersPaginated, { "nextPage": temp.nextPage, "size": temp.size });
    yield put(putCarManufacturersPaginated({
        'data': data.carManufacturers,
        'totalPageCnt': data.totalPageCnt,
        'nextPage': temp.nextPage,
        'size': temp.size,
        'isFetch': true
    }));
}