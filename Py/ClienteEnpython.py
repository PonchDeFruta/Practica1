import socket
import threading

def recibir_mensajes(sock):
    while True:
        mensaje = sock.recv(1024).decode("utf-8")
        if not mensaje:
            break
        print("Servidor: " + mensaje)

try:
    # Configura el cliente
    host = '127.0.0.1'
    port = 12346

    # Crea un socket TCP y conecta al servidor
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client_socket.connect((host, port))

    # Inicia un hilo para recibir mensajes del servidor
    recibir_thread = threading.Thread(target=recibir_mensajes, args=(client_socket,))
    recibir_thread.start()

    # Lee mensajes del usuario y los envía al servidor
    while True:
        mensaje_usuario = input()
        client_socket.send(mensaje_usuario.encode("utf-8"))

except Exception as e:
    print("Error: " + str(e))
finally:
    client_socket.close()
