package org.example.model.herois;

import org.example.model.Personagem;

// CLASSE HEROIS QUE ESTENDE PERSONAGEM

/**
 * Classe concreta que representa heróis do jogo. Ela herda de {@link Personagem}
 * e implementa a forma específica de ataque.
 */
public class Herois extends Personagem {
    // CONSTRUTOR

    /**
     * Construtor que simplesmente repassa os parâmetros para o construtor da
     * classe-mãe (Personagem).
     *
     * @param nome     nome do herói
     * @param tipo     tipo do herói (ex.: "Paladino", "Arqueiro")
     * @param vida     pontos de vida iniciais
     * @param armadura valor de armadura
     */
    public Herois(String nome, String tipo, int vida, int armadura) {
        // chama o construtor da superclasse Personagem
        super(nome, tipo, vida, armadura);
    }

    // SOBRESCRITA DO MÉTODO ABSTRATO ATAQUE


    /**
     * Implementação concreta do método de ataque.
     * <p>
     * A lógica escolhida para heróis é:
     * - Rolar dois "dados" (números aleatórios entre 0 e 100 inclusive).
     * - O valor do ataque será o maior dos dois resultados.
     * <p>
     * Observações:
     * - Aqui o ataque não aplica dano diretamente ao alvo (não chama
     * `alvo.receberDano(...)`), apenas retorna o valor calculado.
     * - Cabe ao código externo ou a uma camada de controle decidir como esse
     * valor é usado para afetar o alvo.
     * - O uso de `Math.random()` não garante distribuições uniformes
     * perfeitamente adequadas para jogos, mas é suficiente para exemplos simples.
     *
     * @param alvo personagem que poderia ser atacado
     * @return valor inteiro do ataque (entre 0 e 100)
     */
    @Override
    public int ataque(Personagem alvo) {
        // "rolagem" do primeiro dado
        int dado1 = (int) (Math.random() * 101);
        // "rolagem" do segundo dado
        int dado2 = (int) (Math.random() * 101);
        // retorna o maior valor obtido
        return Math.max(dado1, dado2);
    }
}
