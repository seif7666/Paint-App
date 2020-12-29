package eg.edu.alexu.csd.oop.draw.cs23.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws Exception {

        SpringApplication.run(DemoApplication.class, args);
//        Controller con = new Controller();
//        con.save("file");
//        Server.getService().load("path.xml");
        System.out.println("Done");
//
//        JFileChooser f = new JFileChooser();
//        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        f.setCurrentDirectory(new File("."));
////        f.setSelectedFile(new File("File.xml"));
//        f.setDialogTitle("Enter ");
//        f.showSaveDialog(null);

//        //////Take name from front end
//        //////File chooser opens in backend with name taken from front
//        System.out.println(f.getCurrentDirectory());
//        System.out.println(f.getSelectedFile());
////        f.getSelectedFile();
    }

}
