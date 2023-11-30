package com.msprcrm.msprcrm.controller;

import com.msprcrm.msprcrm.entity.Client;
import com.msprcrm.msprcrm.payload.CreateClientRequest;
import com.msprcrm.msprcrm.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ViewController {

    @Autowired
    private ClientRepository clientRepository;
    @RequestMapping(value = "client", method = RequestMethod.GET)
    public String client(Model model){
        List<Client> client = clientRepository.findAll();
        model.addAttribute("client", client);
        return "AfficheClient";
    }
}
