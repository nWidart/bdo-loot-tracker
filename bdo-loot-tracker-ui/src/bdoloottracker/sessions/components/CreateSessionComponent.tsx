import * as React from 'react';
import CreateSessionForm from './CreateSessionForm';

export class CreateSessionComponent extends React.Component<any> {
  handleSubmit = (values: any) => {
    console.log(values);
  };

  render() {
    return <>
      <div className="flex justify-center -my-4 flex-gr">
        <div className="container mx-auto">
          <div className="flex flex-wrap -mx-4">
            <div className="w-full px-4">
              <div className="py-4 px-4">
                <h1>create session!</h1>
                <CreateSessionForm handleSubmit={this.handleSubmit}/>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>;
  }
}
