package exemplo.ui;

import java.util.Scanner;

public class MenuPrincipalTexto {
	
	private static final int OP_PESSOAS = 1;
	private static final int OP_DEPARTAMENTO = 2;
	private static final int OP_PRODUTO = 3;
	private static final int OP_COLABORADOR = 4;

	private static final int OP_ADICIONAR = 1;
	private static final int OP_LISTAR = 2;
	private static final int OP_EDITAR = 3;
	private static final int OP_EXCLUIR = 4;
	
	private enum Estado {PRINCIPAL, PESSOAS, DEPARTAMENTO, PRODUTO, COLABORADOR};
	
	private Estado estadoAtual; 
	private Scanner entrada;
	
	public MenuPrincipalTexto() {
		estadoAtual = Estado.PRINCIPAL;
		entrada = new Scanner(System.in); 
	}
	
	private void imprimeMenuPrincipal() {
		System.out.println(" \t ====================================== ");
		System.out.println(" \t | 1 - Administracao de Pessoas       | ");	
		System.out.println(" \t | 2 - Administracao de Departamento  | ");
		System.out.println(" \t | 3 - Administracao de Produtos      | ");
		System.out.println(" \t | 4 - Administracao de Colaboradores | ");
		System.out.println(" \t ====================================== ");

		
	}
	
	private void imprimeMenuSecundário(String tipoMenu) {
		System.out.println("Administração de " + tipoMenu);
		System.out.println();
		System.out.println(" \t ================= ");
		System.out.println(" \t | 1 - Adicionar | ");
		System.out.println(" \t ----------------- ");
		System.out.println(" \t | 2 - Listar    | ");
		System.out.println(" \t ----------------- ");
		System.out.println(" \t | 3 - Editar    | ");
		System.out.println(" \t ----------------- ");
		System.out.println(" \t | 4 - Excluir   | ");
		System.out.println(" \t ================= ");
	}
	
	public void executa() {
		int opcao;
		MenuEspecificoTexto menuEspecificoTexto = null;
		
		do {
			System.out.println();
			System.out.println(" \t\t ====================== ");
			System.out.println(" \t\t |Administracao de RH | "); 
			System.out.println(" \t\t ====================== ");
			System.out.println();
			
			switch(estadoAtual) {
		
			case PESSOAS:
				imprimeMenuSecundário("Pessoas");
				break;			
			case DEPARTAMENTO:
				imprimeMenuSecundário("Departamento");
				break;
			case PRODUTO:
				imprimeMenuSecundário("Produtos");
				break;
			case COLABORADOR:
				imprimeMenuSecundário("Colaboradores");
				break;
			default:
				imprimeMenuPrincipal();
			}
			
			System.out.println();
			System.out.println(" \t\t ================= ");
			System.out.println(" \t\t | 0 - Sair      | ");
			System.out.println(" \t\t ================= ");
			
			System.out.println();
			System.out.print(" --> Escolha uma opcao: ");
	
			opcao = entrada.nextInt();
			entrada.nextLine();
			
			System.out.println(" --> Voce escolheu a opcao: " + opcao);
				
			if (estadoAtual == Estado.PRINCIPAL) {
				switch (opcao) {
				case OP_PESSOAS:
					estadoAtual = Estado.PESSOAS;
					break;
				case OP_DEPARTAMENTO:
					estadoAtual = Estado.DEPARTAMENTO;
					break;
				case OP_PRODUTO:
					estadoAtual = Estado.PRODUTO;
					break;
				case OP_COLABORADOR:
					estadoAtual = Estado.COLABORADOR;
					break;
				}
				
		
                if (estadoAtual == Estado.PESSOAS) {
                    menuEspecificoTexto = new MenuPessoaTexto();
                }if (estadoAtual == Estado.DEPARTAMENTO) {
                    menuEspecificoTexto = new MenuDepartamentoTexto(); 
                } if (estadoAtual == Estado.PRODUTO) {
                    menuEspecificoTexto = new MenuProdutoTexto();
                } if (estadoAtual == Estado.COLABORADOR){
                	menuEspecificoTexto = new MenuColaboradorTexto();
                }
                 
				switch (opcao) {
					case OP_ADICIONAR:
						menuEspecificoTexto.adicionar();
						break;
					case OP_EDITAR:
						menuEspecificoTexto.editar();
						break;
					case OP_EXCLUIR:
						menuEspecificoTexto.excluir();
						break;
					case OP_LISTAR:
						menuEspecificoTexto.listarTodos();
						break;
					default:
						System.out.println();
						System.out.println(" \t ==================================== ");
						System.out.println(" \t | Opcao invalida. Tente novamente! | ");
						System.out.println(" \t ==================================== ");
				}
			}
			
			
		} while (opcao != 0);
		
	}
	
}
