import React, { useState, useEffect } from 'react';
import AdComponent from '../../components/Ad/AdComponent';
import { useDispatch, useSelector } from 'react-redux';
import { fetchBestGrade, fetchMaxMileage, fetchMaxComments } from '../../store/ad/actions';
import StatisticsComponent from '../../components/AgentFirm/StatisticsComponent'
import { adSelector } from '../../store/ad/selectors';
import { tokenSelector } from '../../store/user/selectors';
import jwt_decode from 'jwt-decode';
import { Container, Row, Col } from 'react-bootstrap';
import SpinnerContainer from '../Common/SpinnerContainer';
import { putAd } from '../../store/ad/actions';

const StatisticsContainer = () => {
    const token = useSelector(tokenSelector);
    const dispatch = useDispatch();
    const ad = useSelector(adSelector);
    const isFetchAd = ad.isFetch;
    const email = jwt_decode(token).sub;
    const [flag, setFlag] = useState(1);

    useEffect(() => {
        return () => {
            dispatch(putAd({
                'data': [],
                'isFetch': false
            }));
        };
    }, []);


    const handleGrade = () => {
        setFlag(1);
        dispatch(
            fetchBestGrade(email)
        );

    }

    const handleMileage  = () => {
        setFlag(2);
        dispatch(
            fetchMaxMileage(email)
        );
    }

    const handleComment  = () => {
        setFlag(3);
        dispatch(
            fetchMaxComments(email)
        );
    }

    return (


        <Container>

            <Row>
                <Col >

                   <StatisticsComponent
                            ad={ad.data}
                            isFetchAd={isFetchAd}
                            handleGrade={handleGrade}
                            handleMileage={handleMileage}
                            handleComment={handleComment}
                            flag={flag}
                    />  
                    </Col>
            </Row>

        </Container>

    );
}

export default StatisticsContainer;