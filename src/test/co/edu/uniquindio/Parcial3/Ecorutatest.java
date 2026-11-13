package co.edu.uniquindio.Parcial3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class EcoRutaTest {

    private static final Logger LOG = Logger.getLogger(EcoRutaTest.class.getName());

    private EcoRuta ecoRuta;
    private LocalDate fecha;

    @BeforeEach
    void preparar() {
        ecoRuta = new EcoRuta("EcoRuta");
        ecoRuta.registrarAportante(new Aportante("1094", "Laura Gomez", "laura@correo.com"));
        fecha = LocalDate.of(2026, 11, 10);
    }

    //Entrega con los tres tipos de lote que se usa en las pruebas 4 y 5
    private void crearEntregaTresLotes(String codigoEntrega) {
        ecoRuta.registrarEntrega(codigoEntrega, fecha, "1094");
        ecoRuta.agregarLoteToEntrega(codigoEntrega, new PapelCarton("L01", "Cajas de carton", 10, true));
        ecoRuta.agregarLoteToEntrega(codigoEntrega, new Metal("L02", "Latas", 5, TipoMetal.ALUMINIO));
        ecoRuta.agregarLoteToEntrega(codigoEntrega, new ResiduoElectronico("L03", "Computadores", 4, 2));
    }

    @DisplayName("Prueba 1: Registro de una entrega")
    @Test
    void testRegistroEntrega() {
        LOG.info("Entrada: entrega E01 con aportante 1094, entrega E01 repetida, entrega E02 con aportante 9999");
        LOG.info("Esperado: true, false, false - E01 ABIERTA, 0 lotes, sin comprobante");

        boolean primerRegistro = ecoRuta.registrarEntrega("E01", fecha, "1094");
        boolean registroRepetido = ecoRuta.registrarEntrega("E01", fecha, "1094");
        boolean registroDesconocido = ecoRuta.registrarEntrega("E02", fecha, "9999");
        Entrega entrega = ecoRuta.buscarEntrega("E01");

        assertTrue(primerRegistro);
        assertFalse(registroRepetido);
        assertFalse(registroDesconocido);
        assertNotNull(entrega);
        assertEquals(EstadoEntrega.ABIERTA, entrega.getEstado());
        assertEquals(0, entrega.getListLotes().size());
        assertNull(entrega.getTheComprobante());
        assertNull(ecoRuta.buscarEntrega("E02"));
        assertEquals(1, ecoRuta.getListEntregas().size());

        LOG.info("Obtenido: " + primerRegistro + ", " + registroRepetido + ", " + registroDesconocido
                + " - E01 " + entrega.getEstado() + ", " + entrega.getListLotes().size()
                + " lotes, comprobante: " + entrega.getTheComprobante());
    }

    @DisplayName("Prueba 2: Validacion de lotes")
    @Test
    void testValidacionLotes() {
        LOG.info("Entrada: lote L01 de 5 kg, otro lote L01 de 3 kg, lote L02 de 0 kg");
        LOG.info("Esperado: true, false, false - la entrega conserva solo L01");

        ecoRuta.registrarEntrega("E01", fecha, "1094");

        boolean primerLote = ecoRuta.agregarLoteToEntrega("E01", new PapelCarton("L01", "Periodico", 5, false));
        boolean loteRepetido = ecoRuta.agregarLoteToEntrega("E01", new Metal("L01", "Varillas", 3, TipoMetal.ACERO));
        boolean loteSinPeso = ecoRuta.agregarLoteToEntrega("E01", new PapelCarton("L02", "Revistas", 0, true));
        Entrega entrega = ecoRuta.buscarEntrega("E01");

        assertTrue(primerLote);
        assertFalse(loteRepetido);
        assertFalse(loteSinPeso);
        assertEquals(1, entrega.getListLotes().size());
        assertEquals("L01", entrega.getListLotes().get(0).getCodigo());
        assertEquals(5, entrega.getListLotes().get(0).getPesoKg());

        LOG.info("Obtenido: " + primerLote + ", " + loteRepetido + ", " + loteSinPeso
                + " - lotes en la entrega: " + entrega.getListLotes());
    }

    @DisplayName("Prueba 3: Rechazo de una entrega vacia")
    @Test
    void testRechazoEntregaVacia() {
        LOG.info("Entrada: aceptar la entrega E01 sin lotes");
        LOG.info("Esperado: false - E01 ABIERTA y sin comprobante");

        ecoRuta.registrarEntrega("E01", fecha, "1094");

        boolean aceptada = ecoRuta.aceptarEntrega("E01");
        Entrega entrega = ecoRuta.buscarEntrega("E01");

        assertFalse(aceptada);
        assertEquals(EstadoEntrega.ABIERTA, entrega.getEstado());
        assertNull(entrega.getTheComprobante());
        assertNull(ecoRuta.consultarComprobante("E01"));

        LOG.info("Obtenido: " + aceptada + " - E01 " + entrega.getEstado()
                + ", comprobante: " + ecoRuta.consultarComprobante("E01"));
    }

    @DisplayName("Prueba 4: Aceptacion y calculo con los tres tipos de lote")
    @Test
    void testAceptacionTresTiposLote() {
        LOG.info("Entrada: 10 kg papel y carton clasificado, 5 kg aluminio, 4 kg residuos electronicos con 2 equipos");
        LOG.info("Esperado: valores 6000, 11000 y 7200 - ACEPTADA - comprobante con 19 kg y 24200");

        crearEntregaTresLotes("E01");
        Entrega entrega = ecoRuta.buscarEntrega("E01");

        double valorPapel = entrega.buscarLote("L01").calcularValor();
        double valorMetal = entrega.buscarLote("L02").calcularValor();
        double valorElectronico = entrega.buscarLote("L03").calcularValor();

        boolean aceptada = ecoRuta.aceptarEntrega("E01");
        Comprobante comprobante = ecoRuta.consultarComprobante("E01");

        assertEquals(6000, valorPapel);
        assertEquals(11000, valorMetal);
        assertEquals(7200, valorElectronico);
        assertTrue(aceptada);
        assertEquals(EstadoEntrega.ACEPTADA, entrega.getEstado());
        assertNotNull(comprobante);
        assertEquals("E01", comprobante.codigoEntrega());
        assertEquals("1094", comprobante.identificacionAportante());
        assertEquals(19, comprobante.pesoTotal());
        assertEquals(24200, comprobante.valorTotal());

        //Una entrega aceptada no se acepta de nuevo ni recibe mas lotes
        assertFalse(ecoRuta.aceptarEntrega("E01"));
        assertFalse(ecoRuta.agregarLoteToEntrega("E01", new Metal("L04", "Tubos", 2, TipoMetal.ACERO)));
        assertEquals(24200, ecoRuta.calcularValorAcumuladoAportante("1094"));
        assertEquals(0, ecoRuta.calcularValorAcumuladoAportante("9999"));

        LOG.info("Obtenido: valores " + valorPapel + ", " + valorMetal + " y " + valorElectronico
                + " - " + entrega.getEstado() + " - " + comprobante);
    }

    @DisplayName("Prueba 5: Consulta de etiquetas")
    @Test
    void testConsultaEtiquetas() {
        LOG.info("Entrada: etiquetas de la entrega E01 aceptada con los tres tipos de lote");
        LOG.info("Esperado: 2 etiquetas - metal con L02 y ALUMINIO - electronico con L03 y 2 equipos");

        crearEntregaTresLotes("E01");
        ecoRuta.aceptarEntrega("E01");

        LinkedList<String> etiquetas = ecoRuta.consultarEtiquetas("E01");

        assertEquals(2, etiquetas.size());
        assertTrue(etiquetas.get(0).contains("L02"));
        assertTrue(etiquetas.get(0).contains("ALUMINIO"));
        assertTrue(etiquetas.get(1).contains("L03"));
        assertTrue(etiquetas.get(1).contains("Equipos completos: 2"));

        LOG.info("Obtenido: " + etiquetas.size() + " etiquetas - " + etiquetas);
    }
}
