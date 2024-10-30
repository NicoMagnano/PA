import React from 'react';
import useApiService from '../hooks/useApiService';

const ApiService = () => {
    const { data, error, loading } = useApiService();

    if (loading) return <p>Cargando...</p>;
    if (error) return <p>Error: {error.message}</p>;

    return (
        <div>
            <h2>Datos del Servicio API:</h2>
            <ul>
                {data.map((item, index) => (
                    <li key={index}>{JSON.stringify(item)}</li>
                ))}
            </ul>
        </div>
    );
};

export default ApiService;
