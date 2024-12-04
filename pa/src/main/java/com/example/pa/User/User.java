package com.example.pa.User;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;


@Data //para generar los metodos comunes, setter getter, toString etc
@Builder
@NoArgsConstructor //generamos un constructor vacio
@AllArgsConstructor //generamos un constructor con argumentos
@Entity
@Table(name="usuario", uniqueConstraints ={@UniqueConstraint(columnNames = {"username"})}) //para que el nombre de usuario no se repita
public class User implements UserDetails{   //ESTA CLASE IMPLEMENTA LA INTERFACE UserDetails
    @Id
    @GeneratedValue

    Integer id;
    @Column(nullable = false) //validamos que el usuario no pueda ser nulo, no se puede hacer un insert a la columna si el username es nulo
    String username;
    String lastname;
    String firstname;
    String password;
    String email;
    String direccionEnvio;
    String telefono;
    @Enumerated(EnumType.STRING)
    Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name()))); //retornamos una lista que representa la autoridad del usuario autenticado
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }





}


