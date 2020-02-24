import java.io.File;

import br.com.raulivan.util.JArquivo;

public class run {

	public static void main(String[] args) {
		try {
			//Carregando o conte�do do arquivo
			JArquivo arquivo = new JArquivo("D:\\arquivo.txt");
			
			//Gerando mais linhas no arquivo
			arquivo.getLinhas().add("Teste de manipula��o de arquivos");
			arquivo.getLinhas().add("Vamos ver se funciona bem");
			arquivo.getLinhas().add("Oremos...");
			
			//Atualizando o arquivo
			arquivo.salvar();
			
			//Carregando o conte�do
			arquivo.carregar();
			
			for(String linha: arquivo.getLinhas())
				System.out.println(linha);
			
			//Copiar o arquivo
			JArquivo.copiar("D:\\arquivo.txt", "D:\\arquivo_copia.txt");
			JArquivo.copiar2("D:\\arquivo.txt", "D:\\arquivo_copia2.txt");
			
			
			JArquivo.excluir("D:\\arquivo.txt");
			
			
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	
}
