import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Seller } from './models/seller.model';
@Injectable({
  providedIn: 'root'
})
export class SellerService {

  constructor(private http: HttpClient) { }

  public url = `http://localhost:8080/YourMart-PMP-Admin-Panel/api/seller`;

  addSeller(seller:Seller){
     
    return this.http.post(`${this.url}`,
    {"name" : seller.name,
     "companyName":seller.companyName,
     "owner" : seller.owner,
     "gstNumber" : seller.gstNumber,
     "address": seller.address,
     "email": seller.email,
     "telephone":seller.telephone,
     "password" :seller.password
  
  });
  }

  getSeller(seller: Seller){
    return this.http.post(`${this.url}/login`, seller);
  }
}
