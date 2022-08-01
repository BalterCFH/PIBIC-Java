/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agroindustria_definitivo;

import java.awt.Dimension;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aluno
 */
public class Tela_RU extends javax.swing.JInternalFrame {

    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    float soma_venda;
    int num_venda = 0;
    float quantidade;
    float desconto, valor;
    int num_venda_excluida;

    public Tela_RU() {
        initComponents();
        con = ConeBD.ConexaoBD.ConectarBD();
        InserirTable();
        inserir_valor_venda();
        this.PopulaCombo();
    }
    public void PopulaCombo(){
        String sql="select * from produtos_ru";
        try{            
            pst= con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                ComboProduto.addItem(rs.getString("nome_prod"));
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Erro na população do Combo: "+ex);
        }
    }

    public void inserir_venda()  {        
        String sql = "insert into ru (quantidade,nome_prod,nome_evento,competencia,valor,num_venda) values (?,?,?,?,?,?)";
        String data1;
        Date data_formatada = null;
        try {
            pst = con.prepareStatement(sql);                            
            pst.setInt(1, Integer.parseInt(espacoquantidade.getText()));            
            pst.setString(2,ComboProduto.getSelectedItem().toString());
            pst.setString(3,espacoNomeEvento.getText().toString());
            pst.setString(4,espacoCompetencia.getText().toString());
            pst.setFloat(5,Float.parseFloat(espacoValor.getText()));
            pst.setInt(6, num_venda);
            pst.executeUpdate();
            InserirTable();
            JOptionPane.showMessageDialog(null, "Venda Cadastrada ", "Aviso", JOptionPane.INFORMATION_MESSAGE);            
        } catch (NumberFormatException | SQLException | NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Dados não inseridos : Erro de Banco" + e.getMessage());
        }
    }

