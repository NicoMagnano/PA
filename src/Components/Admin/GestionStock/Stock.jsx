import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { API_URL } from '../../../Service/Constante';

const StockManagement = () => {
    const [products, setProducts] = useState([]);
    const [stockEntry, setStockEntry] = useState({ productId: '', quantity: '', reason: '' });

    useEffect(() => {
        axios.get(API_URL +'/productos').then(response => {
            setProducts(response.data);
        });
    }, []);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setStockEntry({
            ...stockEntry,
            [name]: value
        });
    };

    const handleStockSubmit = (e) => {
        e.preventDefault();
        // Validación para asegurarse de que la cantidad sea positiva
        const quantity = parseInt(stockEntry.quantity, 10);
        if (quantity <= 0) {
            alert('La cantidad debe ser un número positivo.');
            return; // Evita el envío del formulario
        }

        axios.post(API_URL +'/stock', stockEntry).then(() => {
            alert('Stock actualizado');
            setStockEntry({ productId: '', quantity: '', reason: '' });
        }).catch(error => {
            console.error('Error al actualizar el stock:', error);
            alert('Ocurrió un error al actualizar el stock. Intenta nuevamente.');
        });
    };

    return (
        <div>
            <form onSubmit={handleStockSubmit}>
                <select name="productId" value={stockEntry.productId} onChange={handleChange} required>
                    <option value="">Seleccionar Producto</option>
                    {products.map(product => (
                        <option key={product.id} value={product.id}>{product.name}</option>
                    ))}
                </select>
                <input type="number" name="quantity" placeholder="Cantidad" value={stockEntry.quantity} onChange={handleChange} required />
                <input type="text" name="reason" placeholder="Razón" value={stockEntry.reason} onChange={handleChange} required />
                <button type="submit">Registrar Ajuste</button>
            </form>
            <h3>Alertas de Stock</h3>
            <ul>
                {products.filter(product => product.stock < product.threshold).map(product => (
                    <li key={product.id}>{product.name} - Stock bajo: {product.stock}</li>
                ))}
            </ul>
        </div>
    );
};

export default StockManagement;
