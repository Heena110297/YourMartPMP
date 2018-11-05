import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {Routes, RouterModule} from '@angular/router'
import {FormsModule,ReactiveFormsModule} from '@angular/forms'
import { AppComponent } from './app.component';
import { ProductListComponent } from './product-list/product-list.component';
import { MatInputModule, MatTableModule, MatPaginatorModule, MatToolbarModule, MatSortModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; 
import { NgxCaptchaModule } from 'ngx-captcha';
import { HttpClientModule } from '@angular/common/http';
import { NavbarComponent } from './navbar/navbar.component';
import { ProductSearchComponent } from './product-search/product-search.component';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import { ProductAddComponent } from './product-add/product-add.component';
import { ProductEditComponent } from './product-edit/product-edit.component';
import { SellerLoginComponent } from './seller-login/seller-login.component';
import { SellerRegistrationComponent } from './seller-registration/seller-registration.component';
import { AuthGuard } from './auth.guard';
import { NavbarCommonComponent } from './navbar-common/navbar-common.component';

const appRoutes: Routes=[
{path:'products',component:ProductListComponent, canActivate: [AuthGuard]},
{path:'product/add',component:ProductAddComponent, canActivate: [AuthGuard]},
{path:'seller/add' ,component:SellerRegistrationComponent},
{ path: 'product/' , component:ProductDetailComponent, canActivate: [AuthGuard]},
{ path: 'product/:productId' , component:ProductDetailComponent,canActivate: [AuthGuard]},
{path: 'login', component:SellerLoginComponent},
{path: 'register', component:SellerRegistrationComponent},
{path: '**', redirectTo: 'login'}
];
@NgModule({
  declarations: [
    AppComponent,
    ProductListComponent,
 
    NavbarComponent,
    ProductSearchComponent,
    ProductDetailComponent,
    ProductAddComponent,
    ProductEditComponent,
    SellerLoginComponent,
    SellerRegistrationComponent,
    NavbarCommonComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgxCaptchaModule,
    RouterModule.forRoot(appRoutes),
    MatInputModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    BrowserAnimationsModule,
    MatToolbarModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
