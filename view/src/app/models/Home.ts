import {Locations} from './Locations';

export interface Home {
  id: number;
  country: string;
  city: string;
  addressa: string;
  locations: Locations[];
}
