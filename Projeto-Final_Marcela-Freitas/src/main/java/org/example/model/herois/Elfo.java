package org.example.model.herois;

import org.example.model.bestas.Orque;
import org.example.model.Personagem;

// CLASSE ELFO QUE ESTENDE HEROIS
/**
 * Classe concreta que representa um Elfo, um tipo específico de Herói.
 *
 * Herda de {@link Herois} e implementa uma lógica de ataque diferenciada contra Orques.
 */
public class Elfo extends Herois {

    // CONSTRUTOR
    /**
     * Construtor que chama o construtor da superclasse {@link Herois}.
     *
     * @param nome nome do elfo
     * @param tipo tipo de elfo (ex.: "Elfo")
     * @param vida pontos de vida iniciais
     * @param armadura valor de armadura
     */
    public Elfo(String nome, String tipo, int vida, int armadura) {
        super(nome, tipo, vida, armadura);
    }

    // SOBRESCRITA DO MÉTODO DE ATAQUE


    /**
     * Implementação concreta do ataque para Elfos.
     *
     * Regras aplicadas:
     * - Primeiro, chama o ataque padrão da superclasse {@link Herois}, que retorna
     * o maior valor entre dois números aleatórios (0 a 100).
     * - Se o alvo for um Orque, adiciona 10 pontos de ataque extras.
     * Isso representa vantagem do Elfo contra Orques.
     * - Caso contrário, retorna o ataque calculado normalmente.
     *
     * Observações:
     * - Demonstra o uso do operador `instanceof` para identificar tipos específicos
     * de personagens no momento do ataque.
     * - O método retorna apenas o valor de ataque; não aplica o dano diretamente.
     *
     * @param alvo personagem que será atacado
     * @return valor inteiro do ataque
     */
    @Override
    public int ataque(Personagem alvo) {
        // ataque básico do herói (maior entre dois dados aleatórios)
        int ataque = super.ataque(alvo);
        // bônus contra Orques
        if (alvo instanceof Orque) {
            ataque += 10;
        }
        return ataque;
    }
}
