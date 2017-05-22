package br.opet.projeto.view;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.opet.projeto.controller.FuncionarioController;
import br.opet.projeto.model.Funcionario;

@ManagedBean(name = "cadastroFuncionario")
@SessionScoped
public class CadastroFuncionarioVB {
	private FuncionarioController funcionarioController;
	private Funcionario funcionario;
	private List<Funcionario> lista;
	private String user;
	private String pass;

	private String nome;
	private long cpf;
	private int telefone;
	
	public String atualiza(Funcionario func){
		this.funcionario = func;
		
		return "alterarFuncionario";
	}
	
	//O eclipse acusará erro pois cpf é tipo long
	// e ID do tipo int
	
	/*public void fazerExclusao(){
		System.out.println("CPF: " + this.funcionario.getCpf());
		if(funcionarioController.deletar(this.funcionario)){
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/cadastro/sucesso.xhtml");
		}else{
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/cadastro/erro.xhtml");
		}
	}
	*/
	
	public void fazerAlteracoes(){
		System.out.println("CPF: " + this.funcionario.getCpf());
		if(funcionarioController.atualizar(this.funcionario)){
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/cadastro/sucesso.xhtml");
		}else{
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/cadastro/erro.xhtml");
		}
	}

	public void fazerCadastro() {
		funcionario = new Funcionario(nome, new Date(), cpf, telefone, 0, "NDA", "NDA");
		if(funcionarioController.inserir(funcionario)){
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/cadastro/sucesso.xhtml");
		}else{
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/cadastro/erro.xhtml");
		}
	}

	public void fazerLogin() {
		if (funcionarioController.fazerLogin(user, pass)) {
			System.out.println("usuário" + user);
			System.out.println("Senha: " + pass);
			System.out.println("Deu certo!");
		} else {
			System.out.println("Login deu errado!");
		}
	}

	public List<Funcionario> getLista() {

		this.lista = funcionarioController.listarTodos();

		return lista;
	}

	public void setLista(List<Funcionario> lista) {
		this.lista = lista;
	}

	public CadastroFuncionarioVB() {
		funcionarioController = new FuncionarioController();
	}

	public FuncionarioController getFuncionarioController() {
		return funcionarioController;
	}

	public void setFuncionarioController(FuncionarioController funcionarioController) {
		this.funcionarioController = funcionarioController;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public int getTelefone() {
		return telefone;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

}
