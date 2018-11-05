import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  @Output() onSearch: EventEmitter<any> = new EventEmitter<any>();
  constructor(private router: Router) { }
  
  ngOnInit() {
  }

  public sendNewList(products: any):void {
    this.onSearch.emit(products);
   // console.log('Picked date: ', date);
}

logout(){
  window.localStorage.removeItem("userToken")
  this.router.navigateByUrl('/login');
}

}
