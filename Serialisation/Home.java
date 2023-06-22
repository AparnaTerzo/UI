package Assessment;

import java.io.Serializable;

public class Home implements Serializable {
    private static final long serialVersionUID =1L;
        String sofa;
        int chair;
        String fridge;
        String bed;
        String tv;

        public Home(int chair, String fridge, String tv, String bed, String sofa){
            this.chair = chair;
            this.fridge = fridge;
            this.sofa=sofa;
            this.tv=tv;
            this.bed= bed;
        }

    @Override
    public String toString() {
        return "Home{" +
                "sofa='" + sofa + '\'' +
                ", chair=" + chair +
                ", fridge='" + fridge + '\'' +
                ", bed='" + bed + '\'' +
                ", tv='" + tv + '\'' +

                '}';
    }
}


