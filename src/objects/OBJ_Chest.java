package objects;
import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Chest extends SuperObject {

	public OBJ_Chest() {
		name = "Chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/object_chest_closed.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
