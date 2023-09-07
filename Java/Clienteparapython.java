import java.io.*;
import java.net.*;

public class Clienteparapython {

    public static void main(String[] args) {
        try {
            // Configura el cliente
            String host = "127.0.0.1";
            int port = 12345;
            
            // Crea un socket TCP y conecta al servidor
            Socket socket = new Socket(host, port);
            
            // Establece flujos de entrada y salida
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            
            // Crea un hilo para recibir mensajes del servidor
            Thread recibirThread = new Thread(() -> {
                try {
                    while (true) {
                        String mensaje = entrada.readLine();
                        if (mensaje == null) {
                            break;
                        }
                        System.out.println("Servidor: " + mensaje);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            recibirThread.start();
            
            // Lee mensajes del usuario y los envía al servidor
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            String mensajeUsuario;
            while ((mensajeUsuario = teclado.readLine()) != null) {
                salida.println(mensajeUsuario);
            }
            
            // Cierra la conexión
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
