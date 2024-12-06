import React from "react";
import { Link } from 'react-router-dom'; // Importar Link
import "./Home.css";

function Home() {
  return (
    <div className="home-container">
      {/* Navbar */}
      <nav className="navbar">
        <div className="logo"><h1>Geraldine</h1></div>       
        <ul className="nav-links">
          <li><Link to="/">Inicio</Link></li>
          <li><Link to="/productos">Productos</Link></li>
          <li><Link to="/ofertas">Ofertas</Link></li>
          <li><Link to="/contacto">Contacto</Link></li>
        </ul>

        <div className="search-container">
          <input
            type="text"
            placeholder="Buscar productos..."
            className="search-input"
          />
          <button className="search-button">🔍</button>
        </div>

        <div className="cart-icon">🛒</div>
      </nav>

      {/* Banner */}
      <div className="banner">
        <div className="banner-content">
          <h1>Renueva tu estilo</h1>
          <p>Encuentra las últimas tendencias en moda femenina</p>
          <button className="shop-now-btn"><Link to="/productos">Compra ahora</Link></button>
        </div>
      </div>

      {/* Categories Section */}
      <div className="categories">
        <h2>Explora por categoría</h2>
        <div className="category-list">
          <div className="category-item">
            <img src="/images/dresses.jpg" alt="Vestidos" />
            <p>Vestidos</p>
          </div>
          <div className="category-item">
            <img src="/images/tops.jpg" alt="Tops" />
            <p>Tops</p>
          </div>
          <div className="category-item">
            <img src="/images/pants.jpg" alt="Pantalones" />
            <p>Pantalones</p>
          </div>
          <div className="category-item">
            <img src="/images/shoes.jpg" alt="Zapatos" />
            <p>Zapatos</p>
          </div>
        </div>
      </div>

      {/* Featured Products */}
      <div className="featured-products">
        <h2>Productos populares</h2>
        <div className="product-list">
          <div className="product-item">
            <img src="/images/product1.jpg" alt="Vestido de verano" />
            <p className="product-name">Vestido de verano</p>
            <p className="product-price">$59.99</p>
          </div>
          <div className="product-item">
            <img src="/images/product2.jpg" alt="Blusa estampada" />
            <p className="product-name">Blusa estampada</p>
            <p className="product-price">$29.99</p>
          </div>
          <div className="product-item">
            <img src="/images/product3.jpg" alt="Jeans ajustados" />
            <p className="product-name">Jeans ajustados</p>
            <p className="product-price">$49.99</p>
          </div>
          <div className="product-item">
            <img src="/images/product4.jpg" alt="Sandalias" />
            <p className="product-name">Sandalias</p>
            <p className="product-price">$39.99</p>
          </div>
        </div>
      </div>

      {/* Footer */}
      <footer className="footer">
        <div className="footer-content">
          <p>&copy; 2024 Fashionista. Todos los derechos reservados.</p>
          <ul className="social-links">
            <li><a href="https://www.instagram.com/hdo.geraldine?igsh=ZHFucGZvazh4ZzE2">Instagram</a></li>           
          </ul>
        </div>
      </footer>
    </div>
  );
}

export default Home;
