import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        
        //fazer conexão HTTP e pegar filmes
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        //ExtratorConteudo extrator = new ExtratorConteudoIMDB();
        
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD.json";
        
        String url = "http://localhost:9090/linguagens";
        ExtratorConteudo extrator = new ExtratorConteudoNasa();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        //exibir e manipular dados
        
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradorDeFigurinhas();

       for(int i=0; i<3; i++){

            Conteudo conteudo = conteudos.get(i);
           
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();

        }   

        
    }

    
}