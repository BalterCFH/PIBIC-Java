/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agroindustria_definitivo;

import agroindustria_definitivo.*;
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
public class Nova_Tela_Produtos_Eventos extends javax.swing.JInternalFrame {

    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private Connection con;

    public Nova_Tela_Produtos_Eventos() {
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
            String sql = "select * from produtos_eventos";
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
        espacoDescricao.setText("");
        espacoQuantidade.setText("");
        espacoValor.setText("");
        espacoPesquisa.setText("");
        espacoProduto.setText("");
    }

    public double retorna_valor_total() {
        String soma=null;
        try {
            String sql = "select sum(valor) as total_produtos_eventos from produtos_eventos";
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
        espacoCompetencia = new javax.swing.JFormattedTextField();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel2 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        botaoDeletar1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        espacoDescricao1 = new javax.swing.JTextField();
        espacoID1 = new javax.swing.JTextField();
        botaoPesquisar2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        espacoQuantidade1 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        espacoPesquisa1 = new javax.swing.JTextField();
        espacoValor1 = new javax.swing.JTextField();
        botaoCadastrar1 = new javax.swing.JButton();
        espacoProduto1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        botaoPesquisar3 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Cadastro de Produto em Eventos");
        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(890, 320));

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
        jLabel1.setText("Competência:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));
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

        try {
            espacoCompetencia.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        espacoCompetencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                espacoCompetenciaKeyPressed(evt);
            }
        });
        jPanel1.add(espacoCompetencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 180, -1));

        jInternalFrame1.setClosable(true);
        jInternalFrame1.setIconifiable(true);
        jInternalFrame1.setTitle("Cadastro de Produto");
        jInternalFrame1.setPreferredSize(new java.awt.Dimension(890, 320));

        jPanel2.setPreferredSize(new java.awt.Dimension(900, 500));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/Cancel_16x16.png"))); // NOI18N
        jButton4.setText("Limpar Campos");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, -1, 30));

        botaoDeletar1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botaoDeletar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/excluir.gif"))); // NOI18N
        botaoDeletar1.setText("Deletar");
        botaoDeletar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoDeletar1ActionPerformed(evt);
            }
        });
        jPanel2.add(botaoDeletar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 220, 100, 30));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/editar.png"))); // NOI18N
        jButton2.setText("Alterar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 220, 100, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Preço do Produto R$:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));
        jPanel2.add(espacoDescricao1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 0, 120, -1));

        espacoID1.setEditable(false);
        espacoID1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                espacoID1ActionPerformed(evt);
            }
        });
        jPanel2.add(espacoID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 180, -1));

        botaoPesquisar2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botaoPesquisar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/impressora.png"))); // NOI18N
        botaoPesquisar2.setText("Imprimir");
        botaoPesquisar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisar2ActionPerformed(evt);
            }
        });
        jPanel2.add(botaoPesquisar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 220, 120, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Descrição do Produto:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, -1, -1));
        jPanel2.add(espacoQuantidade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, 120, -1));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cód. Produto", "Descrição", "Produto", "Quantidade", "Valor"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 830, 140));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Quantidade:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Código do produto:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));
        jPanel2.add(espacoPesquisa1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 30, 140, 20));
        jPanel2.add(espacoValor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 180, -1));

        botaoCadastrar1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botaoCadastrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/salvar.png"))); // NOI18N
        botaoCadastrar1.setText("Cadastrar");
        botaoCadastrar1.setToolTipText("");
        botaoCadastrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrar1ActionPerformed(evt);
            }
        });
        jPanel2.add(botaoCadastrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, -1, 30));
        jPanel2.add(espacoProduto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 140, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Produto:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, -1, -1));

        botaoPesquisar3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botaoPesquisar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/pesquisar.png"))); // NOI18N
        botaoPesquisar3.setText("Pesquisar");
        botaoPesquisar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisar3ActionPerformed(evt);
            }
        });
        jPanel2.add(botaoPesquisar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, 130, -1));

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 838, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 838, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        limpar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void botaoDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoDeletarActionPerformed
        String sqlDel = "Delete from produtos_eventos where nome_prod=?";
        try {
            pst = con.prepareStatement(sqlDel);
            pst.setString(1, espacoProduto.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Dados deletados com sucesso");
            InserirTable();
            limpar();
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Assigment Failure: Dados Incompatíveis " + e.getMessage());
        }
    }//GEN-LAST:event_botaoDeletarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String sqlUp = "Update produtos_eventos set unidade=?,quantidade=?,valor=?,competencia=? where nome_prod=?";
        try {
            pst = con.prepareStatement(sqlUp);
            pst.setString(1, espacoDescricao.getText());
            pst.setInt(2, Integer.valueOf(espacoQuantidade.getText()));
            pst.setFloat(3, Float.parseFloat(espacoValor.getText()));
            pst.setString(4, espacoCompetencia.getText());            
            pst.setString(5, espacoProduto.getText());            
            pst.executeUpdate();
            InserirTable();
            JOptionPane.showMessageDialog(null,"Dados de "+espacoProduto.getText()+" alterados com sucesso");
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Assigment Failure: Sem dados selecionados" + e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed
         String sql = "select * from produtos_eventos where nome_prod=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,espacoProduto.getText());
            rs = pst.executeQuery();
            JRResultSetDataSource relat = new JRResultSetDataSource(rs);           
            JasperPrint jp = JasperFillManager.fillReport("src\\Relatorio\\Produtos_3_Eventos_Individual.jasper", null, relat);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);
            jv.toFront();
        } catch (SQLException | JRException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Erro de Emissão de Relatórios Botao Imprimir: " + ex.getMessage());
        }
    }//GEN-LAST:event_botaoPesquisarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int linha = jTable1.getSelectedRow();        
        espacoProduto.setText(jTable1.getValueAt(linha, 2).toString());
        espacoDescricao.setText(jTable1.getValueAt(linha, 1).toString());
        espacoValor.setText(jTable1.getValueAt(linha, 4).toString());
        espacoQuantidade.setText(jTable1.getValueAt(linha, 3).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
        String sql = "insert into produtos_eventos(unidade,quantidade,valor,nome_prod,competencia) values (?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);            
            pst.setString(1, espacoDescricao.getText());
            pst.setInt(2, Integer.parseInt(espacoQuantidade.getText()));
            pst.setFloat(3, Float.parseFloat(espacoValor.getText()));
            pst.setString(4, espacoProduto.getText());
            pst.setString(5, espacoCompetencia.getText());
            pst.executeUpdate();
            InserirTable();
            JOptionPane.showMessageDialog(null,"Produto Cadastrado com sucesso");
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Dados não inseridos:Erro de Banco" + e);
        }
    }//GEN-LAST:event_botaoCadastrarActionPerformed

    private void botaoPesquisar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisar1ActionPerformed
        try {
            String nome_prod = espacoPesquisa.getText();
            String sql = "Select * from produtos_eventos where nome_prod ILIKE ? ";
            pst = con.prepareStatement(sql);
            pst.setString(1, "%" + nome_prod + "%");
            rs = pst.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            modelo.setRowCount(0);
            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getInt("id"), rs.getString("unidade"),rs.getString("nome_prod"),rs.getString("quantidade"), rs.getString("valor")});
            }
            jTable1.setModel(modelo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_botaoPesquisar1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void botaoDeletar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoDeletar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoDeletar1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void espacoID1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_espacoID1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_espacoID1ActionPerformed

    private void botaoPesquisar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoPesquisar2ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void botaoCadastrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoCadastrar1ActionPerformed

    private void botaoPesquisar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisar3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoPesquisar3ActionPerformed

    private void espacoCompetenciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_espacoCompetenciaKeyPressed

    }//GEN-LAST:event_espacoCompetenciaKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoCadastrar1;
    private javax.swing.JButton botaoDeletar;
    private javax.swing.JButton botaoDeletar1;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JButton botaoPesquisar1;
    private javax.swing.JButton botaoPesquisar2;
    private javax.swing.JButton botaoPesquisar3;
    private javax.swing.JFormattedTextField espacoCompetencia;
    private javax.swing.JTextField espacoDescricao;
    private javax.swing.JTextField espacoDescricao1;
    private javax.swing.JTextField espacoID1;
    private javax.swing.JTextField espacoPesquisa;
    private javax.swing.JTextField espacoPesquisa1;
    private javax.swing.JTextField espacoProduto;
    private javax.swing.JTextField espacoProduto1;
    private javax.swing.JTextField espacoQuantidade;
    private javax.swing.JTextField espacoQuantidade1;
    private javax.swing.JTextField espacoValor;
    private javax.swing.JTextField espacoValor1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
