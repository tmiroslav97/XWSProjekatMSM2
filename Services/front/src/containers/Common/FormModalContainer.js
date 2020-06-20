import React from 'react';
import { Button, Modal } from 'react-bootstrap'

const FormModalContainer = ({ component: Component, selectedItem = null, footer = true, ...props }) => {

  return (

    <Modal show={props.show} onHide={() => { props.setShow(false) }}>
      <Modal.Header closeButton>
        <Modal.Title>{props.name}</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Component onSubmit={props.onSubmit} validated={props.validated} data={props.data} selectedItem={selectedItem}></Component>
      </Modal.Body>
      {footer &&
        <Modal.Footer>
          <Button variant="secondary" onClick={() => { props.setShow(false) }}>
            Close
        </Button>
        </Modal.Footer>
      }
    </Modal>
  );
}

export default FormModalContainer;
