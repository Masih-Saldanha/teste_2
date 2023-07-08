package segundoteste;

import java.util.ArrayList;
import java.util.List;

public class Segundo {
  public List<Candidato> candidatos;
  public int codCandidatoNovo = 1;

  Segundo() {
    this.candidatos = new ArrayList<>();
  }

  public Candidato buscarCandidatoPorNome(String nome) {
    for (Candidato candidato : this.candidatos) {
      if (candidato.nome.equals(nome)) {
        return candidato;
      }
    }
    return null;
  }

  public Candidato buscarCandidatoPorCodigo(int codCandidato) {
    for (Candidato candidato : this.candidatos) {
      if (candidato.codCandidato == codCandidato) {
        return candidato;
      }
    }
    return null;
  }

  public int iniciarProcesso(String nome) {
    Candidato jaParticipa = buscarCandidatoPorNome(nome);
    if (jaParticipa != null || nome.isBlank()) {
      throw new RuntimeException("Candidato já participa do processo.");
    } else {
      Candidato novoCandidato = new Candidato(nome, this.codCandidatoNovo, StatusCandidato.Recebido);
      this.candidatos.add(novoCandidato);
      this.codCandidatoNovo++;
      return novoCandidato.codCandidato;
    }
  }

  public void marcarEntrevista(int codCandidato) {
    Candidato candidato = buscarCandidatoPorCodigo(codCandidato);
    if (candidato != null && candidato.status == StatusCandidato.Recebido) {
      candidato.status = StatusCandidato.Qualificado;
    } else {
      throw new RuntimeException("Candidato não encontrado");
    }
  }

  public void desqualificarCandidato(int codCandidato) {
    Candidato candidato = buscarCandidatoPorCodigo(codCandidato);
    if (candidato != null) {
      this.candidatos.remove(candidato);
    } else {
      throw new RuntimeException("Candidato não encontrado");
    }
  }

  public String verificarStatusCandidato(int codCandidato) {
    Candidato candidato = buscarCandidatoPorCodigo(codCandidato);
    if (candidato != null) {
      return candidato.status.toString();
    } else {
      throw new RuntimeException("Candidato não encontrado");
    }
  }

  public void aprovarCandidato(int codCandidato) {
    Candidato candidato = buscarCandidatoPorCodigo(codCandidato);
    if (candidato != null && candidato.status == StatusCandidato.Qualificado) {
      candidato.status = StatusCandidato.Aprovado;
    } else {
      throw new RuntimeException("Candidato não encontrado");
    }
  }

  public List<String> obterAprovados() {
    List<String> aprovados = new ArrayList<>();
    this.candidatos.forEach(candidato -> {
      if (candidato.status == StatusCandidato.Aprovado) {
        aprovados.add(candidato.nome);
      }
    });
    return aprovados;
  }
}
