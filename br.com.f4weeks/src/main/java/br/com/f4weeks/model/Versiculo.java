package br.com.f4weeks.model;

import jakarta.persistence.*;

@Entity
@Table(name = "versiculos")
public class Versiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String livro;

    @Column(nullable = false)
    private Integer capitulo;

    @Column(nullable = false)
    private Integer versiculo;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String texto;

    @Column(name = "nome_parasha", nullable = false)
    private String nomeParasha;

    // Construtor vazio exigido pelo JPA
    public Versiculo() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLivro() { return livro; }
    public void setLivro(String livro) { this.livro = livro; }

    public Integer getCapitulo() { return capitulo; }
    public void setCapitulo(Integer capitulo) { this.capitulo = capitulo; }

    public Integer getVersiculo() { return versiculo; }
    public void setVersiculo(Integer versiculo) { this.versiculo = versiculo; }

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public String getNomeParasha() { return nomeParasha; }
    public void setNomeParasha(String nomeParasha) { this.nomeParasha = nomeParasha; }
}