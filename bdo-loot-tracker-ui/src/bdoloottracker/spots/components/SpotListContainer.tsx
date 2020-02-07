import * as React from 'react';
import { RematchDispatch, RematchRootState } from '@rematch/core';
import { models } from '../../../app/store';
import { connect } from 'react-redux';
import { Spot } from '../model';

interface SpotListContainerProps {
  getSpots: () => void,
  spots: Spot[],
}

class SpotListContainer extends React.Component<any> {
  componentDidMount() {
    this.props.getSpots();
  }

  render() {
    return (
      <div className="container">
        <div className="row">
          <div className="col">
            <ul>
              {
                this.props.spots && this.props.spots.map((spot: Spot) => (
                  <li key={spot.id}>{spot.name}</li>
                ))}
            </ul>
          </div>
        </div>
      </div>
    );
  }
}

const mapStateToProps = (state: RematchRootState<models>) => ({
  spots: state.spots.spots
});

const mapDispatchToProps = (dispatch: RematchDispatch<models>) => ({
  getSpots: () => dispatch.spots.getSpots(),
});

export default connect(mapStateToProps as any, mapDispatchToProps as any)(SpotListContainer);
