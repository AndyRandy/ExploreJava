import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.logging.Logger;

public class UDPScanner
{
    public static Logger logger = Logger.getLogger(UDPScanner.class.getName());

    public static boolean udpScan(String host, int port, int timeOut)
    {
        boolean flag = false;
        DatagramSocket socket = null;
        byte[] data = host.getBytes();
        try
        {
            socket = new DatagramSocket();
            socket.setSoTimeout(timeOut);
            socket.setTrafficClass(0x04 | 0x10);
            socket.connect(new InetSocketAddress(host, port));
            socket.send(new DatagramPacket(data, data.length));
            while (true)
            {
                byte[] receive = new byte[4096];
                DatagramPacket dp = new DatagramPacket(receive, 4096);
                socket.receive(dp);
                if (dp != null && dp.getData() != null)
                {
                    System.out.println("---------------------------------------------------");
                    logger.info(new String(dp.getData()));
                    byte[] bs = dp.getData();
                    for (int i = 0; i < bs.length; i++)
                    {
                        logger.info(bs[i] + "");
                    }
                    flag = true;
                    System.out.println("---------------------------------------------------");
                    break;
                }
            }
        }
        catch (Exception e)
        {
            //e.printStackTrace();
        }
        finally
        {
            try
            {
                if (socket != null)
                {
                    socket.close();
                }
            }
            catch (Exception e)
            {
            }
        }
        return flag;
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        String host = "127.0.0.1";
        for (int i = 1; i <= 65535; i++)
        {
            if (udpScan(host, i, 5))
            {
                System.out.println("PORT listening:" + i);
            }
        }
    }
}
