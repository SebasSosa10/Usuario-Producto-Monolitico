package com.UsuarioProducto.Usuario_productos.service;

import com.UsuarioProducto.Usuario_productos.model.Product;
import com.UsuarioProducto.Usuario_productos.repository.RepositoryProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceProduct {

    @Autowired
    private RepositoryProduct repositoryProduct;

    public List<Product> findAll() {
        return repositoryProduct.findAll();
    }

    public Product saveProduct(Product product){
        return repositoryProduct.save(product);
    }

    public void eliminarProduct(Long id) {
        repositoryProduct.deleteById(id);
    }
}
