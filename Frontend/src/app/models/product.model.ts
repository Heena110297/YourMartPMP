import { Seller } from "./seller.model";
import { Category } from "./category.model";

export class Product{
    id: number;
    seller: Seller;
    name: string;
    primaryImg: string;
    galleryImages: Array<string>;
    status: string;
    category: Category;
    mrp: number;
    ssp: number;
    ymp: number;
    shortDescription: string;
    longDescription: string;
    created: Date;
    comment: string;
}