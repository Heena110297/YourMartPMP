import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../product.service';
import { Product } from '../models/product.model';
import { Category } from '../models/category.model';
import { first } from 'rxjs/operators';
import { CategoryService } from '../category.service';


@Component({
  selector: 'app-product-add',
  templateUrl: './product-add.component.html',
  styleUrls: ['./product-add.component.css']
})
export class ProductAddComponent implements OnInit {
  productForm: FormGroup;
  loading = false;
  submitted = false;
  product: Product = new Product;
  categories: Array<Category>;
  imageSrc;
  multipleImageSrc: Array<any> = new Array<any>();
  public id =localStorage.getItem('userToken');
  constructor(private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder,
    private productService: ProductService,
    private router: Router,
    private categoryService: CategoryService) {
    // this.product = new Product();
  }

  ngOnInit() {
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
    if (this.productForm.invalid) {
      window.alert('Please Fill all the fields');
    }
    
    console.log(this.product.seller);
    this.product.name = this.f.name.value;
    this.product.shortDescription = this.f.shortDescription.value;
    this.product.longDescription = this.f.longDescription.value;
    this.product.mrp = this.f.mrp.value;
    this.product.ssp = this.f.ssp.value;
    this.product.ymp = this.f.ymp.value;

    this.product.primaryImg = this.imageSrc
    this.product.galleryImages = this.multipleImageSrc;
    this.product.category =(this.f.category.value);

    this.productService.addProduct(this.product)
      .pipe(first())
      .subscribe(
        data => {
          console.log('done');
          this.router.navigateByUrl('/products');
        },
      );
  }

  onFileSelected(event) {
    let file = event.target.files[0];
    console.log(event);
    let pattern = /image-*/;
    let reader = new FileReader();
    if (!file.type.match(pattern)) {
      alert('invalid format');
      return;
    }
    reader.onload = this._handleReaderLoaded.bind(this);
    reader.readAsDataURL(file);
  }
  _handleReaderLoaded(e) {
    let reader = e.target;
    this.imageSrc = reader.result;
    console.log(this.imageSrc)
  }

  onMultipleFileSelected(event) {
    let file = event.target.files;
    console.log(file);
    for(var i =0 ; i< file.length; i++) {
      let pattern = /image-*/;
      let reader = new FileReader();
      if (!file[i].type.match(pattern)) {
        alert('invalid format');
        return;
      }
      reader.onload = this._handleMultipleReaderLoaded.bind(this);
      reader.readAsDataURL(file[i]);

    }
  }
  _handleMultipleReaderLoaded(e) {
    let reader = e.target;
    this.multipleImageSrc.push(reader.result);
    console.log(this.multipleImageSrc)
  }
}
