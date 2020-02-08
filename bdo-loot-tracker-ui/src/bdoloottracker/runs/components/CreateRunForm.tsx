import * as React from 'react';
import { useState } from 'react';
import { Button, Form, FormGroup, Input, Label } from 'reactstrap';
import { RematchRootState } from '@rematch/core';
import { models } from '../../../app/store';
import { connect } from 'react-redux';
import { camelCase } from 'lodash';
import { LootTable } from '../../loottables/model';

interface CreateRunFormProps {
  handleSubmit: (payload: any) => void;
  handleDropChange: (itemId: any, count: any) => void;
  lootTables: LootTable[];
}

const CreateRunForm = (props: CreateRunFormProps) => {
  const [comment, setComment] = useState();
  const { handleSubmit, lootTables, handleDropChange } = props;

  return <Form>
    <div className="row">
      <div className="col-8">
        {lootTables && lootTables.map((lootTable) => (
          <FormGroup row className="bg-light" key={lootTable.item.id}>
            <Label for={camelCase(lootTable.item.name)}
                   className="col-sm-8 col-form-label">{lootTable.item.name}</Label>
            <div className="col-sm-4">
              <Input type="number"
                     name={lootTable.item.id.toString()}
                     id={camelCase(lootTable.item.name)}
                     onChange={(event => handleDropChange(lootTable.item.id, event.target.value))}
              />
            </div>
          </FormGroup>
        ))}
      </div>
      <div className="col-4">
        <FormGroup>
          <Label for="comment">Comment</Label>
          <Input type="textarea" name="comment" id="comment" value={comment}
                 onChange={(event) => setComment(event.target.value)}/>
        </FormGroup>
      </div>
    </div>

    <Button className="float-right" color="primary" onClick={() => handleSubmit(comment)}>Add run</Button>
  </Form>;
};

const mapStateToProps = (state: RematchRootState<models>) => ({
  lootTables: state.lootTables.lootTable,
});

export default connect(mapStateToProps)(CreateRunForm);
