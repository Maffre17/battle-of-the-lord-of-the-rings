package org.example.view;

import org.example.controller.Batalha;
import org.example.model.Personagem;
import org.example.model.bestas.Orque;
import org.example.model.bestas.Troll;
import org.example.model.herois.Elfo;
import org.example.model.herois.Hobbit;
import org.example.model.herois.Humanos;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
// CLASSE BATALHAGUI
/**
 * Interface gráfica (GUI) para criar e gerenciar uma batalha entre heróis e bestas.
 * Permite adicionar personagens aos exércitos, organizar a ordem de combate e executar a batalha.
 */
public class BatalhaGUI {
    // Componentes da GUI
    private JPanel listaHeroisLuta;
    private JPanel listaBestasLuta;
    private JFrame janela;
    private JPanel herois;
    private JPanel bestas;
    private JPanel luta;
    private JTextField campoNomeHerois;
    private JTextField campoNomeBestas;
    private JFormattedTextField campoVidaHerois;
    private JFormattedTextField campoVidaBestas;
    private JFormattedTextField campoArmaduraHerois;
    private JFormattedTextField campoArmaduraBestas;
    private JComboBox<String> tipoHerois;
    private JComboBox<String> tipoBestas;
    private DefaultListModel<String> listaHeroisModel;
    private DefaultListModel<String> listaBestasModel;
    private JList<String> listaHerois;
    private JList<String> listaBestas;
    private JTextArea areaBatalha;

