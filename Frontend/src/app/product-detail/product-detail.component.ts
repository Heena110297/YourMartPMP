import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CategoryService } from '../category.service';
import { Product } from '../models/product.model';
import { Category } from '../models/category.model';


@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  constructor(private productService :ProductService,
 
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder,
    private router: Router,
    private categoryService: CategoryService) { }

  productForm: FormGroup;
  loading = false;
  submitted = false;
  product: Product = new Product;
  categories: Array<Category>;
  imageSrc;
  galleryImages : Array<any>;
  multipleImageSrc: Array<any> = new Array<any>();
  id;
  
  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
      this.id  = params['productId'];
          this.productService.getProduct(this.id)
    .subscribe((data: Product) => {
      this.product= data;
      console.log(this.product);
      console.log(this.product.galleryImages)
    });

    });
    this.categoryService.getCategories()
    .subscribe(data => {
      this.categories = data;
    });
    
  this.productForm = this.formBuilder.group({
  
    name: ['', Validators.required],
    shortDescription: ['', Validators.required],
    longDescription: ['', Validators.required],
    category: ['', Validators.required],
    mrp: ['', Validators.required],
    ssp: ['', Validators.required],
    ymp: ['', Validators.required],
    primaryImage: ['', Validators.required],
    galleryImages: ['', Validators.required],
    comment:['']
  });
  
}
get f() {
  // console.log(this.productForm.controls);
  return this.productForm.controls;
}

onSubmit() {
  this.loading = true;
  this.submitted = true;
  // console.log(this.productForm.value);
  this.router.navigateByUrl('/product/edit/'+this.product.id);

}
}
