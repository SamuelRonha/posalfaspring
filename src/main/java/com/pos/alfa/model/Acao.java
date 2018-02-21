package com.pos.alfa.model;

import org.hibernate.validator.constraints.NotBlank;

public class Acao {
    @NotBlank
    private String agencia;
    @NotBlank
    private String conta;
    @NotBlank
    private String tipo;
    @NotBlank
    private Double valor;

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
