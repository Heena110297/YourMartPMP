import { Component, OnInit, ViewChild, ElementRef,AfterViewInit  } from '@angular/core';
import { ProductService } from '../product.service';
import { Product } from '../models/product.model';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit  {
  products: Array<Product>;
  @ViewChild('myTable') myTableId: ElementRef;
  constructor(private activatedRoute:ActivatedRoute,  private productService :ProductService) {
   }

  ngOnInit() {
    if(!this.products) {
    this.productService.getProducts()
    .subscribe(data=>{
        this.products = data ;
        console.log(this.products);  
     });
    }

}

public sort(){

}
public recieveNewList(products: any):void {
  this.products = products

}

}
