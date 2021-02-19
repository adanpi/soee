import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../service/user.service';
import { User } from '../model/user';
import { AppComponent } from '../app.component';

/**
 * Componente de creación de nuevos usuarios. Al crear no nuevo se logea con él y accede al listado.
 */
@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent {

  user: User;

  constructor(
    private route: ActivatedRoute, 
      private router: Router, 
        private userService: UserService,
		private appcomponent: AppComponent) {
    this.user = new User();
  }

  onSubmit() {
    this.userService.save(this.user).subscribe(result => this.gotoUserList());
  }

  gotoUserList() {
	this.appcomponent.user=this.user;
	this.appcomponent.logedIn();
    this.router.navigate(['/users']);
  }
}
