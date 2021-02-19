import { Component, LOCALE_ID, Inject } from '@angular/core';
import { User } from './model/user';

/**
 * Aplicación principal.
 */
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title: string;
  user: User;
  login_str: string;

  languageList = [ { code: 'en', label: 'English' }, { code: 'es', label: 'Español' } ];
  
  constructor(@Inject(LOCALE_ID) protected localeId: string) {
	  this.title = 'Spring-Angular SistemasOEE';
	  this.login_str = 'Login';
  }

/**
 * Al logearse cambia el botón Login por Logout.
 */
  logedIn(){
	this.login_str = 'Logout ';
  }

/**
 * Al salir cambia el botón Logout por Login.
 */
  logedOut(){
	this.login_str = 'Login';
	this.user=null;
  }

/**
 * Retorna true si hay algún usuario logeado.
 */
  isLogedIn(){
	return (this.user!=null);
  }
}
