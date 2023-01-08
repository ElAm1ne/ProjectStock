package com.example.ProjectStock.Modele;

import javax.persistence.*;

    @Entity
    public class Portfolio {
        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        private long id;

        @ManyToOne
        private Users user;

        // liste des actions du portefeuille
        //private List<Stock> stocks;

        // getters et setters
    }


