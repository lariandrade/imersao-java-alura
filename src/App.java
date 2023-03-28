import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        
        //fazer conexão HTTP e pegar filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        //extrair apenas dados que interessam (titulo, poster e nota)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //exibir e manipular dados
        var geradora = new GeradorDeFigurinhas();
        for (Map<String,String> filme : listaDeFilmes) {

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");
           
            InputStream inputStream = new URL(urlImagem).openStream();

            String nomeArquivo = titulo + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println("\u001b[1mTitulo: \u001b[m" + filme.get("title"));
            System.out.println("\u001b[1mImagem: \u001b[m" + filme.get("image"));
            System.out.println("\u001b[1m\u001b[44;1mNota:  " + filme.get("imDbRating") + "\u001b[m");
            
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int qtdEstrelas = (int) classificacao;
            for(int n=1; n<=qtdEstrelas; n++){
                System.out.print("⭐");
            }
            System.out.println("\n");

        }   

        
    }

    
}