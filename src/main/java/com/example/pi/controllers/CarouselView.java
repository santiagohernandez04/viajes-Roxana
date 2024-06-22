package com.example.pi.controllers;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import com.example.pi.models.Plan_turistico;
import com.example.pi.repositories.PlanTuristicoRepositoryImpl;
import org.primefaces.model.ResponsiveOption;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



@Named("carouselView")
@SessionScoped
public class CarouselView implements Serializable {


        private List<Plan_turistico> planTuristicos;

        private List<ResponsiveOption> responsiveOptions;

        private PlanTuristicoRepositoryImpl planTuristicoRepository;

        @PostConstruct
        public void init() {
            planTuristicoRepository = new PlanTuristicoRepositoryImpl();
            planTuristicos = planTuristicoRepository.list(0);
            planTuristicos.forEach(plan -> {

            });


            responsiveOptions = new ArrayList<>();
            responsiveOptions.add(new ResponsiveOption("1024px", 3, 3));
            responsiveOptions.add(new ResponsiveOption("768px", 2, 2));
            responsiveOptions.add(new ResponsiveOption("560px", 1, 1));
        }

        public List<Plan_turistico> getPlanTuristicos() {
            return planTuristicos;
        }

        public void setPlanTuristicos(List<Plan_turistico> planTuristicos) {
            this.planTuristicos = planTuristicos;
        }

        public List<ResponsiveOption> getResponsiveOptions() {
            return responsiveOptions;
        }

        public void setResponsiveOptions(List<ResponsiveOption> responsiveOptions) {
            this.responsiveOptions = responsiveOptions;
        }

        public String getStatusStyle(Plan_turistico plan) {
            if (plan.isEstado()) {

                return "Activo";
            } else {

                return "Inactivo";
            }
        }




    }

