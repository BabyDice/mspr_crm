package com.msprcrm.msprcrm.controller;

import com.msprcrm.msprcrm.entity.Client;
import com.msprcrm.msprcrm.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/view/")
public class ViewController {

    @Autowired
    ClientRepository clientRepository;

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String home(){
        return "index";
    }

    @RequestMapping(value = "auth", method = RequestMethod.GET)
    public String auth(){
        return "auth";
    }

    @RequestMapping(value = "clients", method = RequestMethod.GET)
    public String client(){
        return "clients";
    }

    @RequestMapping(value = "commandes", method = RequestMethod.GET)
    public String commande(){
        return "commandes";
    }

    @RequestMapping(value = "products", method = RequestMethod.GET)
    public String produit(){
        return "products";
    }

    @RequestMapping(value = "statistiques", method = RequestMethod.GET)
    public String statistiques(){
        return "statistiques";
    }


}
