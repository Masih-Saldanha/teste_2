package segundoteste;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

class SegundoTest {

  private Segundo sistemaRH;

  @BeforeEach

  public void setup() {

    sistemaRH = new Segundo();

  }

  @Test

  public void testInteractions() throws Exception {

    // Test iniciarProcesso e verificarStatusCandidato

    int id1 = sistemaRH.iniciarProcesso("João");

    assertEquals("Recebido", sistemaRH.verificarStatusCandidato(id1));

    // Test iniciarProcesso com nome inválido

    assertThrows(Exception.class, () -> sistemaRH.iniciarProcesso(null));

    assertThrows(Exception.class, () -> sistemaRH.iniciarProcesso(""));

    // Test marcarEntrevista

    sistemaRH.marcarEntrevista(id1);

    assertEquals("Qualificado", sistemaRH.verificarStatusCandidato(id1));

    // Test desqualificarCandidato

    sistemaRH.desqualificarCandidato(id1);

    assertThrows(Exception.class, () -> sistemaRH.verificarStatusCandidato(id1));

    // Test desqualificarCandidato com ID inválido

    assertThrows(Exception.class, () -> sistemaRH.desqualificarCandidato(100));

    // Test aprovarCandidato

    int id2 = sistemaRH.iniciarProcesso("Maria");

    sistemaRH.marcarEntrevista(id2);

    sistemaRH.aprovarCandidato(id2);

    assertEquals("Aprovado", sistemaRH.verificarStatusCandidato(id2));

    // Test iniciarProcesso para candidato existente

    assertThrows(Exception.class, () -> sistemaRH.iniciarProcesso("Maria"));

    // Test desqualificarCandidato após aprovação

    sistemaRH.desqualificarCandidato(id2);

    assertThrows(Exception.class, () -> sistemaRH.verificarStatusCandidato(id2));

    // Test verificarStatusCandidato para candidato desqualificado

    assertThrows(Exception.class, () -> sistemaRH.verificarStatusCandidato(id2));

    // Test iniciarProcesso com nome vazio

    assertThrows(Exception.class, () -> sistemaRH.iniciarProcesso(" "));

    // Test marcarEntrevista para candidato inexistente

    assertThrows(Exception.class, () -> sistemaRH.marcarEntrevista(100));

    // Test aprovarCandidato para candidato inexistente

    assertThrows(Exception.class, () -> sistemaRH.aprovarCandidato(100));

  }

  @Test

  public void testCenarioMaior() throws Exception {

    List<Integer> ids = new ArrayList<>();

    for (int i = 1; i <= 11; i++) {

      int id = sistemaRH.iniciarProcesso("Candidato " + i);

      ids.add(id);

    }

    for (int id : ids) {

      assertEquals("Recebido", sistemaRH.verificarStatusCandidato(id));

    }

    // Marca entrevista nos candidatos de indice par

    for (int id : ids) {

      if (id % 2 == 0) {

        sistemaRH.marcarEntrevista(id);

      }

    }

    // Verifica status dos candidatos

    for (int id : ids) {

      if (id % 2 == 0) {

        assertEquals("Qualificado", sistemaRH.verificarStatusCandidato(id));

      } else {

        assertEquals("Recebido", sistemaRH.verificarStatusCandidato(id));

      }

    }

    // Aprova candidatos em indice múltiplo de 6

    for (int id : ids) {

      if (id % 6 == 0) {

        sistemaRH.aprovarCandidato(id);

      }

    }

    // Verificar status dos candidatos aprovados, qualificados e recebidos usando os
    // IDs armazenados

    for (int id : ids) {

      if (id % 6 == 0) {

        assertEquals("Aprovado", sistemaRH.verificarStatusCandidato(id));

      } else if (id % 2 == 0) {

        assertEquals("Qualificado", sistemaRH.verificarStatusCandidato(id));

      } else {

        assertEquals("Recebido", sistemaRH.verificarStatusCandidato(id));

      }

    }

    // Desqualificar candidatos múltiplos de 4 usando os IDs armazenados

    for (int id : ids) {

      if (id % 4 == 0) {

        sistemaRH.desqualificarCandidato(id);

      }

    }

    // Verificar status dos candidatos desqualificados, aprovados, qualificados e
    // recebidos usando os IDs armazenados

    for (int id : ids) {

      if (id % 4 == 0) {

        assertThrows(Exception.class, () -> sistemaRH.verificarStatusCandidato(id));

      } else if (id % 6 == 0) {

        assertEquals("Aprovado", sistemaRH.verificarStatusCandidato(id));

      } else if (id % 2 == 0) {

        assertEquals("Qualificado", sistemaRH.verificarStatusCandidato(id));

      } else {

        assertEquals("Recebido", sistemaRH.verificarStatusCandidato(id));

      }

    }

  }

}