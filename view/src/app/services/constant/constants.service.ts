import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConstantsService {
  readonly baseApplicationUrl: string =  'https://application-appl.herokuapp.com';
  readonly baseDeviceUrl: string = 'https://test-home-automations.herokuapp.com';
}
