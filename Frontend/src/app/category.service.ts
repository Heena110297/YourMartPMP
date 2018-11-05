import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from './models/category.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  public url = `http://localhost:8080/YourMart-PMP-Admin-Panel/api/category`;
  constructor(private http: HttpClient) { }
  getCategories(): Observable<Array<Category>> {
    return this.http.get<Array<Category>>(this.url);
 
  }
}
