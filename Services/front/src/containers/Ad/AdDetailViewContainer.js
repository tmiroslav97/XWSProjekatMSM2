import React, { useState, useEffect } from 'react';
import AdDetailViewComponent from '../../components/Ad/AdDetailViewComponent';
import { useDispatch, useSelector } from 'react-redux';
import { Container, Row, Col } from 'react-bootstrap';
import jwt_decode from 'jwt-decode';
import PaginationContainer from '../Pagination/PaginationContainer';
import PaginationSize from '../../components/Pagination/PaginationSize';
import { adSelector, searchDataSelector, commentsSelector } from '../../store/ad/selectors';
import { putSuccessMsg, putWarnMsg } from '../../store/common/actions';
import { fetchAd, fetchAllComments, fetchAllCommentsFromUser } from '../../store/ad/actions';
import SpinnerContainer from '../Common/SpinnerContainer';


const AdDetailViewContainer = (props) => {
    const dispatch = useDispatch();
    const searchData = useSelector(searchDataSelector);
    const ad = useSelector(adSelector);
    const isFetchAd = ad.isFetch;
    const adId = props.match.params.ad;
    const token = localStorage.getItem('token');

    const comments = useSelector(commentsSelector);
    const [flagComments, setFlagComments] = useState(false);

    useEffect(() => {
        dispatch(
            fetchAd({
                adId
            })
        );
    }, []);

    const hasRole = (accessRole) => {
        if (token != null) {
            const roles = jwt_decode(token).roles;
            const role = roles.filter(val => accessRole.includes(val));
            return role.length > 0;
        } else {
            return false;
        }
    };

    const addToCart = (ad) => {
        if (searchData.startDate === undefined || searchData.startDate === null || searchData.startDate === '' || searchData.endDate === undefined || searchData.endDate === null || searchData.endDate === '') {
            dispatch(putWarnMsg('Niste odabrali pocetni i krajnji datum rentiranja'));
        } else {
            let cart = new Map();
            if (JSON.parse(localStorage.getItem('cart')) != null) {
                cart = new Map(JSON.parse(localStorage.getItem('cart')));
            }
            if (cart.get(ad.publisherUserId) == null) {
                cart.set(ad.publisherUserId, { user: ad.publisherUserFirstName + " " + ad.publisherUserLastName, bundle: false, ads: [{ id: ad.id, adName: ad.name, startDate: searchData.startDate, endDate: searchData.endDate }] });
                dispatch(putSuccessMsg('Oglas uspjesno dodat u korpu'));
            } else {
                var temp = cart.get(ad.publisherUserId);
                var flag = false;
                for (let item of temp.ads) {
                    if (item.id == ad.id) {
                        flag = true;
                        break;
                    }
                }

                if (flag) {
                    dispatch(putWarnMsg('Oglas ste vec dodali u korpu'));
                } else {
                    cart.get(ad.publisherUserId).ads.push({ id: ad.id, adName: ad.name, startDate: searchData.startDate, endDate: searchData.endDate });
                    dispatch(putSuccessMsg('Oglas uspjesno dodat u korpu'));
                }
            }

            localStorage.setItem('cart', JSON.stringify(Array.from(cart.entries())));
        }
    };

    const handleDateFormat = (event) => {
        var datum = new Date(event);
        let date = datum.getDate();
        let month = datum.getMonth() + 1;
        let year = datum.getFullYear();
        let hours = datum.getHours();
        let minutes = datum.getMinutes();
        let ret = "";
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
        ret = + hours + ":" + minutes + " " + date + "-" + month + "-" + year;
        return ret;
    }

    const handleYear = (event) => {
        var datum = new Date(event);
        var year = datum.getFullYear();
        return year;
    }

    const getCommentsFromUser = (adId) => {
        console.log("oglas usera " + adId);
        dispatch(
            fetchAllCommentsFromUser({
                'id': adId
            })
        );
        setFlagComments(true);
    }
    const getComments = (adId) => {
        console.log("oglas " + adId);
        dispatch(
            fetchAllComments({
                'id': adId
            })
        );
        setFlagComments(true);
    }

    const getCommentsView = () => {
        let list = [];
        if (comments.isFetch) {
            comments.data.map((comment) => {
                let ss = comment.creationDate.substring(0, 10);
                let ss2 = comment.creationDate.substring(11, 16);
                ss = ss2 + " " + ss;
                list.push(
                    <tr key={comment.id}>
                        <td>{ss}</td>
                        <td>{comment.publisherUserFirstName + ' ' + comment.publisherUserLastName}</td>
                        <td>{comment.content}</td>
                    </tr>
                );
            })
        }
        return list;
    }

    const hideComments = () => {
        setFlagComments(false);
    }
    return (

        <Container >

            <Row>
                <Col >
                    {/* <AdDetailViewComponent id={adId} ad={ad.data}/> */}


                    {
                        isFetchAd ? <AdDetailViewComponent id={adId}
                            ad={ad.data}
                            token={token}
                            handleDateFormat={handleDateFormat}
                            handleYear={handleYear}
                            hasRole={hasRole}
                            addToCart={addToCart}
                            getCommentsFromUser={getCommentsFromUser}
                            getComments={getComments}
                            flagComments={flagComments} setFlagComments={setFlagComments}
                            getCommentsView={getCommentsView}
                            hideComments={hideComments}
                        /> : <SpinnerContainer />
                    }
                </Col>
            </Row>

        </Container>


    );
}

export default AdDetailViewContainer;