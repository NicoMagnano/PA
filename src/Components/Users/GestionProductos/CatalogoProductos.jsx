// CatalogoProductos.jsx
import React, { useEffect, useState } from "react";
import ProductoService from "../../../Service/ProductoService.js";
import "./Estilos/EstiloCatalogo.css";

const CatalogoProductos = () => {
    const [productos, setProductos] = useState([]);

    // Obtener productos del backend al cargar el componente
    useEffect(() => {
        const fetchProductos = async () => {
            try {
                const response = await ProductoService.getProducts();
                setProductos(response.data); // Suponiendo que la API devuelve un array de productos en response.data
            } catch (error) {
                console.error("Error al obtener los productos:", error);
            }
        };
        fetchProductos();
    }, []);

    return (
        <div className="catalogo-container">
            <h2>Catálogo de Productos</h2>
            <div className="productos-grid">
                {productos.map((producto, index) => (
                    <div key={index} className="producto-card">
                        <img src={producto.imagen} alt={producto.nombre} className="producto-imagen" />
                        <h3>{producto.nombre}</h3>
                        <p>{producto.descripcion}</p>
                        <p><strong>Precio:</strong> ${producto.precio}</p>
                        <p><strong>SKU:</strong> {producto.sku}</p>
                        <p><strong>Stock:</strong> {producto.stock}</p>
                        <p><strong>Marca:</strong> {producto.marca}</p>
                        <p><strong>Categoría:</strong> {producto.categoria}</p>
                        <p><strong>Color:</strong> {producto.variante?.color}</p>
                        <p><strong>Talle:</strong> {producto.variante?.talle}</p>
                        <p><strong>Material:</strong> {producto.variante?.material}</p>
                        <p><strong>Estilo:</strong> {producto.variante?.estilo}</p>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default CatalogoProductos;
