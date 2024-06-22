package com.example.pi.controllers;

import com.example.pi.models.Vendedor;
import com.example.pi.repositories.VendedorRepositoryImp;
import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;


import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

@Named("beanLogin")
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
    private String username;
    private String password;
    private Boolean admon = false;
    public static String cedula_vendedor;

    public static boolean tipolog = false;

    public Boolean getAdmon() {
        return admon;
    }

    public void setAdmon(Boolean admon) {
        this.admon = admon;
    }

    private String rolusuario;

    public String getRolusuario() {
        return rolusuario;
    }

    public void setRolusuario(String rolusuario) {
        this.rolusuario = rolusuario;
    }

    public static boolean logged = false;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    VendedorRepositoryImp vendedorrepository;

    public String login() {
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
        Optional<Vendedor> vendedor;
        vendedorrepository = new VendedorRepositoryImp();
        vendedor = vendedorrepository.login(username, password);
        if (vendedor.isPresent()) {
            Vendedor rol = vendedor.get();
            if (Objects.equals(rol.getRol(), "vendedor")) {
                rolusuario = "Vendedor";
                cedula_vendedor = rol.getCedula();
                admon = false;
                logged = true;
                tipolog = admon;
                return "Ventas?faces-redirect=true";
            } else {
                rolusuario = "Super administrador";
                logged = true;
                admon = true;
                tipolog = admon;
                return "Dashboard?faces-redirect=true";
            }

        } else {
            logged = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("loginForm.userpasword"), ""));
            return null;
        }
    }

    public String cerrarSesion() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ex = fc.getExternalContext();
        HttpSession session = (HttpSession) ex.getSession(false);
        session.invalidate();
        logged = false;
        return "login"; // Redirige a la página de inicio después de cerrar la sesión
    }

}
