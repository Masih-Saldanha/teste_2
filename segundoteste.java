import java.util.ArrayList;
import java.util.List;

// TODO:
// - ERROS TRATADOS COM EXCEPETIONS
// - aprovarCandidato -> apenas para qualificados - EXCEPTION "Candidato não encontrado"
// - desqualificarCandidato -> qualquer um
// - ENVIAR COMO ZIP OU COMO ARQUIVO DO GITHUB

// - * marcarEntrevista -> apenas para recebidos - EXCEPTION "Candidato não encontrado"

public class segundoteste {
  public static void main(String[] args) {
    // TESTES:
    
    Segundo segundo = new Segundo();
    System.out.println(segundo.iniciarProcesso("Pessoa 1"));
    // System.out.println(segundo.iniciarProcesso("Pessoa 1"));
    System.out.println(segundo.iniciarProcesso("Pessoa 2"));
    System.out.println(segundo.iniciarProcesso("Pessoa 3"));
    System.out.println(segundo.iniciarProcesso("Pessoa 4"));
    System.out.println(segundo.iniciarProcesso("Pessoa 5"));
    // segundo.desqualificarCandidato(0);
    segundo.desqualificarCandidato(1);
    // segundo.marcarEntrevista(0);
    segundo.marcarEntrevista(2);
    segundo.marcarEntrevista(3);
    // segundo.aprovarCandidato(0);
    // segundo.aprovarCandidato(3); //ERRO PQ É RECEBIDO E NÃO QUALIFICADO
    segundo.aprovarCandidato(3);
    // System.out.println(segundo.verificarStatusCandidato(0));
    // System.out.println(segundo.verificarStatusCandidato(1));
    System.out.println(segundo.verificarStatusCandidato(2));
    System.out.println(segundo.verificarStatusCandidato(3));
    System.out.println(segundo.verificarStatusCandidato(4));
    System.out.println(segundo.obterAprovados());
    System.out.println("Candidatos Size: " + segundo.candidatos.size());
    System.out.println(segundo.candidatos.get(0).codCandidato);
    System.out.println(segundo.candidatos.get(0).nome);
    System.out.println(segundo.candidatos.get(0).status);
    System.out.println(segundo.candidatos.get(1).codCandidato);
    System.out.println(segundo.candidatos.get(1).nome);
    System.out.println(segundo.candidatos.get(1).status);
    System.out.println(segundo.candidatos.get(2).codCandidato);
    System.out.println(segundo.candidatos.get(2).nome);
    System.out.println(segundo.candidatos.get(2).status);
    System.out.println(segundo.candidatos.get(3).codCandidato);
    System.out.println(segundo.candidatos.get(3).nome);
    System.out.println(segundo.candidatos.get(3).status);
  }

  public enum StatusCandidato {
    Recebido,
    Qualificado,
    Aprovado,
  }

  public static class Candidato {
    public String nome;
    public int codCandidato;
    public StatusCandidato status;

    Candidato(String nome, int codCandidato, StatusCandidato status) {
      this.nome = nome;
      this.codCandidato = codCandidato;
      this.status = status;
    }
  }

  public static class Segundo {
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
      if (jaParticipa != null) {
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
}
