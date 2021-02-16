import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MyEcosystem_predprey extends CAtoolbox {


    public static void main(String[] args) {

        // initialisation generale

        int dx = 20;
        int dy = 20;

        int displayWidth = dx;  // 200
        int displayHeight = dy; // 200

        // pick dimension for display
        if (displayWidth < 200)
            displayWidth = 200;
        else if (displayWidth > 600)
            displayWidth = 600;
        else if (displayWidth < 300)
            displayWidth = displayWidth * 2;
        if (displayHeight < 200)
            displayHeight = 200;
        else if (displayHeight > 600)
            displayHeight = 600;
        else if (displayHeight < 300)
            displayHeight = displayHeight * 2;


        int delai = 20;//100; // -- delay before refreshing display -- program is hold during delay, even if no screen update was requested. USE WITH CARE.
        int nombreDePasMaximum = Integer.MAX_VALUE;
        int it = 0;
        int displaySpeed = 1;//50; // from 1 to ...

        CAImageBuffer image = new CAImageBuffer(dx, dy);
        ImageFrame imageFrame = ImageFrame.makeFrame("My Ecosystem", image, delai, displayWidth, displayHeight);

        // initialise l'ecosysteme

        World world = new World(dx, dy, true, true);

        for (int i = 0; i != 10; i++)
            world.add(new PreyAgent((int) (Math.random() * dx), (int) (Math.random() * dy), world));
        for (int i = 0; i != 10; i++)
            world.add(new PredatorAgent((int) (Math.random() * dx), (int) (Math.random() * dy), world));

//        BufferedWriter out = null;
//        try {
//            out = new BufferedWriter(new FileWriter("Preda_" + PredatorAgent.p_reproduce + "Prey_" + PreyAgent.p_reproduce + "L_" + PredatorAgent.delai_de_famine + ".csv", true));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        // mise a jour de l'état du monde
        while (it <= nombreDePasMaximum) {
            // 1 - display

            if (it % displaySpeed == 0)
                world.display(image);

            // 2 - update

            world.step();
//			int nb[]= world.getNumbers(); // nb[0] prey nb[1] predator
            // 3 - iterate
//			try {
//                out.write(it + "," + nb[0] + "," + nb[1] + "\n");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            it++;

            try {
                Thread.sleep(delai);
            } catch (InterruptedException ignored) {
            }
        }

//        try {
//			assert out != null;
//			out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
