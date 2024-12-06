// Formulario.jsx
import React, { useState } from "react";
import ProductoService from "../../../Service/ProductoService.js";
import "./Estilos/Formulario.css"

const FormularioProducto = ()  => {
    const [productData, setProductData] = useState({
        nombre: "",
        descripcion: "",
        precio: "",
        sku: "",
        stock: "",
        umbralStockBajo: "",
        imagen: "",
        categoria: "",
        marca: "",
        variante: {
            color: "",
            talle: "",
            material: "",
            estilo: "",
        },
    });

    const [categorias, setCategorias] = useState(["Remera", "Pantalon", "Camisa"]);
    const [marcas, setMarcas] = useState(["Nike", "Adidas"]);
    const [colores, setColores] = useState(["Rojo", "Negro"]);
    const [talles, setTalles] = useState(["M", "L", "XL", "XXL"]);
    const [materiales, setMateriales] = useState(["Poliéster", "Algodón"]);
    const [estilos, setEstilos] = useState(["Casual", "Deportivo"]);

    const [nuevoValor, setNuevoValor] = useState("");
    const [campoAAgregar, setCampoAAgregar] = useState(""); // Para saber qué campo se está agregando

    const handleInputChange = (e) => {
        const { name, value, type, files } = e.target;

        if (type === "file" && files[0]) {
            const reader = new FileReader();
            reader.onload = (event) => {
                setProductData((prevData) => ({
                    ...prevData,
                    imagen: event.target.result,
                }));
            };
            reader.readAsDataURL(files[0]);
        } else if (name.startsWith("variante.")) {
            const varianteKey = name.split(".")[1];
            setProductData((prevData) => ({
                ...prevData,
                variante: { ...prevData.variante, [varianteKey]: value },
            }));
        } else {
            setProductData((prevData) => ({
                ...prevData,
                [name]: value,
            }));
        }
    };

    const handleAgregarNuevoValor = (campo) => {
        if (nuevoValor.trim() === "") return; // Evitar agregar valores vacíos

        switch (campo) {
            case "categoria":
                setCategorias((prev) => [...prev, nuevoValor]);
                break;
            case "marca":
                setMarcas((prev) => [...prev, nuevoValor]);
                break;
            case "color":
                setColores((prev) => [...prev, nuevoValor]);
                break;
            case "talle":
                setTalles((prev) => [...prev, nuevoValor]);
                break;
            case "material":
                setMateriales((prev) => [...prev, nuevoValor]);
                break;
            case "estilo":
                setEstilos((prev) => [...prev, nuevoValor]);
                break;
            default:
                break;
        }
        setNuevoValor(""); // Reiniciar el campo de texto
        setCampoAAgregar(""); // Reiniciar el campo agregado
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (parseFloat(productData.precio) <= 0) {
            alert("El precio debe ser mayor a 0.");
            return;
        }
        if (parseInt(productData.umbralStockBajo) < 0) {
            alert("El umbral de stock bajo debe ser 0 o mayor.");
            return;
        }
    
        try {
            // Llama al servicio para crear el producto
            await ProductoService.getProducts(productData);
            alert("Producto cargado exitosamente.");
            // Reiniciar el estado del formulario
            setProductData({
                nombre: "",
                descripcion: "",
                precio: "",
                sku: "",
                stock: "",
                umbralStockBajo: "",
                imagen: "",
                categoria: "",
                marca: "",
                variante: {
                    color: "",
                    talle: "",
                    material: "",
                    estilo: "",
                },
            });
        } catch (error) {
            alert("Hubo un error al cargar el producto. Inténtalo de nuevo.");
        }
    };

    return (
        <div style={{ display: "flex", gap: "20px" }}>
            <form onSubmit={handleSubmit}>
                
                <label>Nombre: <input type="text" name="nombre" value={productData.nombre} onChange={handleInputChange} required /></label><br />
                <label>Descripción: <textarea name="descripcion" value={productData.descripcion} onChange={handleInputChange} required /></label><br />
                <label>Precio: <input type="number" name="precio" value={productData.precio} onChange={handleInputChange} min="0.01" step="0.01" required /></label><br />
                <label>SKU: <input type="text" name="sku" value={productData.sku} onChange={handleInputChange} required /></label><br />
                <label>Stock: <input type="number" name="stock" value={productData.stock} onChange={handleInputChange} min="0" required /></label><br />
                <label>Umbral de Stock Bajo: <input type="number" name="umbralStockBajo" value={productData.umbralStockBajo} onChange={handleInputChange} min="0" required /></label><br />
                <label>Imagen: <input type="file" name="imagen" onChange={handleInputChange} accept="image/*" /></label><br />
                <label>Categoría:
                    <select name="categoria" value={productData.categoria} onChange={handleInputChange} required>
                        <option value="">Seleccione...</option>
                        {categorias.map((categoria, index) => (
                            <option key={index} value={categoria}>{categoria}</option>
                        ))}
                    </select>
                    <button type="button" onClick={() => { setCampoAAgregar("categoria"); }}>Agregar</button>
                </label><br />

                <label>Marca:
                    <select name="marca" value={productData.marca} onChange={handleInputChange} required>
                        <option value="">Seleccione...</option>
                        {marcas.map((marca, index) => (
                            <option key={index} value={marca}>{marca}</option>
                        ))}
                    </select>
                    <button type="button" onClick={() => { setCampoAAgregar("marca"); }}>Agregar</button>
                </label><br />

                <h3>Variantes</h3>
                <label>Color:
                    <select name="variante.color" value={productData.variante.color} onChange={handleInputChange} required>
                        <option value="">Seleccione...</option>
                        {colores.map((color, index) => (
                            <option key={index} value={color}>{color}</option>
                        ))}
                    </select>
                    <button type="button" onClick={() => { setCampoAAgregar("color"); }}>Agregar</button>
                </label><br />

                <label>Talle:
                    <select name="variante.talle" value={productData.variante.talle} onChange={handleInputChange} required>
                        <option value="">Seleccione...</option>
                        {talles.map((talle, index) => (
                            <option key={index} value={talle}>{talle}</option>
                        ))}
                    </select>
                    <button type="button" onClick={() => { setCampoAAgregar("talle"); }}>Agregar</button>
                </label><br />

                <label>Material:
                    <select name="variante.material" value={productData.variante.material} onChange={handleInputChange} required>
                        <option value="">Seleccione...</option>
                        {materiales.map((material, index) => (
                            <option key={index} value={material}>{material}</option>
                        ))}
                    </select>
                    <button type="button" onClick={() => { setCampoAAgregar("material"); }}>Agregar</button>
                </label><br />

                <label>Estilo:
                    <select name="variante.estilo" value={productData.variante.estilo} onChange={handleInputChange} required>
                        <option value="">Seleccione...</option>
                        {estilos.map((estilo, index) => (
                            <option key={index} value={estilo}>{estilo}</option>
                        ))}
                    </select>
                    <button type="button" onClick={() => { setCampoAAgregar("estilo"); }}>Agregar</button>
                </label><br />

                {campoAAgregar && (
                    <div>
                        <input
                            type="text"
                            value={nuevoValor}
                            onChange={(e) => setNuevoValor(e.target.value)}
                            placeholder={`Agregar nuevo ${campoAAgregar}`}
                        />
                        <button type="button" onClick={() => handleAgregarNuevoValor(campoAAgregar)}>Confirmar</button>
                        <button type="button" onClick={() => setCampoAAgregar("")}>Cancelar</button>
                    </div>
                )}

                <button type="submit">Cargar Producto</button>
            </form>

            <div style={{ border: "1px solid #ccc", padding: "10px", width: "250px", background: "#f9f9f9" }}>
                <h2>Vista Previa</h2>
                <p><strong>Nombre:</strong> {productData.nombre}</p>
                <p><strong>Descripción:</strong> {productData.descripcion}</p>
                <p><strong>Precio:</strong> {productData.precio}</p>
                <p><strong>SKU:</strong> {productData.sku}</p>
                <p><strong>Stock:</strong> {productData.stock}</p>
                <p><strong>Umbral de Stock Bajo:</strong> {productData.umbralStockBajo}</p>
                <p><strong>Categoria:</strong> {productData.categoria}</p>
                <p><strong>Marca:</strong> {productData.marca}</p>
                <p><strong>Color:</strong> {productData.color}</p>
                <p><strong>Talle:</strong> {productData.talle}</p>
                <p><strong>Material:</strong> {productData.material}</p>
                <p><strong>Estilo:</strong> {productData.estilo}</p>
                <p><strong>Imagen:</strong></p>
                {productData.imagen && <img src={productData.imagen} alt="Vista previa" style={{ maxWidth: "100%" }} />}
            </div>
        </div>
    );
};

export default FormularioProducto;
