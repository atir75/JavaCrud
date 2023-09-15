package com.mycompany.desafio1;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author maria
 */
public class DadosJson {
    private static String cep;
    private static String logradouro;
    private static String complemento;
    private static String bairro;
    private static String localidade;
    private static String uf;
    private static String ibge;
    private static String gia;
    private static String ddd;
    private static String siafi;
    
    public static void setcep(String _cep){cep = _cep;}
    public static void setlogradouro(String _log){logradouro = _log;}
    public static void setcomplemento(String _complemento) {complemento = _complemento;}
    public static void setbairro(String _bairro) {bairro = _bairro;}
    public static void setlocalidade(String _localidade) {localidade = _localidade;}
    public static void setuf(String _uf) {uf = _uf;}
    public static void setibge(String _ibge) {ibge = _ibge;}
    public static void setgia(String _gia){gia = _gia;}
    public static void setddd(String _ddd){ddd = _ddd;}
    public static void setsiafi(String _siafi){siafi = _siafi;}
    
    public static String getcep() {return cep;}
    public static String getlogradouro() {return logradouro;}
    public static String getcomplemento() {return complemento;}
    public static String getbairro() {return bairro;}
    public static String getlocalidade() {return localidade;}
    public static String getuf() {return uf;}
    public static String getibge() {return ibge;}
    public static String getgia() {return gia;}
    public static String getddd() {return ddd;}
    public static String getsiafi() {return siafi;}
    
}
