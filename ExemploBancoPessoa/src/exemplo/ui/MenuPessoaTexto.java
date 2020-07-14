package exemplo.ui;

import exemplo.dao.PessoaDao;
import exemplo.modelo.Pessoa;

import java.util.List;

public class MenuPessoaTexto extends MenuEspecificoTexto {
    private PessoaDao dao;

    public MenuPessoaTexto() {
        super();
        dao = new PessoaDao();
    }

    private int obterIdPessoa() {
        System.out.print(" --> Escolha o id da pessoa: ");
        int id = entrada.nextInt();
        entrada.nextLine();

        return id;
    }

    private Pessoa obterDadosPessoa(Pessoa pessoa) {
        Pessoa p;

        if (pessoa == null) {
            p = new Pessoa();
        } else {
            p = pessoa;
        }

        System.out.print(" --> Informe o nome da pessoa: ");
        String nome = entrada.nextLine();

        System.out.print(" --> Informe a idade da pessoa: ");
        int idade = entrada.nextInt();
        entrada.nextLine();

        p.setNome(nome);
        p.setIdade(idade);

        return p;
    }

    public void adicionar() {
        System.out.println();
        System.out.println(" ==================== ");
        System.out.println(" | Adicionar Pessoa | ");
        System.out.println(" ==================== ");
        System.out.println();

        // obter dados da pessoa
        Pessoa novaPessoa = obterDadosPessoa(null);

        // inserir no banco a pessoa -> DAO
        dao.insert(novaPessoa);
    }

    public void editar() {
        System.out.println();
        System.out.println(" ================= ");
        System.out.println(" | Editar Pessoa | ");
        System.out.println(" ================= ");
        System.out.println();

        // listar as pessoas
        imprimirPessoas();

        // pedir um id de pessoa
        int id = obterIdPessoa();

        Pessoa pessoaAModificar = dao.getById(id);

        // obter os dados da pessoa
        Pessoa novaPessoa = obterDadosPessoa(pessoaAModificar);

        // atualizar pessoa no banco
        novaPessoa.setId(pessoaAModificar.getId());
        dao.update(novaPessoa);
    }

    public void excluir() {
        System.out.println();
        System.out.println(" ================== ");
        System.out.println(" | Excluir Pessoa | ");
        System.out.println(" ================== ");
        System.out.println();

        // listar as pessoas
        imprimirPessoas();
        // pedir um id de pessoa
        int id = obterIdPessoa();

        // remover pessoa do banco
        dao.delete(id);
    }

    public void listarTodos() {
        System.out.println();
    	System.out.println(" ==================== ");
        System.out.println(" | Lista de Pessoas | ");
        System.out.println(" ==================== ");
        System.out.println();

        imprimirPessoas();
    }

    private void imprimirPessoas() {
        // obter pessoas do banco
        List<Pessoa> pessoas = dao.getAll();

        // imprimir pessoas
        System.out.println("id\tNome\tIdade");

        for (Pessoa p : pessoas) {
            System.out.println(p.getId() + "\t" + p.getNome() + "\t" + p.getIdade());
        }
    }
}
