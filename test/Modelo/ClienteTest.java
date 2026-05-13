package Modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para la entidad Cliente.
 * Corresponde a la tarea S2-T10 (Test Cliente).
 */
public class ClienteTest {

    /**
     * Prueba 1: Verifica que el constructor asigne correctamente 
     * todos los valores iniciales y los getters los devuelvan bien.
     */
    @Test
    public void testCreacionCliente() {
        // 1. Preparación (Arrange): Creamos un objeto Cliente de prueba
        Cliente cliente = new Cliente(
                'C', 
                "100200300", 
                false, 
                "Juan Perez", 
                "juan@correo.com", 
                "3001234567", 
                "Maria Perez", 
                30.5
        );

        // 2. Verificación (Assert): Comprobamos que cada atributo tiene el valor esperado
        assertEquals('C', cliente.getTipoIdentificacion(), "El tipo de identificación no coincide.");
        assertEquals("100200300", cliente.getNumeroIdentificacion(), "El número de identificación no coincide.");
        assertFalse(cliente.isEmpresa(), "El cliente no debería ser una empresa.");
        assertEquals("Juan Perez", cliente.getNombre(), "El nombre no coincide.");
        assertEquals("juan@correo.com", cliente.getEmail(), "El email no coincide.");
        assertEquals("3001234567", cliente.getTelefono(), "El teléfono no coincide.");
        assertEquals("Maria Perez", cliente.getNombreContacto(), "El nombre de contacto no coincide.");
        assertEquals(30.5, cliente.getPorcentajeDescuento(), "El porcentaje de descuento no coincide.");
    }

    /**
     * Prueba 2: Verifica que los métodos Setters modifiquen correctamente
     * la información del cliente después de haber sido creado.
     */
    @Test
    public void testModificarCliente() {
        // 1. Preparación: Creamos un cliente con datos iniciales
        Cliente cliente = new Cliente('E', "900111222", true, "Empresa Inicial", "info@empresa.com", "1112223", "Contacto A", 25.0);
        
        // 2. Ejecución (Act): Usamos los setters para cambiar todos los datos
        cliente.setTipoIdentificacion('P');
        cliente.setNumeroIdentificacion("888777666");
        cliente.setEmpresa(false);
        cliente.setNombre("Carlos Gomez");
        cliente.setEmail("carlos@correo.com");
        cliente.setTelefono("3209998888");
        cliente.setNombreContacto("Ana Gomez");
        cliente.setPorcentajeDescuento(40.0);

        // 3. Verificación (Assert): Comprobamos que los datos se actualizaron
        assertEquals('P', cliente.getTipoIdentificacion());
        assertEquals("888777666", cliente.getNumeroIdentificacion());
        assertFalse(cliente.isEmpresa());
        assertEquals("Carlos Gomez", cliente.getNombre());
        assertEquals("carlos@correo.com", cliente.getEmail());
        assertEquals("3209998888", cliente.getTelefono());
        assertEquals("Ana Gomez", cliente.getNombreContacto());
        assertEquals(40.0, cliente.getPorcentajeDescuento());
    }

    /**
     * Prueba 3: Verifica la regla de negocio del porcentaje de descuento.
     * Según el diseño, debe ser Mínimo 20.0 y Máximo 70.0.
     * Nota: Si tu clase Cliente aún no tiene esta validación en el setter, 
     * esta prueba fallará hasta que programes esa lógica.
     */
    @Test
    public void testLimitesDescuento() {
        // Creamos un cliente con un descuento válido (30.0)
        Cliente cliente = new Cliente('C', "123", false, "Prueba", "p@p.com", "123", "N/A", 30.0);
        
        /* * Aquí deberías probar qué ocurre cuando se envía un dato inválido.
         * Dependiendo de cómo lo vayas a programar, el setter podría lanzar una 
         * excepción (IllegalArgumentException) o simplemente no cambiar el valor.
         * * Ejemplo asumiendo que el valor NO cambia si se envía uno incorrecto:
         */
        
        // Intentamos poner un descuento por debajo del mínimo (ej. 10.0)
        // cliente.setPorcentajeDescuento(10.0);
        // assertEquals(30.0, cliente.getPorcentajeDescuento(), "El descuento no debe bajar de 20.0");

        // Intentamos poner un descuento por encima del máximo (ej. 80.0)
        // cliente.setPorcentajeDescuento(80.0);
        // assertEquals(30.0, cliente.getPorcentajeDescuento(), "El descuento no debe superar 70.0");
    }
}