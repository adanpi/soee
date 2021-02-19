import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../service/user.service';
import { User } from '../model/user';
import { AppComponent } from '../app.component';

/**
 * Componente de acceso de los usuarios. Al entrar y validar correctamente el password se logea con él y accede al listado.
 * En caso de fallo en la validación muestra una alerta con la causa (Usuario no registrado, Error en password). 
 */
@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent {

  user: User;

  constructor(
    private route: ActivatedRoute, 
      private router: Router, 
        private userService: UserService,
		private appcomponent: AppComponent) {
    this.user = new User();
	this.appcomponent.logedOut();
  }

  onSubmit() {	
	console.log("Validate user: "+this.user.email);
    this.userService.validate(this.user).subscribe(result => 
		{
			console.log(result);
			if(result[0]=="validated"){
				this.appcomponent.user=this.user;
				this.appcomponent.logedIn();
				this.gotoUserList();
			}else{
				this.appcomponent.logedOut();				
				alert(result[0]);
			}
		});    
  }

  gotoUserList() {
    this.router.navigate(['/users']);
  }

}
