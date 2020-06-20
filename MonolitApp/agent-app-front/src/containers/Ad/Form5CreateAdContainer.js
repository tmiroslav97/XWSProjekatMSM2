import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import Form5CreateAd from '../../components/Ad/Form5CreateAd'
import { createdAd, uploadImage } from '../../store/ad/actions';
import { Form, Button, ButtonGroup, ButtonToolbar, Row, Col, Container, Image, Card } from 'react-bootstrap';

const Form5CreateAdContainer = (props) => {
    const dispatch = useDispatch();
    const [validated, setValidated] = useState(false);

    // const [flagCover, setFlagCover] = useState();
    const [flag1, setFlag1] = useState();
    const [flag2, setFlag2] = useState();

    const handleForm5 = (event) => {
        event.preventDefault();
        const form = event.target;
        if (props.brPhotos === 4 && props.flagCover != null) {
            let dto = [];
            // console.log(props.formData);
            console.log("FORMA 5")
            setValidated(false);
            props.handleNext();
        } else {
            event.stopPropagation();
            setValidated(true);
        }
        if (props.brPhotos < 4) {
            setFlag1(true);
        }
        if (props.coverPhoto == null) {
            setFlag2(true);
        }
    };

    const handleImageChange = (e) => {

        if (e.target.files[0] != null) {
            let reader = new FileReader();
            let name = e.target.files[0].name;

            let flag = 0;
            // props.photos.map((image) => {
            //     if (image.name === name) {
            //         flag = 1;
            //         console.log("postoji vec slika");
            //     }
            // })

            if (flag === 0) {
                reader.onloadend = () => {
                    let i = props.photos.length;
                    let temp = {
                        "id": i,
                        "imagePreviewUrl": reader.result,
                        "name": name
                    };
                    let prom = props.photos;
                    prom.push(temp);
                    props.setPhotos(prom);

                    props.setBrPhotos(props.brPhotos + 1);
                    if (props.brPhotos === 4) {
                        setFlag1(false);
                    }
                }
                reader.readAsDataURL(e.target.files[0])

                let formData = new FormData();
                formData.append('photo', e.target.files[0]);
                formData.append('data', JSON.stringify(name));

                dispatch(
                    uploadImage(formData)
                );


            }
        }

    };

    const removeImage = (id) => {
        let temp = [];
        props.photos.map((photo) => {
            if (photo.id === id) {
                console.log(photo.name + " " + photo.id);
            }
            let naziv = "image" + temp.length;
            if (photo.id != id) {
                let p = {
                    "id": temp.length,
                    "imagePreviewUrl": photo.imagePreviewUrl,
                    "name": photo.name
                };
                temp.push(p);
            }
        })
        props.setPhotos(temp);
        props.setBrPhotos(props.brPhotos - 1);
    };

    const setCoverPhoto = (id) => {
        props.photos.map((photo) => {
            if (photo.id === id) {
                props.setFlagCover(id);
                setFlag2(false);
                
            }
        })
    };
    const removeCoverPhoto = (id) => {
        props.photos.map((photo) => {
            if (photo.id === id) {
                props.setFlagCover();
            }
        })
    };

    const previewImage = () => {
        let rez = [];
        props.photos.map((photo) => {
            rez.push(
                <Card key={photo.id} id={photo.id} className="imgPreview" id={photo.id} border="secondary" style={{ height: "140px", width: "140px", margin: "10px" }} >
                    <ButtonGroup style={{ height: "40px", width: "140px" }} >

                        {
                            (props.flagCover === photo.id) ?
                                <Button onClick={() => { removeCoverPhoto(photo.id) }} >Uncover</Button>
                                :
                                <Button onClick={() => { setCoverPhoto(photo.id) }} >Cover</Button>
                        }
                        <Button onClick={() => { removeImage(photo.id) }}>X</Button>

                    </ButtonGroup>
                    <img style={{ height: "100px", width: "140px" }} src={photo.imagePreviewUrl} />
                    {/* <div style={{ height: "20px", width: "140px" }}>{photo.name}</div> */}
                </Card>
            )
        });
        return rez;
    }

    return (
        <Form5CreateAd
            onSubmit={handleForm5}
            validated={validated}
            activeStep={props.activeStep}
            steps={props.steps}
            isStepOptional={props.isStepOptional}
            handleBack={props.handleBack}
            handleSkip={props.handleSkip}
            handleReset={props.handleReset}
            handleImageChange={handleImageChange}
            previewImage={previewImage}
            coverPhoto={props.coverPhoto} setCoverPhoto={props.setCoverPhoto}
            imagesDTO={props.imagesDTO} setImagesDTO={props.setImagesDTO}
            flagCover={props.flagCover} setFlagCover={props.setFlagCover}
            setCoverPhoto={setCoverPhoto}
            removeImage={removeImage}
            photos={props.photos} setPhotos={props.setPhotos}
            brPhotos={props.brPhotos} setBrPhotos={props.setBrPhotos}
            flag1={flag1} flag2={flag2}
        />
    );
}
export default Form5CreateAdContainer;