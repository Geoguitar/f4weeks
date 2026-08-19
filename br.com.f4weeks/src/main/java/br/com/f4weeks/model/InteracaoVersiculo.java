package br.com.f4weeks.model;

import jakarta.persistence.*;

@Entity
@Table(name = "interacoes_versiculos", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"usuario_id", "versiculo_id"})
})
public class InteracaoVersiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "versiculo_id", nullable = false)
    private Versiculo versiculo;

    @Column(nullable = false)
    private boolean lido = false;

    @Column(name = "cor_destaque", length = 7)
    private String corDestaque; // Ex: Código Hex (#FFD700) para uma das 5 cores permitidas

    // Getters, Setters e Construtores omitidos para brevidade
}