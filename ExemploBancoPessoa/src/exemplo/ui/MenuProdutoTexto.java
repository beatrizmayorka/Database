package exemplo.ui;

import exemplo.dao.ProdutoDao;
import exemplo.modelo.Produto;

import java.util.List;

public class MenuProdutoTexto extends MenuEspecificoTexto {
	private ProdutoDao dao;

    public MenuProdutoTexto() {
        super();
        dao = new ProdutoDao();
    }

    private int obterIdProduto() {
        System.out.print(" --> Informe o id do produto: ");
        int id = entrada.nextInt();
        entrada.nextLine();

        return id;
    }

    private Produto obterDadosProduto(Produto produto) {
        Produto pr;

        if (produto == null) {
            pr = new Produto(0, null, 0);
        } else {
            pr = produto;
        }

        System.out.print(" --> Informe o nome do produto: ");
        String nome = entrada.nextLine();

        System.out.print(" --> Informe o preco: ");
        double preco = entrada.nextInt();
        entrada.nextLine();

        pr.setNome(nome);
        pr.setPreco(preco);

        return pr;
    }

    public void adicionar() {
    	System.out.println();
    	System.out.println(" ===================== ");
        System.out.println(" | Adicionar Produto | ");
        System.out.println(" ===================== ");
        System.out.println();
        
        // obter dados do produto
        Produto novoProduto = obterDadosProduto(null);
        
        // inserir no banco o produto -> DAO
        dao.insert(novoProduto);
    }

    public void editar() {
    	System.out.println();
    	System.out.println(" ================== ");
        System.out.println(" | Editar Produto | ");
        System.out.println(" ================== ");
        System.out.println();
        
        // listar os produtos
        imprimirProdutos();

        // pedir um id de produto
        int id = obterIdProduto();

        Produto produtoAModificar = dao.getById(id);
        
        // obter os dados do produto
        Produto novoProduto = obterDadosProduto(produtoAModificar);

        // atualizar produto no banco
        novoProduto.setId(produtoAModificar.getId());
        dao.update(novoProduto);
    }

    public void excluir() {
    	System.out.println();
    	System.out.println(" =================== ");
        System.out.println(" | Excluir Produto | ");
        System.out.println(" =================== ");
        System.out.println();
        
        // listar os produtos
        imprimirProdutos();
        
        // pedir um id de produto
        int id = obterIdProduto();

        // remover produto do banco
        dao.delete(id);
    }

    public void listarTodos() {
    	System.out.println();
    	System.out.println(" ===================== ");
        System.out.println(" | Lista de Produtos | ");
        System.out.println(" ===================== ");
        System.out.println();

        imprimirProdutos();
    }

    private void imprimirProdutos() {
        
        // obter produtos do banco
        List<Produto> produtos = dao.getAll();

        // imprimir produtos
        System.out.println("id\tNome\tPreco");

        for (Produto pr : produtos) {
            System.out.println(pr.getId() + "\t" + pr.getNome() + "\t" + pr.getPreco());
        }
    }
}
