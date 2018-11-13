import { Component, OnInit, ViewChild, ElementRef,AfterViewInit  } from '@angular/core';
import { ProductService } from '../product.service';
import { Product } from '../models/product.model';
import {Router, ActivatedRoute } from '@angular/router';
import {MatTableDataSource, MatPaginator, MatSort} from '@angular/material';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit  {
  products: Array<Product>;
  displayedColumns = ['id','primaryImg','name','seller','category','status','mrp','ssp','ymp','created'];
  dataSource: any;
  dataCount: any;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private activatedRoute:ActivatedRoute,  private router: Router,  private productService :ProductService) {
   }

   applyFilter(filterValue: string) {
    filterValue = filterValue.trim();
    filterValue = filterValue.toLowerCase();
    this.dataSource.filter = filterValue;
  }


  ngOnInit() {
    if(!this.products) {
    this.productService.getProducts()
    .subscribe(data=>{
        this.products = data ;
        this.dataSource = new MatTableDataSource(this.products);
        this.dataCount = this.dataSource.length ;
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort ;
       
     });
    }

}

public recieveNewList(products: any):void {
  this.products = products

}
 onRowClick(row){
  // console.log(row);
   this.router.navigateByUrl('/product/'+row.id);
 }
}
