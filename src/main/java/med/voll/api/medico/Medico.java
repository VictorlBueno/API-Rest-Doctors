package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded // diz que tem classes diferentes, mas que fiquem na mesma tabela
    private Endereco endereco;
    private boolean ativo;

    public Medico(DadosCadastroMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.crm = dados.crm();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        if(dados.nome() != null) {
            this.nome = dados.nome();
        }
        if(dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if(dados.endereco() != null) {
            this.endereco.atualizaInformacoes(dados.endereco());
        }

    }

    public void excluir() {
        this.ativo = false;
    }
}
