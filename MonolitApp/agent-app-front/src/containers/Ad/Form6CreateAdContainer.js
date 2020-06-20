import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import Form6CreateAd from '../../components/Ad/Form6CreateAd'
import { Form, Button, ButtonGroup, ButtonToolbar, Row, Col, Container, Image, Card } from 'react-bootstrap';
import { imageNameSelector } from '../../store/ad/selectors';

const Form6CreateAdContainer = (props) => {
    const dispatch = useDispatch();
    const [validated, setValidated] = useState(false);

    const imageName = useSelector(imageNameSelector);

    const handleForm6 = (event) => {
        event.preventDefault();
        console.log("FORMA 6")

        if (imageName != null) {
            console.log(imageName);

            console.log(JSON.stringify(imageName));
            props.setImagesDTO(imageName);
            console.log(imageName[props.flagCover])
            props.setCoverPhoto(imageName[props.flagCover]);
            // if(props.carModel === null){
            //     props.setCarModel("");
            // }
            props.setFormData({
                ...props.formData,
                imagesDTO: JSON.stringify(imageName),
                // carCalendarTermCreateDTOList: JSON.stringify(props.carCalendarTermList),
                // id: props.id,
                // pricePerDay: props.pricePerDay,
                // pricePerKm: props.pricePerKm,
                // pricePerKmCDW: props.pricePerKmCDW,
                // gearboxType: props.gearboxType,
                // fuelType: props.fuelType,
                // childrenSeatNum: props.childrenSeatNum,
                // cdw: props.cdw,
                // androidFlag: props.androidFlag,
                // name: props.name,
                // location: props.location,
                // distanceLimitFlag: props.distanceLimitFlag,
                // distanceLimit: props.distanceLimit,
                // carManufacturer: props.carManufacturer,
                // carModel: props.carModel,
                // carType: props.carType,
                // year: props.year,
                // mileage: props.mileage
            });
            setValidated(true);
            // props.handleCreatedAd();
            props.handleNext();
        }

         console.log(props.formData);
    };
    return (
        <Form6CreateAd
            handleForm6={handleForm6}
            handleCreatedAd={props.handleCreatedAd}
            validated={validated}
            activeStep={props.activeStep}
            steps={props.steps}
            isStepOptional={props.isStepOptional}
            handleBack={props.handleBack}
            handleSkip={props.handleSkip}
            handleReset={props.handleReset}

        />
    );
}
export default Form6CreateAdContainer;