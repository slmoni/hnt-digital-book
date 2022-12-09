import { Component } from '@angular/core';
import { UserService } from '../user.service';

@Component({
  selector: 'app-userform',
  templateUrl: './userform.component.html',
  styleUrls: ['./userform.component.css']
})
export class UserformComponent {
  constructor(public userService:UserService){

  }
  //state
  user={
    username : "",
    email:"",
    password:"",
    gender: ""
  }
  // public register(){
  //   console.log("test");
  //   const observable = this.userService.save(this.user);
  //   observable.subscribe((response:any)=>{
  //     console.log(response);
  //     sessionStorage.setItem('token', response.accessToken);
      
  //   })
  //   console.error('error has happed'+this.user)
  // }

  public signup() {
    const observable=this.userService.signup(this.user);
      observable.subscribe((response:any)=>{
      console.log(response);
      
    })
  }

  public signin() {
    const observable=this.userService.signin(this.user);
      observable.subscribe((response:any)=>{
      console.log(response);
      sessionStorage.setItem('token', response.accessToken);
      
    })
  }
}
