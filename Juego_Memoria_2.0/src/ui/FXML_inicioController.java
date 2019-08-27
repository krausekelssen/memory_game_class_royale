package ui;

import controller.Controller;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class FXML_inicioController implements Initializable {

    Controller adm = new Controller();
    //Presentacion
    //Atributos para las ventanas
    JFrame ventana;
    JPanel panelJuego;
    JLabel fondoJuego;
    //Nombre del jugador para mostrar
    JLabel nombreJugador;
    //Contador del tiempo que se va mostrar
    JLabel cronometro;

    //Atributos para las matrices logicas
    //Matrices logicas
    int mat[][];
    int matAux[][];
    //Generador de numeros aleatorios
    Random aleatorio;
    //Matriz grafica
    JLabel matriz[][];
    //Tiempo logico
    Timer tiempo;
    int min, seg;
    //Tiempo logico de espera (2seg) para voltear las cartas
    Timer tiempoEspera;
    //Contador del tiempo logico (para que llegue a 2seg)
    int contadorSegEsp = 0;
    //Entero contador para los turnos (2 cartas seleccionadas)
    int contador;
    //Variables para eliminar cartas pares
    //Almaceno el num de la carta antigua y sus cordenadas
    int antnum; //Numero de la carta 
    int antx; //Posicion antigua en x de la carta
    int anty;//Posicion antigua en y de la carta

    //Almaceno el num de la carta nueva y sus cordenadas
    int actualnum; //Numero de la carta 
    int actualx; //Posicion antigua en x de la carta
    int actualy;//Posicion antigua en y de la carta

    //Necesito una variable bandera para que el timerEspera1 se arrranque
    int bandera = 0;
    int bandera1 = 0;
    //Cronometro para el tiempo de espera
    Timer tiempoEspera1;

    //Texto para el nombre del jugador
    @FXML
    TextField nombreJugadorTF = new TextField();

    //Validacion espacios
    @FXML
    Label espacio = new Label();

    //DATOS PARA LA BASE DE DATOS
    static String name;

    public void facebook() {
        String link = "https://www.facebook.com/finn.heroe.7";
        try {
            Desktop.getDesktop().browse(new URI(link));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void instagram() {
        String link = "https://www.instagram.com/krause_kelsen/?hl=es-la";
        try {
            Desktop.getDesktop().browse(new URI(link));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void score(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/ui/FXML_score.fxml"));
            loader.load();
            Parent root = loader.getRoot();
            Platform.runLater(() -> root.requestFocus());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Score");
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void juego(MouseEvent event) throws IOException {
        name = nombreJugadorTF.getText();
        if (name.equals("")) {
            espacio.setVisible(true);
        } else {
            ventana = new JFrame("Jugador: " + name);
            //tamaño de la ventana
            ventana.setSize(600, 600);
            //Nos permite colocar componentes
            ventana.setLayout(null);
            //Se cierra el juego y no se sigue ejecutando
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //Nos permite que nuestra ventana aparezca en el centro
            ventana.setLocationRelativeTo(null);
            //No permite maximizar la ventana
            ventana.setResizable(false);

            //Propiedades del panel de juego
            panelJuego = new JPanel();
            panelJuego.setSize(600, 600);
            //Desde donde comienza el panel
            panelJuego.setLocation(0, 0);
            panelJuego.setLayout(null);
            panelJuego.setVisible(true);

            //Fondo del juego
            fondoJuego = new JLabel();
            //Le doy los mismos tamaños de la ventana con el getW y el getH
            fondoJuego.setSize(600, 600);
            //Aqui le digo donde va agarrar la imagen
            fondoJuego.setLocation(0, 0);
            //Agrego la imagen
            ImageIcon fondo = new ImageIcon("imgs_juego/fondo.jpg");
            fondoJuego.setIcon(fondo);
            //Y le hago visible
            fondoJuego.setVisible(true);
            //Añado primero el fondo al panel
            panelJuego.add(fondoJuego, 0);

            //Nombre de jugador
            nombreJugador = new JLabel();
            //Le doy los mismos tamaños de la ventana con el getW y el getH
            nombreJugador.setSize(250, 30);
            nombreJugador.setLocation(40, 30);
            nombreJugador.setForeground(java.awt.Color.WHITE);
            Border border = BorderFactory.createRaisedBevelBorder();
            nombreJugador.setBorder(border);
            nombreJugador.setBackground(java.awt.Color.decode("#EDA81F"));
            //Y le hago visible
            nombreJugador.setVisible(true);
            //Añado primero el label al panel
            panelJuego.add(nombreJugador, 0);

            nombreJugador.setText("   Jugador: " + name);

            cronometro = new JLabel();
            //Le doy los mismos tamaños de la ventana con el getW y el getH
            cronometro.setSize(100, 30);
            cronometro.setLocation(475, 30);
            cronometro.setForeground(java.awt.Color.WHITE);
            Border borderTiempo = BorderFactory.createRaisedBevelBorder();
            cronometro.setBorder(borderTiempo);
            cronometro.setBackground(java.awt.Color.decode("#EDA81F"));
            //Y le hago visible
            cronometro.setVisible(true);
            //Añado primero el label al panel
            panelJuego.add(cronometro, 0);
            ejecutarJuego();
            ventana.add(panelJuego);
            ventana.setVisible(true);
            panelJuego.setVisible(true);
            //Para mostrar la ventana (esto debe ir al final siempre)
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
    }

    public void ejecutarJuego() {
        //Le doy memoria a mat
        //Son 10 cartas, por ende al ser pares son 20 cartas
        //por eso son 4 filas, 5 columnas: 4x5=20
        //MATRIZ LOGICA (posee los numeros logicos para las posiciones)
        mat = new int[4][5];
        //MATRIZ LOGICA (posee las posiciones de cartas vacias)
        matAux = new int[4][5];
        //Le doy memoria a random
        aleatorio = new Random();
        this.numerosAleatorios();

        //Le doy memoria a la matriz grafica y le seteo las imagenes
        //Le doy tamaño primero para darle memoria
        matriz = new JLabel[4][5];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                //Le doy memoria a la matriz de imagenes
                matriz[i][j] = new JLabel();
                //Luego de darle memoria le seteo las posiciones donde iran
                //las imagenes
                // 95: posicion en X, 80: posicion en Y, 84: Ancho, 100: Largo
                matriz[i][j].setBounds(95 + (j * 84), 80 + (i * 100), 84, 100);
                //Utilizo la matAux en vez de mat porque ocupo las cartas volteadas
                ImageIcon iconos = new ImageIcon("imgs_juego/" + matAux[i][j] + ".png");

                matriz[i][j].setIcon(iconos);
                matriz[i][j].setVisible(true);
                panelJuego.add(matriz[i][j], 0);
            }
        }

        //Tiempo grafico juego
        min = 0;
        seg = 0;
        //Le doy memoria
        //1000: Corresponde a los 1000 milisegundos
        tiempo = new Timer(1000, (ActionEvent e) -> {
            seg++;
            if (seg == 60) {
                min++;
                seg = 0;
            }
            cronometro.setText("   Tiempo: " + min + ":" + seg);
        });
        tiempo.start();

        //Tiempo de espera
        tiempoEspera = new Timer(1000, (ActionEvent e) -> {
            contadorSegEsp++;
        });
        tiempoEspera.start();
        tiempoEspera.stop();
        contadorSegEsp = 0;

        //Inicializo contador
        contador = 0;
        //Evento para voltear las cartas
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                //Cuando se clickee una carta ocurrira todo esto
                matriz[i][j].addMouseListener(new MouseAdapter() {
                    public void mousePressed(java.awt.event.MouseEvent e) {
                        for (int k = 0; k < 4; k++) {
                            for (int l = 0; l < 5; l++) {
                                //Voy a mostrar la posicion de la carta
                                //Si el evento clickeado de la matriz es
                                //igual a el recorrido de la segunda matriz
                                if (e.getSource() == matriz[k][l]) {
                                    //debera mostrar la posicion
//                                    System.out.println(k + "  " + l);
                                    //Si yo clickee una carta de oculta, me la cambie
                                    //Si el contador es diferente a dos que entre a cambiar las cartas 
                                    if (matAux[k][l] == 0 && contador != 2) {
                                        matAux[k][l] = mat[k][l];
                                        ImageIcon iconos = new ImageIcon("imgs_juego/" + matAux[k][l] + ".png");
                                        matriz[k][l].setIcon(iconos);
                                        //Acumula numeros al contador
                                        contador++;//Esto debe llegar a 2 para que sean 2 cartas
                                        //Almaceno el numero de la primera carta seleccionada y sus coordenadas
                                        actualnum = mat[k][l];
                                        actualx = k;
                                        actualy = l;
                                        //Entrará al if siempre y cuando no se haya seleccionado ninguna carta
                                        if (contador == 1) {
                                            //Almaceno el numero de la carta (1-10)
                                            antnum = mat[k][l];
                                            //Cordenadas de la carta (su posicion)
                                            antx = k;
                                            anty = l;

                                        }
                                        //Entonces empieza a contar el tiempoEspera1 hasta 3 seg para volver a 
                                        //voltear todo
                                        tiempoEspera1 = new Timer(500, new ActionListener() {
                                            public void actionPerformed(ActionEvent r) {
                                                //Si yo ya voltee 2 cartas y la bandera no se ha tocado una sola vez
                                                //debera resetearme el reloj y tocarme la bandera, para que el reseteo
                                                //del reloj ocurra solo una vez por cada 2 cartas clickeadas
                                                if (contador == 2 && bandera1 == 0) { //Cuando ya se eligieron 2 cartas(contador ==2)
                                                    //se reinicia el tiempo
                                                    tiempoEspera.restart(); //Como se reinicia el tiempo de espera el contadorSegEsp == 0;
                                                    //Se le suma 1 a la bandera para que no vuelva a entrar (ni resetear el timer)
                                                    bandera1 = 1;
                                                }
                                                //Si contador es igual a 2 (osea ya voltee dos cartas)
                                                //debo voltear las cartas para que se vuelvan a seleccionar
                                                //otras dos y ademas el tiempo debe ser 1seg
                                                //ESTA CONDICION ES IMPORTANTE porque determina cuanto tiempo tardan
                                                //las cartas en voltearse de nuevo (contadorSegEsp = 1) significa
                                                //que un segundo despues de haberlas tocado se voltearan
                                                if (contador == 2 && contadorSegEsp == 1) {
                                                    //Si el contador llego a tocar 2 cartas el tiempo se detiene
                                                    tiempoEspera.stop();
                                                    //Y el contador de tiempo de espera se reinicia y cambia las cartas
                                                    //por cartas vacias
                                                    contadorSegEsp = 0;

                                                    //Aqui ya se cumplieron los dos segundos y las cartas serán volteadas
                                                    //Entonces debo desaparecer las cartas que sean pares
                                                    //Para desaparecer la carta esta debe ser -1
                                                    //Esto pasa cuando estamos en el segundo turno
                                                    //Entonces SI la ultima carta seleccionada (de la que ya tengo las cordenadas
                                                    //porque fue la ultima carta seleccionada) es igual a la primera carta
                                                    //seleccionada (con las coordenadas que guarde de esa primera carta)
                                                    if (matAux[actualx][actualy] == matAux[antx][anty]) {
                                                        //Si las cartas son iguales debe decirme que esa carta ya es -1
                                                        //y las cartas en -1 son eliminadas (todo esto debe ocurrir)
                                                        //justo antes de volverlas a voltear
                                                        matAux[actualx][actualy] = -1;//Volteo la primera carta
                                                        matAux[antx][anty] = -1;
                                                        //UNA VEZ PUESTOS LOS VALORES -1 a esas cartas en especifico
                                                        //deberá de volverme a llenar el tablero con las cartas actuales
                                                        //descartando las recientes en -1
                                                        ImageIcon iconosAux = new ImageIcon("imgs_juego/" + matAux[actualx][actualy] + ".png");
                                                        matriz[actualx][actualy].setIcon(iconosAux);
                                                        ImageIcon iconosAux2 = new ImageIcon("imgs_juego/" + matAux[antx][anty] + ".png");
                                                        matriz[antx][anty].setIcon(iconosAux2);
                                                        //Reinicio el contador para que pueda volver a seleccionar otra carta
                                                        contador = 0;

                                                        //Funcionalidad para ganar
                                                        int acumuladorPares = 0;
                                                        for (int o = 0; o < 4; o++) {
                                                            for (int p = 0; p < 5; p++) {
                                                                //Si encuentro una carta par entonces la acumulo
                                                                if (matAux[o][p] == -1) {
                                                                    acumuladorPares++;
                                                                }
                                                            }
                                                        }
                                                        //Entonces cuando acumuladorPares llegue a 20 significa que ya
                                                        //no hay mas cartas en el tablero (porque todas son -1)
                                                        //Se debe mostrar que ganó
                                                        if (acumuladorPares == 20) {
                                                            //Primero detengo el tiempo que se muestra en pantalla
                                                            tiempo.stop();
                                                            //Luego muestro el mensaje
                                                            JOptionPane.showMessageDialog(ventana, "Felicitaciones "
                                                                    + name + " ganó el juego con un tiempo de: "
                                                                    + min + ":" + seg);
                                                            //Se registra el usuario y su record
                                                            LocalTime t = LocalTime.of(0, min, seg);
                                                            Time time = Time.valueOf(t);
                                                            adm.registrarUsuario(name, time);
                                                            adm.updateUser();

                                                            for (int q = 0; q < 4; q++) {
                                                                for (int s = 0; s < 5; s++) {
                                                                    mat[q][s] = 0;
                                                                    matAux[q][s] = 0;
                                                                    ImageIcon iconosAux3 = new ImageIcon("imgs_juego/" + matAux[q][s] + ".png");
                                                                    matriz[q][s].setIcon(iconosAux3);
                                                                }
                                                            }
                                                            //Debo ingresar nuevos numeros aleatorios
                                                            numerosAleatorios();
                                                            min = 0;
                                                            seg = 0;
                                                            tiempo.restart();
                                                            //Me redirecciona a la pagina de inicio
//                                                           panelJuego.setVisible(false);
                                                            ventana.setVisible(false);

                                                            try {
                                                                AudioStream audio = reproducirMusica();
                                                                AudioPlayer.player.stop(audio);
                                                            } catch (Exception e) {
                                                                System.out.println(e.getMessage());
                                                            }
                                                        }
                                                    }
                                                    for (int m = 0; m < 4; m++) {
                                                        for (int n = 0; n < 5; n++) {
                                                            //Si la carta es diferente a 0
                                                            //(cualquier carta con un muñequito) el
                                                            //va entrar en este if o si es igual a -1
                                                            //entonces va cambiarme las cartas a 0 de nuevo
                                                            //porque el usuario ya clickeo 2 cartas
                                                            if (matAux[m][n] != 0 && matAux[m][n] != -1) {
                                                                matAux[m][n] = 0;
                                                                ImageIcon iconosAux4 = new ImageIcon("imgs_juego/" + matAux[m][n] + ".png");
                                                                matriz[m][n].setIcon(iconosAux4);
                                                                contador = 0;
                                                            }
                                                        }
                                                    }
                                                    //El tiempo se vuelve a detener
                                                    tiempoEspera1.stop();
                                                    //Vuelve a ser 0 cuando todas las variables se hayan reiniciado
                                                    //para que cuando se vuelvan a clickear el ciclo se reinicie
                                                    bandera1 = 0;
                                                }
                                            }
                                        });//Fin del timer
                                        //Este if se usa varias veces por los clicks
                                        //pero necesito que se haga una sola vez por click
                                        if (bandera == 0) {
                                            tiempoEspera1.start();
                                            bandera = 1;
                                        }
                                        if (contador == 2) {
                                            tiempoEspera1.restart();
                                        }
                                    }
                                }
                            }//Fin del for
                        }//Fin del for
                    }
                });
            }//Fin del for
        }//Fin del for
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/ui/FXML_inicio.fxml"));
            loader.load();
            Parent root = loader.getRoot();
            Platform.runLater(() -> root.requestFocus());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Clash Royale MEMORY CARD");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Va llenar una matriz con numeros aleatorios (2 de la carta 1,2,3,4...10)
    public void numerosAleatorios() {
        int acumulador = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) //Le digo a mi matriz que tenga ceros 
            {
                //Necesito una matriz logica para las cartas pares
                mat[i][j] = 0;
                //Necesito una matriz vacia para las cartas ocultas
                matAux[i][j] = 0;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                //Los numeros van del uno al 10, pero si pongo 10 los pone
                //del 0 al 9 por ende es mejor poner 10+1
                //es posible que esten repetidos por lo que
                //se debe validar eso
                mat[i][j] = aleatorio.nextInt(10) + 1;
                do {
                    //Acumulador se debe reiniciar cuando llegue a 2 o 3
                    acumulador = 0;
                    //Aqui valido que no se repitan
                    for (int k = 0; k < 4; k++) {
                        for (int l = 0; l < 5; l++) {
                            //Si un numero aleatorio de la matriz que estoy llenando
                            //es igual a un numero que estoy guardando (repetido) entonces
                            //voy a acumularlo
                            if (mat[i][j] == mat[k][l]) {
                                acumulador += 1;
                                //Este acumulador debe llegar a 2 porque solo necesito
                                //dos copias de un mismo numero entonces ¿que pasa
                                //si acumulador llega a 3?
                            }
                        }
                    }
                    //Si acumulador es igual a 3 quiere decir que ya hay dos cartas
                    //del mismo numero escritas en la matriz
                    if (acumulador == 3) {
                        //Si acumulador es igual a 3 debo pedir otro numero
                        mat[i][j] = aleatorio.nextInt(10) + 1;
                        //¿que pasa si vuelve a caer otro numero y se acumulan 3 numeros iguales?
                        //Bueno ocupo que busque otro numero hasta que acumulador 
                        //deje de ser 3. Lo hago con do-while
                    }
                } while (acumulador == 3);
                //Y asi està lista la matriz de numeros aleatorios
            }
        }
    }

    public AudioStream reproducirMusica() throws IOException {

        String sonido = "C:/Users/viviana/Documents/NetBeansProjects/Juego_Memoria_2.0/src/ui/music.wav";
        InputStream in = new FileInputStream(sonido);
        AudioStream audio = new AudioStream(in);
        AudioPlayer.player.start(audio);
        return audio;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            reproducirMusica();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        espacio.setVisible(false);

    }

}
