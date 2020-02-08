import SpotListContainer from '../spots/components/SpotListContainer';
import CreateSessionComponent from '../sessions/components/CreateSessionComponent';
import BdoLootTrackerRoutes from './BdoLootTrackerRoutes';
import SessionList from '../sessions/components/SessionList';
import CreateRunComponent from '../runs/components/CreateRunComponent';

const routes = {
  bdoLootTracker: {
    path: '/app',
    exact: false,
    component: BdoLootTrackerRoutes,
    children: {
      spots: {
        path: '/app/spots',
        exact: true,
        component: SpotListContainer,
      },
      createSession: {
        path: '/app/session/new',
        exact: true,
        component: CreateSessionComponent,
      },
      sessionList: {
        path: '/app/sessions',
        exact: true,
        component: SessionList,
      },
      createRun: {
        path: '/app/session/:sessionId/run/new',
        exact: true,
        component: CreateRunComponent,
      }
    }
  }
};

export default routes;
