import socket
import threading

# Configura el servidor
host = '127.0.0.1'
port = 12346 #puerto

# Crea un socket TCP
server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_socket.bind((host, port))
server_socket.listen(5)

print(f"Servidor escuchando en {host}:{port}")

# Lista para almacenar conexiones de clientes
clientes = []

# Función para manejar la conexión con un cliente
def handle_client(client_socket):
    try:
        while True:
            data = client_socket.recv(1024).decode("utf-8")
            if not data:
                break
            print(f"Mensaje recibido: {data}")
            
            # Enviar el mensaje a todos los otros clientes conectados
            for cliente in clientes:
                if cliente != client_socket:
                    cliente.send(data.encode("utf-8"))
    except Exception as e:
        print(f"Error en la conexión: {str(e)}")
    finally:
        # Cierra la conexión con el cliente
        client_socket.close()
        clientes.remove(client_socket)

# Bucle principal del servidor para aceptar conexiones entrantes
while True:
    client_socket, addr = server_socket.accept()
    print(f"Conexión aceptada desde {addr[0]}:{addr[1]}")
    
    # Agrega el cliente a la lista de clientes
    clientes.append(client_socket)
    
    # Inicia un hilo para manejar la comunicación con el cliente
    client_handler = threading.Thread(target=handle_client, args=(client_socket,))
    client_handler.start()
