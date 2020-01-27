import { BAD_REQUEST, FORBIDDEN, NOT_FOUND, UNAUTHORIZED } from '../http-response-status';
import { Injectable } from '@angular/core';
import {
  HttpClient, HttpErrorResponse, HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from '@angular/common/http';
import { BehaviorSubject, Observable, of, throwError } from 'rxjs';
import { catchError, filter, switchMap, take } from 'rxjs/operators';
import { LocalStorageService } from './local-storage.service';
import { Router } from '@angular/router';

interface NewTokenPair {
  accessToken: string;
  refreshToken: string;
}

@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor {
  private refreshTokenSubject: BehaviorSubject<NewTokenPair> = new BehaviorSubject<NewTokenPair>(null);
  private isRefreshing = false;
  private updateAccessTokenLink: string;

  constructor(private http: HttpClient,
              private localStorageService: LocalStorageService,
              private router: Router) {
  }

  /**
   * Intercepts all HTTP requests, adds access token to authentication header (except authentication requests),
   * intercepts 401, 403, and 404 error responses.
   */
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (req.url.includes('login') || req.url.includes('register')) {
      return next.handle(req);
    }
    if (this.localStorageService.getAccessToken()) {
      req = this.addAccessTokenToHeader(req, this.localStorageService.getAccessToken());
    }
    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === UNAUTHORIZED) {
          return this.handle401Error(req, next);
        }
        if (error.status === FORBIDDEN) {
          return this.handle403Error(req);
        }
        if (error.status === NOT_FOUND) {
          return this.handle404Error(req);
        }
        this.router.navigate(['/login']).then(r => r);
        return throwError(error);
      })
    );
  }

  /**
   * Handles 401 response. It tries to get new access/refresh token pair with refresh token.
   * All of the rest request are put on hold.
   */
  private handle401Error(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (!this.isRefreshing) {
      this.isRefreshing = true;
      this.refreshTokenSubject.next(null);
      return this.getNewTokenPair(this.localStorageService.getRefreshToken()).pipe(
        catchError((error: HttpErrorResponse) => this.handleRefreshTokenIsNotValid(error)),
        switchMap((newTokenPair: NewTokenPair) => {
          this.localStorageService.setAccessToken(newTokenPair.accessToken);
          this.localStorageService.setRefreshToken(newTokenPair.refreshToken);
          this.isRefreshing = false;
          this.refreshTokenSubject.next(newTokenPair);
          return next.handle(this.addAccessTokenToHeader(req, newTokenPair.accessToken));
        })
      );
    } else {
      return this.refreshTokenSubject.pipe(
        filter((newTokenPair: NewTokenPair) => newTokenPair !== null),
        take(1),
        switchMap((newTokenPair: NewTokenPair) => next.handle(this.addAccessTokenToHeader(req, newTokenPair.accessToken))),
        catchError(() => of<HttpEvent<any>>())
      );
    }
  }

  /**
   * Handles a situation when refresh token is expired.
   */
  private handleRefreshTokenIsNotValid(error: HttpErrorResponse): Observable<HttpEvent<any>> {
    this.isRefreshing = false;
    if (error.status === BAD_REQUEST) {
      this.localStorageService.clear();
      this.router.navigate(['/login']).then(r => r);
      return of<HttpEvent<any>>();
    }
    return throwError(error);

  }

  /**
   * Send refresh token in order to get new access/refresh token pair.
   */
  private getNewTokenPair(refreshToken: string): Observable<NewTokenPair> {
    this.updateAccessTokenLink = 'localhost:4200/refreshTokens';
    return this.http.get<NewTokenPair>(`${this.updateAccessTokenLink}`);
  }

  /**
   * Adds access token to authentication header.
   */
  addAccessTokenToHeader(req: HttpRequest<any>, accessToken: string) {
    return req.clone({
      setHeaders: {
        Authorization: `${accessToken}`
      }
    });
  }

  /**
   * Handles 403 HTTP error response, redirects to sign in page.
   */
  private handle403Error(req: HttpRequest<any>): Observable<HttpEvent<any>> {
    console.log(`You don't have authorities to access ${req.url}`);
    this.router.navigate(['/login']).then(r => r);
    return of<HttpEvent<any>>();
  }

  /**
   * Handles 404 HTTP error response, redirects to custom error page.
   */
  private handle404Error(req: HttpRequest<any>): Observable<HttpEvent<any>> {
    console.log(`Page does not exist ${req.url}`);
    this.router.navigate(['/error.component.html']).then(r => r);
    return of<HttpEvent<any>>();
  }
}
