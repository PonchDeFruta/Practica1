import java.io.*;
import java.net.*;

public class Clienteparapython {

    public static void main(String[] args) {
        try {
            // Configura el cliente
            String host = "127.0.0.1";
            int port = 12345;
            int port_2 = 12346;
            
            // Crea un socket TCP y conecta al servidor
            Socket socket = new Socket(host, port);
            Socket socket_2 = new Socket(host, port_2);

            
            // Establece flujos de entrada y salida
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader entrada2 = new BufferedReader(new InputStreamReader(socket_2.getInputStream()));


            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            PrintWriter salida2 = new PrintWriter(socket_2.getOutputStream(), true);
            
            // Crea un hilo para recibir mensajes del servidor
            Thread recibirThread = new Thread(() -> {
                try {
                    while (true) {
                        String mensaje = entrada.readLine();
                        String mensaje2 = entrada2.readLine();
                        if (mensaje == null) {
                            break;
                        }
                        System.out.println("Servidor: " + mensaje);
                        System.out.println("Servidor: " + mensaje2);
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
                salida2.println(mensajeUsuario);
            }
            
            // Cierra la conexión
            socket.close();
            socket_2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
