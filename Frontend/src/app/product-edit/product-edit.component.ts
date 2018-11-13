import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CategoryService } from '../category.service';
import { Product } from '../models/product.model';
import { Category } from '../models/category.model';


@Component({
  selector: 'app-product-edit',
  templateUrl: './product-edit.component.html',
  styleUrls: ['./product-edit.component.css']
})
export class ProductEditComponent implements OnInit {

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
  multipleImageSrc: Array<any> = new Array<any>();
  id;
  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
      this.id  = params['productId'];
          this.productService.getProduct(this.id)
    .subscribe((data: Product) => {
      this.product= data;
      console.log(this.product);
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
    galleryImages: ['', Validators.required]
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
  
 
  // console.log(this.product.seller);
  console.log(this.product.seller.id)
  this.product.name = this.f.name.value;
  this.product.shortDescription = this.f.shortDescription.value;
  this.product.longDescription = this.f.longDescription.value;
  this.product.mrp = this.f.mrp.value;
  this.product.ssp = this.f.ssp.value;
  this.product.ymp = this.f.ymp.value;

  // this.product.primaryImg = this.imageSrc
  // this.product.galleryImages = this.multipleImageSrc;
  this.product.category =this.product.category;
  this.productService.updateProduct(this.product, this.id)
  .subscribe(() => {
    this.router.navigateByUrl['products'];
  })
}
}