    // CONSTRUTOR
    /**
     * Configura a janela, campos de entrada, listas e botões para a interação do usuário.
     */
    public BatalhaGUI() {
        //Formatador para vida
        NumberFormat format = NumberFormat.getIntegerInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setAllowsInvalid(false);

        // Formatador para armadura (máx. 80)
        NumberFormatter armaduraFormatter = new NumberFormatter(format);
        armaduraFormatter.setValueClass(Integer.class);
        armaduraFormatter.setMinimum(0);
        armaduraFormatter.setMaximum(80);   // <<< AQUI O LIMITE
        armaduraFormatter.setAllowsInvalid(false);

        // Configuração da janela principal
        janela = new JFrame("Batalha");
        janela.setSize(700, 800);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(new BorderLayout());

        // -------------- PAINEL TOPO: Adição de herois e bestas ----------------------
        JPanel painelTopo = new JPanel(new GridLayout(1, 2, 10, 10));

        // PAINEL HEROIS
        herois = new JPanel(new GridLayout(5, 2, 5, 5));
        herois.setBorder(BorderFactory.createTitledBorder("Exército de Heróis"));

        campoNomeHerois = new JTextField();
        tipoHerois = new JComboBox<>(new String[]{"Elfo", "Hobbit", "Humano"});
        campoVidaHerois = new JFormattedTextField(formatter);
        campoArmaduraHerois = new JFormattedTextField(armaduraFormatter);

        JButton botaoAdicionarHeroi = new JButton("Adicionar");
        botaoAdicionarHeroi.addActionListener(e -> {
            String nome = campoNomeHerois.getText();
            String tipoSelecionado = (String) tipoHerois.getSelectedItem();
            Object valorVida = campoVidaHerois.getValue();
            Object valorArmadura = campoArmaduraHerois.getValue();

            if (nome.isEmpty() || valorVida == null) {
                JOptionPane.showMessageDialog(janela, "Preencha todos os campos!");
                return;
            }

            int vidaHeroi = ((Number) valorVida).intValue();
            int armaduraHeroi = ((Number) valorArmadura).intValue();
            listaHeroisModel.addElement(nome + " - " + tipoSelecionado + " (" + vidaHeroi + ", " + armaduraHeroi + ")");
        });
        // Adiciona campos ao painel
        herois.add(new JLabel("Nome: "));
        herois.add(campoNomeHerois);
        herois.add(new JLabel("Tipo: "));
        herois.add(tipoHerois);
        herois.add(new JLabel("Vida: "));
        herois.add(campoVidaHerois);
        herois.add(new JLabel("Armadura: "));
        herois.add(campoArmaduraHerois);
        herois.add(new JLabel(""));
        herois.add(botaoAdicionarHeroi);
        painelTopo.add(herois);

        // PAINEL BESTAS
        bestas = new JPanel(new GridLayout(5, 2, 5, 5));
        bestas.setBorder(BorderFactory.createTitledBorder("Exército de Bestas"));

        campoNomeBestas = new JTextField(20);
        tipoBestas = new JComboBox<>(new String[]{"Troll", "Orque"});
        campoVidaBestas = new JFormattedTextField(formatter);
        campoArmaduraBestas = new JFormattedTextField(armaduraFormatter);

        JButton botaoAdicionarBesta = new JButton("Adicionar");
        botaoAdicionarBesta.addActionListener(e -> {
            String nome = campoNomeBestas.getText();
            String tipoSelecionado = (String) tipoBestas.getSelectedItem();
            Object valorVida = campoVidaBestas.getValue();
            Object valorArmadura = campoArmaduraBestas.getValue();

            if (nome.isEmpty() || valorVida == null) {
                JOptionPane.showMessageDialog(janela, "Preencha todos os campos!");
                return;
            }

            int vidaBesta = ((Number) valorVida).intValue();
            int armaduraBesta = ((Number) valorArmadura).intValue();
            listaBestasModel.addElement(nome + " - " + tipoSelecionado + " (" + vidaBesta + ", " + armaduraBesta + ")");
        });
        // Adiciona campos ao painel
        bestas.add(new JLabel("Nome:"));
        bestas.add(campoNomeBestas);
        bestas.add(new JLabel("Tipo:"));
        bestas.add(tipoBestas);
        bestas.add(new JLabel("Vida:"));
        bestas.add(campoVidaBestas);
        bestas.add(new JLabel("Armadura:"));
        bestas.add(campoArmaduraBestas);
        bestas.add(new JLabel(""));
        bestas.add(botaoAdicionarBesta);
        painelTopo.add(bestas);

        janela.add(painelTopo, BorderLayout.NORTH);

        // ---------------- PAINEL CENTRAL: Listas e botões ----------------
        luta = new JPanel(new BorderLayout());
        luta.setBorder(BorderFactory.createTitledBorder("Luta"));

        // --- Listas de Heróis e Bestas com botões de manipulação ---
        listaHeroisLuta = new JPanel();
        listaHeroisLuta.setLayout(new BoxLayout(listaHeroisLuta, BoxLayout.Y_AXIS));
        JLabel labelListaHerois = new JLabel("Heróis: ");
        listaHeroisModel = new DefaultListModel<>();
        listaHerois = new JList<>(listaHeroisModel);
        JScrollPane scrollHerois = new JScrollPane(listaHerois);
        scrollHerois.setPreferredSize(new Dimension(250, 200));
        scrollHerois.setMaximumSize(new Dimension(250, 200));

        JPanel botoesHerois = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        Dimension tamanhoBotao = new Dimension(80, 25);
        JButton botaoSubirHeroi = new JButton("Subir");
        botaoSubirHeroi.setPreferredSize(tamanhoBotao);
        botaoSubirHeroi.addActionListener(e -> moverItem(listaHerois, listaHeroisModel, -1));
        JButton botaoBaixarHeroi = new JButton("Baixar");
        botaoBaixarHeroi.setPreferredSize(tamanhoBotao);
        botaoBaixarHeroi.addActionListener(e -> moverItem(listaHerois, listaHeroisModel, 1));
        JButton botaoEliminarHeroi = new JButton("Eliminar");
        botaoEliminarHeroi.setPreferredSize(tamanhoBotao);
        botaoEliminarHeroi.addActionListener(e -> removerItem(listaHerois, listaHeroisModel));

        botoesHerois.add(botaoSubirHeroi);
        botoesHerois.add(botaoBaixarHeroi);
        botoesHerois.add(botaoEliminarHeroi);

        listaHeroisLuta.add(labelListaHerois);
        listaHeroisLuta.add(scrollHerois);
        listaHeroisLuta.add(botoesHerois);

        // Lista Bestas
        listaBestasLuta = new JPanel();
        listaBestasLuta.setLayout(new BoxLayout(listaBestasLuta, BoxLayout.Y_AXIS));
        JLabel labelListaBestas = new JLabel("Bestas: ");
        listaBestasModel = new DefaultListModel<>();
        listaBestas = new JList<>(listaBestasModel);
        JScrollPane scrollBestas = new JScrollPane(listaBestas);
        scrollBestas.setPreferredSize(new Dimension(250, 200));
        scrollBestas.setMaximumSize(new Dimension(250, 200));

        JPanel botoesBesta = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        JButton botaoSubirBesta = new JButton("Subir");
        botaoSubirBesta.setPreferredSize(tamanhoBotao);
        botaoSubirBesta.addActionListener(e -> moverItem(listaBestas, listaBestasModel, -1));
        JButton botaoBaixarBesta = new JButton("Baixar");
        botaoBaixarBesta.setPreferredSize(tamanhoBotao);
        botaoBaixarBesta.addActionListener(e -> moverItem(listaBestas, listaBestasModel, 1));
        JButton botaoEliminarBesta = new JButton("Eliminar");
        botaoEliminarBesta.setPreferredSize(tamanhoBotao);
        botaoEliminarBesta.addActionListener(e -> removerItem(listaBestas, listaBestasModel));

        botoesBesta.add(botaoSubirBesta);
        botoesBesta.add(botaoBaixarBesta);
        botoesBesta.add(botaoEliminarBesta);

        listaBestasLuta.add(labelListaBestas);
        listaBestasLuta.add(scrollBestas);
        listaBestasLuta.add(botoesBesta);

        // Painel central com listas (BoxLayout respeita tamanho)
        JPanel listasPanel = new JPanel();
        listasPanel.setLayout(new BoxLayout(listasPanel, BoxLayout.X_AXIS));
        listasPanel.add(Box.createHorizontalGlue());
        listasPanel.add(listaHeroisLuta);
        listasPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        listasPanel.add(listaBestasLuta);
        listasPanel.add(Box.createHorizontalGlue());

        // Botão Lutar centralizado
        JButton botaoLutar = new JButton("Lutar!");
        botaoLutar.addActionListener(e -> executarBatalha());
        JPanel painelBotaoLutar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotaoLutar.add(botaoLutar);

        luta.add(listasPanel, BorderLayout.CENTER);
        luta.add(painelBotaoLutar, BorderLayout.SOUTH);

        janela.add(luta, BorderLayout.CENTER);

        // ---------------- ÁREA DE BATALHA ----------------
        areaBatalha = new JTextArea(15, 70);
        areaBatalha.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(areaBatalha);
        janela.add(scrollLog, BorderLayout.SOUTH);

        janela.setVisible(true);
    }
    // MÉTODOS AUXILIARES PARA MOVIMENTAÇÃO E REMOÇÃO
    private void moverItem(JList<String> lista, DefaultListModel<String> modelo, int direcao) {
        int index = lista.getSelectedIndex();
        int novoIndex = index + direcao;
        if (index >= 0 && novoIndex >= 0 && novoIndex < modelo.size()) {
            String item = modelo.getElementAt(index);
            modelo.remove(index);
            modelo.add(novoIndex, item);
            lista.setSelectedIndex(novoIndex);
        }
    }

