export class Alert {
  constructor(
    public data: string,
    public timestamp: Date,
    public id: string,
    public uuid: string,
    public description: string,
    public homeId: number,
  ) {
  }
}
