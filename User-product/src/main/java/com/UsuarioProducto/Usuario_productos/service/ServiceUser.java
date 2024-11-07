package com.UsuarioProducto.Usuario_productos.service;

import com.UsuarioProducto.Usuario_productos.model.User;
import com.UsuarioProducto.Usuario_productos.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceUser {
    @Autowired
    private RepositoryUser repositoryUser;

    public List<User> findAll() {
        return repositoryUser.findAll();
    }

    public User saveUser(User user){
        return repositoryUser.save(user);
    }

    public User searchUser(String identificador, String password) {
        // Busca el usuario por email o nombre de usuario
        Optional<User> foundUser = repositoryUser.findByEmailOrUsername(identificador, identificador);
        // Verifica si el usuario fue encontrado y si la contraseña coincide
        if (foundUser.isPresent() && foundUser.get().getPassword().equals(password)) {
            return foundUser.get();  // Si todo coincide, retorna el usuario encontrado
        } else {
            throw new RuntimeException("Email/nombre de usuario o contraseña incorrectos");
        }
    }


    public void eliminarUser(Long id) {
        repositoryUser.deleteById(id);
    }
}
