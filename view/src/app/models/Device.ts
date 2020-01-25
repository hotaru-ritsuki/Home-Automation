import {Feature} from './Feature';

export class Device {
  constructor(
    public id: number,
    public brand: string,
    public model: string,
    public type: string,
    public releaseYear: number,
    public powerSupply: string,
    public features: Feature[] = [],
  ) {
  }
}
