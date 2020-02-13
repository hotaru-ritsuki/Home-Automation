import {Feature} from './Feature';

export class DeviceFeature {
  constructor(
    public uuid: string,
    public featureDTO: Feature
  ) {
  }
}
