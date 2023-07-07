import java.util.ArrayList;
import java.util.List;

// TODO:
// - ERROS TRATADOS COM EXCEPETIONS
// - ENVIAR COMO ZIP OU COMO ARQUIVO DO GITHUB

public class segundoteste {
  public static void main(String[] args) {
    Segundo segundo = new Segundo();
    System.out.println(segundo.iniciarProcesso("Masih"));
    System.out.println(segundo.iniciarProcesso("Masih"));
    System.out.println(segundo.iniciarProcesso("Kaká"));
    System.out.println(segundo.iniciarProcesso("Ronildo"));
    System.out.println(segundo.iniciarProcesso("Felipe"));
    System.out.println(segundo.iniciarProcesso("Goh"));
    segundo.desqualificarCandidato(0);
    segundo.desqualificarCandidato(1);
    segundo.marcarEntrevista(0);
    segundo.marcarEntrevista(2);
    segundo.aprovarCandidato(0);
    segundo.aprovarCandidato(3);
    System.out.println(segundo.verificarStatusCandidato(0));
    System.out.println(segundo.verificarStatusCandidato(1));
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
        System.out.println("Candidato já participa do processo.");
        return 0;
      } else {
        Candidato novoCandidato = new Candidato(nome, this.codCandidatoNovo, StatusCandidato.Recebido);
        this.candidatos.add(novoCandidato);
        this.codCandidatoNovo++;
        return novoCandidato.codCandidato;
      }
    }

    public void marcarEntrevista(int codCandidato) {
      Candidato candidato = buscarCandidatoPorCodigo(codCandidato);
      if (candidato != null) {
        candidato.status = StatusCandidato.Qualificado;
      } else {
        System.out.println("Candidato não encontrado");
      }
    }

    public void desqualificarCandidato(int codCandidato) {
      Candidato candidato = buscarCandidatoPorCodigo(codCandidato);
      if (candidato != null) {
        this.candidatos.remove(candidato);
      } else {
        System.out.println("Candidato não encontrado");
      }
    }

    public String verificarStatusCandidato(int codCandidato) {
      Candidato candidato = buscarCandidatoPorCodigo(codCandidato);
      if (candidato != null) {
        return candidato.status.toString();
      } else {
        return "Candidato não encontrado";
      }
    }

    public void aprovarCandidato(int codCandidato) {
      Candidato candidato = buscarCandidatoPorCodigo(codCandidato);
      if (candidato != null) {
        candidato.status = StatusCandidato.Aprovado;
      } else {
        System.out.println("Candidato não encontrado");
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
