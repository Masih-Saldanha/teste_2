import java.util.ArrayList;
import java.util.List;

public class segundoteste_BACKUP {
  public static void main(String[] args) {
    // Candidato candidato = new Candidato("Candidato 1", 1);
    // System.out.println(candidato.nome);
    // System.out.println(candidato.codCandidato);

    Segundo segundo = new Segundo();
    System.out.println(segundo.iniciarProcesso("Masih"));
    System.out.println(segundo.iniciarProcesso("Masih"));
    System.out.println(segundo.iniciarProcesso("Kaká"));
    System.out.println(segundo.iniciarProcesso("Kaká"));
    // System.out.println(segundo.recebidos.get(0).nome);
    // System.out.println(segundo.recebidos.get(0).codCandidato);
    segundo.marcarEntrevista(2);
    // System.out.println(segundo.qualificados);
    // System.out.println(segundo.aprovados);
  }

  public static class Candidato {
    public String nome;
    public int codCandidato;

    Candidato(String nome, int codCandidato) {
      this.nome = nome;
      this.codCandidato = codCandidato;
    }
  }

  public static class Segundo {
    List<Candidato> recebidos;
    List<Candidato> qualificados;
    List<Candidato> aprovados;
    int codCandidatoNovo = 1;

    Segundo() {
      this.recebidos = new ArrayList<>();
      this.qualificados = new ArrayList<>();
      this.aprovados = new ArrayList<>();
    }

    public int iniciarProcesso(String nome) {
      Candidato novoCandidato = new Candidato(nome, this.codCandidatoNovo);
      this.recebidos.add(novoCandidato);
      this.codCandidatoNovo++;
      return novoCandidato.codCandidato;
    }

    public void marcarEntrevista(int codCandidato) {
      for (int i = 0; i < this.recebidos.size(); i++) {
        Candidato candidato = this.recebidos.get(i);
        if (candidato.codCandidato == codCandidato) {
          this.qualificados.add(candidato);
          this.recebidos.remove(candidato);
          break;
        }
      }
    }

    public void desqualificarCandidato(int codCandidato) {
      for (int i = 0; i < this.recebidos.size(); i++) {
        Candidato candidato = this.recebidos.get(i);
        if (candidato.codCandidato == codCandidato) {
          this.recebidos.remove(candidato);
          break;
        }
      }
      // FALTA VERIFICAR SE NECESSÁRIO CODIGO ABAIXO
      for (int i = 0; i < this.qualificados.size(); i++) {
        Candidato candidato = this.qualificados.get(i);
        if (candidato.codCandidato == codCandidato) {
          this.qualificados.remove(candidato);
          break;
        }
      }
    }

    public String verificarStatusCandidato(int codCandidato) {
      return "Nome do Candidato";
    }

    public void aprovarCandidato(int codCandidato) {
      for (int i = 0; i < this.qualificados.size(); i++) {
        Candidato candidato = this.qualificados.get(i);
        if (candidato.codCandidato == codCandidato) {
          this.aprovados.add(candidato);
          this.qualificados.remove(candidato);
          break;
        }
      }
    }

    public List<Candidato> obterAprovados() {
      return this.aprovados;
    }
  }
}
