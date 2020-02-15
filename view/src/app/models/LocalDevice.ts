import {Locations} from './Locations';
import {Device} from './Device';

export interface LocalDevice {
  uuid: string;
  description: string,
  location: Locations;
  deviceTemplate: Device;
}
