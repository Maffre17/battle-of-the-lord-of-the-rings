package org.example.model.herois;

// CLASSE HUMANOS QUE ESTENDE HEROIS

/**
 * Classe concreta que representa Humanos, um tipo específico de Herói.
 * <p>
 * Herda de {@link Herois} e não sobrescreve o método ataque, portanto utiliza o
 * ataque padrão de Herois (maior valor entre dois números aleatórios 0 a 100).
 */
public class Humanos extends Herois {

    // CONSTRUTOR

    /**
     * Construtor que chama o construtor da superclasse {@link Herois}.
     *
     * @param nome     nome do humano
     * @param tipo     tipo de humano (ex.: "Humano")
     * @param vida     pontos de vida iniciais
     * @param armadura valor de armadura
     */
    public Humanos(String nome, String tipo, int vida, int armadura) {
        super(nome, tipo, vida, armadura);
    }
    // OBSERVAÇÃO:
    // Não sobrescrevemos o método ataque, então Humanos usam o ataque padrão de Herois.
}
