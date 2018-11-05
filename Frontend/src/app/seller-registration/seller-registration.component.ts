import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Seller } from '../models/seller.model';
import { SellerService } from '../seller.service';
import { Router } from '@angular/router';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-seller-registration',
  templateUrl: './seller-registration.component.html',
  styleUrls: ['./seller-registration.component.css']
})
export class SellerRegistrationComponent implements OnInit {
  sellerForm: FormGroup;
  loading = false;
  submitted = false;
  seller: Seller = new Seller;
  constructor(
    private formBuilder: FormBuilder,
    private sellerService: SellerService,
    private router: Router
  ) { }

  ngOnInit() {
    this.sellerForm = this.formBuilder.group({
      companyName: ['', Validators.required],
      ownerName: ['', Validators.required],
      address: ['', Validators.required],
      email: ['', Validators.compose([
        Validators.required,
        Validators.pattern('^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$')
      ])],
      pw: ['', Validators.compose([
        Validators.minLength(8),
        Validators.required,
        Validators.pattern('/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{6,100})/')])], 
 

      gst: ['', Validators.required],
      cpw: ['', Validators.required],
      telephone: ['', Validators.required]
    });
  }

  get f(){
    return this.sellerForm.controls;
  }

  onSubmit(){
    if (this.sellerForm.invalid){
      return;
    }
    this.submitted = true;
    this.seller.companyName = this.f.companyName.value ; 
    this.seller.owner = this.f.ownerName.value ; 
    this.seller.address = this.f.address.value;
    this.seller.email = this.f.email.value;
    this.seller.gstNumber = this.f.gst.value ; 
    this.seller.telephone = this.f.telephone.value ;
    this.seller.password = this.f.pw.value ; 
    this.seller.name = this.f.companyName.value ; 
    console.log(this.seller);
    this.sellerService.addSeller(this.seller)
      .pipe(first())
      .subscribe(
        data => {
          console.log('done');
          this.router.navigateByUrl('/login');
        },
      );

  }
}
