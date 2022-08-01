/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agroindustria_definitivo;

import java.awt.Dimension;
import static java.awt.Frame.ICONIFIED;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author aluno
 */
public class Nova_Tela_Usuarios extends javax.swing.JInternalFrame {

    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private Connection con;
    int muda;
    int md;

    public Nova_Tela_Usuarios() {
        initComponents();
        con = ConeBD.ConexaoBD.ConectarBD();
        InserirTable();        

    }

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    final void InserirTable() {              
        try {
            String sql = "select * from usuario";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            modelo.setNumRows(0);
            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getString("login"), rs.getString("senha")});
            }
            jTable1.setModel(modelo);
        } catch (SQLException e) {
            System.out.println("asdfads " + e.getMessage());
        }
    }

    public void limpar() {
        espacoID.setText("");
        espacoLogin.setText("");
        espacoPesquisa.setText("");
        espacoSenha.setText("");
    }

    public void cadastrarmamifero() {
        if (muda == 1) {
            if (JOptionPane.showConfirmDialog(null, "Deseja realmente salvar?" + "" + "", "Confirmação", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

                //String caminho = jLabel6.getIcon().toString();
                //File file = new File(caminho);
                InputStream fis;
                String sql = "insert into usuario (login,senha) values (?,?)";

                try {

                    pst = con.prepareStatement(sql);
                    //pst.setInt(1, Integer.parseInt(espacoID.getText()));
                    pst.setString(1, espacoLogin.getText());
                    pst.setString(2, espacoSenha.getText());
                    //pst.setString(4, (String) ComboCategoria.getSelectedItem());
                    //fis = new FileInputStream(caminho);
                    //pst.setBinaryStream(5, fis, file.length());
                    pst.execute();
                    InserirTable();
                    JOptionPane.showMessageDialog(null, "Pessoa cadastrado com sucesso!", "Informação", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException error) {
                    JOptionPane.showMessageDialog(null, "Já existe uma pessoa cadastrado com o mesmo código!" + error);
                }
            }
        } else {
            muda = 2;
            if (JOptionPane.showConfirmDialog(null, "Nenhuma imagem foi selecionada para a pessoa!\n" + "Deseja realmente salvar?" + "", "Aviso", JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {

                String sql = "insert into usuario (login,senha) values (?,?)";
                try {
                    pst = con.prepareStatement(sql);

                    //pst.setInt(1, Integer.parseInt(espacoID.getText()));
                    pst.setString(1, espacoLogin.getText());
                    pst.setString(2, espacoSenha.getText());
                    //pst.setString(4, (String) ComboCategoria.getSelectedItem());                    
                    pst.execute();
                    InserirTable();
                    JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!", "Informação", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException error) {
                    JOptionPane.showMessageDialog(null, "Já existe um usuario cadastrado com o mesmo código!", "Erro", JOptionPane.ERROR_MESSAGE);
                    JOptionPane.showMessageDialog(null, error.getMessage());
                }

            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        botaoCadastrar = new javax.swing.JButton();
        espacoPesquisa = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        botaoPesquisar = new javax.swing.JButton();
        espacoID = new javax.swing.JTextField();
        espacoLogin = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        botaoDeletar = new javax.swing.JButton();
        ComboCategoria = new javax.swing.JComboBox();
        espacoSenha = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setPreferredSize(new java.awt.Dimension(700, 400));

        jPanel1.setPreferredSize(new java.awt.Dimension(950, 150));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botaoCadastrar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botaoCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/salvar.png"))); // NOI18N
        botaoCadastrar.setText("Cadastrar");
        botaoCadastrar.setToolTipText("");
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });
        jPanel1.add(botaoCadastrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, 30));

        espacoPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                espacoPesquisaActionPerformed(evt);
            }
        });
        jPanel1.add(espacoPesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 510, 20));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("ID:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Senha:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Login", "Senha"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 640, 171));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Login:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        botaoPesquisar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botaoPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/pesquisar.png"))); // NOI18N
        botaoPesquisar.setText("Pesquisar");
        botaoPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarActionPerformed(evt);
            }
        });
        jPanel1.add(botaoPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, 30));
        jPanel1.add(espacoID, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 230, -1));
        jPanel1.add(espacoLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 230, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Categoria:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, -1, -1));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/editar.png"))); // NOI18N
        jButton1.setText("Alterar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, 100, 30));

        botaoDeletar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botaoDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/excluir.gif"))); // NOI18N
        botaoDeletar.setText("Deletar");
        botaoDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoDeletarActionPerformed(evt);
            }
        });
        jPanel1.add(botaoDeletar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 300, 110, 30));

        ComboCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Comum", "Administrador" }));
        jPanel1.add(ComboCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(416, 6, 230, -1));
        jPanel1.add(espacoSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, 250, -1));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/Cancel_16x16.png"))); // NOI18N
        jButton2.setText("Limpar Campos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 300, -1, 30));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/icons8-manual-do-usuário-24.png"))); // NOI18N
        jButton3.setText("Relatorio");
        jButton3.setMaximumSize(new java.awt.Dimension(147, 25));
        jButton3.setMinimumSize(new java.awt.Dimension(147, 25));
        jButton3.setPreferredSize(new java.awt.Dimension(147, 25));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 300, 130, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
        String sql = "insert into usuario (login,senha) values (?,?)";
        try {
            pst = con.prepareStatement(sql);
            //pst.setInt(1, Integer.parseInt(espacoID.getText()));
            pst.setString(1, espacoLogin.getText());
            pst.setString(2, espacoSenha.getText());
            //pst.setString(4, (String) ComboCategoria.getSelectedItem());                    
            pst.execute();
            InserirTable();
            JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!", "Informação", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Já existe um usuario cadastrado com o mesmo código!", "Erro", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }//GEN-LAST:event_botaoCadastrarActionPerformed

    private void espacoPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_espacoPesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_espacoPesquisaActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            int linha = jTable1.getSelectedRow();            
            espacoLogin.setText(jTable1.getValueAt(linha, 0).toString());
            espacoSenha.setText(jTable1.getValueAt(linha, 1).toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Erro" + e, "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed
        try {
            String nome_prod = espacoPesquisa.getText().toString();
            String sql = "Select * from usuarios where login ILIKE ? ";
            pst = con.prepareStatement(sql);
            pst.setString(1, "%" + nome_prod + "%");
            rs = pst.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            modelo.setRowCount(0);
            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getString("login"), rs.getString("senha")});
            }
            jTable1.setModel(modelo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_botaoPesquisarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String sqlUp = "Update usuario set senha=? where login= ?";
        try {
            pst = con.prepareStatement(sqlUp);            
            pst.setString(1,espacoSenha.getText());                                         
            pst.setString(2, espacoLogin.getText());
            pst.executeUpdate();
            InserirTable();
            JOptionPane.showMessageDialog(null,"Usuario"+espacoLogin.getText()+" alterado com sucesso");
        } 
        catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Assigment Failure: Sem dados selecionados" + e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void botaoDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoDeletarActionPerformed
        String sqlDel = "Delete from usuarios where id=?";
        try {
            pst = con.prepareStatement(sqlDel);
            pst.setInt(1, Integer.parseInt(espacoID.getText()));
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Dados deletados com sucesso");
            InserirTable();
            limpar();
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Assigment Failure: Dados Incompatíveis " + e.getMessage());
        }
    }//GEN-LAST:event_botaoDeletarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        limpar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String sql = "select (id,login,senha,categoria) from usuarios where id=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(espacoID.getText()));
            rs = pst.executeQuery();
            JRResultSetDataSource relat = new JRResultSetDataSource(rs);
            JasperPrint jp = JasperFillManager.fillReport("src\\Relatorio\\Usuarios_3_Individual.jasper", null, relat);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);
            jv.toFront();
        } catch (SQLException | JRException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Erro de Emissão de Relatórios Botao Imprimir: " + ex.getMessage());
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboCategoria;
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoDeletar;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JTextField espacoID;
    private javax.swing.JTextField espacoLogin;
    private javax.swing.JTextField espacoPesquisa;
    private javax.swing.JPasswordField espacoSenha;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
