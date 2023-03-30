import javax.imageio.ImageIO;
import java.io.File;
import java.io.InputStream;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;

public class GeradorDeFigurinhas {
    
    public void cria(InputStream inputStream, String nomeArquivo ) throws Exception{

        BufferedImage imagemOriginal = ImageIO.read(inputStream);
        
        //criar nova imagem em memoria
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura+200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        //copiar a imagem original para nova imagem
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        //configurar a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 150);
        graphics.setFont(fonte);
        graphics.setColor(Color.YELLOW);

        //escrever uma frase na nova imagem
        graphics.drawString("APROVADO", 100, novaAltura-100);

        //escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File("saida/"+nomeArquivo));
    }

}
