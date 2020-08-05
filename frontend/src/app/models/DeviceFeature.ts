import {Feature} from './Feature';
import {LocalDevice} from "./LocalDevice";

export class DeviceFeature {
  constructor(
    public uuid: string,
    public featureDTO: Feature,
    public localDevice: LocalDevice
  ) {
  }
}
