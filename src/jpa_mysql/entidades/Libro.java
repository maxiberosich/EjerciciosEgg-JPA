/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpa_mysql.entidades;


import com.sun.istack.internal.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Max
 */
@Entity
@Table
public class Libro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    @NotNull
    private Long isbn;
    
    @Column(unique = true)
    @NotNull
    private String titulo;
    
    
    @NotNull
    private Integer anio;
    
    
    @NotNull
    private Integer ejemplares;
    
   
    @NotNull
    private Integer ejemplaresPrestados;
    
    
    @NotNull
    private Integer ejemplaresRestantes;
    
    private Boolean alta;
    

    @NotNull
    @ManyToOne
    @JoinColumn(name = "autor_id", unique = true)
    private Autor autor;
    
    /*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="libroEditorial",joinColumns = @JoinColumn(name="idLibro",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="idEditorial",referencedColumnName = "id") )*/
    @ManyToMany
    @JoinTable(
        name = "libro_editorial",
        joinColumns = @JoinColumn(name = "libro_id"),
        inverseJoinColumns = @JoinColumn(name = "editorial_id")
    )
    private List<Editorial> editorial = new ArrayList<>();


    public Libro() {
        
    }

    public Libro(Long isbn, String titulo, Integer anio,
            Integer ejemplares, Integer ejemplaresPrestados,
            Integer ejemplaresRestantes, Boolean alta) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.anio = anio;
        this.ejemplares = ejemplares;
        this.ejemplaresPrestados = ejemplaresPrestados;
        this.ejemplaresRestantes = ejemplaresRestantes;
        this.alta = alta;
    }

    public Libro(Long isbn, String titulo, Integer anio,
            Integer ejemplares, Integer ejemplaresPrestados, 
            Integer ejemplaresRestantes, Boolean alta, Autor autor,
            List<Editorial> editorial) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.anio = anio;
        this.ejemplares = ejemplares;
        this.ejemplaresPrestados = ejemplaresPrestados;
        this.ejemplaresRestantes = ejemplaresRestantes;
        this.alta = alta;
        this.autor = autor;
        this.editorial = editorial;
    }

    public List<Editorial> getEditorial() {
        return editorial;
    }

    public void setEditorial(List<Editorial> editorial) {
        this.editorial = editorial;
    }

    public Long getId() {
        return id;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(Integer ejemplares) {
        this.ejemplares = ejemplares;
    }

    public Integer getEjemplaresPrestados() {
        return ejemplaresPrestados;
    }

    public void setEjemplaresPrestados(Integer ejemplaresPrestados) {
        this.ejemplaresPrestados = ejemplaresPrestados;
    }

    public Integer getEjemplaresRestantes() {
        return ejemplaresRestantes;
    }

    public void setEjemplaresRestantes(Integer ejemplaresRestantes) {
        this.ejemplaresRestantes = ejemplaresRestantes;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Libro{" + "id=" + id + ", isbn=" + isbn + ", titulo=" 
                + titulo + ", anio=" + anio + ", ejemplares=" + ejemplares 
                + ", ejemplaresPrestados=" + ejemplaresPrestados + ", ejemplaresRestantes=" 
                + ejemplaresRestantes + ", alta=" + alta + ", autor=" + autor
                + ", editorial=" + editorial + '}';
    }

    

}
