package org.example.model.herois;

import org.example.model.Personagem;
import org.example.model.bestas.Troll;

// CLASSE HOBBIT QUE ESTENDE HEROIS

/**
 * Classe concreta que representa um Hobbit, um tipo específico de Herói.
 * <p>
 * Herda de {@link Herois} e implementa uma lógica de ataque diferenciada contra Trolls.
 */
public class Hobbit extends Herois {
    // CONSTRUTOR

    /**
     * Construtor que chama o construtor da superclasse {@link Herois}.
     *
     * @param nome     nome do hobbit
     * @param tipo     tipo de hobbit (ex.: "Hobbit")
     * @param vida     pontos de vida iniciais
     * @param armadura valor de armadura
     */
    public Hobbit(String nome, String tipo, int vida, int armadura) {
        super(nome, tipo, vida, armadura);
    }

    // SOBRESCRITA DO MÉTODO DE ATAQUE


    /**
     * Implementação concreta do ataque para Hobbits.
     * <p>
     * Regras aplicadas:
     * - Primeiro, chama o ataque padrão da superclasse {@link Herois}, que retorna
     * o maior valor entre dois números aleatórios (0 a 100).
     * - Se o alvo for um Troll, subtrai 5 pontos do ataque.
     * - Garante que o ataque não fique negativo (mínimo 0).
     * <p>
     * Observações:
     * - Demonstra o uso do operador `instanceof` para identificar tipos específicos
     * de personagens no momento do ataque.
     * - Mostra como aplicar penalidades dependendo do tipo de inimigo.
     * - O método retorna apenas o valor do ataque; não aplica o dano diretamente.
     *
     * @param alvo personagem que será atacado
     * @return valor inteiro do ataque, >= 0
     */
    @Override
    public int ataque(Personagem alvo) {

        int ataque = super.ataque(alvo);
        // penalidade contra Trolls
        if (alvo instanceof Troll) {
            ataque -= 5;
        }
        // garante que o ataque não fique negativo
        if (ataque < 0) {
            ataque = 0;
        }
        return ataque;
    }
}
