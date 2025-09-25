package org.example.model.bestas;

// CLASSE TROLL QUE ESTENDE BESTAS

/**
 * Classe concreta que representa um Troll, um tipo específico de Besta.
 * <p>
 * Herda de {@link Bestas} e não sobrescreve o método ataque, ou seja, utilizará
 * o ataque padrão da classe Bestas (um valor aleatório entre 0 e 90).
 */
public class Troll extends Bestas {

    // CONSTRUTOR

    /**
     * Construtor que chama o construtor da superclasse {@link Bestas}.
     *
     * @param nome     nome do troll
     * @param tipo     tipo de troll (ex.: "Troll da Montanha")
     * @param vida     pontos de vida iniciais
     * @param armadura valor de armadura
     */
    public Troll(String nome, String tipo, int vida, int armadura) {
        super(nome, tipo, vida, armadura);
    }

    // OBSERVAÇÃO:
    // Não sobrescrevemos o método ataque, então o Troll usa o ataque padrão de Bestas.
}