    private void removerItem(JList<String> lista, DefaultListModel<String> modelo) {
        int index = lista.getSelectedIndex();
        if (index >= 0) {
            modelo.remove(index);
        }
    }
    // EXECUTA A BATALHA
    private void executarBatalha() {
        List<Personagem> heroisList = new ArrayList<>();
        for (int i = 0; i < listaHeroisModel.size(); i++) {
            heroisList.add(parsePersonagem(listaHeroisModel.getElementAt(i)));
        }
        List<Personagem> bestasList = new ArrayList<>();
        for (int i = 0; i < listaBestasModel.size(); i++) {
            bestasList.add(parsePersonagem(listaBestasModel.getElementAt(i)));
        }

        Batalha batalha = new Batalha(new ArrayList<>(heroisList), new ArrayList<>(bestasList));
        String resultado = batalha.inicio();
        areaBatalha.append(resultado + "\n");
    }
    // CONVERTE STRING DA LISTA EM OBJETO PERSONAGEM
    private Personagem parsePersonagem(String entrada) {
        String[] partes = entrada.split(" - ");
        String nome = partes[0];
        String resto = partes[1];
        String tipo = resto.split(" ")[0];
        String atributos = resto.substring(resto.indexOf("(") + 1, resto.indexOf(")"));
        String[] valores = atributos.split(",");
        int vida = Integer.parseInt(valores[0].trim());
        int armadura = Integer.parseInt(valores[1].trim());

        return switch (tipo) {
            case "Elfo" -> new Elfo(nome, tipo, vida, armadura);
            case "Humano" -> new Humanos(nome, tipo, vida, armadura);
            case "Hobbit" -> new Hobbit(nome, tipo, vida, armadura);
            case "Orque" -> new Orque(nome, tipo, vida, armadura);
            case "Troll" -> new Troll(nome, tipo, vida, armadura);
            default -> throw new IllegalArgumentException("Tipo desconhecido: " + tipo);
        };
    }
}
