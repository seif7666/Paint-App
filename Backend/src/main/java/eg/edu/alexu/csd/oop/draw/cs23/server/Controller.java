package eg.edu.alexu.csd.oop.draw.cs23.server;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.swing.*;
import java.io.File;

@CrossOrigin
@RestController
public class Controller {

    private final Server server = Server.getService();
    private SentShape shape;
    private SentShape[] shapes;
    private String path;
    //We need a service class to get and send data to.


    /**
     * First:
     *  All get requests
     */
    @GetMapping("/saved")
    public String finished(){
        return path;
    }

    @GetMapping("/getShape")
    @ResponseBody
    public SentShape getShape(){
//        System.out.println("Entered in shape");
        return shape;
    }
    @GetMapping("/getShapes")
    @ResponseBody
    public SentShape[] getShapes(){
        if (shapes == null) return null;
//        System.out.println("Entered in shapes");
//        System.out.println(Arrays.toString(shapes));
        return server.sendShapes();
    }




    /**
     * Second:
     * All post requests
     */

    @PostMapping("/operation")
    @ResponseBody
    public void getOperation(@Validated @RequestBody Operation operation) throws Exception {
        System.out.println(operation);
        //try{
        switch (operation.operation) {
            case "Delete":
                System.out.println("Entered delete");
                shape = server.deleteShape(operation.x1, operation.y1);
                break;
            case "resize":
                System.out.println("Entered resize");
                shapes = server.resizeShape(operation.x1, operation.y1, operation.ratio);
                break;

            case "move":
                System.out.println("Entered move");
                shapes = server.moveShape(operation.x1, operation.y1, operation.x2, operation.y2);
                break;
            case "copy":
                System.out.println("Entered copy ^-^");
                shape = server.copy(operation.x1, operation.y1, operation.x2, operation.y2);
                break;
            case "changeColorButton":
                shape = server.changeColor(operation.x1, operation.y1, operation.fill, operation.stroke);
                System.out.println("Entered in color");
                break;
            case "undo":
                server.undo();
                break;
            case "redo":
                server.redo();
                break;
            case "save":
                 server.save(operation.fill , operation.stroke);
                break;
            case "load":
                 server.load(operation.fill);
                break;
            default:
                shape = null;
                shapes = null;
        }
        shapes = server.sendShapes();
    }


    @PostMapping("/draw")
    @ResponseBody
    public void drawShape(@Validated @RequestBody SentShape shape){
        System.out.println("Received Draw!");
        System.out.println(shape);
        server.draw(shape);
    }

    private String saveAndLoad(String message){
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        f.showSaveDialog(null);

        System.out.println(f.getCurrentDirectory());
        System.out.println(f.getSelectedFile());
        System.out.println(f.getCurrentDirectory()+"/"+f.getSelectedFile());
        return f.getCurrentDirectory()+"/"+f.getSelectedFile();
    }
//    public String save(String path){
//        path = path+".xml";
//        JFileChooser f = new JFileChooser();
//        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        f.setCurrentDirectory(new File("."));
//        f.setDialogTitle("Choose directory to save file ");
//        f.showSaveDialog(null);
//        path = f.getSelectedFile()+""+path;
//        System.out.println(path);
//        return path;
//    }
//    public File load(){
//        JFileChooser f = new JFileChooser();
//        f.setFileSelectionMode(JFileChooser.FILES_ONLY);
//        f.setCurrentDirectory(new File("."));
//        f.setDialogTitle("Select XML file to be uploaded ");
//        f.showSaveDialog(null);
//        System.out.println(f.getSelectedFile());
//        return f.getSelectedFile();
//    }
}

