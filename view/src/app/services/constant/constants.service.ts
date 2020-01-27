import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConstantsService {
  readonly baseApplicationUrl: string =  'https://application-appl.herokuapp.com';
  readonly baseDeviceUrl: string = 'http://localhost:8081';
}
