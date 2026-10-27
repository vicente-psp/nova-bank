package br.com.desafionttdata.interfaces;

import java.util.List;

public interface IService<T> {
    void cadastrar(T objeto);
    void listar();
}
