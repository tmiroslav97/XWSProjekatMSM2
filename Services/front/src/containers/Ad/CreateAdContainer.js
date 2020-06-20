import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import CreateAd from '../../components/Ad/CreateAd';
import { createdAd, uploadImage, createdAdPhotos } from '../../store/ad/actions';
import Form1CreateAdContainer from './Form1CreateAdContainer';
import Form2CreateAdContainer from './Form2CreateAdContainer';
import Form3CreateAdContainer from './Form3CreateAdContainer';
import Form4CreateAdContainer from './Form4CreateAdContainer';
import Form5CreateAdContainer from './Form5CreateAdContainer';
import Form6CreateAdContainer from './Form6CreateAdContainer';
import { Container, Row, Col, Button } from 'react-bootstrap';

const CreateAdContainer = () => {
    const dispatch = useDispatch();
    const [validated, setValidated] = useState(false);
    const [activeStep, setActiveStep] = useState(0);
    const [skipped, setSkipped] = useState(new Set());
    const steps = ['Osnovne informacije', 'Dodatne informacije', 'Cena', 'Slike', 'Dostupnost'];
    //forma 1 
    const [name, setName] = useState("");
    const [location, setLocation] = useState("");
    const [distanceLimitFlag, setDistanceLimitFlag] = useState(false);
    const [distanceLimit, setDistanceLimit] = useState();
    const [carModel, setCarModel] = useState("");
    const [carManufacturer, setCarManufacturer] = useState("");
    const [carType, setCarType] = useState("");
    const [year, setYear] = useState();
    const [mileage, setMileage] = useState();
    //forma 2
    const [gearboxType, setGearboxType] = useState("");
    const [fuelType, setFuelType] = useState("");
    const [childrenSeatNum, setChildrenSeatNum] = useState();
    const [cdw, setCdw] = useState(false);
    const [androidFlag, setAndroidFlag] = useState(false);
    //forma 3
    const [pricePerDay, setPricePerDay] = useState(null);
    const [pricePerKm, setPricePerKm] = useState(null);
    const [pricePerKmCDW, setPricePerKmCDW] = useState(null);
    const [id, setId] = useState(null);
    const [activeToggle, setActiveToggle] = useState(1);
    //forma 4
    const [carCalendarTermList, setCarCalendarTermList] = useState([]);
    //forma 5
    const [coverPhoto, setCoverPhoto] = useState();
    const [flagCover, setFlagCover] = useState();
    const [imagesDTO, setImagesDTO] = useState([]);
    const [photos, setPhotos] = useState([]);
    const [brPhotos, setBrPhotos] = useState(0);
    //konacna forma
    const [formData, setFormData] = useState({
        name: null,
        location: null,
        distanceLimitFlag: null,
        distanceLimit: null,
        carManufacturer: null,
        carModel: null,
        carType: null,
        year: null,
        mileage: null,
        gearboxType: null,
        fuelType: null,
        childrenSeatNum: null,
        cdw: null,
        androidFlag: null,
        pricePerKm: null,
        pricePerKmCDW: null,
        pricePerDay: null,
        id: null,
        carCalendarTermCreateDTOList: null,
        imagesDTO: null

    });


    const handleCreatedAd = () => {
        console.log(formData);
        let data = {
            "name": name,
            "coverPhoto": coverPhoto,
            "imagesDTO": imagesDTO,
            "location": location,
            "distanceLimitFlag": distanceLimitFlag,
            "distanceLimit": distanceLimit,
            "carCreateDTO": {
                "year": year,
                "carManufacturer": carManufacturer,
                "carModel": carModel,
                "gearboxType": gearboxType,
                "fuelType": fuelType,
                "carType": carType,
                "mileage": mileage,
                "childrenSeatNum": childrenSeatNum,
                "cdw": cdw,
                "androidFlag": androidFlag
            },
            "priceListCreateDTO": {
                "pricePerKm": pricePerKm,
                "pricePerKmCWD": pricePerKmCDW,
                "pricePerDay": pricePerDay,
                "id": id
            },
            "carCalendarTermCreateDTOList": carCalendarTermList
        }
        //POKUSAJ POZIVANJA DRUGE METODE
        // let form = new FormData(); 
        // for(const i in photos){
        //     let naziv = "photos" + i;
        //     form.append(naziv, photos[i]);
        //     console.log(photos[i]);
        // }
        // // for(const i in photos){
        // //     form.append("photos", photos[i]);
        // // }

        // form.append("data", JSON.stringify(data));
        // console.log("ispis");
        // console.log(form.values.toString);
        // dispatch(createdAdPhotos(form));

        dispatch(createdAd(JSON.stringify(data)));

    };

    const handleDistanceLimitFlag = (event) => {
        setDistanceLimitFlag(event.target.checked);
        setDistanceLimit(null);
    };

    const handleCDW = (event) => {
        setCdw(event.target.checked);
    };

    const handleAndroidFlag = (event) => {
        setAndroidFlag(event.target.checked);
    };

    const isStepOptional = (step) => {
        // return step === 3;
    };

    const isStepSkipped = (step) => {
        return skipped.has(step);
    };

    const handleNext = () => {
        let newSkipped = skipped;
        if (isStepSkipped(activeStep)) {
            newSkipped = new Set(newSkipped.values());
            newSkipped.delete(activeStep);
        }
        setSkipped(newSkipped);
        setActiveStep((prevActiveStep) => prevActiveStep + 1);
    };

    const handleBack = () => {
        setActiveStep((prevActiveStep) => prevActiveStep - 1);
    };

    const handleSkip = () => {
        if (!isStepOptional(activeStep)) {
            throw new Error("You can't skip a step that isn't optional.");
        }

        setActiveStep((prevActiveStep) => prevActiveStep + 1);
        setSkipped((prevSkipped) => {
            const newSkipped = new Set(prevSkipped.values());
            newSkipped.add(activeStep);
            return newSkipped;
        });
    };

    const handleReset = () => {
        setActiveStep(0);
    };


    return (
        <Container>
            <CreateAd
                skipped={skipped}
                setSkipped={setSkipped}
                isStepOptional={isStepOptional}
                isStepSkipped={isStepSkipped}
                steps={steps}
                handleNext={handleNext}
                handleBack={handleBack}
                handleSkip={handleSkip}
                handleReset={handleReset}
                formData={formData} setFormData={setFormData}
                activeStep={activeStep} setActiveStep={setActiveStep}

            />

            {activeStep === 0 ?
                <Form1CreateAdContainer
                    formData={formData} setFormData={setFormData}
                    activeStep={activeStep} setActiveStep={setActiveStep}
                    steps={steps}
                    isStepOptional={isStepOptional}
                    handleNext={handleNext}
                    handleBack={handleBack}
                    handleSkip={handleSkip}
                    handleReset={handleReset}
                    name={name} setName={setName}
                    location={location} setLocation={setLocation}
                    distanceLimitFlag={distanceLimitFlag} setDistanceLimitFlag={setDistanceLimitFlag}
                    distanceLimit={distanceLimit} setDistanceLimit={setDistanceLimit}
                    carModel={carModel} setCarModel={setCarModel}
                    carManufacturer={carManufacturer} setCarManufacturer={setCarManufacturer}
                    carType={carType} setCarType={setCarType}
                    year={year} setYear={setYear}
                    mileage={mileage} setMileage={setMileage}
                ></Form1CreateAdContainer>
                : null
            }
            {activeStep === 1 ?
                <Form2CreateAdContainer
                    formData={formData} setFormData={setFormData}
                    activeStep={activeStep} setActiveStep={setActiveStep}
                    steps={steps}
                    isStepOptional={isStepOptional}
                    handleNext={handleNext}
                    handleBack={handleBack}
                    handleSkip={handleSkip}
                    handleReset={handleReset}
                    cdw={cdw} setCdw={setCdw}
                    androidFlag={androidFlag} setAndroidFlag={setAndroidFlag}
                    gearboxType={gearboxType} setGearboxType={setGearboxType}
                    fuelType={fuelType} setFuelType={setFuelType}
                    childrenSeatNum={childrenSeatNum} setChildrenSeatNum={setChildrenSeatNum}
                ></Form2CreateAdContainer>
                : null
            }
            {activeStep === 2 ?
                <Form3CreateAdContainer
                    formData={formData} setFormData={setFormData}
                    activeStep={activeStep} setActiveStep={setActiveStep}
                    steps={steps}
                    isStepOptional={isStepOptional}
                    handleNext={handleNext}
                    handleBack={handleBack}
                    handleSkip={handleSkip}
                    handleReset={handleReset}
                    cdw={cdw} setCdw={setCdw}
                    distanceLimitFlag={distanceLimitFlag} setDistanceLimitFlag={setDistanceLimitFlag}
                    pricePerDay={pricePerDay} setPricePerDay={setPricePerDay}
                    pricePerKm={pricePerKm} setPricePerKm={setPricePerKm}
                    pricePerKmCDW={pricePerKmCDW} setPricePerKmCDW={setPricePerKmCDW}
                    id={id} setId={setId}
                    activeToggle={activeToggle} setActiveToggle={setActiveToggle}

                ></Form3CreateAdContainer>
                : null
            }
            {activeStep === 3 ?
                <Form5CreateAdContainer
                    formData={formData} setFormData={setFormData}
                    activeStep={activeStep} setActiveStep={setActiveStep}
                    steps={steps}
                    isStepOptional={isStepOptional}
                    handleNext={handleNext}
                    handleBack={handleBack}
                    handleSkip={handleSkip}
                    handleReset={handleReset}
                    coverPhoto={coverPhoto} setCoverPhoto={setCoverPhoto}
                    imagesDTO={imagesDTO} setImagesDTO={setImagesDTO}
                    flagCover={flagCover} setFlagCover={setFlagCover}
                    photos={photos} setPhotos={setPhotos}
                    brPhotos={brPhotos} setBrPhotos={setBrPhotos}
                ></Form5CreateAdContainer>
                : null
            }
            {activeStep === 4 ?
                <Form4CreateAdContainer
                    formData={formData} setFormData={setFormData}
                    activeStep={activeStep} setActiveStep={setActiveStep}
                    steps={steps}
                    isStepOptional={isStepOptional}
                    handleNext={handleNext}
                    handleBack={handleBack}
                    handleSkip={handleSkip}
                    handleReset={handleReset}
                    carCalendarTermList={carCalendarTermList}
                    setCarCalendarTermList={setCarCalendarTermList}

                    imagesDTO={imagesDTO} setImagesDTO={setImagesDTO}
                    flagCover={flagCover} setFlagCover={setFlagCover}
                    coverPhoto={coverPhoto} setCoverPhoto={setCoverPhoto}
                    carModel={carModel} setCarModel={setCarModel}
                ></Form4CreateAdContainer>
                : null
            }

            {activeStep === 5 ?
                <Form6CreateAdContainer
                    formData={formData} setFormData={setFormData}
                    activeStep={activeStep} setActiveStep={setActiveStep}
                    steps={steps}
                    isStepOptional={isStepOptional}
                    handleNext={handleNext}
                    handleBack={handleBack}
                    handleSkip={handleSkip}
                    handleReset={handleReset}
                    
                    handleCreatedAd={handleCreatedAd}
                    imagesDTO={imagesDTO} setImagesDTO={setImagesDTO}
                    flagCover={flagCover} setFlagCover={setFlagCover}
                    coverPhoto={coverPhoto} setCoverPhoto={setCoverPhoto}
                    carModel={carModel} setCarModel={setCarModel}
                >
                </Form6CreateAdContainer>

                : null
            }
            {/* {
                activeStep === 6 ?
                    <Form6CreateAdContainer
                        formData={formData} setFormData={setFormData}
                        activeStep={activeStep} setActiveStep={setActiveStep}
                        steps={steps}
                        isStepOptional={isStepOptional}
                        handleNext={handleNext}
                        handleBack={handleBack}
                        handleSkip={handleSkip}
                        handleReset={handleReset}
                        handleCreatedAd={handleCreatedAd}
                    >
                    </Form6CreateAdContainer>
                    : null
            } */}

        </Container>


    )
}

export default CreateAdContainer;