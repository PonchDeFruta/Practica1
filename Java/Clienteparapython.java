import java.io.*;
import java.net.*;

public class Clienteparapython {

    public static void main(String[] args) {
        try {
            // Configura el cliente
            String host = "127.0.0.1";
            int port = 12345;
            int
            
            // Crea un socket TCP y conecta al servidor
            Socket socket = new Socket(host, port);
            Socket socket2 = new Socket(host, port2);
            
            // Establece flujos de entrada y salida
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader entrada2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));

            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            PrintWriter salida2 = new PrintWriter(socket2.getOutputStream(), true);
            
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
            socket2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
