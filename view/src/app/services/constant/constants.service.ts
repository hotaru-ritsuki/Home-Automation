import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConstantsService {
  readonly baseApplicationUrl: string =  'https://auotomation-application.herokuapp.comheroku';
  readonly baseDeviceUrl: string = 'http://localhost:8081';
}
