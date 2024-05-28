package com.accesadades.botiga.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.accesadades.botiga.Model.Product;
import com.accesadades.botiga.Model.Subcategory;
import com.accesadades.botiga.Service.ProductService;
import com.accesadades.botiga.Service.SubcategoryService;

import java.time.LocalDateTime;
import java.util.Set;

@Controller
public class WebController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SubcategoryService subcategoryService;

    // Devuelve la pagina index
    @RequestMapping(value = "/")
    public String index(Model model) {
        return "index";
    }

    // Devuelve la pagina del catalogo
    @RequestMapping(value = "/catalog")
    public String catalog(Model model) {
        Set<Product> products = productService.findAllProducts();
        System.out.println(products);
        model.addAttribute("products", products);
        return "catalog";
    }

    // Devuelve la pagina de busqueda, el nombre insertado dentro del cuadro tiene
    // que ser EXACTO al buscado
    @RequestMapping(value = { "/search", "/prodname" }, method = { RequestMethod.GET, RequestMethod.POST })
    public String searchProductByName(@RequestParam(value = "name", required = false) String name, Model model) {
        if (name != null) {
            Product product = productService.findProductsByName(name);
            model.addAttribute("product", product);
        }
        return "search"; // Referencia a search.html en el directorio templates
    }

    // Devuelve la pagina de insercion de un nuevo producto, con una lista de las
    // subcategorias de la base de datos
    @RequestMapping(value = "/newproduct", method = { RequestMethod.GET })
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        Set<Subcategory> subcategories = subcategoryService.findAllSubcategories();

        model.addAttribute("subcategories", subcategories);
        return "newproduct";
    }

    // Guarda el producto si no ha habido ningun error y redirige a la pagina del
    // mensaje de confirmacion
    // Si hay un error devuelve la pagina newproduct
    @RequestMapping(value = "/desar", method = { RequestMethod.POST })
    public String newProduct(@Validated @ModelAttribute("product") Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "newproduct"; // Redirige de nuevo al formulario si hay errores
        }

        LocalDateTime now = LocalDateTime.now();
        product.setCreationDate(now);
        product.setUpdateDate(now);
        productService.saveProduct(product);
        model.addAttribute("message", "Producte inserit correctament");
        return "success_prod_created";
    }
}
