import java.io.FileReader;
import java.io.BufferedReader; 
import java.util.*;
import java.util.Scanner;
public class Parser {
	//pathname.getName().toLowerCase().endsWith(suffix)
	ArrayList<String> perceptions;
	
	public Parser(String file) {
		perceptions = new ArrayList<String>();
		try {
			String linha = "";
			// instancia do arquivo que vou ler  
			FileReader reader = new FileReader(file);  
			BufferedReader leitor = new BufferedReader(reader);
			// loop que percorrerá todas as  linhas do arquivo.txt que eu quero ler
			while ((linha = leitor.readLine()) != null) {
				//No metodo StringTokenizer passo os parametros que quero ler, em seguida o que eu quero descartar no meu caso ( - ) e ( ; ).   
				StringTokenizer st = new StringTokenizer(linha, "<().;");
				//Aqui determino que enquanto existir tokens que ele faça a leitura 
				String percept = st.nextToken().replace("!", "").replace("?", "").replace("-","").replace("+","")
								 .replace(",", "").replace(":","").replace("not", "").replaceAll("\\s+","");
				if(percept != "" && percept != null && !perceptions.contains(percept))
					perceptions.add(percept);
		}
		reader.close();
		} catch (Exception e) {  
			e.printStackTrace();  
		}
		while(perceptions.contains(""))
			perceptions.remove(perceptions.indexOf(""));
		System.out.println(perceptions.toString() + perceptions.get(4));
	}
	
	public boolean contain(String percept){
		return perceptions.contains(percept);
	}
}
