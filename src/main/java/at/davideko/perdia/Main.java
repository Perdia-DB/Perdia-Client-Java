package at.davideko.perdia;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
	    TCPClient client = new TCPClient("127.0.0.1", 3000);
        Crypto c = new Crypto();

        try {
            byte[] raw = Files.readAllBytes(Path.of("query.txt"));
            byte[] qb = c.encrypt(raw);
            client.write(qb);

            byte[] b = client.read();
            b = c.decrypt(b);
            String s = new String(b, StandardCharsets.UTF_8);
            System.out.println(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
