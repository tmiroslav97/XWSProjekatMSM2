import React, { useState, useEffect } from 'react';
import AdComponent from '../../components/Ad/AdComponent';
import { useDispatch, useSelector } from 'react-redux';
import { Container, Row, Col } from 'react-bootstrap';
import { adsSelector } from '../../store/ad/selectors';
import PaginationContainer from '../Pagination/PaginationContainer';
import PaginationSize from '../../components/Pagination/PaginationSize';
import OrdinarySearchContainer from '../../containers/Search/OrdinarySearchContainer'
import { fetchAds } from '../../store/ad/actions';
import SpinnerContainer from '../Common/SpinnerContainer';
import Typography from '@material-ui/core/Typography';
import Box from '@material-ui/core/Box';
import { makeStyles, useTheme } from '@material-ui/core/styles';
import StatisticsComponent from '../../components/AgentFirm/StatisticsComponent'


const StatisticsContainer = () => {


    return(
        <StatisticsComponent>

        </StatisticsComponent>
       
  
    );
}

export default StatisticsContainer;