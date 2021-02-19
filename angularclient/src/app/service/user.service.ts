import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../model/user';
import { Observable } from 'rxjs/Observable';

/**
 * Servicio de gestión de Usuarios del Back-end
 */
@Injectable()
export class UserService {

  private usersUrl: string;

  private validateUsersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8081/users';
    this.validateUsersUrl = 'http://localhost:8081/userval';
  }

/**
 * Método de listado de Usuarios del Back-end
 */
  public findAll(): Observable<User[]> {
    return this.http.get<User[]>(this.usersUrl);
  }

/**
 * Método de creación de nuevos Usuarios del Back-end
 */
  public save(user: User) {
    return this.http.post<User>(this.usersUrl, user);
  }

/**
 * Método de validación de Usuarios del Back-end
 */
  public validate(user: User) {
    return this.http.post<string>(this.validateUsersUrl, user);
  }

}
