import * as React from "react";
import * as ReactDOM from 'react-dom';
import {Product} from './model/product';
import {Productresponse} from "./model/productresponse";

class Index extends React.Component<{}, { inputAmount: string, totalWeight: number, totalCost: number, suggestedProducts: Array<Product> }> {

    constructor(props: any) {
        super(props);
        this.state = {
            suggestedProducts: [],
            totalCost: 0,
            totalWeight: 0,
            inputAmount: '0'
        }
    }

    private inputAmountChanged(value: string): void {
        this.setState({inputAmount: value, suggestedProducts: [], totalCost: 0, totalWeight: 0}, () => {
            if (this.state.inputAmount !== "") {
                this.fetchProducts();
            }
        });
    }

    private fetchProducts() {
        fetch('http://localhost:8080/getProductsForAmount', {
            method: 'POST',
            headers: {'Content-type': 'application/json'},
            body: this.state.inputAmount
        }).then(r => r.json()).then(res => {
            if (res) {
                let productResponse: Productresponse = res as Productresponse;
                this.setState({
                    suggestedProducts: productResponse.products,
                    totalWeight: productResponse.weight,
                    totalCost: productResponse.amount
                });
            }
        });
    }

    render() {
        return (
            <div style={{border: '1px solid black', width: '40%', height: '500px', margin: '0 auto'}}>
                <div style={{margin: '10px'}}>
                    <div style={{width: '40%', float: 'left'}}>
                        <label>Amount:</label>
                        <input type="text" pattern="[0-9.]+" value={this.state.inputAmount} onChange={(
                            ev: React.ChangeEvent<HTMLInputElement>,
                        ): void => this.inputAmountChanged(ev.target.value)}/>
                    </div>
                    <div style={{width: '50%', float: 'right'}}>
                        <table style={{float: 'right'}}>
                            <th>Product</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            {
                                this.state.suggestedProducts.map(product =>
                                    <tr>
                                        <td>{product.name}</td>
                                        <td>{product.quantity}</td>
                                        <td>{product.price}</td>
                                    </tr>
                                )
                            }
                            <tr>
                                <td><b>Total</b></td>
                                <td><b>{this.state.totalWeight}</b></td>
                                <td><b>{this.state.totalCost}</b></td>
                            </tr>
                        </table>

                    </div>
                </div>
            </div>
        )
    }
}

const render = () => {
    ReactDOM.render(
        <Index/>,
        document.getElementById("root")
    );
};

export default render;
