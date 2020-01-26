import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConstantsService {
  readonly baseApplicationUrl: string =  'https://rocky-dawn-44081.herokuapp.com';
  readonly baseDeviceUrl: string = 'http://localhost:8081';
}
