package org.example.controller;

import org.example.model.Personagem;

import java.util.ArrayList;
// CLASSE BATALHA

/**
 * Classe responsável por controlar e simular uma batalha entre dois exércitos:
 * heróis e bestas.
 * <p>
 * Ela mantém listas separadas para cada exército e executa turnos de combate
 * até que um dos lados seja completamente derrotado.
 */
public class Batalha {
    //LISTA DE EXERCITOS
    private ArrayList<Personagem> exercitoHerois;
    private ArrayList<Personagem> exercitoBestas;


    //CONSTRUTOR

    /**
     * Inicializa a batalha com os exércitos de heróis e bestas fornecidos.
     *
     * @param herois lista de personagens do exército de heróis
     * @param bestas lista de personagens do exército de bestas
     */
    public Batalha(ArrayList<Personagem> herois, ArrayList<Personagem> bestas) {
        this.exercitoHerois = herois;
        this.exercitoBestas = bestas;

    }

    //METODO INICIO

    /**
     * Inicia a batalha, executando turnos até que um dos exércitos seja derrotado.
     * <p>
     * Regras de execução:
     * - Cada turno, personagens do mesmo índice lutam entre si.
     * - O herói ataca primeiro, causando pelo menos 1 de dano.
     * - Se o alvo sobreviver, a besta contra-ataca, também causando pelo menos 1 de dano.
     * - Personagens derrotados são removidos do exército imediatamente após o turno.
     * - A batalha continua até que um dos exércitos esteja vazio.
     *
     * @return log da batalha detalhando os ataques, danos e resultados de cada turno
     */
    public String inicio() {
        // string para registrar o histórico da batalha
        StringBuilder log = new StringBuilder();

        int turno = 1;

        log.append("Turno ").append(turno).append(":\n");

        while (!exercitoHerois.isEmpty() && !exercitoBestas.isEmpty()) {
            System.out.println("Turno " + turno + ": ");
            // Determina quantas lutas podem ocorrer neste turno (pares de combatentes)
            int tamanho = Math.min(exercitoHerois.size(), exercitoBestas.size());

            for (int j = 0; j < tamanho; j++) {
                Personagem heroi = exercitoHerois.get(j);
                Personagem besta = exercitoBestas.get(j);
                // Mostra o estado inicial da luta
                System.out.println("Luta entre " + heroi.getNome() + " TIPO = " + heroi.getTipo() + " VIDA = " + heroi.getVida() + " ARMADURA = " + heroi.getArmadura() + " E " + besta.getNome() + " TIPO = " + besta.getTipo() + " VIDA = " + besta.getVida() + " ARMADURA = " + besta.getArmadura());
                log.append("Luta entre ")
                        .append(heroi.getNome()).append(" (Vida=").append(heroi.getVida())
                        .append(" Armadura=").append(heroi.getArmadura()).append(") e ")
                        .append(besta.getNome()).append(" (Vida=").append(besta.getVida())
                        .append(" Armadura=").append(besta.getArmadura()).append(")\n");

                // Ataque do herói
                int ataqueHeroi = heroi.ataque(besta);
                int danoHeroi = Math.max(1, ataqueHeroi); // garante pelo menos 1 de dano
                besta.receberDano(danoHeroi);
                System.out.println(heroi.getNome() + " ATACOU CAUSANDO = " + danoHeroi + " DE DANO A " + besta.getNome());
                log.append(heroi.getNome()).append(" ataca causando ").append(danoHeroi)
                        .append(" de dano a ").append(besta.getNome()).append("\n");

                if (besta.estaVivo()) {
                    // Contra-ataque da besta
                    int ataqueBesta = besta.ataque(heroi);
                    int danoBesta = Math.max(1, ataqueBesta); // garante pelo menos 1 de dano
                    heroi.receberDano(danoBesta);
                    System.out.println(besta.getNome() + " ATACOU CAUSANDO = " + danoBesta + " DE DANO A " + heroi.getNome());
                    log.append(besta.getNome()).append(" ataca causando ").append(danoBesta)
                            .append(" de dano a ").append(heroi.getNome()).append("\n");
                } else {
                    System.out.println(besta.getNome() + " FOI DERROTADO!");
                    log.append(besta.getNome()).append(" foi derrotado!\n");
                }

                if (!heroi.estaVivo()) {
                    System.out.println(heroi.getNome() + " FOI DERROTADO!");
                    log.append(heroi.getNome()).append(" foi derrotado!\n");
                }
            }

            // Remove personagens mortos de ambos os exércitos
            exercitoHerois.removeIf(p -> !p.estaVivo());
            exercitoBestas.removeIf(p -> !p.estaVivo());

            turno++;
            log.append("\nTurno ").append(turno).append(":\n");
        }

        // Resultado final
        if (exercitoHerois.isEmpty()) {
            System.out.println("VITÓRIA DAS BESTAS!");
            log.append("VITÓRIA DAS BESTAS!\n");
        } else if (exercitoBestas.isEmpty()) {
            System.out.println("VITÓRIA DOS HERÓIS");
            log.append("VITÓRIA DOS HERÓIS!\n");
        }

        return log.toString();
    }

}
