import {Product} from "./product";

export class Productresponse {

    public products: Product[];
    public amount: number;
    public weight: number;

    constructor(products: Product[], amount: number, weight: number) {
        this.products = products;
        this.amount = amount;
        this.weight = weight;
    }
}
