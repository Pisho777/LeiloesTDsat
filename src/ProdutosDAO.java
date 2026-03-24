/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    //  CADASTRAR PRODUTO
    public void cadastrarProduto (ProdutosDTO produto){
        
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        
        try {
            conn = new conectaDAO().connectDB(); // ✅ CORRIGIDO
            
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            
            prep.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close(); // 🔥 fecha conexão
            } catch (Exception e) {}
        }
    }
    
    
    // LISTAR PRODUTOS
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        String sql = "SELECT * FROM produtos";
        
        try {
            conn = new conectaDAO().connectDB(); // ✅ CORRIGIDO
            
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            
            listagem = new ArrayList<>();
            
            while(resultset.next()){
                ProdutosDTO produto = new ProdutosDTO();
                
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                
                listagem.add(produto);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar: " + e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {}
        }
        
        return listagem;
    }

    // VENDER PRODUTO
    public void venderProduto(int id){
        
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
        
        try {
            conn = new conectaDAO().connectDB();
            
            prep = conn.prepareStatement(sql);
            prep.setInt(1, id);
            prep.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao vender: " + e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {}
        }
    }
    
    
    // LISTAR VENDIDOS
    public ArrayList<ProdutosDTO> listarProdutosVendidos(){
        
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
        
        try {
            conn = new conectaDAO().connectDB();
            
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            
            listagem = new ArrayList<>();
            
            while(resultset.next()){
                ProdutosDTO produto = new ProdutosDTO();
                
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                
                listagem.add(produto);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar vendidos: " + e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {}
        }
        
        return listagem;
    }
}