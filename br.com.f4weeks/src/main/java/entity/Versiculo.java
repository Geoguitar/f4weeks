package entity;

import com.fasterxml.jackson.annotation.JsonTypeInfo;


@Entity
@Table(name = "versiculos")
public class Versiculo {

        @JsonTypeInfo.Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String livro; // Gênesis, Êxodo, Levítico, Números, Deuteronômio
        private Integer capitulo;
        private Integer versiculo;

        @Column(columnDefinition = "TEXT")
        private String texto;

        // Coluna para demarcar a qual Parashá este versículo pertence
        @Column(name = "nome_parasha")
        private String nomeParasha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLivro() {
        return livro;
    }

    public void setLivro(String livro) {
        this.livro = livro;
    }

    public Integer getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(Integer capitulo) {
        this.capitulo = capitulo;
    }

    public Integer getVersiculo() {
        return versiculo;
    }

    public void setVersiculo(Integer versiculo) {
        this.versiculo = versiculo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getNomeParasha() {
        return nomeParasha;
    }

    public void setNomeParasha(String nomeParasha) {
        this.nomeParasha = nomeParasha;
    }
// Getters, Setters e Construtores omitidos para brevidade

}
