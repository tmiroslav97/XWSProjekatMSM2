import React from 'react';
import { Row, Col, Table } from 'react-bootstrap'
import BeachAccessIcon from '@material-ui/icons/BeachAccess';
import { makeStyles } from '@material-ui/core/styles';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import ListItemAvatar from '@material-ui/core/ListItemAvatar';
import Avatar from '@material-ui/core/Avatar';
import LocalOfferIcon from '@material-ui/icons/LocalOffer';
import DescriptionIcon from '@material-ui/icons/Description';
import ConfirmationNumberIcon from '@material-ui/icons/ConfirmationNumber';

const useStyles = makeStyles((theme) => ({
    root: {
      width: '100%',
      maxWidth: 360,
      backgroundColor: theme.palette.background.paper,
    },
  }));

const ReportComponent = (props) => {

    const classes = useStyles();

    return (
        <Row>
            <Col md={12} xs={12}>
                <List className={classes.root}>
                    <ListItem>
                        <ListItemAvatar>
                        <Avatar>
                            <ConfirmationNumberIcon />
                        </Avatar>
                        </ListItemAvatar>
                        <ListItemText primary="Broj predjenih kilometara" secondary={props.report.distanceTraveled + " KM"} />
                    </ListItem>
                    <ListItem>
                        <ListItemAvatar>
                        <Avatar>
                            <DescriptionIcon />
                        </Avatar>
                        </ListItemAvatar>
                        <ListItemText primary="Opis" secondary= {props.report.description} />
                    </ListItem>
                    <ListItem>
                        <ListItemAvatar>
                        <Avatar>
                            <LocalOfferIcon />
                        </Avatar>
                        </ListItemAvatar>
                        <ListItemText primary="Dodatna doplata" secondary= {props.report.invoice != null ? props.report.invoice.amount + " RSD"  : "-"}  />
                    </ListItem>
                </List>
            </Col>
        </Row>
    );
}

export default ReportComponent;
