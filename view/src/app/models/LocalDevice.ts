import {Locations} from '../home/model/Locations';
import {Device} from './Device';

export interface LocalDevice {
  uuid: string;
  location: Locations;
  deviceTemplate: Device;
}
