/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agroindustria_definitivo;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author aluno
 */
public class Nova_Tela_Produtos extends javax.swing.JInternalFrame {

    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private Connection con;

    public Nova_Tela_Produtos() {
        initComponents();
        con = ConeBD.ConexaoBD.ConectarBD();
        InserirTable();
    }
    public void setPosicao() {
    Dimension d = this.getDesktopPane().getSize();
    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); }

    final void InserirTable() {
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(3);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(40);

        try {
            String sql = "select * from produtos";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            modelo.setNumRows(0);
            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getInt("id"), rs.getString("unidade"), rs.getString("nome_prod"), rs.getString("quantidade"), rs.getString("valor")});
            }
            jTable1.setModel(modelo);
        } catch (SQLException e) {
            System.out.println("asdfads " + e.getMessage());
        }

    }

    public void limpar() {
        espacoID.setText("");
        espacoDescricao.setText("");
        espacoQuantidade.setText("");
        espacoValor.setText("");
        espacoPesquisa.setText("");
        espacoProduto.setText("");
    }

    public double retorna_valor_total() {
        String soma=null;
        try {
            String sql = "select sum(valor) as total_produtos from produtos";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()){
                 soma = rs.getString(1);
            }
            return Float.parseFloat(soma);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de Preco Venda");
        }
        return (float) 0.00;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        botaoDeletar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        espacoDescricao = new javax.swing.JTextField();
        espacoID = new javax.swing.JTextField();
        botaoPesquisar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        espacoQuantidade = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        espacoPesquisa = new javax.swing.JTextField();
        espacoValor = new javax.swing.JTextField();
        botaoCadastrar = new javax.swing.JButton();
        espacoProduto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        botaoPesquisar1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Cadastro de Produto");
        setPreferredSize(new java.awt.Dimension(870, 320));

        jPanel1.setPreferredSize(new java.awt.Dimension(900, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/Cancel_16x16.png"))); // NOI18N
        jButton3.setText("Limpar Campos");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, -1, 30));

        botaoDeletar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botaoDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/excluir.gif"))); // NOI18N
        botaoDeletar.setText("Deletar");
        botaoDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoDeletarActionPerformed(evt);
            }
        });
        jPanel1.add(botaoDeletar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 220, 100, 30));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/editar.png"))); // NOI18N
        jButton1.setText("Alterar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 220, 100, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Preço do Produto R$:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));
        jPanel1.add(espacoDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 0, 120, -1));

        espacoID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                espacoIDActionPerformed(evt);
            }
        });
        jPanel1.add(espacoID, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 180, -1));

        botaoPesquisar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botaoPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/impressora.png"))); // NOI18N
        botaoPesquisar.setText("Imprimir");
        botaoPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarActionPerformed(evt);
            }
        });
        jPanel1.add(botaoPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 220, 120, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Descrição do Produto:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, -1, -1));
        jPanel1.add(espacoQuantidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, 120, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cód. Produto", "Descrição", "Produto", "Quantidade", "Valor"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 830, 140));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Quantidade:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Código do produto:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        espacoPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                espacoPesquisaKeyPressed(evt);
            }
        });
        jPanel1.add(espacoPesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 30, 140, 20));
        jPanel1.add(espacoValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 180, -1));

        botaoCadastrar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botaoCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/salvar.png"))); // NOI18N
        botaoCadastrar.setText("Cadastrar");
        botaoCadastrar.setToolTipText("");
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });
        jPanel1.add(botaoCadastrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, -1, 30));
        jPanel1.add(espacoProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 140, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Produto:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, -1, -1));

        botaoPesquisar1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botaoPesquisar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/pesquisar.png"))); // NOI18N
        botaoPesquisar1.setText("Pesquisar");
        botaoPesquisar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisar1ActionPerformed(evt);
            }
        });
        jPanel1.add(botaoPesquisar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, 130, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 838, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        limpar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void botaoDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoDeletarActionPerformed
        JOptionPane.showMessageDialog(null, "Digite o id a ser procurado");
        String sqlDel = "Delete from produtos where id=?";
        try {
            pst = con.prepareStatement(sqlDel);
            pst.setInt(1, Integer.parseInt(espacoID.getText()));
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Dados deletados com sucesso");
            InserirTable();
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Assigment Failure: Dados Incompatíveis " + e.getMessage());
        }

    }//GEN-LAST:event_botaoDeletarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String sqlUp = "Update produtos set unidade=?,quantidade=?,valor=?,nome_prod=? where id= ?";
        try {
            pst = con.prepareStatement(sqlUp);
            pst.setString(1, espacoDescricao.getText());
            pst.setInt(2, Integer.valueOf(espacoQuantidade.getText()));
            pst.setFloat(3, Float.parseFloat(espacoValor.getText()));
            pst.setString(4,espacoProduto.getText());   
            pst.setInt(5, Integer.parseInt(espacoID.getText()));                     
            pst.executeUpdate();
            InserirTable();
            JOptionPane.showMessageDialog(null,"Produto "+espacoProduto.getText()+" alterado com sucesso");
        } 
        catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Assigment Failure: Sem dados selecionados" + e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed
         String sql = "select * from produtos where id=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1,Integer.parseInt(espacoID.getText()));
            rs = pst.executeQuery();
            JRResultSetDataSource relat = new JRResultSetDataSource(rs);           
            JasperPrint jp = JasperFillManager.fillReport("src\\Relatorio\\Produtos_3_Individual.jasper", null, relat);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);
            jv.toFront();
        } catch (SQLException | JRException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Erro de Emissão de Relatórios Botao Imprimir: " + ex.getMessage());
        }
    }//GEN-LAST:event_botaoPesquisarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int linha = jTable1.getSelectedRow();
        espacoID.setText(jTable1.getValueAt(linha, 0).toString());
        espacoProduto.setText(jTable1.getValueAt(linha, 2).toString());
        espacoDescricao.setText(jTable1.getValueAt(linha, 1).toString());
        espacoValor.setText(jTable1.getValueAt(linha, 4).toString());
        espacoQuantidade.setText(jTable1.getValueAt(linha, 3).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
        String sql = "insert into produtos(id,unidade,quantidade,valor,nome_prod) values (?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(espacoID.getText()));
            pst.setString(2, espacoDescricao.getText());
            pst.setInt(3, Integer.parseInt(espacoQuantidade.getText()));
            pst.setFloat(4, Float.parseFloat(espacoValor.getText()));
            pst.setString(5,espacoProduto.getText());
            pst.executeUpdate();
            InserirTable();
            JOptionPane.showMessageDialog(null,"Produto Adicionado com sucesso");
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Dados não inseridos:Erro de Banco" + e);
        }
    }//GEN-LAST:event_botaoCadastrarActionPerformed

    private void espacoIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_espacoIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_espacoIDActionPerformed

    private void botaoPesquisar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisar1ActionPerformed
        try {
            String nome_prod = espacoPesquisa.getText().toString();
            String sql = "Select * from produtos where nome_prod ILIKE ? ";
            pst = con.prepareStatement(sql);
            pst.setString(1, "%"+nome_prod+"%");
            rs = pst.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            modelo.setRowCount(0);
            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getInt("id"), rs.getString("unidade"), rs.getString("nome_prod"), rs.getString("quantidade"), rs.getString("valor")});
            }
            jTable1.setModel(modelo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!Tente outro produto: "+e);
        }
    }//GEN-LAST:event_botaoPesquisar1ActionPerformed

    private void espacoPesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_espacoPesquisaKeyPressed
        try {
            String nome_prod = espacoPesquisa.getText().toString();
            String sql = "Select * from produtos where nome_prod ILIKE ? ";
            pst = con.prepareStatement(sql);
            pst.setString(1, "%"+nome_prod+"%");
            rs = pst.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            modelo.setRowCount(0);
            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getInt("id"), rs.getString("unidade"), rs.getString("nome_prod"), rs.getString("quantidade"), rs.getString("valor")});
            }
            jTable1.setModel(modelo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!Tente outro produto: "+e);
        }
    }//GEN-LAST:event_espacoPesquisaKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoDeletar;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JButton botaoPesquisar1;
    private javax.swing.JTextField espacoDescricao;
    private javax.swing.JTextField espacoID;
    private javax.swing.JTextField espacoPesquisa;
    private javax.swing.JTextField espacoProduto;
    private javax.swing.JTextField espacoQuantidade;
    private javax.swing.JTextField espacoValor;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
