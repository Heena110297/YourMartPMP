import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Product } from '../models/product.model';
import { ProductService } from '../product.service';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-product-search',
  templateUrl: './product-search.component.html',
  styleUrls: ['./product-search.component.css']
})
export class ProductSearchComponent implements OnInit {
  searchForm: FormGroup;
  products:Array<Product>;
 
  
  @Output() onSearch: EventEmitter<any> = new EventEmitter<any>();
  constructor(private formBuilder:FormBuilder,
    private productService: ProductService,
    private route:ActivatedRoute,
    private router:Router  ) { }

  ngOnInit() {
    this.searchForm = this.formBuilder.group({
       searchText:['',Validators.required]
    });
  }
  get f(){
    return this.searchForm.controls;
  }
  onSubmit(){
        this.productService.getSearchResults(this.f.searchText.value)
        .subscribe(data=>{
          this.products = data ;
          console.log(this.products);
          this.onSearch.emit(this.products);
         // this.router.navigateByUrl(`products/${this.products}`)  
       });
  }

}
