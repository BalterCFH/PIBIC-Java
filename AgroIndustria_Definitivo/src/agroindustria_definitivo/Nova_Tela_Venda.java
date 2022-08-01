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
public class Nova_Tela_Venda extends javax.swing.JInternalFrame {

    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    float soma_venda;
    int num_venda = 0;
    float quantidade;
    float desconto, valor;
    int num_venda_excluida;

    public Nova_Tela_Venda() {
        initComponents();
        con = ConeBD.ConexaoBD.ConectarBD();        
        //inserir_valor_venda();
        recupera_num_venda();
        InserirTable();
        this.PopulaCombo();
    }
    
    public void PopulaCombo(){
        String sql="select * from produtos";
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
    
    public void inserir_venda() {
        String sql = "insert into venda(nome_prod,cpf_contribuinte,quantidade,valor,competencia,num_venda,preco_total) values (?,?,?,?,?,?,?)";
        String data1;
        Date data_formatada = null;
        try {
            pst = con.prepareStatement(sql);            
            pst.setString(1, (String) ComboProduto.getSelectedItem());
            pst.setString(2, espacoContribuinte.getText().toString());
            pst.setInt(3, Integer.parseInt(espacoquantidade.getText().toString()));
            pst.setFloat(4, Float.parseFloat(espacovalor.getText()));            
            data1 = espacoCompetencia.getText().toString();
            pst.setString(5, data1);
            pst.setInt(6, num_venda);
            pst.setFloat(7, Float.parseFloat(espaco_precototal.getText()));
            pst.executeUpdate();
            InserirTable();
            JOptionPane.showMessageDialog(null, "Venda Cadastrada ", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            espaco_precototal.setText(String.valueOf(soma_venda));
        } catch (NumberFormatException | SQLException | NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Dados não inseridos : Erro de Banco" + e.getMessage());
        }
    }

    final void InserirTable() {
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(3);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(40);

        try {
            String sql = "select * from venda where num_venda= " + num_venda + "";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            modelo.setNumRows(0);
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("num_referencia"),
                    rs.getString("quantidade"),
                    rs.getString("cpf_contribuinte"),
                    rs.getString("valor"),
                    rs.getString("competencia"),
                    rs.getString("num_venda")
                });
            }
            jTable1.setModel(modelo);
        } catch (SQLException | NullPointerException e) {
            System.out.println("asdfads " + e.getMessage());
        }
    }

    public void deletarvenda() {
        String sql = "delete from venda where nome_prod='" + (ComboProduto.getSelectedItem().toString()) + "'";
        try {
            pst = con.prepareStatement(sql);
            pst.execute();
            InserirTable();
            JOptionPane.showMessageDialog(null, "Venda excluido com sucesso!", "Informação", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException | NullPointerException error) {
            JOptionPane.showMessageDialog(null, "A venda não pode ser excluida!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    void finalizarvenda() {
        try {
            String sql = "delete from venda";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            JOptionPane.showMessageDialog(null, rs, "Compra Finalizada", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao finalizar venda" + e.getMessage(), "Aviso", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void limpar() {
        espacoContribuinte.setText(" ");
        espaco_precototal.setText(" ");
        espacoquantidade.setText(" ");
        espacoreferencia.setText(" ");
        espacovalor.setText(" ");
        espacoPesquisa.setText(" ");
        espacoCompetencia.setText(" ");
    }

    public void somarvenda() {
        String text;
        valor = Float.parseFloat(espacovalor.getText());
        quantidade = Float.parseFloat(espacoquantidade.getText());
        soma_venda = (valor * quantidade);
        text = String.valueOf(soma_venda);
        espaco_precototal.setText(text);
    }

    public void inserir_valor_venda() {
        String sql = "insert into num_venda_table(num_venda) values (?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, num_venda);
            pst.executeUpdate();
            espaco_precototal.setText(String.valueOf(soma_venda));
        } catch (NumberFormatException | SQLException | NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Dados não inseridos : Erro de inserção de numero de venda" + e.getMessage());
        }
    }

    void recupera_num_venda() {
        String sql = "select max(num_venda) as num_venda from num_venda_table";
        try {
            pst = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY,ResultSet.TYPE_FORWARD_ONLY);
            rs = pst.executeQuery();
            if (rs.first()) {
                num_venda = rs.getInt("num_venda");                
                espaco_precototal.setText(String.valueOf(soma_venda));
                InserirTable();
                dispose();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Erro na recuperação de numero de venda");
            }

        } catch (NumberFormatException | SQLException | NullPointerException e) {
            System.out.println("Dados não inseridos : Erro de inserção de numero de venda" + e.getMessage());

        }

    }

    public String preco_venda() {
        try {
            String sql2 = " select sum(preco_total) as preco_total from venda where num_venda = ? ";
            pst.setInt(1, num_venda);
            pst = con.prepareStatement(sql2);
            rs = pst.executeQuery();
            if (rs.first()) {
                System.out.println(rs.getString("preco_total"));
                return rs.getString("preco_total");
            }
            if(rs.equals(null)){
                JOptionPane.showMessageDialog(rootPane, "Sem vendas cadastradas");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro na seleção do preco Venda: "+ex.getMessage());
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
        BotaoApaga = new javax.swing.JButton();
        botaoPesquisar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        espacovalor = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        espaco_precototal = new javax.swing.JTextField();
        ComboProduto = new javax.swing.JComboBox<String>();
        espacoPesquisa = new javax.swing.JFormattedTextField();
        botaoFinalizar1 = new javax.swing.JButton();
        espacoContribuinte = new javax.swing.JFormattedTextField();
        BotaoApaga1 = new javax.swing.JButton();
        BotaoApaga2 = new javax.swing.JButton();
        BotaoRecibo = new javax.swing.JButton();
        espacoCompetencia = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();
        BotaoCalcular = new javax.swing.JButton();

        setBackground(java.awt.Color.white);
        setClosable(true);
        setIconifiable(true);
        setTitle("Tela de Vendas");
        setOpaque(true);
        setPreferredSize(new java.awt.Dimension(950, 390));
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

        espacoquantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                espacoquantidadeKeyPressed(evt);
            }
        });

        espacoreferencia.setEnabled(false);

        botaoAdicionar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Codigo Venda", "Produto", "CPF", "Valor", "Competência"
            }
        ));
        jTable1.setPreferredSize(new java.awt.Dimension(340, 96));
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

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("CPF do Contribuinte:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Valor: ");

        jLabel15.setText("Total Geral:");

        espaco_precototal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                espaco_precototalActionPerformed(evt);
            }
        });

        ComboProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ComboProdutoMouseClicked(evt);
            }
        });

        try {
            espacoPesquisa.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        botaoFinalizar1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botaoFinalizar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/salvar.png"))); // NOI18N
        botaoFinalizar1.setText("Finalizar venda");
        botaoFinalizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFinalizar1ActionPerformed(evt);
            }
        });

        try {
            espacoContribuinte.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

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

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/icons8-backup-de-dados-24.png"))); // NOI18N
        jButton1.setText("Localizar Produtos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        BotaoCalcular.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BotaoCalcular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens_bernan/money_add.png"))); // NOI18N
        BotaoCalcular.setText("Calcular");
        BotaoCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoCalcularActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(botaoFinalizar1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoApaga1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoApaga)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotaoApaga2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(espaco_precototal))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(botaoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(espacoPesquisa))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(espacoContribuinte, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ComboProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(espacoCompetencia, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(espacoreferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botaoAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(espacoquantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BotaoCalcular)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(espacovalor, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(607, 607, 607))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(espacoContribuinte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(espacoCompetencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(espacoreferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jLabel3)
                        .addComponent(espacoquantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(espacovalor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BotaoCalcular))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(ComboProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botaoPesquisar)
                            .addComponent(espacoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotaoApaga, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoApaga2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoFinalizar1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoApaga1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(espaco_precototal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 956, Short.MAX_VALUE)
                .addGap(54, 54, 54))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void espacoquantidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_espacoquantidadeKeyPressed
        
    }//GEN-LAST:event_espacoquantidadeKeyPressed

    private void botaoAdicionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoAdicionarMouseClicked

    }//GEN-LAST:event_botaoAdicionarMouseClicked

    private void botaoAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAdicionarActionPerformed
        try {
            inserir_venda();
            somarvenda();
            float total, quantidade, valor;
            String text;
            quantidade = Float.parseFloat(espacoquantidade.getText());
            valor = Float.parseFloat(espacovalor.getText());
            text = String.valueOf((valor * quantidade));
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Erro de Adição" + ex.getMessage());
        }
        limpar();
    }//GEN-LAST:event_botaoAdicionarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int linha = jTable1.getSelectedRow();
        espacoreferencia.setText(jTable1.getValueAt(linha, 1).toString());
        espacoquantidade.setText(jTable1.getValueAt(linha, 2).toString());
        espacovalor.setText(jTable1.getValueAt(linha, 3).toString());

    }//GEN-LAST:event_jTable1MouseClicked

    private void BotaoApagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoApagaActionPerformed
        deletarvenda();
        limpar();
    }//GEN-LAST:event_BotaoApagaActionPerformed

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed
        String sql = "select * from venda where cpf_contribuinte = ?";
        String cpf = espacoPesquisa.getText().toString();
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,"%"+cpf+"%");
            rs = pst.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            modelo.setRowCount(0);
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("num_referencia"),
                    rs.getString("quantidade"),
                    rs.getString("cpf_contribuinte"),
                    rs.getString("valor"),
                    rs.getString("competencia"),
                    rs.getString("num_venda")});
            }
            jTable1.setModel(modelo);
        } catch (SQLException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Erro de Pesquisa" + ex);
        }
    limpar();
    }//GEN-LAST:event_botaoPesquisarActionPerformed

    private void espaco_precototalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_espaco_precototalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_espaco_precototalActionPerformed

    private void botaoFinalizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFinalizar1ActionPerformed
        String sql = "insert into preco_venda(num_venda,preco_total) values (?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, num_venda);
            pst.setFloat(2, soma_venda);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Venda Finalizada e Armazenada ", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            espaco_precototal.setText(String.valueOf(soma_venda));
        } catch (NumberFormatException | SQLException | NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Dados não inseridos : Erro de Banco" + e.getMessage());
        }
        JOptionPane.showMessageDialog(null,"Venda Número "+(num_venda+1)+" finalizada");
        num_venda++;
        soma_venda = 0;
        inserir_valor_venda();
        InserirTable();
        try {
            limpar();
        } catch (Exception e) {

        }
        limpar();
    }//GEN-LAST:event_botaoFinalizar1ActionPerformed

    private void BotaoApaga1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoApaga1ActionPerformed
        if(num_venda<0){
        String sql = "delete from venda where num_venda=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, num_venda);
            pst.executeUpdate();
            num_venda_excluida = num_venda;
            num_venda--;                                        
            JOptionPane.showMessageDialog(null, "Venda n°" + num_venda_excluida + "excluida com sucesso");
            InserirTable();
        } catch (SQLException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Erro:"+ex.getMessage());
        }
        }else{
            JOptionPane.showMessageDialog(null,"Número de Venas igual a zero Não é mais impossível excluir");
        }
        limpar();
    }//GEN-LAST:event_BotaoApaga1ActionPerformed

    private void BotaoApaga2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoApaga2ActionPerformed
        limpar();
    }//GEN-LAST:event_BotaoApaga2ActionPerformed

    private void BotaoReciboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoReciboActionPerformed
        Splash tela_carregamento = new Splash();
        tela_carregamento.setVisible(true);
        String sql = "select * from venda where competencia=?";
        //String soma = preco_venda();
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, espacoCompetencia.getText().toString());
            rs = pst.executeQuery();
            JRResultSetDataSource relat = new JRResultSetDataSource(rs);
            Map<Object, Object> parametros;
            parametros = new HashMap<>();
            InputStream relatorio = this.getClass().getClassLoader().getResourceAsStream("src\\Relatorio\\Relatorio_3_Individual.jasper");
            //parametros.put("total", soma);
            JasperPrint jp = JasperFillManager.fillReport("src\\Relatorio\\Relatorio_3_Individual.jasper", null, relat);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);
            jv.toFront();
        } catch (SQLException | JRException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Erro de Emissão de Relatórios Botao Imprimir: " + ex.getMessage());
        }
        limpar();
    }//GEN-LAST:event_BotaoReciboActionPerformed

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
                espacovalor.setText(rs.getString("valor"));
                float valor = rs.getFloat("valor");
                int quantidade = rs.getInt("quantidade");
                float preco = valor * quantidade;
                espaco_precototal.setText(String.valueOf(preco));
            }
        } catch (SQLException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Erro na seleção do combo" + ex.getMessage());
        }
        limpar();
    }//GEN-LAST:event_ComboProdutoMouseClicked

    private void espacoCompetenciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_espacoCompetenciaKeyPressed
    }//GEN-LAST:event_espacoCompetenciaKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String item = ComboProduto.getSelectedItem().toString();
        String sql = "select * from produtos where nome_prod=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, item);
            rs = pst.executeQuery();
            while (rs.next()) {
                JOptionPane.showMessageDialog(null, "Você selecionou" + item);
                //espacoreferencia.setText(rs.getString("num_referencia"));
                espacoquantidade.setText(rs.getString("quantidade"));
                espacovalor.setText(rs.getString("valor"));
                float valor = rs.getFloat("valor");
                int quantidade = rs.getInt("quantidade");
                float preco = valor * quantidade;
                espaco_precototal.setText(String.valueOf(preco));
            }
        } catch (SQLException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Erro na seleção do combo" + ex.getMessage());
        }        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void BotaoCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoCalcularActionPerformed
        float valor = Float.parseFloat(espacovalor.getText());
        int quantidade = Integer.parseInt(espacoquantidade.getText());
        float preco = valor * quantidade;
        espaco_precototal.setText(String.valueOf(preco));
    }//GEN-LAST:event_BotaoCalcularActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoApaga;
    private javax.swing.JButton BotaoApaga1;
    private javax.swing.JButton BotaoApaga2;
    private javax.swing.JButton BotaoCalcular;
    private javax.swing.JButton BotaoRecibo;
    private javax.swing.JComboBox<String> ComboProduto;
    private javax.swing.JButton botaoAdicionar;
    private javax.swing.JButton botaoFinalizar1;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JFormattedTextField espacoCompetencia;
    private javax.swing.JFormattedTextField espacoContribuinte;
    private javax.swing.JFormattedTextField espacoPesquisa;
    private javax.swing.JTextField espaco_precototal;
    private javax.swing.JTextField espacoquantidade;
    private javax.swing.JTextField espacoreferencia;
    private javax.swing.JTextField espacovalor;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
