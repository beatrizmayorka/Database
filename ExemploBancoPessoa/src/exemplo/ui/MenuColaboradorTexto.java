package exemplo.ui;

import exemplo.dao.ColaboradorDao;
import exemplo.modelo.Colaborador;

import java.util.List;

public class MenuColaboradorTexto extends MenuEspecificoTexto{
    private ColaboradorDao dao;

    public MenuColaboradorTexto() {
        super();
        dao = new ColaboradorDao();
    }

    private int obterIdColaborador() {
        System.out.print("Escolha o id do colaborador: ");
        int id = entrada.nextInt();
        entrada.nextLine();

        return id;
    }

    private Colaborador obterDadosColaborador(Colaborador colaborador) {
        Colaborador c;

        if (colaborador == null) {
            c = new Colaborador(0, null, null);
        } else {
            c = colaborador;
        }

        System.out.print(" --> Informe o nome do colaborador: ");
        String nome = entrada.nextLine();

        System.out.print(" --> Informe o produto: ");
        String pedidos = entrada.nextLine();
        entrada.nextLine();

        c.setNome(nome);

        return c;
    }

    public void adicionar() {
        System.out.println("Adicionar Colaborador");
        System.out.println();

        Colaborador novoColaborador = obterDadosColaborador(null);

        dao.insert(novoColaborador);
    }

    public void editar() {
        System.out.println("Editar Colaborador");
        System.out.println();

        imprimirColaborador();

        int id = obterIdColaborador();

        Colaborador colaboradorAModificar = dao.getById(id);

        Colaborador novoColaborador = obterDadosColaborador(colaboradorAModificar);

        novoColaborador.setId(colaboradorAModificar.getId());
        dao.update(novoColaborador);
    }

    public void excluir() {
        System.out.println("Excluir Colaborador");
        System.out.println();

        imprimirColaborador();
        int id = obterIdColaborador();


        dao.delete(id);
    }

    public void listarTodos() {
        System.out.println("Lista de Colaboradores");
        System.out.println();

        imprimirColaborador();
    }

    private void imprimirColaborador() {
        List<Colaborador> colaboradores = dao.getAll();
        System.out.println("id\tNome\tPedidos");

        for (Colaborador c : colaboradores) {
            System.out.println(f.getId() + "\t" + f.getNome() + "\t" + f.getPedidos());
        }
    }
}
