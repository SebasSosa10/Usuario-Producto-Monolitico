    package com.UsuarioProducto.Usuario_productos.controller;

    import com.UsuarioProducto.Usuario_productos.model.User;
    import com.UsuarioProducto.Usuario_productos.service.ServiceUser;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.dao.DataIntegrityViolationException;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;

    @Controller
    @RequestMapping("/user")
    public class ControllerUser {

        @Autowired
        private final ServiceUser serviceUser;

        public ControllerUser(ServiceUser serviceUser) {
            this.serviceUser = serviceUser;
        }

        @GetMapping("/log")
        public String listUser(Model model) {
            model.addAttribute("users", serviceUser.findAll());
            // Añadir el objeto user para el formulario
            model.addAttribute("user", new User());
            return "login";
        }

        //-------------------------------- REGISTER LOGIN --------------------------------------------

        @GetMapping("/regis")
        public String ShowFormNewUser(Model model) {
            model.addAttribute("user", new User()); // Cambia "users" a "user"
            return "register";
        }

        @PostMapping("/regis")
        public String saveUser(@ModelAttribute User users, Model model) {
            try {
                serviceUser.saveUser(users); // Intenta guardar al usuario
                return "redirect:/user/log"; // Redirige si el guardado es exitoso
            } catch (DataIntegrityViolationException e) {
                // Captura la excepción específica para errores de integridad de datos
                if (e.getRootCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                    model.addAttribute("error", "El correo electrónico, nombre de usuario o ID nacional ya están registrados.");
                } else {
                    model.addAttribute("error", "Error al guardar el usuario. Intente nuevamente.");
                }
                return "register"; // Regresa al formulario con el mensaje de error
            } catch (RuntimeException e) {
                model.addAttribute("error", e.getMessage());
                return "register"; // Regresa al formulario con el mensaje de error
            }
        }
        //-------------------------------- REGISTER TABLE --------------------------------------------

        @GetMapping("/formN")
        public String ShowFormNewUserN(Model model) {
            model.addAttribute("user", new User()); // Cambia "users" a "user"
            return "formUser";
        }

        @PostMapping("/formN")
        public String saveUserN(@ModelAttribute User users, Model model) {
            try {
                serviceUser.saveUser(users); // Método que guarda al usuario
                return "redirect:/user/table"; // Redirige si el guardado es exitoso
            } catch (DataIntegrityViolationException e) {
                // Captura la excepción específica para errores de integridad de datos
                if (e.getRootCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                    model.addAttribute("error", "El correo electrónico, nombre de usuario o ID nacional ya están registrados.");
                } else {
                    model.addAttribute("error", "Error al guardar el usuario. Intente nuevamente.");
                }
                return "formUser"; // Regresa al formulario con el mensaje de error
            } catch (RuntimeException e) {
                model.addAttribute("error", e.getMessage());
                return "formUser"; // Regresa al formulario con el mensaje de error
            }
        }

        //-------------------------------- LOGIN --------------------------------------------

        @PostMapping("/log")
        public String loginUser(@RequestParam("username") String identificador, @RequestParam("password") String password, Model model) {
            try {
                User authenticatedUser = serviceUser.searchUser(identificador, password); // Busca el usuario por correo o nombre de usuario
                model.addAttribute("user", authenticatedUser);
                return "redirect:/user/table"; // Redirige a la tabla si el login es exitoso
            } catch (RuntimeException e) {
                model.addAttribute("error", e.getMessage()); // Si hay un error, muestra el mensaje
                return "login"; // Devuelve a la vista de login con el error
            }
        }


        @GetMapping("/table")
        public String showUserTable(Model model) {
            model.addAttribute("users", serviceUser.findAll());
            return "table"; // Remove "user/" if the template is directly in the templates folder
        }

        @GetMapping("/eliminar/{id}")
        public String eliminarUsuario(@PathVariable Long id) {
            serviceUser.eliminarUser(id);
            return "redirect:/user/table";
        }
    }

