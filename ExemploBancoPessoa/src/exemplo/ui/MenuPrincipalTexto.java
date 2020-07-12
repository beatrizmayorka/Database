package exemplo.ui;

import java.util.Scanner;

public class MenuPrincipalTexto {
	
	private static final int OP_PESSOAS = 1;
	private static final int OP_DEPTOS = 2;
	private static final int OP_PRODUTOS = 3;
	private static final int OP_COLAB = 4;
	
	private static final int OP_ADICIONAR = 1;
	private static final int OP_LISTAR = 2;
	private static final int OP_EDITAR = 3;
	private static final int OP_EXCLUIR = 4;
	
	// conjunto de estados possiveis no sistema
	private enum Estado {PRINCIPAL, PESSOAS, DEPTOS, PRODUTOS, COLAB};
	
	private Estado estadoAtual; // armazena o estado atual do menu
	private Scanner entrada;
	
	public MenuPrincipalTexto() {
		estadoAtual = Estado.PRINCIPAL;
		entrada = new Scanner(System.in); // configura o Scanner para ler da entrada padrão (STDIN)
	}
	
	private void imprimeMenuPrincipal() {
		System.out.println("1 - Administração de Pessoas");
		System.out.println("2 - Administração de Departamentos");
		System.out.println("3 - Administração de Produtos");
		System.out.println("4 - Administração de Colaboradores");
	}
	
	private void imprimeMenuSecundário(String tipoMenu) {
		System.out.println("Administração de " + tipoMenu);
		System.out.println();
		System.out.println("1 - Adicionar");
		System.out.println("2 - Listar");
		System.out.println("3 - Editar");
		System.out.println("4 - Excluir");
	}
	
	// método principal de execução do menu
	public void executa() {
		int opcao;
		MenuEspecificoTexto menuEspecificoTexto;
		
		do {
			// Mostra o menu principal ou o menu secundário
			System.out.println("Administração de RH"); // Título
			System.out.println();
			
			switch(estadoAtual) {
		
			case PESSOAS:
				imprimeMenuSecundário("Pessoas");
				break;			
			case DEPTOS:
				imprimeMenuSecundário("Departamentos");
				break;
			case PRODUTOS:
				imprimeMenuSecundário("Produtos");
				break;
			case COLAB:
				imprimeMenuSecundário("Colaboradores");
				break;

			default:
				imprimeMenuPrincipal();
			}
			
			System.out.println();
			System.out.println("0 - Sair");
			
			System.out.println();
			System.out.print("Escolha uma opção: ");
	
			// obtem entrada do usuário
			opcao = entrada.nextInt();
			entrada.nextLine();
			
			System.out.println("Voce escolheu a opção: " + opcao);
				
				
			if (estadoAtual == Estado.PRINCIPAL) {
				switch (opcao) {
				case OP_PESSOAS:
					estadoAtual = Estado.PESSOAS;
					break;
				case OP_DEPTOS:
					estadoAtual = Estado.DEPTOS;
					break;
				case OP_PRODUTOS:
					estadoAtual = Estado.PRODUTOS;
					break;
				case OP_COLAB:
					estadoAtual = Estado.COLAB;
					break;
				
		
                if (estadoAtual == Estado.PESSOAS) {
                    menuEspecificoTexto = new MenuPessoaTexto();
                }if (estadoAtual == Estado.DEPTOS) {
                    menuEspecificoTexto = new MenuSetorTexto(); 
                } if (estadoAtual == Estado.PRODUTOS) {
                    menuEspecificoTexto = new MenuProdutoTexto();
		} if (estadoAtual == Estado.COLAB) {
                    menuEspecificoTexto = new MenuProdutoTexto();
          
                 
				switch (opcao) {
					case OP_ADICIONAR:
						//adicionar um item
						menuEspecificoTexto.adicionar();
						break;
					case OP_EDITAR:
						//editar um item
						menuEspecificoTexto.editar();
						break;
					case OP_EXCLUIR:
						//excluir um item
						menuEspecificoTexto.excluir();
						break;
					case OP_LISTAR:
						//listar um item
						menuEspecificoTexto.listarTodos();
						break;
					default:
						System.out.println("Opção inválida. Tente novamente!");
				}
			}
			
			
		} while (opcao != 0);// enquanto o usuário não sai do sistema
		
	}
	
}
