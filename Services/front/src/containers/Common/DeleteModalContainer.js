import React from 'react';
import { Button, Modal } from 'react-bootstrap'

const DeleteModalContainer = (props) => {


    return (

        <Modal show={props.show} onHide={() => { props.setShow(false); }}>
            <Modal.Header closeButton>
                <Modal.Title>Brisanje</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <p>Jeste li sigurni da želite da nastavite brisanje?</p>
                <p>{props.msg}</p>
            </Modal.Body>

            <Modal.Footer>
                <Button variant="secondary" onClick={() => { props.setShow(false); }}>Ne</Button>
                <Button variant="danger" onClick={props.onDelete}>Da</Button>
            </Modal.Footer>

        </Modal>
    );
}

export default DeleteModalContainer;
