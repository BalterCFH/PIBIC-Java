/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agroindustria_definitivo;

import java.awt.Dimension;
import java.io.InputStream;
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
public class Tela_Eventos extends javax.swing.JInternalFrame {

    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    float soma_venda;
    int num_venda = 0;
    float quantidade;
    float desconto, valor;
    int num_venda_excluida;

    public Tela_Eventos() {
        initComponents();
        con = ConeBD.ConexaoBD.ConectarBD();        
        inserir_valor_venda();
        recupera_num_venda();
        InserirTable();
        this.PopulaCombo();
    }
    public void PopulaCombo(){
        String sql="select * from produtos_eventos";
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
        String sql = "insert into eventos (quantidade,nome_prod,nome_evento,competencia,valor,num_venda) values (?,?,?,?,?,?)";
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
            String sql = "select * from eventos where num_venda= "+num_venda+"";
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
        String sql = "delete from eventos where id='" + (espacoreferencia.getText().toString()) + "'";
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
        espacoValor.setText(" ");
        espacoNomeEvento.setText(" ");
    }
    public void inserir_valor_venda() {
        String sql = "insert into num_venda_table_evento(num_venda) values (?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, num_venda);
            pst.executeUpdate();            
        } catch (NumberFormatException | SQLException | NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Dados não inseridos : Erro de inserção de numero de venda" + e.getMessage());
        }
    }

    void recupera_num_venda() {
        String sql = "select sum(num_venda) as num_venda from num_venda_table_evento";
        try {
            pst = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            if (rs.first()) {
                num_venda = rs.getInt("num_venda");                
                dispose();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Erro na recuperação de numero de venda");
            }

        } catch (NumberFormatException | SQLException | NullPointerException e) {
            System.out.println("Dados não inseridos : Erro de inserção de numero de venda" + e.getMessage());

        }

    }
