package br.com.desafionttdata.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Arquivos {
    public void criar(List<String> textos, String nomeArquivo) throws IOException {
        Files.write(Path.of(nomeArquivo + ".txt"), textos);
    }
}
