import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import Form4CreateAd from '../../components/Ad/Form4CreateAd'
import { imageNameSelector } from '../../store/ad/selectors';

const Form4CreateAdContainer = (props) => {
    const dispatch = useDispatch();
    const [validated, setValidated] = useState(false);
    const [startDate, setStartDate] = useState(null);
    const [endDate, setEndDate] = useState(null);
    const [flag, setFlag] = useState(true);

    const imageName = useSelector(imageNameSelector);

    const addTerm = (event) => {
        event.preventDefault();
        const form = event.target;



        if (form.checkValidity() === false) {
            event.stopPropagation();
            setValidated(true);
        } else {
            let flag = false;
            if (props.carCalendarTermList != "") {
                props.carCalendarTermList.map((temp) => {
                    if (temp.startDate <= startDate && startDate <= temp.endDate) {
                        flag = true;
                        console.log("start date je izmedju")
                    } else if (temp.startDate <= endDate && endDate <= temp.endDate) {
                        flag = true;
                        console.log("end date je izmedju")
                    } 
                })
            }
            if(flag === true){
                console.log("ne mozes da dodas datum.");
                alert("Datumi se ne smeju preklapati.");
            }else{
                let temp = {
                    'startDate': startDate,
                    'endDate': endDate
                };
                let cctl = props.carCalendarTermList;
                cctl.push(temp);
                props.setCarCalendarTermList(cctl);
                // props.setCarCalendarTermList([...props.carCalendarTermList, temp]);
                console.log("FORMA 4")
                console.log(props.carCalendarTermList);
                props.setFormData({
                    ...props.formData,
                    carCalendarTermCreateDTOList: props.carCalendarTermList
                });
                console.log(props.formData);
                setValidated(false);
            }
            
        }
    }
    const getCurrentDate = () => {
        let newDate = new Date()
        let date = newDate.getDate();
        let month = newDate.getMonth() + 1;
        let year = newDate.getFullYear();
        let hours = newDate.getHours();
        let minutes = newDate.getMinutes();
        let rez = "";
        if (month < 10) {
            month = "0" + month;
        }
        if (date < 10) {
            date = "0" + date;
        }
        if (hours < 10) {
            hours = "0" + hours;
        }
        if (minutes < 10) {
            minutes = "0" + minutes;
        }
        rez = year + "-" + month + "-" + date + "T" + hours + ":" + minutes;
        return rez;
    }
    const handleStartDate = (event) => {
        setStartDate(event.target.value);
    }
    const handleEndDate = (event) => {
        setEndDate(event.target.value);
    }
    const getCarCalentarTermList = () => {
        let list = [];
        let i = 1;
        if (props.carCalendarTermList != "") {
            props.carCalendarTermList.map((term) => {
                let ss = term.startDate.substring(0, 10);
                let ss2 = term.startDate.substring(11, 16);
                let ee = term.endDate.substring(0, 10);
                let ee2 = term.endDate.substring(11, 16);
                ss = ss + " " + ss2;
                ee = ee + " " + ee2;
                list.push(
                    <tr key={i}>
                        <td>{i}</td>
                        <td>{ss}</td>
                        <td>{ee}</td>
                        {/* <td align="right">
                            <Button variant="outline-success" onClick={() => { props.handleEdit(carManufacturer); }}>Izmjeni</Button>
                        </td>
                        <td align="right">
                            <Button variant="outline-danger" onClick={() => { props.handleDelete(carManufacturer.id); }}>Obriši</Button>
                        </td> */}
                    </tr>
                );
                i++;
            })
        }

        return list;
    }
    const handlerForm4 = () => {
        if (props.carCalendarTermList.length == 0) {
            setFlag(0);
        } else {
            // setFlag(1);

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
                });


                setFlag(1);
                console.log(props.formData);
                props.handleNext();
            }


            // console.log(props.formData);
            // props.handleNext();
        }
    }

    return (
        <Form4CreateAd
            handleCreatedAd={props.handleCreatedAd}
            addTerm={addTerm}
            handlerForm4={handlerForm4}
            validated={validated}
            activeStep={props.activeStep}
            steps={props.steps}
            isStepOptional={props.isStepOptional}
            handleNext={props.handleNext}
            handleBack={props.handleBack}
            handleSkip={props.handleSkip}
            handleReset={props.handleReset}
            carCalendarTermList={props.carCalendarTermList}
            setCarCalendarTermList={props.setCarCalendarTermList}
            getCurrentDate={getCurrentDate}
            startDate={startDate}
            endDate={endDate}
            handleStartDate={handleStartDate}
            handleEndDate={handleEndDate}
            getCarCalentarTermList={getCarCalentarTermList}
            flag={flag}
        />
    );
}
export default Form4CreateAdContainer;