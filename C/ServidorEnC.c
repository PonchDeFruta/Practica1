#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <arpa/inet.h>
#include <unistd.h>

int main() {
    int server_socket, client_socket;
    struct sockaddr_in server_addr, client_addr;
    socklen_t client_len = sizeof(client_addr);
    char buffer[1024];

    server_socket = socket(AF_INET, SOCK_STREAM, 0);

    memset(&server_addr, 0, sizeof(server_addr));
    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(12347);
    server_addr.sin_addr.s_addr = INADDR_ANY;



//bind(server_socket, (struct sockaddr*)&server_addr, sizeof(server_addr));
//listen(server_socket, 1);
//printf("Servidor C esperando conexiones... \n");
//client_socket = accept(server_socket, (struct sockaddr*)&client_addr, &client_len);
//printf("Cliente conectad: %s \n", inet_ntoa(client_addr.sin_addr));
//close(server_socket);
//return 0;

 bind(server_socket, (struct sockaddr*)&server_addr, sizeof(server_addr));
    listen(server_socket, 1);
    printf("Servidor C esperando conexiones... \n");
    
    client_socket = accept(server_socket, (struct sockaddr*)&client_addr, &client_len);
    printf("Cliente conectado: %s\n", inet_ntoa(client_addr.sin_addr));
    
    // Recibir datos del cliente
    ssize_t bytes_received = recv(client_socket, buffer, sizeof(buffer), 0);
    if (bytes_received == -1) {
        perror("Error al recibir datos del cliente");
    } else {
        buffer[bytes_received] = '\0';
        printf("Mensaje del cliente: %s\n", buffer);
    }

    close(client_socket);
    close(server_socket);

    return 0;


}