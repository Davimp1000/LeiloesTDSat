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
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;



public class ProdutosDAO {
    
    Connection con;
    
    ResultSet resultset;
    
    private DefaultTableModel model;
  
    
    
    
    
    
    
    //CONEXÃO COM O DATABASE
    
    public void conexaoMySQL()
    {
        
         
              //CONEXÃO COM O BANCO DE DADOS//
        try
        {
          Class.forName("com.mysql.cj.jdbc.Driver" );
          System.out.println( "Driver JDBC carregado" );
          
        }
        catch(ClassNotFoundException cnfe)
                {
                     System.out.println( "Driver JDBC nao encontrado : " +
                               cnfe.getMessage() );
                }
            
        
        
        try
        {
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11","root","2960412032.");
            System.out.println( "Conexao com o banco de dados estabelecida." );
        }
        catch(SQLException sqle)
                {
                     System.out.println( "Erro na conexao ao Bando de Dados : " +
                                        sqle.getMessage() );
                }
        
       //======================================================================================================== 
        
        
       
                                        //STATEMENT
       
          Statement stmt = null;
       try
       {
          stmt = con.createStatement();
           System.out.println( "Pronto para execucao de comandos sql." );
       }
       catch(SQLException sqle)
               {
                    System.out.println( "Erro no acesso ao Bando de Dados : " +
                                        sqle.getMessage() );
               }
       
        }
    
    
    
    
    
    
    
     //FECHAMENTO DA CONEXÃO
    
     public void fecharConexao()
    {
           
       
try {
    con.close();
    System.out.println( "Conexão com o banco de dados fechada" );
} catch (SQLException sqle) {
    System.out.println( "Erro no fechamento da conexão : " + sqle.getMessage() );
}
    }
    
    
    
    
    
    
    
    
  
    
    
    //MÉTODO DE CADASTRAR PRODUTO
    
    public void cadastrarProduto (ProdutosDTO produto)
    {
         
        PreparedStatement ps = null;
       
       
        
        String sql = "insert into produtos(nome,valor,status) values (?,?,?)";
        
        try
        {
            ps = con.prepareStatement(sql);
            
            ps.setString(1, produto.getNome());
            ps.setInt(2, produto.getValor());
            ps.setString(3, produto.getStatus());
          
           
            
            ps.executeUpdate();
              
            JOptionPane.showMessageDialog(null,"CADASTRO REALIZADO COM SUCESSO!");
        }
        catch(SQLException sqle)
        {
               JOptionPane.showMessageDialog(null,"NÃO CONSEGUIMOS REALIZAR O CADASTRO");
        }
        
     
        
        
    }
    
    
    
    
    
    
    //MÉTODO DE LISTAR PRODUTOS
    
    public void listar(DefaultTableModel model)
    {
       
        String sql = "select*from produtos";
        
        try
        {
            
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
          
              while(rs.next())
              {
             int id =     rs.getInt("id");
             String nome =    rs.getString("nome");
             int valor =    rs.getInt("valor");
             String status =    rs.getString("status");
                        
                  model.addRow(new Object[] {id,nome,valor,status});
              }
              
             
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Ocorreu um erro");
        }
         
    }
            
    
   
    
    
        
}

