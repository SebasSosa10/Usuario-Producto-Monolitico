package com.UsuarioProducto.Usuario_productos.controller;

import com.UsuarioProducto.Usuario_productos.model.Product;
import com.UsuarioProducto.Usuario_productos.service.ServiceProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/product")
public class ControllerProduct {

    @Autowired
    private final ServiceProduct serviceProduct;

    public ControllerProduct(ServiceProduct serviceProduct) {
        this.serviceProduct = serviceProduct;
    }

    //-------------------------------- REGISTER PRODUCT --------------------------------------------

    @GetMapping("/formProduct")
    public String ShowFormNewProduct(Model model) {
        model.addAttribute("product", new Product());
        return "formP";  // Asegúrate de que sea exactamente "formProduct"
    }

    @PostMapping("/formProduct")
    public String saveProduct(Product product, RedirectAttributes redirectAttributes) {
        try {
            serviceProduct.saveProduct(product);
            redirectAttributes.addFlashAttribute("successMessage", "Product saved successfully!");
            return "redirect:/product/tableProduct";
        } catch (DataIntegrityViolationException e) {
            // Maneja errores relacionados con violaciones de integridad de la base de datos, como SKU duplicado
            redirectAttributes.addFlashAttribute("errorMessage", "SKU already exists. Please enter a unique SKU.");
            return "redirect:/product/formProduct";
        } catch (Exception e) {
            // Maneja cualquier otra excepción genérica
            redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while saving the product. Please try again.");
            return "redirect:/product/formProduct";
        }
    }

    //-------------------------------- TABLE PRODUCT --------------------------------------------

    @GetMapping("/tableProduct")
    public String showProductTable(Model model) {
        model.addAttribute("products", serviceProduct.findAll());
        return "tableProduct";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProduct(@PathVariable Long id) {
        serviceProduct.eliminarProduct(id);
        return "redirect:/product/tableProduct";
    }
}
