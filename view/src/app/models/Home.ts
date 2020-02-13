import {Locations} from './Locations';

export interface Home {
  id: number;
  name: string;
  country: string;
  city: string;
  addressa: string;
  locations: Locations[];
}
