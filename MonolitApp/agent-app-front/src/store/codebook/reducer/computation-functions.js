export function putCarManufacturers(state, payload) {
    return {
        ...state,
        carManufacturers: payload
    };
}

export function putCarTypes(state, payload) {
    return {
        ...state,
        carTypes: payload
    };
}

export function putFuelTypes(state, payload) {
    return {
        ...state,
        fuelTypes: payload
    };
}

export function putGearboxTypes(state, payload) {
    return {
        ...state,
        gearboxTypes: payload
    };
}

export function putCarModels(state, payload) {
    return {
        ...state,
        carModels: payload
    };
}

export function putCarManufacturersPaginated(state, payload) {
    return {
        ...state,
        carManufacturersPaginated: payload
    };
}

export function putCarTypesPaginated(state, payload) {
    return {
        ...state,
        carTypesPaginated: payload
    };
}

export function putFuelTypesPaginated(state, payload) {
    return {
        ...state,
        fuelTypesPaginated: payload
    };
}

export function putGearboxTypesPaginated(state, payload) {
    return {
        ...state,
        gearboxTypesPaginated: payload
    };
}

export function putCarModelsPaginated(state, payload) {
    return {
        ...state,
        carModelsPaginated: payload
    };
}