package org.example.model.bestas;

import org.example.model.Personagem;

// CLASSE ORQUE QUE ESTENDE BESTAS

/**
 * Classe concreta que representa um Orque, um tipo específico de Besta.
 * <p>
 * Herda de {@link Bestas} e implementa uma lógica de ataque própria, diferenciada
 * dos demais tipos de personagens.
 */
public class Orque extends Bestas {

    // CONSTRUTOR

    /**
     * Construtor que chama o construtor da superclasse {@link Bestas}.
     *
     * @param nome     nome do orque
     * @param tipo     tipo de orque (ex.: "Orque")
     * @param vida     pontos de vida iniciais
     * @param armadura valor de armadura
     */
    public Orque(String nome, String tipo, int vida, int armadura) {
        super(nome, tipo, vida, armadura);
    }

    // SOBRESCRITA DO MÉTODO DE ATAQUE


    /**
     * Implementação concreta do ataque para os orques.
     * <p>
     * Regras aplicadas:
     * - Primeiro chama o ataque básico definido em {@link Bestas}, que retorna
     * um valor aleatório entre 0 e 90.
     * - Em seguida, calcula uma "armadura reduzida" do alvo: 90% do valor real
     * da armadura (reduz a proteção do inimigo em 10%).
     * - Se o ataque for maior que essa armadura reduzida, retorna a diferença
     * (dano efetivo aplicado).
     * - Caso contrário, retorna 0 (ataque não conseguiu superar a armadura).
     * <p>
     * Observações:
     * - Essa lógica torna os orques mais perigosos, pois eles enfraquecem a
     * armadura do inimigo.
     * - Ainda assim, o método apenas retorna o dano calculado; não aplica
     * diretamente em `alvo.receberDano(...)`.
     *
     * @param alvo personagem alvo do ataque
     * @return valor do dano efetivo (>= 0)
     */
    @Override
    public int ataque(Personagem alvo) {
        // ataque básico da classe Bestas (0 a 90)
        int ataque = super.ataque(alvo);
        // armadura do alvo reduzida em 10%
        int armaduraAlvo = (int) (alvo.getArmadura() * 0.9);
        // calcula dano efetivo se ataque superar a armadura reduzida
        if (ataque > armaduraAlvo) {
            return ataque - armaduraAlvo;
        }
        return 0;
    }
}