    final void InserirTable() {
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(3);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(40);

        try {
            String sql = "select * from ru where num_venda="+num_venda+"";            
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            modelo.setNumRows(0);
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("id"),
                    rs.getString("quantidade"),
                    rs.getString("valor"),
                    rs.getString("nome_evento"),
                    rs.getString("nome_prod"),
                    rs.getString("competencia")                    
                });
            }
            jTable1.setModel(modelo);
        } catch (SQLException | NullPointerException e) {
            System.out.println("asdfads " + e.getMessage());
        }
    }

    public void deletarvenda() {
        String sql = "delete from ru where id=" + (espacoreferencia.getText().toString());
        try {
            pst = con.prepareStatement(sql);
            pst.execute();
            InserirTable();
            JOptionPane.showMessageDialog(null, "Venda excluido com sucesso!", "Informação", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException | NullPointerException error ) {
            JOptionPane.showMessageDialog(null, "A venda não pode ser excluida!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void limpar(){                
        espacoquantidade.setText(" ");
        espacoreferencia.setText(" ");        
        espacoPesquisa.setText(" ");
        espacoCompetencia.setText("//");
        espacoNomeEvento.setText(" ");
        espacoValor.setText(" ");
    }
    public void somarvenda() {
        String text;        
        quantidade = Float.parseFloat(espacoquantidade.getText());
        soma_venda += (valor * quantidade) - desconto;
        text = String.valueOf(soma_venda);        
    }

    public void inserir_valor_venda() {
        String sql = "insert into num_venda_table_ru(num_venda) values (?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, num_venda);
            pst.executeUpdate();            
        } catch (NumberFormatException | SQLException | NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Dados não inseridos : Erro de inserção de numero de venda" + e.getMessage());
        }
    }
public String preco_venda() {
        try {
            String sql2 = "select sum(preco_total_ru) as preco_total from preco_venda_ru";
            pst.setInt(1, num_venda);
            pst = con.prepareStatement(sql2);
            rs = pst.executeQuery();
            if (rs.first()) {
                return rs.getString("preco_total");
            } else {
                JOptionPane.showMessageDialog(rootPane, "Erro de Consulta");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de Preco Venda"+ex.getMessage());
        }
        return null;
    }
    public void setPosicao() {
    Dimension d = this.getDesktopPane().getSize();
    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        espacoquantidade = new javax.swing.JTextField();
        espacoreferencia = new javax.swing.JTextField();
        botaoAdicionar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        BotaoCalcelar = new javax.swing.JButton();
        botaoPesquisar = new javax.swing.JButton();
        espacoPesquisa = new javax.swing.JFormattedTextField();
        botaoFinalizar1 = new javax.swing.JButton();
        BotaoApaga1 = new javax.swing.JButton();
        BotaoLimpar = new javax.swing.JButton();
        BotaoRecibo = new javax.swing.JButton();
        espacoCompetencia = new javax.swing.JFormattedTextField();
        BotaoLocalizar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        espacoNomeEvento = new javax.swing.JTextPane();
        ComboProduto = new javax.swing.JComboBox<>();
        espacoValor = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setBackground(java.awt.Color.white);
        setClosable(true);
        setIconifiable(true);
        setTitle("Tela de R.U");
        setOpaque(true);
        setPreferredSize(new java.awt.Dimension(900, 390));
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.setBorder(new javax.swing.border.LineBorder(java.awt.Color.gray, 1, true));
        jPanel1.setToolTipText("VENDA");
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Quantidade:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Num.Referência:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Nome do produto:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Competência:");

        espacoquantidade.setToolTipText("Digite a quantidade do produto");
        espacoquantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                espacoquantidadeKeyPressed(evt);
            }
        });

        espacoreferencia.setEnabled(false);
        espacoreferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                espacoreferenciaActionPerformed(evt);
            }
        });

        botaoAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/editar.png"))); // NOI18N
        botaoAdicionar.setText("Adicionar");
        botaoAdicionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botaoAdicionarMouseClicked(evt);
            }
        });
        botaoAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAdicionarActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Venda", "Quantidade", "Valor", "Nome do Evento", "Nome do Produto", "Competência"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        BotaoCalcelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BotaoCalcelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/Cancel_16x16.png"))); // NOI18N
        BotaoCalcelar.setText("Cancelar Produto");
        BotaoCalcelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoCalcelarActionPerformed(evt);
            }
        });

        botaoPesquisar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botaoPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/pesquisar.png"))); // NOI18N
        botaoPesquisar.setText("Pesquisar Vendas");
        botaoPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarActionPerformed(evt);
            }
        });

        espacoPesquisa.setToolTipText("Nome do evento");

        botaoFinalizar1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botaoFinalizar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/salvar.png"))); // NOI18N
        botaoFinalizar1.setText("Finalizar venda");
        botaoFinalizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFinalizar1ActionPerformed(evt);
            }
        });

        BotaoApaga1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BotaoApaga1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/excluir.gif"))); // NOI18N
        BotaoApaga1.setText("Apagar Venda");
        BotaoApaga1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoApaga1ActionPerformed(evt);
            }
        });

        BotaoLimpar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BotaoLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/Limpar símbolo-16.png"))); // NOI18N
        BotaoLimpar.setText("Limpar");
        BotaoLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoLimparActionPerformed(evt);
            }
        });

        BotaoRecibo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BotaoRecibo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/impressora.png"))); // NOI18N
        BotaoRecibo.setText("Recibo");
        BotaoRecibo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoReciboActionPerformed(evt);
            }
        });

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

        BotaoLocalizar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BotaoLocalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/icons8-backup-de-dados-24.png"))); // NOI18N
        BotaoLocalizar.setText("Localizar Produtos");
        BotaoLocalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoLocalizarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Nome Evento:");

        jScrollPane3.setViewportView(espacoNomeEvento);

        ComboProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ComboProdutoMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Preço:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botaoFinalizar1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoApaga1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BotaoCalcelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BotaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(650, 650, 650))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(botaoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(espacoPesquisa))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(ComboProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(BotaoLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(espacoValor, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(155, 155, 155)))
                        .addGap(487, 487, 487))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(345, 345, 345)
                                .addComponent(espacoquantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botaoAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(espacoCompetencia, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(espacoreferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(483, 483, 483))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(espacoreferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(espacoCompetencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botaoAdicionar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(BotaoLocalizar)
                        .addComponent(jLabel3)
                        .addComponent(espacoquantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ComboProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoPesquisar)
                    .addComponent(espacoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(espacoValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaoCalcelar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoFinalizar1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoApaga1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 924, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void espacoquantidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_espacoquantidadeKeyPressed
                
    }//GEN-LAST:event_espacoquantidadeKeyPressed

    private void botaoAdicionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoAdicionarMouseClicked

    }//GEN-LAST:event_botaoAdicionarMouseClicked

    private void botaoAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAdicionarActionPerformed
        try{
        inserir_venda();
        somarvenda();
        float total, quantidade, valor;
        String text;
        quantidade = Float.parseFloat(espacoquantidade.getText());                
        }catch(NullPointerException ex){
            JOptionPane.showMessageDialog(null,"Erro de Adição"+ex.getMessage());
        }        
    }//GEN-LAST:event_botaoAdicionarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int linha = jTable1.getSelectedRow();        
        espacoquantidade.setText(jTable1.getValueAt(linha, 1).toString());
        espacoValor.setText(jTable1.getValueAt(linha, 2).toString());
        espacoNomeEvento.setText(jTable1.getValueAt(linha, 3).toString());
        espacoCompetencia.setText(jTable1.getValueAt(linha, 5).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void BotaoCalcelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoCalcelarActionPerformed
        deletarvenda();
    }//GEN-LAST:event_BotaoCalcelarActionPerformed

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed
        String sql = "select * from ru where nome_evento = ?";
        String nome_evento = espacoPesquisa.getText().toString();
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,"%"+nome_evento+"%");
            rs = pst.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            modelo.setRowCount(0);
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("id"),
                    rs.getString("quantidade"),
                    rs.getString("valor"),
                    rs.getString("nome_evento"),
                    rs.getString("nome_prod"),
                    rs.getString("competencia")});
            }
            jTable1.setModel(modelo);
        } catch (SQLException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Erro de Pesquisa" + ex);
        }       
    }//GEN-LAST:event_botaoPesquisarActionPerformed

    private void BotaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoLimparActionPerformed
        limpar();
    }//GEN-LAST:event_BotaoLimparActionPerformed

    private void BotaoReciboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoReciboActionPerformed
        Splash tela_carregamento = new Splash();
        tela_carregamento.setVisible(true);
        String sql = "select * from ru where id=?";
        //String soma=preco_venda();
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1,num_venda);
            rs = pst.executeQuery();            
            JRResultSetDataSource relat = new JRResultSetDataSource(rs);
            Map<Object, Object> parametros;
            parametros = new HashMap<>();            
            JasperPrint jp = JasperFillManager.fillReport("src\\Relatorio\\Produtos_3_RU.jasper", null, relat);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);
            jv.toFront();
        } catch (SQLException | JRException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Erro de Emissão de Relatórios Botao Imprimir: " + ex.getMessage());
        }
    }//GEN-LAST:event_BotaoReciboActionPerformed

    private void BotaoLocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoLocalizarActionPerformed
        String item = ComboProduto.getSelectedItem().toString();     
        String sql = "select * from produtos_ru where nome_prod=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, item);
            rs = pst.executeQuery();
            while (rs.next()) {
                JOptionPane.showMessageDialog(null, "Você selecionou: "+item);
                //espacoreferencia.setText(rs.getString("num_referencia"));
                espacoquantidade.setText(rs.getString("quantidade"));                
                espacoValor.setText(rs.getString("valor"));  
            }
        } catch (SQLException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Erro na seleção do combo " + ex.getMessage());
        }
    }//GEN-LAST:event_BotaoLocalizarActionPerformed

    private void BotaoApaga1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoApaga1ActionPerformed
        String sql = "delete from ru where id=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, num_venda);
            pst.executeUpdate();
            num_venda_excluida = num_venda;
            num_venda--;
            JOptionPane.showMessageDialog(null, "Venda n°" + num_venda_excluida + " excluida com sucesso");
            InserirTable();
        } catch (SQLException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Venda não excluida :"+ex);
        }
    }//GEN-LAST:event_BotaoApaga1ActionPerformed

    private void botaoFinalizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFinalizar1ActionPerformed
        String sql = "insert into preco_venda_ru(num_venda,preco_total) values (?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, num_venda);
            pst.setFloat(2, soma_venda);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Venda Finalizada e Armazenada ", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException | SQLException | NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Dados não inseridos : Erro de Banco" + e.getMessage());
        }
        num_venda++;
        soma_venda = 0;
        inserir_valor_venda();
        InserirTable();
        try{
            limpar();
        }catch(Exception e){

        }
    }//GEN-LAST:event_botaoFinalizar1ActionPerformed

    private void espacoCompetenciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_espacoCompetenciaKeyPressed

    }//GEN-LAST:event_espacoCompetenciaKeyPressed

    private void ComboProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ComboProdutoMouseClicked
        String item = ComboProduto.getSelectedItem().toString();

        String sql = "select * from produtos where nome_prod=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, item);
            rs = pst.executeQuery();
            while (rs.next()) {
                JOptionPane.showMessageDialog(null, item);
                //espacoreferencia.setText(rs.getString("num_referencia"));
                espacoquantidade.setText(rs.getString("quantidade"));
                float valor = rs.getFloat("valor");
                int quantidade = rs.getInt("quantidade");
                float preco = valor * quantidade;
            }
        } catch (SQLException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Erro na seleção do combo" + ex.getMessage());
        }
    }//GEN-LAST:event_ComboProdutoMouseClicked

    private void espacoreferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_espacoreferenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_espacoreferenciaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoApaga1;
    private javax.swing.JButton BotaoCalcelar;
    private javax.swing.JButton BotaoLimpar;
    private javax.swing.JButton BotaoLocalizar;
    private javax.swing.JButton BotaoRecibo;
    private javax.swing.JComboBox<String> ComboProduto;
    private javax.swing.JButton botaoAdicionar;
    private javax.swing.JButton botaoFinalizar1;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JFormattedTextField espacoCompetencia;
    private javax.swing.JTextPane espacoNomeEvento;
    private javax.swing.JFormattedTextField espacoPesquisa;
    private javax.swing.JTextField espacoValor;
    private javax.swing.JTextField espacoquantidade;
    private javax.swing.JTextField espacoreferencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
