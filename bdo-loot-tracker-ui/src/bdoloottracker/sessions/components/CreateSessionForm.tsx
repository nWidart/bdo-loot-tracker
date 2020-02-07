import * as React from 'react';
import { useState } from 'react';
import { Button, Form, FormGroup, Input, Label } from 'reactstrap';
import { RematchRootState } from '@rematch/core';
import { models } from '../../../app/store';
import { connect } from 'react-redux';
import { Spot } from '../../spots/model';

interface CreateSessionFormProps {
  handleSubmit: (payload: any) => void;
  spots: Spot[];
}

const CreateSessionForm = (props: CreateSessionFormProps) => {
  const [spotId, setSpotId] = useState();
  const [ap, setAp] = useState('150');
  const [dp, setDp] = useState('150');
  const [bdoClass, setClass] = useState();
  const { handleSubmit, spots } = props;
  return <Form>
    <FormGroup>
      <Label for="spot">Spot</Label>
      <Input type="select" name="select" id="spot" onChange={(event) => setSpotId(event.target.value)} required>
        <option></option>
        {spots && spots.map((spot: Spot) => (
          <option value={spot.id} key={spot.id}>{spot.name}</option>
        ))}
      </Input>
    </FormGroup>
    <FormGroup>
      <Label for="AP">AP</Label>
      <Input type="number" name="AP" id="AP" value={ap} onChange={(event) => setAp(event.target.value)}/>
    </FormGroup>
    <FormGroup>
      <Label for="DP">DP</Label>
      <Input type="number" name="DP" id="DP" value={dp} onChange={(event) => setDp(event.target.value)}/>
    </FormGroup>
    <FormGroup>
      <Label for="class">Class</Label>
      <Input type="text" name="class" id="class" defaultValue={bdoClass}
             onChange={(event) => setClass(event.target.value)}/>
    </FormGroup>
    <Button className="float-right" color="primary" onClick={() => handleSubmit({ spotId, ap, dp, bdoClass })}>Start
      session</Button>
  </Form>;
};

const mapStateToProps = (state: RematchRootState<models>) => ({
  spots: state.spots.spots
});

export default connect(mapStateToProps)(CreateSessionForm);
