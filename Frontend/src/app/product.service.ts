import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from './models/product.model';
import { Seller } from './models/seller.model';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  public url = `http://localhost:8080/YourMart-PMP-Admin-Panel/api/product`;
  public id =localStorage.getItem('userToken');
  constructor(private http: HttpClient) { }
  getProducts() {
    return this.http.get<Array<Product>>(this.url);
  }

  getSearchResults(searchText): Observable<Array<Product>> {
       return this.http.get<Array<Product>>(`${this.url}/search/${searchText}`)
  }

  addProduct(product:Product){
    
    return this.http.post(`${this.url}`,
    {"seller" : {
      "id" : Number(this.id)
    },
     "category":{
       "id" : product.category
     },
     "name" :product.name ,
     "mrp":product.mrp,
     "ymp":product.ymp,
     "ssp":product.ssp ,
     "primaryImg":product.primaryImg,
     "galleryImages":product.galleryImages,
     "shortDescription":product.shortDescription,
     "longDescription":product.longDescription
   });
  }

  updateProduct(product:Product, id){
    console.log(product);
    return this.http.put(`${this.url}/${id}`,
    {"seller" : {
      "id" : Number(this.id)
    },
     "category":{
       "id" : product.category.id
     },
     "name" :product.name ,
     "mrp":product.mrp,
     "ymp":product.ymp,
     "ssp":product.ssp ,
     "primaryImg":product.primaryImg,
     "shortDescription":product.shortDescription,
     "longDescription":product.longDescription
   });
  }


  getProduct(id: number){
    return this.http.get(`${this.url}/${id}`);
  }
}
