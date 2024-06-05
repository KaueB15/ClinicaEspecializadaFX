package br.edu.fescfafic.clicinaespecializadafx.exceptions;

public class InvalidDateException extends RuntimeException{
    public InvalidDateException(){
        super("ERRO > Data Invalida");
    }
}
