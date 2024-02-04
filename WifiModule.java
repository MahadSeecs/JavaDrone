import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class WifiModule {
    private final String IP = "192.168.4.1";
    private final int Port = 80;
    private BufferedReader Reader;
    private Socket soc;
    private int inputData[] = new int[1000];
    private String Data;
    private int index;
    WifiModule()
    {
        try
        {
            soc = new Socket(IP,Port);    // Setup Socket
            Reader = new BufferedReader(new InputStreamReader(soc.getInputStream()));  // Setup Reader
            ResetArray();     // Set All Array Elements to 0;
            Data = "";       // Set Data to Empty;
            System.out.println("Connected To Arduino");
        }catch (Exception Ex)
        {
            System.out.println(Ex.getMessage());
        }

    }

    private void ResetArray ()     //Private method to reset the array
    {
        for (int i = 0;i<1000;i++)
        {
            inputData[i] = 0;
        }
    }

    private String convert(int[] inputData)      //Private Method for formatting the received data
    {
        int index = 0;
        char converted;
        String Output = "";
        while(inputData[index] != 0)
        {
            converted = (char) inputData[index];
            if (converted != '!') {
                Output += converted;
            }
            index ++;
        }

        return  Output;
    }

    public String getData() {                //Method for Receiving data
        Data = "";
        try {
            index = -1;
            do {
                index++;
                inputData[index] = Reader.read();
            } while (inputData[index] != 33);

            if (inputData[0] != 0) {
                Data = convert(inputData);
            }
        } catch (Exception Ex)
        {
            System.out.println(Ex.getMessage());
        }
        ResetArray();
        return Data;
    }


    public void sendData(String LED_DIRECTION)         //Function for Controlling LEDS
    {
        try {
            LED_DIRECTION += "!";
            soc.getOutputStream().write(LED_DIRECTION.getBytes(StandardCharsets.US_ASCII)); //U //D //L //D
        }
        catch (Exception a)
        {
            System.out.println(a.getMessage());
        }
    }


}
