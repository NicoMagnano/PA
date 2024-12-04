package com.example.pa.service;
import com.example.pa.model.Producto;
import com.example.pa.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @InjectMocks
    private ProductoService productoService; // Clase que estamos probando

    @Mock
    private ProductoRepository productoRepository; // Dependencia que estamos simulando

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    // Método para crear un producto para las pruebas
    private Producto crearProducto(String nombre, double precio, int stock) {
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setStock(stock);
        producto.setActivo(true); // Considerando que todos los productos son activos
        return producto;
    }

    // Caso de prueba: Registrar un producto con el precio mínimo permitido
    @Test
    public void testRegistrarProductoPrecioMinimo() {
        Producto producto = crearProducto("Producto Mínimo", 1.0, 10);

        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        Producto resultado = productoService.crearProducto(producto);

        assertNotNull(resultado);
        assertEquals("Producto Mínimo", resultado.getNombre());
        assertEquals(1.0, resultado.getPrecio());
        verify(productoRepository, times(1)).save(producto);
    }

    // Caso de prueba: Registrar un producto con el precio máximo permitido
    @Test
    public void testRegistrarProductoPrecioMaximo() {
        Producto producto = crearProducto("Producto Máximo", 9999.0, 10);

        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        Producto resultado = productoService.crearProducto(producto);

        assertNotNull(resultado);
        assertEquals("Producto Máximo", resultado.getNombre());
        assertEquals(9999.0, resultado.getPrecio());
        verify(productoRepository, times(1)).save(producto);
    }

    // Caso de prueba: Registrar un producto con precio fuera de los límites permitidos (bajo)
    @Test
    public void testRegistrarProductoPrecioNegativo() {
        Producto producto = crearProducto("Producto Negativo", -1.0, 10);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productoService.ValidarPrecio(producto.getPrecio());
        });

        String expectedMessage = "El precio no puede ser negativo";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Caso de prueba: Registrar un producto con cantidad mínima permitida en stock
    @Test
    public void testRegistrarProductoStockMinimo() {
        Producto producto = crearProducto("Producto Stock Mínimo", 10.0, 0);

        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        Producto resultado = productoService.crearProducto(producto);

        assertNotNull(resultado);
        assertEquals("Producto Stock Mínimo", resultado.getNombre());
        assertEquals(0, resultado.getStock());
        verify(productoRepository, times(1)).save(producto);
    }

    // Caso de prueba: Registrar un producto con cantidad máxima permitida en stock
    @Test
    public void testRegistrarProductoStockMaximo() {
        Producto producto = crearProducto("Producto Stock Máximo", 10.0, 10000);

        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        Producto resultado = productoService.crearProducto(producto);

        assertNotNull(resultado);
        assertEquals("Producto Stock Máximo", resultado.getNombre());
        assertEquals(10000, resultado.getStock());
        verify(productoRepository, times(1)).save(producto);
    }
}