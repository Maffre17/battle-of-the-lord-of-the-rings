package org.example.model.bestas;

import org.example.model.Personagem;

// CLASSE BESTAS QUE ESTENDE PERSONAGEM

/**
 * Classe concreta que representa as bestas do jogo. Ela herda de {@link Personagem}
 * e implementa sua própria lógica de ataque.
 */
public class Bestas extends Personagem {
    // CONSTRUTOR

    /**
     * Construtor que chama o construtor da superclasse {@link Personagem} para
     * inicializar os atributos.
     *
     * @param nome     nome da besta
     * @param tipo     tipo da besta (ex.: "Orc", "Troll")
     * @param vida     pontos de vida iniciais
     * @param armadura valor de armadura
     */
    public Bestas(String nome, String tipo, int vida, int armadura) {
        // chama o construtor da superclasse Personagem
        super(nome, tipo, vida, armadura);
    }

    // SOBRESCRITA DO MÉTODO ABSTRATO ATAQUE


    /**
     * Implementação concreta do método de ataque para bestas.
     * <p>
     * A lógica escolhida para bestas é:
     * - Gerar um número aleatório entre 0 e 90 (inclusive).
     * - Esse número representa a força do ataque.
     * <p>
     * Diferença em relação aos heróis:
     * - As bestas rolam apenas um "dado" (número aleatório).
     * - Isso tende a resultar em ataques mais fracos em média do que os heróis,
     * que escolhem o melhor entre dois valores.
     * <p>
     * Observações:
     * - O ataque, assim como na classe Herois, não aplica dano diretamente ao alvo.
     * Apenas retorna o valor do ataque calculado.
     * - Cabe ao código controlador decidir quando chamar `alvo.receberDano(...)`.
     *
     * @param alvo personagem que poderia ser atacado
     * @return valor inteiro do ataque (entre 0 e 90)
     */
    @Override
    public int ataque(Personagem alvo) {
        // valores de 0 a 90
        return (int) (Math.random() * 91);
    }

}