public float preco_venda() {
        try {
            String sql2 = "select max(preco_total) as preco_total from preco_venda_evento ";
            pst.setInt(1,num_venda);
            pst = con.prepareStatement(sql2);
            rs = pst.executeQuery();            
            if (rs.first()) {
                return rs.getFloat("preco_total");               
            } else {
                JOptionPane.showMessageDialog(rootPane, "Erro de Consulta");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de Preco Venda");
        }
        return (float) 0.00;
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
        BotaoApaga = new javax.swing.JButton();
        botaoPesquisar = new javax.swing.JButton();
        espacoPesquisa = new javax.swing.JFormattedTextField();
        botaoFinalizar1 = new javax.swing.JButton();
        BotaoApaga1 = new javax.swing.JButton();
        BotaoApaga2 = new javax.swing.JButton();
        BotaoApaga3 = new javax.swing.JButton();
        espacoCompetencia = new javax.swing.JFormattedTextField();
        botaoLocaliza = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        espacoNomeEvento = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        ComboProduto = new javax.swing.JComboBox<>();
        espacoValor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setBackground(java.awt.Color.white);
        setClosable(true);
        setIconifiable(true);
        setTitle("Tela de Vendas");
        setOpaque(true);
        setPreferredSize(new java.awt.Dimension(770, 390));
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

        espacoquantidade.setToolTipText("Digite q quantidade do produto");
        espacoquantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                espacoquantidadeKeyPressed(evt);
            }
        });

        espacoreferencia.setEditable(false);
        espacoreferencia.setBackground(new java.awt.Color(204, 204, 204));
        espacoreferencia.setForeground(new java.awt.Color(204, 204, 204));

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
                "Código", "Quantidade", "Valor", "Nome do Evento", "Nome do Produto", "Competência"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        BotaoApaga.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BotaoApaga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/Cancel_16x16.png"))); // NOI18N
        BotaoApaga.setText("Cancelar Produto");
        BotaoApaga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoApagaActionPerformed(evt);
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

        espacoPesquisa.setText("");
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

        BotaoApaga2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BotaoApaga2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/Limpar símbolo-16.png"))); // NOI18N
        BotaoApaga2.setText("Limpar");
        BotaoApaga2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoApaga2ActionPerformed(evt);
            }
        });

        BotaoApaga3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BotaoApaga3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/impressora.png"))); // NOI18N
        BotaoApaga3.setText("Recibo");
        BotaoApaga3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoApaga3ActionPerformed(evt);
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

        botaoLocaliza.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botaoLocaliza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/icons8-backup-de-dados-24.png"))); // NOI18N
        botaoLocaliza.setText("Localizar Produtos");
        botaoLocaliza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLocalizaActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(espacoNomeEvento);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Nome Evento");

        ComboProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ComboProdutoMouseClicked(evt);
            }
        });
        ComboProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboProdutoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Preço:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(espacoCompetencia, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(espacoreferencia))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(botaoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(espacoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(12, 12, 12)
                                        .addComponent(ComboProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(botaoLocaliza, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3)
                                    .addComponent(espacoValor))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(espacoquantidade)
                                    .addComponent(botaoAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(613, 613, 613))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(botaoFinalizar1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoApaga1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BotaoApaga)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoApaga3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BotaoApaga2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(652, 652, 652))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(espacoCompetencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(espacoreferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(botaoLocaliza)
                    .addComponent(jLabel3)
                    .addComponent(espacoquantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoPesquisar)
                    .addComponent(espacoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoAdicionar)
                    .addComponent(espacoValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaoApaga, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoApaga3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoApaga2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(177, 177, 177))
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
        InserirTable();        
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

    private void BotaoApagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoApagaActionPerformed
        deletarvenda();
    }//GEN-LAST:event_BotaoApagaActionPerformed

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed
        String sql = "select * from eventos where nome_evento = ? ";
        try {
            String nome_evento = espacoPesquisa.getText();
            pst = con.prepareStatement(sql);
            pst.setString(1, "%"+nome_evento+"%");
            pst.executeQuery();
        } catch (SQLException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Erro de Pesquisa" + ex);
        }        
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, espacoPesquisa.getText().toString());
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
    limpar();
       
    }//GEN-LAST:event_botaoPesquisarActionPerformed

    private void BotaoApaga2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoApaga2ActionPerformed
        limpar();
    }//GEN-LAST:event_BotaoApaga2ActionPerformed

    private void BotaoApaga3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoApaga3ActionPerformed
        Splash tela_carregamento = new Splash();
        tela_carregamento.setVisible(true);
        String sql = "select * from eventos where competencia=?";
        //float soma = preco_venda();
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, espacoCompetencia.getText().toString());
            rs = pst.executeQuery();            
            JRResultSetDataSource relat = new JRResultSetDataSource(rs);
            Map<Object, Object> parametros;
            parametros = new HashMap<>();
            //InputStream relatorio = this.getClass().getClassLoader().getResourceAsStream("src\\Relatorio\\Relatorio_3.jasper");
            //parametros.put("total",soma);
            JasperPrint jp = JasperFillManager.fillReport("src\\Relatorio\\Produtos_3_Eventos.jasper", null, relat);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);
            jv.toFront();
        } catch (SQLException | JRException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Erro de Emissão de Relatórios Botao Imprimir: " + ex.getMessage());
        }
    }//GEN-LAST:event_BotaoApaga3ActionPerformed

    private void botaoLocalizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLocalizaActionPerformed
        String item = ComboProduto.getSelectedItem().toString();     
        String sql = "select * from produtos_eventos where nome_prod=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, item);
            rs = pst.executeQuery();
            while (rs.next()) {
                JOptionPane.showMessageDialog(null, "Você selecionou "+item);
                espacoquantidade.setText(rs.getString("quantidade"));
                espacoValor.setText(rs.getString("valor"));                
            }
        } catch (SQLException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Erro na seleção do combo " + ex.getMessage());
        }
    }//GEN-LAST:event_botaoLocalizaActionPerformed

    private void botaoFinalizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFinalizar1ActionPerformed
        String sql = "insert into preco_venda_venda (num_venda,preco_total) values (?,?)";
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

    private void BotaoApaga1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoApaga1ActionPerformed
        String sql = "delete from eventos where num_venda=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, num_venda);
            pst.executeUpdate();
            num_venda_excluida = num_venda;
            num_venda--;
            InserirTable();
            JOptionPane.showMessageDialog(null, "Venda n° " + num_venda_excluida + " excluida com sucesso");
        } catch (SQLException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Venda não excluída:"+ex);
        }
    }//GEN-LAST:event_BotaoApaga1ActionPerformed

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

    private void ComboProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboProdutoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoApaga;
    private javax.swing.JButton BotaoApaga1;
    private javax.swing.JButton BotaoApaga2;
    private javax.swing.JButton BotaoApaga3;
    private javax.swing.JComboBox<String> ComboProduto;
    private javax.swing.JButton botaoAdicionar;
    private javax.swing.JButton botaoFinalizar1;
    private javax.swing.JButton botaoLocaliza;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
