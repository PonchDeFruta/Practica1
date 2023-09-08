#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>

int main() {
    int client_socket;
    struct sockaddr_in server_addr;
    char mensaje[1024];

    // Crear socket
    client_socket = socket(AF_INET, SOCK_STREAM, 0);
    if (client_socket == -1) {
        perror("Error al crear el socket");
        exit(EXIT_FAILURE);
    }

    // Configurar la dirección del servidor
    memset(&server_addr, 0, sizeof(server_addr));
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(12347); // Puerto del servidor Java
    server_addr.sin_addr.s_addr = inet_addr("127.0.0.1"); // Dirección IP del servidor Java

    // Conectar al servidor
    if (connect(client_socket, (struct sockaddr*)&server_addr, sizeof(server_addr)) == -1) {
        perror("Error al conectar con el servidor");
        close(client_socket);
        exit(EXIT_FAILURE);
    }

    // Enviar un mensaje al servidor
    strcpy(mensaje, "Hola desde el cliente C");
    if (send(client_socket, mensaje, strlen(mensaje), 0) == -1) {
        perror("Error al enviar mensaje al servidor");
    }

    // Cerrar el socket
    close(client_socket);

 return 0;

}