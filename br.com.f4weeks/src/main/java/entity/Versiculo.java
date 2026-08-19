package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "versiculos")
public class Versiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String livro; // Ex: Gênesis

    @Column(nullable = false)
    private Integer capitulo;

    @Column(nullable = false)
    private Integer versiculo;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String texto;

    @Column(name = "nome_parasha", nullable = false)
    private String nomeParasha; // Ex: Bereshit

    // Getters, Setters e Construtores omitidos para brevidade
}