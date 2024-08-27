package com.example.latinhaexpress.entities;

public class Co2
{
    private static final double EMISSOES_PRODUCAO_PRIMARIA = 16.5;
    private static final double EMISSOES_RECICLAGEM = 0.6;

    public static double calcularReducao(double quantidadeReciclar)
    {
        return (EMISSOES_PRODUCAO_PRIMARIA - EMISSOES_RECICLAGEM) * quantidadeReciclar;
    }
}
