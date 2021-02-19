import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { UserService } from '../service/user.service';
import { AppComponent } from '../app.component';

/**
 * Componente de listado de usuarios. Si no hay ninguno logeado muestra una alerta.
 */
@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users: User[];

  constructor(private userService: UserService,private appcomponent: AppComponent) {
  }

  ngOnInit() {
	if(this.appcomponent.isLogedIn()){
	    this.userService.findAll().subscribe(data => {
    	  this.users = data;
    	});
	}else{
		alert("Not registered");
	}
  }

}
