import React, { useState, useEffect } from 'react';
import { getCategorias } from './services/apiService';

const CategoriaComponent = () => {
    const [categorias, setCategorias] = useState([]);

    useEffect(() => {
        const fetchCategorias = async () => {
            const data = await getCategorias();
            setCategorias(data);
        };
        fetchCategorias();
    }, []);

    return (
        <div>
            <h2>Categorías</h2>
            <ul>
                {categorias.map(categoria => (
                    <li key={categoria.id}>{categoria.nombre}</li>
                ))}
            </ul>
        </div>
    );
};

export default CategoriaComponent;
