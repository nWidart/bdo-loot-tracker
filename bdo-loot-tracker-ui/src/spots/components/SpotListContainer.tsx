import * as React from 'react';
import { RematchDispatch, RematchRootState } from '@rematch/core';
import { models } from '../../store';
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
      <div className="w-1/2 h-12">
        <ul>
          {
            this.props.spots && this.props.spots.map((spot: Spot) => (
              <li key={spot.id}>{spot.name}</li>
            ))}
        </ul>
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
