import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-navbar-common',
  templateUrl: './navbar-common.component.html',
  styleUrls: ['./navbar-common.component.css']
})
export class NavbarCommonComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  logout(){
    window.localStorage.removeItem("userToken")
    this.router.navigateByUrl('/login');
  }

}
