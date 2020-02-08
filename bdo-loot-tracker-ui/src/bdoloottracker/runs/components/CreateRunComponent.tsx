import * as React from 'react';
import CreateRunForm from './CreateRunForm';
import { RematchDispatch, RematchRootState } from '@rematch/core';
import { models } from '../../../app/store';
import { connect } from 'react-redux';
import { withRouter } from 'react-router';
import { SaveRunPayload } from '../model';

class CreateRunComponent extends React.Component<any, any> {

  constructor(props: any, context: any) {
    super(props, context);
    this.state = {
      itemDrops: {},
    };
  }

  async componentDidMount() {
    const session = await this.props.findSessionById(this.props.match.params.sessionId);
    this.props.getLootTable(session.payload.spotId);
  }

  handleDropChange = (itemId: any, count: any) => {
    this.setState({
      itemDrops: {
        ...this.state.itemDrops,
        [itemId]: count,
      }
    });
  };

  handleSubmit = async (comment: string) => {
    const itemDrops: any = [];
    Object.keys(this.state.itemDrops).forEach(itemId => {
      itemDrops.push({ itemId, total: this.state.itemDrops[itemId] });
    });

    await this.props.saveRun({
      sessionId: this.props.session.id,
      comment,
      itemDrops,
    });
  };

  render() {
    return <div className="container">
      <div className="row">
        <div className="col">
          <h4>Add a new run</h4>
          <CreateRunForm handleSubmit={this.handleSubmit} handleDropChange={this.handleDropChange}/>
        </div>
      </div>
    </div>;
  }
}

const mapStateToProps = (state: RematchRootState<models>) => ({
  session: state.sessions.currentSession,
});

const mapDispatchToProps = (dispatch: RematchDispatch<models>) => ({
  findSessionById: (sessionId: any) => dispatch.sessions.findSessionById(sessionId),
  getLootTable: (spotId: any) => dispatch.lootTables.getLootTableForSpot(spotId),
  saveRun: (payload: SaveRunPayload) => dispatch.runs.saveRun(payload),
});

export default withRouter(connect(mapStateToProps, mapDispatchToProps as any)(CreateRunComponent));
