package USBlezen;

import Database.Database;
import com.serialpundit.serial.SerialComManager;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.serialpundit.serial.SerialComManager.BAUDRATE;
import com.serialpundit.serial.SerialComManager.DATABITS;
import com.serialpundit.serial.SerialComManager.FLOWCONTROL;
import com.serialpundit.serial.SerialComManager.PARITY;
import com.serialpundit.serial.SerialComManager.STOPBITS;


import java.text.SimpleDateFormat; // omzetten voor datum en tijd naar iets leesbaars
import java.util.Date; // voor bepalen datum en tijd

    public class USBlezen {


        public USBlezen() {
            // Begin van het "hoofdprogramma"
            Database database = new Database();   //Deze regel uitcommenten als SQL nog niet werkt.
            String port = "COM3";


            try {
                SerialComManager scm = new SerialComManager();

                long handle = scm.openComPort(port, true, true, true);
                scm.configureComPortData(handle, SerialComManager.DATABITS.DB_8, SerialComManager.STOPBITS.SB_1, SerialComManager.PARITY.P_NONE, SerialComManager.BAUDRATE.B9600, 0);
                scm.configureComPortControl(handle, SerialComManager.FLOWCONTROL.NONE, 'x', 'x', false, false);
                scm.writeString(handle, "test", 0);


                while (true) { // gewoon altijd doorgaan, vergelijkbaar met de Arduino loop()

                    // tijdstip = nu, dit moment.
                    String tijdstip = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                    tijdstip = tijdstip.replaceAll("[\\n\\r]", ""); // tijdstip om af te drukken, handig...


                    // Data ontvangen via serieel
                    String dataOntvangen = scm.readString(handle);
                    if (dataOntvangen != null) { // Er is data ontvangen
                        // verwijder alle newlines '\n' en carriage_returns '\r':
                        dataOntvangen = dataOntvangen.replaceAll("[\\n\\r]", "");
                        System.out.println(tijdstip + " Ontvangen data: " + dataOntvangen);

                        //   this.inkomend.setText("Ontvangen op " + tijdstip + ": " + dataOntvangen);
                        boolean dataswitch = false;

                        // String naar float omzetten
                        if (!dataswitch) {
                            //zet in db.tabel van CO2
                            float luchtkwaliteit_binnen_CO2 = Float.parseFloat(dataOntvangen);
                            //System.out.println("Float: " + temperatuur); // Kun je mee testen of er correct verstuurd wordt.
                            System.out.println("CO2 in ppm:" + luchtkwaliteit_binnen_CO2);
                            Database.insertbinnen(tijdstip, luchtkwaliteit_binnen_CO2);  //Deze regel uitcommenten als SQL nog niet werkt.

                            dataswitch = true;
                        } else {
                            //zet in db.tabel van Fijnstof
                            float luchtkwaliteit_buiten_fijnstof = Float.parseFloat(dataOntvangen);
                            //System.out.println("Float: " + temperatuur); // Kun je mee testen of er correct verstuurd wordt.
                            System.out.println("Fijnstof concentratie:" + luchtkwaliteit_buiten_fijnstof);
                            Database.insertbuiten(tijdstip, luchtkwaliteit_buiten_fijnstof);  //Deze regel uitcommenten als SQL nog niet werkt.

                            dataswitch = false;
                        }

                        // afronden op 1 cijfer achter de komma
                        //    lucht = (float) (Math.round(temperatuur * 10.0) / 10.0);

                        //System.out.println("Float: " + temperatuur); // Kun je mee testen of er correct verstuurd wordt.

                        // System.out.println("CO2 in ppm:" + luchtkwaliteit_binnen_CO2);
                        //Database.insertbinneen(tijdstip, luchtkwaliteit_binnen_CO2);  //Deze regel uitcommenten als SQL nog niet werkt.

                  /*  if (dataOntvangen.contains("1")) { // Piepje als er een 1 gelezen wordt vanaf de seriële poort
                        System.out.println("\"1\" ontvangen, dus: Windows default beep");
                        Toolkit.getDefaultToolkit().beep(); // Piep
                    }*/
                    }
                }

            } catch (Exception e) { // Stukje foutafhandeling, wordt als het goed is nooit gebruikt
                System.out.print("\033[1;93m\033[41m"); // Dikke gele tekst in rode achtergrond (ANSI colors Java)
                System.out.print("Ai, er zit een fout in je programma. Kijk eerst naar de onderste rode foutmeldingen en werk omhoog:");
                System.out.println("\033[0m"); // Tekstkleuren weer resetten naar standaard.
                e.printStackTrace(); // Dit drukt de foutmeldingen af.
                System.exit(2); // Programma afbreken
            }
        }


        public static void main(String[] args) {
            while(true) {
                new USBlezen();
            }
        }

    }


