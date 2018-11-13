import { Component, OnInit ,ChangeDetectorRef} from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Seller } from '../models/seller.model';
import { SellerService } from '../seller.service';
import { first } from 'rxjs/operators';
import { error } from 'util';

@Component({
  selector: 'app-seller-login',
  templateUrl: './seller-login.component.html',
  styleUrls: ['./seller-login.component.css']
})
export class SellerLoginComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  seller: Seller =new Seller;
  retrievedSeller :Seller ; 
  public readonly siteKey = '6LcvoUgUAAAAAJJbhcXvLn3KgG-pyULLusaU4mL1';
  public theme: 'light' | 'dark' = 'light';
  public size: 'compact' | 'normal' = 'normal';
  public lang = 'en';
  public type: 'image' | 'audio';
  public captchaIsLoaded = false;
  public captchaSuccess = false;
  public captchaIsExpired = false;
  public captchaResponse?: string;
  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private cdr: ChangeDetectorRef,
    private sellerService: SellerService
  ) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      recaptcha: ['', Validators.required]
  });

  }
  get f() { return this.loginForm.controls; }

  handleSuccess(captchaResponse: string): void {
    this.captchaSuccess = true;
    this.captchaResponse = captchaResponse;
    this.captchaIsExpired = false;
    this.cdr.detectChanges();
  }

  handleLoad(): void {
    this.captchaIsLoaded = true;
    this.captchaIsExpired = false;
    this.cdr.detectChanges();
  }

  handleExpire(): void {
    this.captchaSuccess = false;
    this.captchaIsExpired = true;
    this.cdr.detectChanges();
  }

  onSubmit() {
    this.submitted = true;
    if (this.loginForm.invalid) {
      return ;
    }
    console.log(this.f.username.value);
    this.seller.email = this.f.username.value;
    this.seller.password = this.f.password.value ; 
    console.log(this.seller);
    this.sellerService.getSeller(this.seller)
    .pipe(first())
      .subscribe(
        data => { 
          console.log(data);
          if(data.status === "NEED_APPROVAL"){
            window.alert('Please wait for your approval');
            this.loading = false;
          }
          else if(data.status === "REJECTED"){
            window.alert('You have been rejected');
            this.loading = false;
          }
          else{
          window.localStorage.setItem("userToken",data.id)
          this.router.navigateByUrl('/products/'+localStorage.getItem("userToken"));
          }
        },
        error =>{
          window.alert('check  your credentials')
          this.loading = false;
        }
      );

   
    


    this.loading = true;
}

}
