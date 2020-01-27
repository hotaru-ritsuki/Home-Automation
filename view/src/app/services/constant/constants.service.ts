import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConstantsService {
  readonly baseApplicationUrl: string =  'http://localhost:8080';
  readonly baseDeviceUrl: string = 'http://localhost:8081';
}
