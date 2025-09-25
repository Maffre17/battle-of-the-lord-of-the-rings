package org.example.model;

//CLASSE ABSTRATA

/**
 * Classe abstrata que representa um personagem generico do jogo.
 * <p>
 * Esta classe concentra atributos e comportamentos comum a todos os personagens
 * - nome e tipo(imultavel)
 * -vida (multavel)
 * -armadura(imultavel)
 * <p>
 * Ela tambem define um contrato(metodo abstrato `ataque`), que obriga subclasses a implementar a forma concreta de atacar outro personagem.
 */
public abstract class Personagem {

    // ATRIBUTOS
    /**
     * Nome do personagem. Marcar como `final` indica que o nome não pode ser alterado
     * depois que o objeto é construído.
     */
    private final String nome;
    /**
     * Tipo/classificação do personagem (por exemplo: "Elfo", "Troll").
     * Também é `final` — definido no construtor e não pode mudar.
     */
    private final String tipo;
    /**
     * Pontos de vida atuais do personagem. Este campo não é `final` porque diminui
     * quando o personagem recebe dano.
     */
    private int vida;
    /**
     * Valor de armadura que reduz o dano recebido. `final` porque o código atual
     * assume que a armadura não muda durante a vida do objeto.
     */
    private final int armadura;

    //CONSTRUTOR

    /**
     * Construtor que inicializa todos os atributos do personagem.
     *
     * @param nome     nome do personagem
     * @param tipo     tipo do personagem
     * @param vida     pontos de vida iniciais
     * @param armadura valor de armadura (redução de dano)
     */
    public Personagem(String nome, String tipo, int vida, int armadura) {
        this.nome = nome;
        this.tipo = tipo;
        this.vida = vida;
        this.armadura = armadura;
    }

    //RETORNA O NOME
    public String getNome() {
        return nome;
    }

    //RETORNA O TIPO
    public String getTipo() {
        return tipo;
    }

    //RETORNA VIDA
    public int getVida() {
        return vida;
    }

    //RETORNA ARMADURA
    public int getArmadura() {
        return armadura;
    }

    //RECEBE O DANO

    /**
     * Aplica dano ao personagem considerando sua armadura.
     * <p>
     * Regras da implementação atual:
     * - Se a armadura for menor que o dano, a diferença (dano - armadura) é subtraída
     * da vida (dano efetivo).
     * - Se a armadura for maior ou igual ao dano, nenhum ponto de vida é perdido
     * (a armadura absorve todo o ataque).
     * - Após aplicar dano, garante que a vida não fique negativa (define 0 se ficar).
     */
    public void receberDano(int dano) {
        if (armadura < dano) {
            // dano efetivo é a parte que ultrapassa a armadura
            vida -= (dano - armadura);
        }
        // evita vida negativa
        if (vida < 0) vida = 0;
    }

    //MOSTRA SE ESTA VIVO

    /**
     * Indica se o personagem ainda está vivo (vida > 0).
     *
     * @return true se vida maior que zero, false caso contrário.
     */
    public boolean estaVivo() {
        return vida > 0;

    }

    //ATAQUE METODO ABSTRATO

    /**
     * Define o contrato de ataque para subclasses: cada subclasse deve implementar
     * como ataca outro Personagem.
     * <p>
     * Exemplo de convenção: o método pode aplicar dano no `alvo` (chamando
     * `alvo.receberDano(...)`) e retornar o valor do dano causado.
     *
     * @param alvo personagem que receberá o ataque
     * @return um inteiro que normalmente representa o dano causado
     */
    public abstract int ataque(Personagem alvo);

}
