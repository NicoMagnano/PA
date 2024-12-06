// ProductoService.js
import axios from 'axios';
import { API_URL } from './Constante';

const getProducts = async () => {
    const response = await axios.get(`${API_URL}/productos`);
    return response.data;
};

const getProductById = async (id) => {
    const response = await axios.get(`${API_URL}/productos/${id}`);
    return response.data;
};

const createProduct = async (productData) => {
    const response = await axios.post(`${API_URL}/productos`, productData, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    });
    return response.data;
};

const updateProduct = async (id, productData) => {
    const response = await axios.put(`${API_URL}/productos/${id}`, productData, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    });
    return response.data;
};

const deleteProduct = async (id) => {
    const response = await axios.delete(`${API_URL}/productos/${id}`);
    return response.data;
};

const registerStockAdjustment = async (stockData) => {
    const response = await axios.post(`${API_URL}/stock`, stockData);
    return response.data;
};

// Exporta las funciones como una exportación por defecto
// eslint-disable-next-line import/no-anonymous-default-export
export default {
    getProducts,
    getProductById,
    createProduct,
    updateProduct,
    deleteProduct,
    registerStockAdjustment
};
