package com.UsuarioProducto.Usuario_productos.dto;


import jakarta.validation.constraints.*;

public class DtoProduct {
    @NotBlank(message = "SKU is mandatory.")
    private String sku;
    @NotBlank(message = "Product name is mandatory.")
    private String name;
    @NotNull(message = "Price cannot be null.")
    @Min(value = 0, message = "Price must be a positive value.")
    private Double price;
    @NotBlank(message = "Brand cannot be empty.")
    private String brand;
    @NotBlank(message = "Store cannot be empty.")
    private String store;

    public DtoProduct() {
        super();
    }

    public DtoProduct(String sku, String name, Double price, String brand, String store) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.store = store;
    }

    public @NotBlank(message = "SKU is mandatory.") String getSku() {
        return sku;
    }

    public void setSku(@NotBlank(message = "SKU is mandatory.") String sku) {
        this.sku = sku;
    }

    public @NotBlank(message = "Product name is mandatory.") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Product name is mandatory.") String name) {
        this.name = name;
    }

    public @NotNull(message = "Price cannot be null.") @Min(value = 0, message = "Price must be a positive value.") Double getPrice() {
        return price;
    }

    public void setPrice(@NotNull(message = "Price cannot be null.") @Min(value = 0, message = "Price must be a positive value.") Double price) {
        this.price = price;
    }

    public @NotBlank(message = "Brand cannot be empty.") String getBrand() {
        return brand;
    }

    public void setBrand(@NotBlank(message = "Brand cannot be empty.") String brand) {
        this.brand = brand;
    }

    public @NotBlank(message = "Store cannot be empty.") String getStore() {
        return store;
    }

    public void setStore(@NotBlank(message = "Store cannot be empty.") String store) {
        this.store = store;
    }
}
