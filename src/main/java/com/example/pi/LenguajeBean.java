package com.example.pi;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Locale;

@Named("Lenguaje")
@SessionScoped
public class LenguajeBean implements Serializable {
    public String getLocaleCode() {
        return LocaleCode;
    }

    public void setLocaleCode(String localeCode) {
        LocaleCode = localeCode;
    }

    private String LocaleCode = "es";
    public void changeLocale(ValueChangeEvent event) {

    Locale newLocale = new Locale(event.getNewValue().toString());
        FacesContext.getCurrentInstance().getViewRoot().setLocale(newLocale);
    }
}
