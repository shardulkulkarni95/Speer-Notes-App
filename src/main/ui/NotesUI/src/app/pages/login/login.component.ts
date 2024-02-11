import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginObj: any={
    "username": "",
    "password": ""
  };

  signupObj: any={
    "name": "",
    "email": "",
    "roles": "user",
    "password": ""
  };

  constructor(private http: HttpClient, private router: Router) { }


  onLogin(){
    this.http.post("http://localhost:8088/api/auth/login",this.loginObj).subscribe((res:any)=>{
      if(res.status){
        alert(res.massage);
        localStorage.setItem("token",res.data);
        this.router.navigateByUrl("/dashboard");
      }else{
        alert("Invalid Username or Password");
      }
    })
  }

  onSignUp(){
    this.http.post("http://localhost:8088/api/auth/signup",this.signupObj).subscribe((res:any)=>{
      if(res.status){
        alert(res.massage);
        this.router.navigateByUrl("/login");
      }else{
        alert(res.err);
      }
    })
  }

  

}
