import * as React from "react";
import * as ReactDOM from 'react-dom';
import {Product} from './model/product';

class Index extends React.Component<{}, { inputAmount: string, selectableProducts: Array<Product>, selectedProducts: Array<Product> }> {

    constructor(props: any) {
        super(props);
        this.state = {
            selectedProducts: [],
            selectableProducts: [],
            inputAmount: '0'
        }
    }

    private inputAmountChanged(value: string): void {
        this.setState({inputAmount: value, selectableProducts: [], selectedProducts: []}, () => {
            if (this.state.inputAmount !== "") {
                this.fetchProducts();
            }
        });
    }

    private selectProduct(product: Product): void {
        let currentSelectedProducts: Product[] = this.state.selectedProducts;
        currentSelectedProducts.push(product);
        this.setState({selectedProducts: currentSelectedProducts}, () => {
            this.fetchProducts();
        })
    }

    private fetchProducts() {
        fetch('http://localhost:8080/getProductsForAmount', {
            method: 'POST',
            headers: {'Content-type': 'application/json'},
            body: JSON.stringify({
                'amount': this.state.inputAmount,
                'selectedProducts': this.state.selectedProducts
            })
        }).then(r => r.json()).then(res => {
            if (res) {
                let products: Array<Product> = res as Product[];
                this.setState({selectableProducts: products});
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
                            <th/>
                            {
                                this.state.selectableProducts.map(product =>
                                    <tr>
                                        <td>{product.name}</td>
                                        <td>{product.quantity}</td>
                                        <td>{product.price}</td>
                                        <td>
                                            <button onClick={() => this.selectProduct(product)}>buy</button>
                                        </td>
                                    </tr>
                                )
                            }
                        </table>
                    </div>
                    <div style={{width: '40%', float: 'left', marginTop: '20px'}}>
                        Selected products
                        <ul>
                            {
                                this.state.selectedProducts.map(product =>
                                    <li>{product.name}</li>
                                )
                            }
                        </ul>
                        total: {
                        this.state.selectedProducts.reduce((sum: number, p: Product) => sum + p.price, 0)
                    }
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
