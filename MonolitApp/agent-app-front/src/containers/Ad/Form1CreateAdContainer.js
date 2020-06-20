import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import Form1CreateAd from '../../components/Ad/Form1CreateAd';
import { carManufacturersSelector, carTypesSelector, carModelsSelector } from '../../store/codebook/selectors';
import { fetchAllCarManufacturers, fetchAllCarTypes, fetchAllCarModels } from '../../store/codebook/actions';
import { Form, Col, Container, Button } from 'react-bootstrap';

const Form1CreateAdContainer = (props) => {
    const dispatch = useDispatch();
    const [validated, setValidated] = useState(false);

    const carManufacturers = useSelector(carManufacturersSelector);
    const carTypes = useSelector(carTypesSelector);
    const carModels = useSelector(carModelsSelector);

    useEffect(() => {
        dispatch(
            fetchAllCarManufacturers()
        );
        dispatch(
            fetchAllCarTypes()
        );
    }, []);

    const handleForm1 = (event) => {
        event.preventDefault();
        const form = event.target;
        if (form.checkValidity() === false) {
            event.stopPropagation();
            setValidated(true);
        } else {
            let data = {
                "name": props.name,
                "location": props.location,
                "distanceLimitFlag": props.distanceLimitFlag,
                "distanceLimit": props.distanceLimit,
                "carCreateDTO" : {
                    "year": props.year,
                    "carManufacturer": props.carManufacturer,
                    "carModel": props.carModel,
                    "carType":props.carType,
                    "mileage":props.mileage,
                }
            }
            console.log("FORMA 1");
            console.log(data);
            // props.setFormData({
            //     ...props.formData,
            //     name: props.name,
            //     location: props.location,
            //     distanceLimitFlag: props.distanceLimitFlag,
            //     distanceLimit: props.distanceLimit,
            //     carManufacturer: props.carManufacturer,
            //     carModel: props.carModel,
            //     carType: props.carType,
            //     year: props.year,
            //     mileage: props.mileage
            // });
            props.handleNext();
            setValidated(false);
        }
    };

    const getCarManufacturers = () => {
        const listCarMan = [];
        if (carManufacturers.isFetch) {
            carManufacturers.data.map((carManufacturer) => {
                listCarMan.push(<option key={carManufacturer.id}>{carManufacturer.name}</option>);
            })
        }
        return listCarMan;
    }

    const getCarModels = () => {
        const listCarModel = [];
        let rez = [];

        if (carModels.isFetch) {
            let i = 0;

            carModels.data.map((carModel) => {
                listCarModel.push(<option key={i}>{carModel}</option>);
                i++
            })
            // if (carModels.data.length != 0) {
            //     rez.push(< Form.Group as={Col}>
            //         <Form.Label>Model</Form.Label>
            //         <Form.Control as="select" name="carModel" id="txtCarModel" placeholder="Model"
            //         onChange={handleCarModel}>
            //             {listCarModel}
            //         </Form.Control>
            //     </Form.Group >);
            // }
        }
        return listCarModel;
    }

    const getCarTypes = () => {
        const listCarType = [];
        if (carTypes.isFetch) {
            carTypes.data.map((carType) => {
                listCarType.push(<option key={carType.id}>{carType.name}</option>);
            })
        }
        return listCarType;
    }

    const getCurrentDate = () => {
        let newDate = new Date()
        let date = newDate.getDate();
        let month = newDate.getMonth() + 1;
        let year = newDate.getFullYear();
        let rez = "";
        if (month < 10) {
            month = "0" + month;
        }
        if (date < 10) {
            date = "0" + date;
        }

        rez = year + "-" + month + "-" + date;
        return rez;
    }

    const handleName = (event) => {
        props.setName(event.target.value);
    };
    const handleLocation = (event) => {
        props.setLocation(event.target.value);
    };

    const handleDistanceLimitFlag = (event) => {
        props.setDistanceLimitFlag(event.target.checked);
    };

    const handleDistanceLimit = (event) => {
        props.setDistanceLimit(event.target.value);
    };

    const handleCarModel = (event) => {
        // let index = event.target.options.selectedIndex;
        // props.setFormData({
        //     ...props.formData,
        //     carModel: props.carModel,
        // });
        props.setCarModel(event.target.value);
    };

    const handleCarManufacturers = (event) => {
        props.setCarManufacturer(event.target.value);
        let index = event.target.options.selectedIndex;
        dispatch(
            fetchAllCarModels({
                "id": carManufacturers.data[index].id
            })
        );
    };

    const handleCarType = (event) => {
        props.setCarType(event.target.value);
    };

    const handleYear = (event) => {
        props.setYear(event.target.value);
    };
    const handleMileage = (event) => {
        props.setMileage(event.target.value);
    };

    return (
        <Form1CreateAd
            onSubmit={handleForm1}
            validated={validated}
            activeStep={props.activeStep}
            steps={props.steps}
            isStepOptional={props.isStepOptional}
            handleSkip={props.handleSkip}
            handleReset={props.handleReset}

            getCarManufacturers={getCarManufacturers}
            getCarModels={getCarModels}
            getCarTypes={getCarTypes}
            getCurrentDate={getCurrentDate}

            name={props.name} setName={props.setName}
            location={props.location} setLocation={props.setLocation}
            distanceLimitFlag={props.distanceLimitFlag} setDistanceLimitFlag={props.setDistanceLimitFlag}
            distanceLimit={props.distanceLimit} setDistanceLimit={props.setDistanceLimit}
            carModel={props.carModel} setCarModel={props.setCarModel}
            carManufacturer={props.carManufacturer} setCarManufacturer={props.setCarManufacturer}
            carType={props.carType} setCarType={props.setCarType}
            year={props.year} setYear={props.setYear}
            mileage={props.mileage} setMileage={props.setMileage}

            handleDistanceLimitFlag={handleDistanceLimitFlag}
            handleDistanceLimit={handleDistanceLimit}
            handleCarManufacturers={handleCarManufacturers}
            handleCarModel={handleCarModel}
            handleName={handleName}
            handleLocation={handleLocation}
            handleCarType={handleCarType}
            handleYear={handleYear}
            handleMileage={handleMileage}



        />

    );
}
export default Form1CreateAdContainer;