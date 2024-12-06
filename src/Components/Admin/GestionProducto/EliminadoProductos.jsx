import React from 'react';

const Eliminacion = ({ product, onConfirm, onCancel }) => {
    return (
        <div className="modal">
            <h3>¿Estás seguro de que quieres eliminar el producto "{product.nombre}"?</h3>
            <button onClick={() => onConfirm(product.id)}>Confirmar</button>
            <button onClick={onCancel}>Cancelar</button>
        </div>
    );
};

export default Eliminacion;