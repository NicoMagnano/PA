import React, { useEffect, useState } from 'react';
import axios from 'axios';
import FormularioProductos from './FormularioProducto.jsx';
import EliminacionProductos from "./EliminadoProductos.jsx"
import { API_URL } from '../../../Service/Constante.js';

const ListadoProductos = () => {
    const [products, setProducts] = useState([]);
    const [selectedProduct, setSelectedProduct] = useState(null);
    const [showForm, setShowForm] = useState(false);
    const [showDeleteModal, setShowDeleteModal] = useState(false); // Estado para mostrar el modal de eliminación

    useEffect(() => {
        refreshProducts();
    }, []);

    const refreshProducts = () => {
        axios.get(API_URL +'/productos').then(response => {
            setProducts(response.data);
        });
    };

    const handleDelete = (productId) => {
        axios.delete(API_URL +`/productos/${productId}`).then(() => {
            refreshProducts();
            setShowDeleteModal(false); // Ocultar modal después de eliminar
        });
    };

    return (
        <div>
            <button onClick={() => { setSelectedProduct(null); setShowForm(true); }}>Agregar Producto</button>
            {showForm && <FormularioProductos productId={selectedProduct} onClose={() => setShowForm(false)} refreshProducts={refreshProducts} />}
            <table>
                <h2>Listado de Productos</h2>
                <tbody>
                    {products.map(product => (
                        <tr key={product.id}>
                            <td>{product.name}</td>
                            <td>{product.description}</td>
                            <td>{product.price}</td>
                            <td>
                                <button onClick={() => { setSelectedProduct(product.id); setShowForm(true); }}>Editar</button>
                                <button onClick={() => { setSelectedProduct(product); setShowDeleteModal(true); }}>Eliminar</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            {showDeleteModal && (
                <EliminacionProductos 
                    product={selectedProduct} 
                    onConfirm={handleDelete} 
                    onCancel={() => setShowDeleteModal(false)} 
                />
            )}
        </div>
    );
};

export default ListadoProductos;
