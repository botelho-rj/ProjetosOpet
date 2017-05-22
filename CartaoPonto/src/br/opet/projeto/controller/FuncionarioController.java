package br.opet.projeto.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.opet.projeto.dao.IAbstractDAO;
import br.opet.projeto.model.Funcionario;
import br.opet.projeto.util.DbConnect;

public class FuncionarioController implements IAbstractDAO<Funcionario> {

	aaaaaaaaaaaaaaaa
	
	@SuppressWarnings("deprecation")
	@Override
	public List<Funcionario> listarTodos() {
		Connection conn = null;
		List<Funcionario> lista = new ArrayList<>();

		try {
			conn = DbConnect.getConnection();
			ResultSet rs = DbConnect.getResultSet(conn, "SELECT * FROM CARTAO_PONTO");
			while (rs.next()) {
				Funcionario func = new Funcionario(rs.getString("nome"),
						new java.util.Date(rs.getDate("dtNiver").getDate()), rs.getLong("cpf"), rs.getInt("telefone"),
						1000, "Analise de Sistemas", "Rua das Palmeiras");
				lista.add(func);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return lista;
	}

	@Override
	public Funcionario procuraPorId(int id) {
		Connection conn = null;
		List<Funcionario> lista = new ArrayList<>();
		try {

		} catch (Exception e) {
			// TODO: handle exception
		} finally {

		}
		return null;
	}

	@Override
	public boolean inserir(Funcionario entidade) {
		Connection conn = null;
		boolean cadastro = false;
		try {
			conn = DbConnect.getConnection();
			PreparedStatement st = DbConnect.getPreparedStatement(conn,
					"INSERT INTO CARTAO_PONTO (nome, dtNiver, cpf, telefone) VALUES (?,?,?,?)");
			st.setString(1, entidade.getNome());
			st.setDate(2, new java.sql.Date(entidade.getDtNiver().getTime()));
			st.setLong(3, entidade.getCpf());
			st.setInt(4, entidade.getTelefone());
			if (st.executeUpdate() != 0) {
				cadastro = true;
			} else {
				cadastro = false;
			}
		} catch (Exception e) {
			cadastro = false;
			e.printStackTrace();
		} finally {

		}
		return cadastro;
	}

	@Override
	public boolean atualizar(Funcionario entidade) {
		Connection conn = null;
		boolean resultado = false;
		try {
			conn = DbConnect.getConnection();
			PreparedStatement st = DbConnect.getPreparedStatement(conn,
					"UPDATE CARTAO_PONTO SET nome = ?, dtNiver = ?, telefone = ? WHERE cpf = ?");
			st.setString(1, entidade.getNome());
			st.setDate(2, new java.sql.Date(entidade.getDtNiver().getTime()));
			st.setInt(3, entidade.getTelefone());
			st.setLong(4, entidade.getCpf());
			if(st.executeUpdate() != 0){
				resultado = true;
			}else{
				System.out.println("VALOR DE CPF: " +entidade.getCpf());
				resultado = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}

	@Override
	public boolean deletar(int id) {
		Connection conn = null;
		List<Funcionario> lista = new ArrayList<>();
		try {

		} catch (Exception e) {
			// TODO: handle exception
		} finally {

		}
		return false;
	}

	@Override
	public boolean fazerLogin(String user, String pass) {
		Connection conn = null;
		boolean fezLogin = false;
		try {
			conn = DbConnect.getConnection();
			ResultSet rs = DbConnect.getResultSet(conn,
					"SELECT * FROM CARTAO_PONTO WHERE nome = '" + user + "' cpf = " + Long.valueOf(pass));
			if (rs.next()) {
				fezLogin = true;
			} else {
				fezLogin = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return fezLogin;
	}

}
