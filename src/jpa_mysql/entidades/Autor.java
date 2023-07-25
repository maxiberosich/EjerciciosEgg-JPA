/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpa_mysql.entidades;

import com.sun.istack.internal.NotNull;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Max
 */
@Entity
@Table
public class Autor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotNull
    private String nombre;
    private Boolean alta;
    
    //@OneToMany(mappedBy = "autor", cascade = CascadeType.DETACH, orphanRemoval = true)
    
    //DE ESTA FORMA ELIMINA AUTORES Y A LOS LIBROS QUE TENGA RELACIONADOS
    @NotNull
    @OneToMany(mappedBy = "autor", cascade = CascadeType.REMOVE)        
    private List<Libro> libros;

    public Autor() {
    }

    public Autor(String nombre, Boolean alta) {
        this.nombre = nombre;
        this.alta = alta;
    }
    
    public Autor(Long id, String nombre, Boolean alta) {
        this.id = id;
        this.nombre = nombre;
        this.alta = alta;
    }

    public Autor(Long id, String nombre, Boolean alta, List<Libro> libros) {
        this.id = id;
        this.nombre = nombre;
        this.alta = alta;
        this.libros = libros;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Autor{" + "id=" + id + ", nombre=" + nombre + ", alta=" + alta + ", libros=" + libros + '}';
    }

    
    

    

}
