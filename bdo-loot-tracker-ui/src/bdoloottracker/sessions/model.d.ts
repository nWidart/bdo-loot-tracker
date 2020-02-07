export interface Session {
  id: number;
  createdAt: string;
  stoppedAt: string;
  userId: string;
  spotId: string;
  ap: string;
  dp: string;
  class: string;
  bdoClass?: string;
}
