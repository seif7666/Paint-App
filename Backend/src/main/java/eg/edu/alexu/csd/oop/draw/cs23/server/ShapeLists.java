package eg.edu.alexu.csd.oop.draw.cs23.server;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ShapeLists {

    @XmlElement
    SentShape[] sentShapes;

}
