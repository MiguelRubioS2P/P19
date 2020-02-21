package cat.paucasesnovescifp.sppsp.models;

import java.io.IOException;
import java.net.*;

public class Programa extends Thread{

    private InetAddress ip;
    private boolean inicial;
    private int puerto;
    private int puertoFinal;
    private DatagramSocket socket;
    private DatagramPacket paqueteEnviar;
    private DatagramPacket paqueteRecibir;
    private String mensajeRecibir;
    private String mensajeEnviar;
    private byte[] buf;

    public Programa(int puerto, int puertoFinal, boolean inicial){
        this.puerto = puerto;
        this.inicial = inicial;
        this.puertoFinal = puertoFinal;
    }

    private void enviar() throws IOException {

        // La ip donde vamos a conectarnos, en este caso en local
        ip = InetAddress.getByName("127.0.0.1");

        // Inicializamos el socket
        socket = new DatagramSocket();
        // Guardamos el mensaje que queremos enviar por los datagram Packet
        mensajeEnviar = "Recibido";
        // Inicializamos el paquete que queremos enviar con el puerto destino
        paqueteEnviar = new DatagramPacket(mensajeEnviar.getBytes(),mensajeEnviar.length(),ip,puertoFinal);
        // Enviamos el paquete
        socket.send(paqueteEnviar);
        // Cerramos el socket
        socket.close();


    }

    private void recibir() throws IOException {

        // Inicializamos el socket con un puerto para ser en el que queremos esperar información
        socket = new DatagramSocket(puerto);
        // Inicializamos un array de bytes
        buf = new byte[1024];
        // Inicializamos el paquete que necesitamos para recibir información
        paqueteRecibir = new DatagramPacket(buf,1024);
        // Ponemos a esperar al socket en el puerto que le indicamos en la inicialización hasta que reciba algo
        socket.receive(paqueteRecibir);
        // Creamos un mensaje con la información recibida en el paquete
        mensajeRecibir = new String(paqueteRecibir.getData(),0,paqueteRecibir.getLength());
        // Mostramos el mensaje
        System.out.println("El mensaje recibido es: " + mensajeRecibir + ", y soy el puerto: " + puerto + " y se lo he enviado al puerto: " + puertoFinal);
        // Cerramos el socket
        socket.close();

        enviar();

    }

    @Override
    public void run() {

        if(inicial){
            try {
                enviar();
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }else{
            try {
                recibir();
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

    }
}
