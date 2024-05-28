package br.edu.fescfafic.clicinaespecializadafx.exceptions;

public class LoginNotFoundException extends RuntimeException{
    public LoginNotFoundException(){
        super("ERRO - LOGIN NÃO ENCONTRADO");
    }
}
