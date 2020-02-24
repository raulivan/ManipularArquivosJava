package br.com.raulivan.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Arquivo de manipulação de arquivos
 * 
 * @author Raulivan
 * @version 1.0
 */
public class JArquivo {

	private List<String> _linhas;
	private String _caminho;
	
	public List<String> getLinhas(){
		return this._linhas;
	}
	
	/**
	 * Construtor
	 * @param caminho
	 */
	public JArquivo(String caminho) {
		_caminho = caminho;
		_linhas = new ArrayList<String>();
	}
	
	
	/**
	 * Carrega o conteúdo do arquivo
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public List<String> carregar() throws FileNotFoundException, IOException {
		
		_linhas.clear();
		BufferedReader buffRead = new BufferedReader(new FileReader(_caminho));
		
        String linha = "";
        while (true) {
            if (linha != null)
            	_linhas.add(linha);
            else
                break;
            linha = buffRead.readLine();
        }
        buffRead.close();
        
        return _linhas;
	}
	
	/**
	 * Grava o conteudo no arquivo
	 * @return true caso sucesso
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public boolean salvar() throws FileNotFoundException, IOException {
		
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(_caminho));
   
		for(String linha: _linhas)
			buffWrite.append(linha + "\n");
		buffWrite.close();
        
        return true;
	}
	
	/**
	 * Copiando arquivo usando FileChannel,FileInputStream e FileOutputStream
	 * @param origem
	 * @param destino
	 * @return true caso sucesso
	 * @throws IOException
	 */
	public static boolean copiar(String origem, String destino) throws IOException {
		
		FileChannel sourceChannel = null;
	    FileChannel destinationChannel = null;

	    try {
	        sourceChannel = new FileInputStream(origem).getChannel();
	        destinationChannel = new FileOutputStream(destino).getChannel();
	        sourceChannel.transferTo(0, sourceChannel.size(),destinationChannel);
	        
	        return true;
	    } finally {
	        if (sourceChannel != null && sourceChannel.isOpen())
	            sourceChannel.close();
	        if (destinationChannel != null && destinationChannel.isOpen())
	            destinationChannel.close();
	        
	        return false;
	   }
	}
	
	/**
	 * Copiando arquivos usando Path e  Files
	 * @param origem
	 * @param destino
	 * @return True caso sucesso
	 * @throws IOException
	 */
	public static boolean copiar2(String origem, String destino) throws IOException {
		
		Path source = Paths.get(origem);
		Path destination = Paths.get(destino);
		Files.copy(source, destination);
	
		return true;
	}
	
	/**
	 * Excluir arquivo
	 * @param arquivo
	 * @return True caso sucesso
	 */
	public static boolean excluir(String arquivo) {
		File file = new File(arquivo); 
		return file.delete();
	}
}
