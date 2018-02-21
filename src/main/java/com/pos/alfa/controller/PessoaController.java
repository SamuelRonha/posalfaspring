package com.pos.alfa.controller;

import com.pos.alfa.model.Acao;
import com.pos.alfa.model.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

@Controller
public class PessoaController {
    static Collection<Pessoa> pessoas = new ArrayList<Pessoa>();

    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @Autowired
    public PessoaController(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }

    @GetMapping("/pessoa")
    private String register(@ModelAttribute("pessoa") Pessoa pessoa) {
        return "/pessoa";
    }

    @GetMapping("/pessoa/{nome}")
    private String register(Model model) {

        return "/consultaPessoa";
    }

    @PostMapping("/pessoa")
    private String save(@Valid Pessoa pessoa, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "pessoa";
        }
        if (pessoas.size() > 0) {
            for (Pessoa p : pessoas
                    ) {
                if (p.getNome().equals(pessoa.getNome())) {
                    pessoas.remove(p);
                    break;
                }
            }
        }
        inMemoryUserDetailsManager.createUser(User.withUsername(pessoa.getLogin()).password(pessoa.getSenha()).roles("USER").build());

        pessoas.add(pessoa);
        return "/success";
    }

    @PostMapping("/acao")
    private String acao(Acao acao, BindingResult bindingResult, Model model) {
        boolean exists = false;
        for (Pessoa p : pessoas) {
            if (p.getContaBancaria().getAgencia().equals(acao.getAgencia())
                    &&
                    p.getContaBancaria().getConta().equals(acao.getConta())) {
                if (acao.getTipo().equals("0")) {
                    p.getContaBancaria().setQuantidade(p.getContaBancaria().getQuantidade() - acao.getValor());

                } else
                    p.getContaBancaria().setQuantidade(p.getContaBancaria().getQuantidade() + acao.getValor());
                exists = true;
            }
        }
        if (exists) {
            return "/success";
        } else
            return "/acao";
    }

    @GetMapping("/acao")
    public String acao(@ModelAttribute("acao") Acao acao) {
        return "/acao";
    }

    @GetMapping("/pessoas")
    public String pessoas(Model model) {
        model.addAttribute("pessoas", pessoas);
        return "/listaPessoas";
    }


}
