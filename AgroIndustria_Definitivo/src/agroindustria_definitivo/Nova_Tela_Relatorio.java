/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agroindustria_definitivo;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import java.lang.ClassNotFoundException;
import java.lang.ExceptionInInitializerError;
import java.util.Locale;
import net.sf.jasperreports.engine.JasperReportsContext;
/**
 *
 * @author Pedro Elias
 */
public class Nova_Tela_Relatorio extends javax.swing.JInternalFrame {
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    int muda = 0;
    Float preco_total;
    //Tela_Cadastro jftp;

    public Nova_Tela_Relatorio() {
        initComponents();
        setResizable(false);
        con = ConeBD.ConexaoBD.ConectarBD();
        preencher();
    }

    int codigo;

    public void limpar() {
        data_inicio.setText(" ");
        data_fim.setText(" ");
    }
    public void preencher(){
        data_inicio.setText("01/01/2019");
        data_fim.setText("31/12/2019");
    }
    

    public float preco_venda() {
        try {
            String sql2 = "select sum(preco_total) as preco_total from venda";            
            pst = con.prepareStatement(sql2);
            rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getFloat("preco_total");
            } else {
                JOptionPane.showMessageDialog(rootPane, "Erro de Consulta");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de Preco Venda: "+ex.getMessage());
        }
        return 0;
    }
    
   

    
    public void setPosicao() {
    Dimension d = this.getDesktopPane().getSize();
    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        data_fim = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        data_inicio = new javax.swing.JFormattedTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        botao_cancelar = new javax.swing.JButton();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jLabel4 = new javax.swing.JLabel();
        data_fim1 = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        data_inicio1 = new javax.swing.JFormattedTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        botao_cancelar1 = new javax.swing.JButton();

        setClosable(true);
        setTitle("Relatório de Vendas");
        setPreferredSize(new java.awt.Dimension(380, 135));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Data de início:");

        try {
            data_fim.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Data final:");

        try {
            data_inicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/impressora.png"))); // NOI18N
        jButton2.setText("Imprimir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/Limpar símbolo-16.png"))); // NOI18N
        jButton3.setText("Limpar");

        botao_cancelar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        botao_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/Cancel_16x16.png"))); // NOI18N
        botao_cancelar.setText("Cancelar");
        botao_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_cancelarActionPerformed(evt);
            }
        });

        jInternalFrame1.setClosable(true);
        jInternalFrame1.setTitle("Relatórios");
        jInternalFrame1.setPreferredSize(new java.awt.Dimension(700, 300));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Data de início:");

        try {
            data_fim1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Data final:");

        try {
            data_inicio1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/impressora.png"))); // NOI18N
        jButton4.setText("Imprimir");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/Limpar símbolo-16.png"))); // NOI18N
        jButton5.setText("Limpar");

        botao_cancelar1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        botao_cancelar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/Cancel_16x16.png"))); // NOI18N
        botao_cancelar1.setText("Cancelar");
        botao_cancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_cancelar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(2, 2, 2)
                        .addComponent(data_inicio1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(data_fim1))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(botao_cancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(data_inicio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(data_fim1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4)
                        .addComponent(jButton5))
                    .addComponent(botao_cancelar1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(data_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(data_fim))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botao_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 997, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {data_inicio, jLabel2});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(data_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(data_fim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jButton3))
                    .addComponent(botao_cancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {botao_cancelar, jButton2, jButton3});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String data1 = data_inicio.getText().toString();
        String data2 = data_fim.getText().toString();
        String sql = null;
        java.sql.Date datainicio=null,datafim=null;
        Float total=preco_venda();
        String Preco_total=total.toString();
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date data_inicio_sql = new java.util.Date(format.parse(data1).getTime());
            java.util.Date data_fim_sql = new java.util.Date(format.parse(data2).getTime());
            sql = "select * from venda where competencia between '" + data1 + "' and '" + data2+"'";                        
            System.out.println("select * from venda where competencia between '" + data1 + "' and '" + data2+"'");
        } catch (ParseException ex) {
            System.out.println("Erro Transformação de data: "+ex.getMessage());
        }
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            JRResultSetDataSource relat = new JRResultSetDataSource(rs);
            String arquivo="src\\Relatorio\\Relatorio_3.jasper";
            HashMap parametros = new HashMap<>();
            parametros.put("datainicio",data1);
            parametros.put("datafim", data2);
            parametros.put("total",Preco_total);            
            JasperPrint jasperPrint = JasperFillManager.fillReport(arquivo,parametros,relat);    
            JasperViewer jv = new JasperViewer(jasperPrint, false);
            jv.setVisible(true);
            jv.toFront();            
        } catch (SQLException ex ) {
            JOptionPane.showMessageDialog(null, "Erro de consulta na emissão de relatórios : "+ex.getLocalizedMessage());
        }
        catch(JRException erro){            
            JOptionPane.showMessageDialog(null, "Erro na formatação de relatórios: "+erro.getMessage());
        }
        catch(NullPointerException erro){
            JOptionPane.showMessageDialog(null,"Erro de não passagem de parâmetro : "+erro.getMessage());
        }
        catch(ExceptionInInitializerError e){
            System.err.println("Erro de inicialização:"+e);
        }       
    }//GEN-LAST:event_jButton2ActionPerformed

    private void botao_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_cancelarActionPerformed
        Nova_Tela_Relatorio tela = new Nova_Tela_Relatorio();
        tela.dispose();
    }//GEN-LAST:event_botao_cancelarActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void botao_cancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_cancelar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botao_cancelar1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botao_cancelar;
    private javax.swing.JButton botao_cancelar1;
    private javax.swing.JFormattedTextField data_fim;
    private javax.swing.JFormattedTextField data_fim1;
    private javax.swing.JFormattedTextField data_inicio;
    private javax.swing.JFormattedTextField data_inicio1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
